package com.AN1D.an1d.DTO;
import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

/**
 * The persistent class for the promotion_detail database table.
 * 
 */
@Entity
@Table(name="promotion_detail")
@NamedQuery(name="PromotionDetail.findAll", query="SELECT p FROM PromotionDetail p")
public class PromotionDetail implements Serializable {
	
    private static final long serialVersionUID = 1L;

	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

    @JsonProperty("promotion_id")
    @Column(name="promotion_id")
	private int promotionId;

    @JsonProperty("product_id")
    @Column(name="product_id")
	private int productId;

    @JsonProperty("type")
    @Column(name="type")
    private byte type;

    @JsonProperty("discount_percent")
    @Column(name="discount_percent")
	private double discountPercent;

    @JsonProperty("discount_amount")
    @Column(name="discount_amount")
	private double discountAmount;

    @JsonProperty("deleted")
    @Column(name="deleted")
    private byte deleted;

    @CreationTimestamp
    @JsonProperty("created_at")
	@Column(name="created_at", updatable =  false)
	private Timestamp createdAt;	

	@UpdateTimestamp
    @JsonProperty("updated_at")
	@Column(name="updated_at")
	private Timestamp updatedAt;

	public PromotionDetail() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Timestamp getCreatedAt() {
		return this.createdAt;
	}

	public void setCreatedAt(Timestamp createdAt) {
		this.createdAt = createdAt;
	}

	public byte getDeleted() {
		return this.deleted;
	}

	public void setDeleted(byte deleted) {
		this.deleted = deleted;
	}

	public double getDiscountAmount() {
		return this.discountAmount;
	}

	public void setDiscountAmount(double discountAmount) {
		this.discountAmount = discountAmount;
	}

	public double getDiscountPercent() {
		return this.discountPercent;
	}

	public void setDiscountPercent(double discountPercent) {
		this.discountPercent = discountPercent;
	}

	public int getProductId() {
		return this.productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

	public int getPromotionId() {
		return this.promotionId;
	}

	public void setPromotionId(int promotionId) {
		this.promotionId = promotionId;
	}

	public byte getType() {
		return this.type;
	}

	public void setType(byte type) {
		this.type = type;
	}

	public Timestamp getUpdatedAt() {
		return this.updatedAt;
	}

	public void setUpdatedAt(Timestamp updatedAt) {
		this.updatedAt = updatedAt;
	}

}