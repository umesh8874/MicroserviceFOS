package com.AN1D.an1d.DTO;
import java.io.Serializable;
import javax.persistence.*;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.sql.Timestamp;

/**
 * The persistent class for the user_referral database table.
 * 
 */
@Entity
@Table(name="user_referral")
@NamedQuery(name="UserReferral.findAll", query="SELECT u FROM UserReferral u")
public class UserReferral implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", insertable = false, updatable = false)
	private Integer id;

    @JsonProperty("user_id")
    @Column(name="user_id")
	private int userId;

	// @JsonProperty("consumed_referral_user_id")
    // @Column(name="consumed_referral_user_id")
	// private int consumed_referral_user_id;

	// @JsonProperty("consumed_referral_code")
    // @Column(name="consumed_referral_code")
	// private String consumedReferralCode;

    @JsonProperty("user_referral_code")
    @Column(name="user_referral_code")
	private String userReferralCode;

	@JsonProperty("consumed_referral_code")
    @Column(name="consumed_referral_code")
	private String consumedReferralCode;

    @JsonProperty("discount_value")
    @Column(name="discount_value")
	private double discountValue;

    @JsonProperty("is_redeemed")
    @Column(name="is_redeemed")
	private byte isRedeemed;

    @JsonProperty("enabled")
    @Column(name="enabled")
    private byte enabled;

    @JsonProperty("validity")
    @Column(name="validity")
    private Timestamp validity;

    @JsonProperty("created_at")
    @Column(name="created_at")
	private Timestamp createdAt;

    @JsonProperty("updated_at")
    @Column(name="updated_at")
	private Timestamp updatedAt;

	public UserReferral() {
	}

	public UserReferral(Integer id, int userId, String userReferralCode, String consumedReferralCode, double discountValue, byte isRedeemed,
			byte enabled, Timestamp validity, Timestamp createdAt, Timestamp updatedAt) {
		this.id = id;
		this.userId = userId;
		this.userReferralCode = userReferralCode;
		this.consumedReferralCode = consumedReferralCode;
		this.discountValue = discountValue;
		this.isRedeemed = isRedeemed;
		this.enabled = enabled;
		this.validity = validity;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	/*
	public String getConsumedReferralCode() {
		return this.consumedReferralCode;
	}

	public void setConsumedReferralCode(String consumedReferralCode) {
		this.consumedReferralCode = consumedReferralCode;
	}

	public int getConsumerReferralUserId() {
		return this.consumed_referral_user_id;
	}

	public void setConsumerReferralUserId(int consumed_referral_user_id) {
		this.consumed_referral_user_id = consumed_referral_user_id;
	}*/

	public Timestamp getCreatedAt() {
		return this.createdAt;
	}

	public void setCreatedAt(Timestamp createdAt) {
		this.createdAt = createdAt;
	}

	public double getDiscountValue() {
		return this.discountValue;
	}

	public void setDiscountValue(double discountValue) {
		this.discountValue = discountValue;
	}

	public byte getEnabled() {
		return this.enabled;
	}

	public void setEnabled(byte enabled) {
		this.enabled = enabled;
	}

	public byte getIsRedeemed() {
		return this.isRedeemed;
	}

	public void setIsRedeemed(byte isRedeemed) {
		this.isRedeemed = isRedeemed;
	}

	public Timestamp getUpdatedAt() {
		return this.updatedAt;
	}

	public void setUpdatedAt(Timestamp updatedAt) {
		this.updatedAt = updatedAt;
	}

	public int getUserId() {
		return this.userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getUserReferralCode() {
		return this.userReferralCode;
	}

	public void setUserReferralCode(String userReferralCode) {
		this.userReferralCode = userReferralCode;
	}

	public String getConsumedReferralCode() {
		return consumedReferralCode;
	}

	public void setConsumedReferralCode(String consumedReferralCode) {
		this.consumedReferralCode = consumedReferralCode;
	}

	public Timestamp getValidity() {
		return this.validity;
	}

	public void setValidity(Timestamp validity) {
		this.validity = validity;
	}

}