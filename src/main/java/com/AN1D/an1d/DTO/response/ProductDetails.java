package com.AN1D.an1d.DTO.response;
import com.fasterxml.jackson.annotation.JsonProperty;

public class ProductDetails {
    
    private int id;

    @JsonProperty("promotion_id")
    private int promotionId;

    @JsonProperty("product_id")
    private int productId;
    
    private int type;

    @JsonProperty("discount_percent")
    private double discountPercent;

    @JsonProperty("discount_amount")
    private double discountAmount;

    private int deleted;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPromotionId() {
        return promotionId;
    }

    public void setPromotionId(int promotionId) {
        this.promotionId = promotionId;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public double getDiscountPercent() {
        return discountPercent;
    }

    public void setDiscountPercent(double discountPercent) {
        this.discountPercent = discountPercent;
    }

    public double getDiscountAmount() {
        return discountAmount;
    }

    public void setDiscountAmount(double discountAmount) {
        this.discountAmount = discountAmount;
    }

    public int getDeleted() {
        return deleted;
    }

    public void setDeleted(int deleted) {
        this.deleted = deleted;
    }
}