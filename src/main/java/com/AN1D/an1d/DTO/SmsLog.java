package com.AN1D.an1d.DTO;
import java.io.Serializable;
import javax.persistence.*;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import java.sql.Timestamp;

/**
 * The persistent class for the sms_log database table.
 */
@Entity
@Table(name="sms_log")
@NamedQuery(name="SmsLog.findAll", query="SELECT s FROM SmsLog s")
public class SmsLog implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", insertable = false, updatable = false)
	private Integer id;


    @JsonProperty("sms_type")
	@Column(name="sms_type")
	private String smsType;

	@JsonProperty("country_code")
	@Column(name="country_code")
	private String countryCode;

	@JsonProperty("mobile_number")
	@Column(name="mobile_number")
	private String mobileNumber;

	@JsonProperty("is_sent")
	@Column(name="is_sent")
	private byte isSent;

	@Lob
    @JsonProperty("failure_log")
	@Column(name="failure_log")
	private String failureLog;

    @CreationTimestamp
    @JsonProperty("created_at")
	@Column(name="created_at", updatable = false)
	private Timestamp createdAt;

    @UpdateTimestamp
    @JsonProperty("updated_at")
	@Column(name="updated_at")
	private Timestamp updatedAt;

	public SmsLog() {
	}

	public SmsLog(Integer id, String countryCode, String mobileNumber, String failureLog, byte isSent, String smsType, 
		Timestamp createdAt, Timestamp updatedAt) {
        this.id = id;
		this.countryCode = countryCode;
		this.mobileNumber = mobileNumber;
        this.failureLog = failureLog;
        this.isSent = isSent;
        this.smsType = smsType;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Timestamp getCreatedAt() {
		return this.createdAt;
	}

	public void setCreatedAt(Timestamp createdAt) {
		this.createdAt = createdAt;
	}

	public String getFailureLog() {
		return this.failureLog;
	}

	public void setFailureLog(String failureLog) {
		this.failureLog = failureLog;
	}

	public byte getIsSent() {
		return this.isSent;
	}

	public void setIsSent(byte isSent) {
		this.isSent = isSent;
	}

	public String getSmsType() {
		return this.smsType;
	}

	public void setSmsType(String smsType) {
		this.smsType = smsType;
	}

	public Timestamp getUpdatedAt() {
		return this.updatedAt;
	}

	public void setUpdatedAt(Timestamp updatedAt) {
		this.updatedAt = updatedAt;
	}

	public String getCountryCode() {
		return countryCode;
	}

	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}

	public String getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}
}