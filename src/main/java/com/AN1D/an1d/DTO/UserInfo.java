package com.AN1D.an1d.DTO;
import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * The persistent class for the user_info database table.
 * 
 */
@Entity
@Table(name="user_info")
@NamedQuery(name="UserInfo.findAll", query="SELECT u FROM UserInfo u")
public class UserInfo implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
    @JsonProperty("user_id")
	@Column(name="user_id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer userId;

    @JsonProperty("first_name")
	@Column(name="first_name")
	private String firstName;

    @JsonProperty("last_name")
	@Column(name="last_name")
	private String lastName;

    @JsonProperty("mobile")
	@Column(name="mobile")
    private String mobile;

    @JsonProperty("email")
	@Column(name="email")
    private String email;

    @JsonProperty("password")
	@Column(name="password")
    private String password;

	@JsonProperty("access_token")
	@Column(name="access_token")
    private String accessToken;

    @JsonProperty("address")
	@Column(name="address")
	private String address;

	@JsonProperty("deleted")
    @Column(name="deleted")
    private byte deleted;

    @JsonProperty("created_at")
	@Column(name="created_at")
	private Timestamp createdAt;

    @JsonProperty("update_at")
	@Column(name="update_at")
	private Timestamp updateAt;

	public UserInfo() {
	}

	public UserInfo(Integer userId, String firstName, String lastName, String mobile, String email, String password,
			String accessToken, String address, byte deleted, Timestamp createdAt, Timestamp updateAt) {
		this.userId = userId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.mobile = mobile;
		this.email = email;
		this.password = password;
		this.accessToken = accessToken;
		this.address = address;
		this.deleted = deleted;
		this.createdAt = createdAt;
		this.updateAt = updateAt;
	}

	public Integer getUserId() {
		return this.userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Timestamp getCreatedAt() {
		return this.createdAt;
	}

	public void setCreatedAt(Timestamp createdAt) {
		this.createdAt = createdAt;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFirstName() {
		return this.firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return this.lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getMobile() {
		return this.mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getAccessToken() {
		return this.accessToken;
	}

	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}
	public Timestamp getUpdateAt() {
		return this.updateAt;
	}

	public void setUpdateAt(Timestamp updateAt) {
		this.updateAt = updateAt;
	}

	public byte getDeleted() {
		return this.deleted;
	}

	public void setDeleted(byte deleted) {
		this.deleted = deleted;
	}
}