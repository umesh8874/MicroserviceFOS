package com.AN1D.an1d.DTO.response;
import java.io.Serializable;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonProperty;

public class PromotionData implements Serializable {

    private static final long serialVersionUID = 8289374018183336359L;

    private int id;

    @JsonProperty("promotion_name")
    private String promotionName;

    private String description;

    @JsonProperty("applicable_on")
    private int applicableOn;

    @JsonProperty("start_time")
    private String startTime;

    @JsonProperty("end_time")
    private String endTime;

    private int status;

    @JsonProperty("unique_url")
    private String uniqueUrl;

    @JsonProperty("promo_url")
    private String promo_url;

    private int deleted;

    @JsonProperty("product_details")
    private List<ProductDetails> productDetails;

    public PromotionData(){
        
    }

    public PromotionData(int id, String promotionName, String description, int applicableOn, String startTime,
            String endTime, int status, String uniqueUrl, String promo_url, int deleted,
            List<ProductDetails> productDetails) {
        this.id = id;
        this.promotionName = promotionName;
        this.description = description;
        this.applicableOn = applicableOn;
        this.startTime = startTime;
        this.endTime = endTime;
        this.status = status;
        this.uniqueUrl = uniqueUrl;
        this.promo_url = promo_url;
        this.deleted = deleted;
        this.productDetails = productDetails;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPromotionName() {
        return promotionName;
    }

    public void setPromotionName(String promotionName) {
        this.promotionName = promotionName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getApplicableOn() {
        return applicableOn;
    }

    public void setApplicableOn(int applicableOn) {
        this.applicableOn = applicableOn;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getUniqueUrl() {
        return uniqueUrl;
    }

    public void setUniqueUrl(String uniqueUrl) {
        this.uniqueUrl = uniqueUrl;
    }

    public String getPromo_url() {
        return promo_url;
    }

    public void setPromo_url(String promo_url) {
        this.promo_url = promo_url;
    }

    public int getDeleted() {
        return deleted;
    }

    public void setDeleted(int deleted) {
        this.deleted = deleted;
    }

    public List<ProductDetails> getProductDetails() {
        return productDetails;
    }

    public void setProductDetails(List<ProductDetails> productDetails) {
        this.productDetails = productDetails;
    }
}
