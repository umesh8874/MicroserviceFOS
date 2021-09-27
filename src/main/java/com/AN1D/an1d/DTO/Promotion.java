package com.AN1D.an1d.DTO;
import java.io.Serializable;
import javax.persistence.*;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import java.sql.Timestamp;

/**
 * The persistent class for the promotion database table.
 * 
 */
@Entity
@NamedQuery(name="Promotion.findAll", query="SELECT p FROM Promotion p")
public class Promotion implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

    @JsonProperty("promotion_name")
	@Column(name="promotion_name")
	private String promotionName;

    @JsonProperty("description")
	@Column(name="description")
    private String description;

    @JsonProperty("applicable_on")
	@Column(name="applicable_on")
	private byte applicableOn;

    @JsonProperty("start_time")
    @Column(name="start_time")
	private Timestamp startTime;

    @JsonProperty("end_time")
    @Column(name="end_time")
	private Timestamp endTime;

    @JsonProperty("status")
    @Column(name="status")
    private byte status;

    @JsonProperty("unique_url")
    @Column(name="unique_url")
	private String uniqueUrl;

    @JsonProperty("promo_url")
    @Column(name="promo_url")
	private String promoUrl;

    @JsonProperty("deleted")
    @Column(name="deleted")
	private byte deleted;

    @CreationTimestamp
    @JsonProperty("created_at")
    @Column(name="created_at", updatable = false)
	private Timestamp createdAt;

    @UpdateTimestamp
    @JsonProperty("updated_at")
	@Column(name="updated_at")
	private Timestamp updatedAt;

	public Promotion() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public byte getApplicableOn() {
		return this.applicableOn;
	}

	public void setApplicableOn(byte applicableOn) {
		this.applicableOn = applicableOn;
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

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Timestamp getEndTime() {
		return this.endTime;
	}

	public void setEndTime(Timestamp endTime) {
		this.endTime = endTime;
	}

	public String getPromoUrl() {
		return this.promoUrl;
	}

	public void setPromoUrl(String promoUrl) {
		this.promoUrl = promoUrl;
	}

	public String getPromotionName() {
		return this.promotionName;
	}

	public void setPromotionName(String promotionName) {
		this.promotionName = promotionName;
	}

	public Timestamp getStartTime() {
		return this.startTime;
	}

	public void setStartTime(Timestamp startTime) {
		this.startTime = startTime;
	}

	public byte getStatus() {
		return this.status;
	}

	public void setStatus(byte status) {
		this.status = status;
	}

	public String getUniqueUrl() {
		return this.uniqueUrl;
	}

	public void setUniqueUrl(String uniqueUrl) {
		this.uniqueUrl = uniqueUrl;
	}

	public Timestamp getUpdatedAt() {
		return this.updatedAt;
	}

	public void setUpdatedAt(Timestamp updatedAt) {
		this.updatedAt = updatedAt;
	}

}