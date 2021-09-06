package com.AN1D.an1d.Service;
import java.sql.Timestamp;
import java.util.*;
import java.util.Map.Entry;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.*;
import com.AN1D.an1d.DTO.Product;
import com.AN1D.an1d.Utils.DateUtils;
import com.AN1D.an1d.config.CommonPath;
import com.AN1D.an1d.config.Constants;
import com.AN1D.an1d.config.CsvHeaders;
import com.AN1D.an1d.Utils.CommonUtils;
import com.AN1D.an1d.Utils.CsvReader;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import com.AN1D.an1d.Repository.CategoryDao;
import com.AN1D.an1d.Repository.ProductDao;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import com.AN1D.an1d.Exceptions.NotFoundException;
import org.springframework.data.domain.PageRequest;
import com.AN1D.an1d.Exceptions.UnProcessableEntityException;
import org.springframework.beans.factory.annotation.Autowired;

@Service
public class ProductService {

    @Autowired
    private ProductDao productDao;

    @Autowired
    private CategoryDao categoryDao;

    private final static Logger LOG = LoggerFactory.getLogger(ProductService.class);

    public Map<String, Object> findAllProducts(PageRequest page_request) {
        Map<String, Object> products_list = new HashMap<String,Object>();
		List<Product> products = productDao.findAllProducts(page_request);
		products_list.put("products", products);
		return products_list;
    }

    public Product findProductById(int product_id) {
        Optional<Product> product = productDao.findById(product_id);
        if(!product.isPresent())
            throw new NotFoundException("Product not found!");
        
        Product product_data = product.get();
        return product_data;
    }

    public Map<String, Object> deleteProductById(int product_id) {
        Optional<Product> product = productDao.findById(product_id);
        if(!product.isPresent())
            throw new NotFoundException("Product not found!");
        
        Product product_data = product.get();
        product_data.setDeleted((byte) 1);
        product_data.setUpdatedAt(DateUtils.getCurretDateAsTimeStamp());
        Product deleted_product = productDao.save(product_data);
        if(deleted_product != null){
            return CommonUtils.customResponseMsg(HttpStatus.OK, "Success", "Product deleted successfully.");
        }else{
            throw new UnProcessableEntityException("Product not able to delete!");
        }
    }

    public ResponseEntity<Product> createProducts(Product product) {
        Product existingProduct = null;
        if(StringUtils.isBlank(product.getBarcode())){
            String product_barcode = barcode(product);
            if(product_barcode.length() > 0)
                product.setBarcode(product_barcode);
        }else{
            existingProduct = productDao.findByBarcode(product.getBarcode());
        }

        product.setDeleted((byte) 0);
        if(existingProduct != null){
            product.setUpdatedAt(DateUtils.getCurretDateAsTimeStamp());
        }else{
            product.setCreatedAt(DateUtils.getCurretDateAsTimeStamp());
            product.setUpdatedAt(DateUtils.getCurretDateAsTimeStamp());
        }

        Product saved_product = productDao.save(product);
        if(saved_product != null)
            return new ResponseEntity<Product>(saved_product, HttpStatus.OK);
        
        return null;
    }

    public ResponseEntity<Product> updateProducts(Product product) {
        Product existingProduct = productDao.findByBarcode(product.getBarcode());
        if(existingProduct != null){
            product.setId(existingProduct.getId());
            Product saved_product = productDao.save(product);
            if(saved_product != null)
                return new ResponseEntity<Product>(saved_product, HttpStatus.OK);
        }else{
            throw new NotFoundException("Product not available for update!");
        }
        
        return null;
    }

    public ResponseEntity<List<Product>> createBulkProducts(MultipartFile file) {
        List<Product> saved_products = null;
        String fileDestPath = CommonPath.PRODUCT_UPLOAD_CSV_PATH;
        String file_name = CommonUtils.dumpUploadFileOnLocalSystem(fileDestPath, file);
        Map<Integer, ArrayList<String>> csv_data = CsvReader.readCsvLineWise(file_name);

        // LOG.info("csv_data = {}", csv_data);

        List<Product> products = new ArrayList<Product>();

        String headers [] = null;
        for(Entry<Integer, ArrayList<String>> product : csv_data.entrySet()){
            Integer key = product.getKey();
            ArrayList<String> product_data = product.getValue();
            if(key == 1){
                int count = 0;
                headers = new String[product_data.size()];
                for(String header : product_data){
                    headers[count] = header;
                    count++;
                }
            }else{
                Timestamp ts = DateUtils.getCurretDateAsTimeStamp();
                int barcode_index = getIndexOf(CsvHeaders.PRODUCT_UPLOAD[1], headers);
                String barcode = product_data.get(barcode_index).toString().trim();

                Product existingProduct = productDao.findByBarcode(barcode);
                Product prod = new Product();
                if(existingProduct != null)
                    prod.setId(existingProduct.getId());
                int name_index = getIndexOf(CsvHeaders.PRODUCT_UPLOAD[0], headers);
                prod.setName(product_data.get(name_index).trim());

                if(barcode.length() <= 0)
                    barcode = barcode(prod);
                prod.setBarcode(barcode);
                
                int description_index = getIndexOf(CsvHeaders.PRODUCT_UPLOAD[2], headers);
                prod.setDescription(product_data.get(description_index).trim());

                int category_index = getIndexOf(CsvHeaders.PRODUCT_UPLOAD[3], headers);
                String category = product_data.get(category_index).toString().trim();
                int category_id = categoryDao.findByName(category);
                prod.setCategoryId(category_id);

                int mrp_index = getIndexOf(CsvHeaders.PRODUCT_UPLOAD[4], headers);
                double mrp = Double.parseDouble(product_data.get(mrp_index).toString().trim());
                prod.setMrp(mrp);

                int cp_index = getIndexOf(CsvHeaders.PRODUCT_UPLOAD[5], headers);
                double cp = Double.parseDouble(product_data.get(cp_index).toString().trim());
                prod.setCp(cp);

                int sp_index = getIndexOf(CsvHeaders.PRODUCT_UPLOAD[6], headers);
                double sp = Double.parseDouble(product_data.get(sp_index).toString().trim());
                prod.setSp(sp);

                int img_index = getIndexOf(CsvHeaders.PRODUCT_UPLOAD[7], headers);
                String img_name = product_data.get(img_index);
                if(img_name.length() > 0)
                    prod.setImage(img_name.toString().trim());

                prod.setDeleted((byte) 0);
                prod.setCreatedAt(ts);
                prod.setUpdatedAt(ts);
                products.add(prod);
            }
        }

        List<Product> saved_product = productDao.saveAll(products);

        if(saved_product.size() > 0)
            return new ResponseEntity<List<Product>>(saved_product, HttpStatus.OK);
        return new ResponseEntity<List<Product>>(saved_products, HttpStatus.BAD_REQUEST);
    }

    private int getIndexOf(String header, String[] headers) {
        int count = 0;
        for(String head : headers){
            if(head.equalsIgnoreCase(header))
                return count;
            count++;
        }
        return 0;
    }   
    
    private String barcode(Product product){
        String product_barcode = "";
        try{
            if(product.getBarcode().isEmpty()){
                product_barcode = Constants.ORDER_REFRREL_PREFIX+CommonUtils.getrandomNumber7Digit();
            }
        }catch(Exception e){
            product_barcode = Constants.ORDER_REFRREL_PREFIX+CommonUtils.getrandomNumber7Digit();
        }
        return product_barcode;
    }
}