package com.AN1D.an1d.Service;
import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.*;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import javax.validation.Valid;
import com.AN1D.an1d.DTO.Promotion;
import com.AN1D.an1d.DTO.PromotionDetail;
import com.AN1D.an1d.DTO.response.ProductDetails;
import com.AN1D.an1d.DTO.response.PromotionData;
import com.AN1D.an1d.Exceptions.BadRequestException;
import com.AN1D.an1d.Exceptions.UnProcessableEntityException;
import com.AN1D.an1d.Repository.PromotionDao;
import com.AN1D.an1d.Repository.PromotionDetailsDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class PromotionService {

    @Value("${server.host.url}")
	private String host_url;

    private static int APPLICABLE_ORDER = 0;
    private static int APPLICABLE_PRODUCT = 1;

    @Autowired
    private PromotionDao promotionDao;

    @Autowired
    private PromotionDetailsDao promotionDetailsDao;
    
    private final static Logger LOG = LoggerFactory.getLogger(PromotionService.class);

    public ResponseEntity<Object> createNewPromotion(@Valid PromotionData promotion) {
        List<Promotion> checkExistsByName = promotionDao.findByPromoName(promotion.getPromotionName());
        List<Promotion> checkExistsByUniqueUrl = promotionDao.findByPromoUniqueUrl(promotion.getUniqueUrl());
        if(checkExistsByName.size() > 0){
            throw new BadRequestException("Promo name already exists...!");
        }else if(checkExistsByUniqueUrl.size() > 0){
            throw new BadRequestException("Unique url already exists...!");
        }

        if(promotion.getApplicableOn() == APPLICABLE_ORDER){
            JSONObject response = processProductLevelPromo(promotion);
            return new ResponseEntity<Object>(response.toMap(), HttpStatus.OK);
        }else if(promotion.getApplicableOn() == APPLICABLE_PRODUCT){
            if(promotion.getProductDetails().size() <= 0)
                throw new UnProcessableEntityException("Product list can't be null...!");

            JSONObject response = processProductLevelPromo(promotion);
            return new ResponseEntity<Object>(response.toMap(), HttpStatus.OK);
        }
        return null;
    }

    private JSONObject processProductLevelPromo(PromotionData promotion){
        List<PromotionDetail> pDetail = null;
        Promotion created_promotion = processPromotionCreation(promotion);
        if(created_promotion != null){
            pDetail = new ArrayList<>();
            for(ProductDetails product : promotion.getProductDetails()){
                PromotionDetail promotionDetail = new PromotionDetail();
                promotionDetail.setPromotionId(created_promotion.getId());
                if(promotion.getApplicableOn() == APPLICABLE_PRODUCT)
                    promotionDetail.setProductId(product.getProductId());

                if(product.getType() == 0){
                    promotionDetail.setType((byte) product.getType());
                    promotionDetail.setDiscountPercent(product.getDiscountPercent());
                }else{
                    promotionDetail.setType((byte) product.getType());
                    promotionDetail.setDiscountAmount(product.getDiscountAmount());
                }
                pDetail.add(promotionDetail);
            }
            pDetail = promotionDetailsDao.saveAll(pDetail);
        }

        if(pDetail.size() == promotion.getProductDetails().size()){
            JSONObject rObject = new JSONObject();
            rObject.put("promotion", created_promotion);
            JSONArray rArray = new JSONArray();
            for(int i = 0; i<pDetail.size(); i++)
                rArray.put(pDetail.get(i));
            rObject.put("discount_details", rArray);
            return rObject;
        }
        return null;
    }

    private Promotion processPromotionCreation(PromotionData promotion){
        Promotion promotion_data = null;
        Promotion _promotion = new Promotion();
        _promotion.setPromotionName(promotion.getPromotionName().trim());
        _promotion.setDescription(promotion.getDescription().trim());
        _promotion.setApplicableOn((byte) promotion.getApplicableOn());
        _promotion.setStartTime(Timestamp.valueOf(promotion.getStartTime()));
        _promotion.setEndTime(Timestamp.valueOf(promotion.getEndTime()));
        _promotion.setUniqueUrl(promotion.getUniqueUrl().trim());
        _promotion.setPromoUrl(host_url+promotion.getUniqueUrl().trim());
        try{
            promotion_data = promotionDao.save(_promotion);
        }catch(Exception e){
            LOG.error(this.getClass()+ " Error while saving promotion...!", e.getMessage());
        }
        return promotion_data;
    }

    public ResponseEntity<Object> getAllPromotions() {
        int count = 0;
        JSONObject rObject = new JSONObject();
        List<Promotion> allActivePromotions = promotionDao.findByAllActivePromotions();
        
        for(Promotion promo : allActivePromotions){
            List<PromotionDetail> promotion_details = promotionDetailsDao.findByPromotionId(String.valueOf(promo.getId()));
            
            JSONObject promoObj = new JSONObject();
            promoObj.put("promotion", promo);
            JSONArray rArray = new JSONArray();
            for(int i = 0; i<promotion_details.size(); i++)
                rArray.put(promotion_details.get(i));
            promoObj.put("discount_details", rArray);
            rObject.put(String.valueOf(count), promoObj);
            count++;
        }
        return new ResponseEntity<Object>(rObject.toMap(), HttpStatus.OK);
    }
}