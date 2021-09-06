package com.AN1D.an1d.controller.v1;
import java.util.*;
import javax.validation.Valid;
import com.AN1D.an1d.Service.ProductService;
import com.AN1D.an1d.Utils.RequestValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.data.domain.Sort;
import org.springframework.http.*;
import com.AN1D.an1d.DTO.Product;
import com.AN1D.an1d.Exceptions.NotFoundException;
import com.AN1D.an1d.Exceptions.UnProcessableEntityException;

@Validated
@RestController
@RequestMapping("v1/")
public class ProductController {

    @Autowired
    private RequestValidator requestValidator;

    @Autowired
    private ProductService productService;
    
    @GetMapping(value = "/products")
    public ResponseEntity<Map<String, Object>> retrieveAllProducts(
        @RequestParam(value = "access_token", required = true) String access_token,
        @RequestParam(value = "type", required = true) int type,
        @RequestParam(value = "offset", defaultValue = "0", required = true) int offset,
        @RequestParam(value = "limit", defaultValue = "20", required = true) int limit)
    {
        Map<String, Object> products = new HashMap<String, Object>();
        requestValidator.tokenValidator(access_token, type);

        PageRequest page_request = PageRequest.of(offset, limit, Sort.Direction.DESC, "created_at");
        products = productService.findAllProducts(page_request);

        if(!products.isEmpty()){
            return new ResponseEntity<Map<String,Object>>(products, HttpStatus.OK);
        }else {
			throw new NotFoundException("Products not found!");
		}
    }

    @GetMapping(value = "/products/{product_id}")
    public Product getProductById(
        @RequestParam(value = "access_token", required = true) String access_token,
        @RequestParam(value = "type", required = true) int type,
        @PathVariable(value = "product_id", required = true) int product_id)
    {
        requestValidator.tokenValidator(access_token, type);
        return productService.findProductById(product_id);
    }

    @PostMapping(value = "/products/create-product", consumes=MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Product> createProduct(
        @RequestParam(value = "access_token", required = true) String access_token,
        @RequestParam(value = "type", required = true) int type,
        @Valid @RequestBody Product product)
    {
        requestValidator.tokenValidator(access_token, type);
        return productService.createProducts(product);
    }

    @PutMapping(value = "/products/update-product/{product_id}")
    public ResponseEntity<Product> updateProduct(
        @RequestParam(value = "access_token", required = true) String access_token,
        @RequestParam(value = "type", required = true) int type,
        @Valid @RequestBody Product product)
    {
        requestValidator.tokenValidator(access_token, type);
        return productService.updateProducts(product);
    }

    @PostMapping(value = "/products/upload-csv-file")
    public ResponseEntity<List<Product>> createBulkProduct(
        @RequestParam(value = "access_token", required = true) String access_token,
        @RequestParam(value = "type", required = true) int type,    
        @RequestParam("file") MultipartFile file, Product product)
    {
        requestValidator.tokenValidator(access_token, type);
        if(file.isEmpty())
            throw new UnProcessableEntityException("File can't be empty!");
        
        return productService.createBulkProducts(file);
    }

    @PutMapping(value = "/products/delete-product/{product_id}")
    public Map<String, Object> deleteProductById(
        @RequestParam(value = "access_token", required = true) String access_token,
        @RequestParam(value = "type", required = true) int type,
        @PathVariable(value = "product_id", required = true) int product_id)
    {
        requestValidator.tokenValidator(access_token, type);
        return productService.deleteProductById(product_id);
    }

}