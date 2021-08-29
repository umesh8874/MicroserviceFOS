package com.AN1D.an1d.DTO;
import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * The persistent class for the order database table.
 * 
 */
@Entity
@Table(name="orders")
@NamedQuery(name="Order.findAll", query="SELECT o FROM Order o")
public class Order implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

    @JsonProperty("order_id")
	@Column(name="order_id")
	private String orderId;

    @JsonProperty("user_id")
	@Column(name="user_id")
	private int userId;

    @JsonProperty("actual_amount")
	@Column(name="actual_amount")
	private double actualAmount;

    @JsonProperty("order_amount")
	@Column(name="order_amount")
	private double orderAmount;

    @JsonProperty("order_discount_perc")
	@Column(name="order_discount_perc")
	private int orderDiscountPerc;

    @JsonProperty("order_discount_amt")
	@Column(name="order_discount_amt")
	private double orderDiscountAmt;

    @JsonProperty("is_referral")
	@Column(name="is_referral")
	private byte isReferral;

	@JsonProperty("referral_updated")
	@Column(name="referral_updated")
	private byte referralUpdated;

    @JsonProperty("transaction_status")
	@Column(name="transaction_status")
	private byte transactionStatus;

    @JsonProperty("shipping_status")
	@Column(name="shipping_status")
	private byte shippingStatus;

    @JsonProperty("created_at")
	@Column(name="created_at")
	private Timestamp createdAt;

    @JsonProperty("updated_at")
	@Column(name="updated_at")
	private Timestamp updatedAt;

	public Order() {
	}

	public Order(int id, double actualAmount, Timestamp createdAt, byte isReferral, byte referralUpdated, double orderAmount,
            double orderDiscountAmt, int orderDiscountPerc, String orderId, byte shippingStatus, byte transactionStatus,
            Timestamp updatedAt, int userId) {
        this.id = id;
        this.actualAmount = actualAmount;
        this.createdAt = createdAt;
        this.isReferral = isReferral;
		this.referralUpdated = referralUpdated;
        this.orderAmount = orderAmount;
        this.orderDiscountAmt = orderDiscountAmt;
        this.orderDiscountPerc = orderDiscountPerc;
        this.orderId = orderId;
        this.shippingStatus = shippingStatus;
        this.transactionStatus = transactionStatus;
        this.updatedAt = updatedAt;
        this.userId = userId;
    }

    public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public double getActualAmount() {
		return this.actualAmount;
	}

	public void setActualAmount(double actualAmount) {
		this.actualAmount = actualAmount;
	}

	public Timestamp getCreatedAt() {
		return this.createdAt;
	}

	public void setCreatedAt(Timestamp createdAt) {
		this.createdAt = createdAt;
	}

	public byte getIsReferral() {
		return this.isReferral;
	}

	public void setIsReferral(byte isReferral) {
		this.isReferral = isReferral;
	}

	public byte getReferralUpdated() {
		return referralUpdated;
	}

	public void setReferralUpdated(byte referralUpdated) {
		this.referralUpdated = referralUpdated;
	}

	public double getOrderAmount() {
		return this.orderAmount;
	}

	public void setOrderAmount(double orderAmount) {
		this.orderAmount = orderAmount;
	}

	public double getOrderDiscountAmt() {
		return this.orderDiscountAmt;
	}

	public void setOrderDiscountAmt(double orderDiscountAmt) {
		this.orderDiscountAmt = orderDiscountAmt;
	}

	public int getOrderDiscountPerc() {
		return this.orderDiscountPerc;
	}

	public void setOrderDiscountPerc(int orderDiscountPerc) {
		this.orderDiscountPerc = orderDiscountPerc;
	}

	public String getOrderId() {
		return this.orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public byte getShippingStatus() {
		return this.shippingStatus;
	}

	public void setShippingStatus(byte shippingStatus) {
		this.shippingStatus = shippingStatus;
	}

	public byte getTransactionStatus() {
		return this.transactionStatus;
	}

	public void setTransactionStatus(byte transactionStatus) {
		this.transactionStatus = transactionStatus;
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

}