package com.AN1D.an1d.controller.v1;
import javax.validation.Valid;
import com.AN1D.an1d.DTO.response.PromotionData;
import com.AN1D.an1d.Service.PromotionService;
import com.AN1D.an1d.Utils.RequestValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Validated
@RestController
@RequestMapping("v1/")
public class PromotionController {
    
    @Autowired
    private RequestValidator requestValidator;

    @Autowired
    private PromotionService promotionService;

    @PostMapping(value = "create-promotion")
    public ResponseEntity<Object> createNewPromotion(
        @RequestParam(value = "access_token", required = true) String access_token,
        @RequestParam(value = "type", required = true) int type,
        @Valid @RequestBody PromotionData promotion) 
    {
        requestValidator.admin_token_validate(access_token);
        return promotionService.createNewPromotion(promotion);
    }

    @GetMapping(value = "/promotions")
    public ResponseEntity<Object> retrieveAllProducts(
        @RequestParam(value = "access_token", required = true) String access_token,
        @RequestParam(value = "type", required = true) int type)
    {
        requestValidator.admin_token_validate(access_token);
        return promotionService.getAllPromotions();
    }
}