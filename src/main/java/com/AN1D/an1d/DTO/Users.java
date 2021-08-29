package com.AN1D.an1d.DTO;
import java.io.Serializable;
import javax.persistence.*;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.sql.Timestamp;


/**
 * The persistent class for the users database table.
 * 
 */
@Entity
@Table(name="users")
@NamedQuery(name="Users.findAll", query="SELECT u FROM Users u")
public class Users implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;

    @JsonProperty("name")
    @Column(name="name")
    private String name;

    @JsonProperty("username")
    @Column(name="username")
	private String username;

    @JsonProperty("password")
    @Column(name="password")
	private String password;

    @JsonProperty("access_token")
	@Column(name="access_token")
	private String accessToken;

    @JsonProperty("type")
    @Column(name="type")
    private byte type;

    @JsonProperty("created_at")
    @Column(name="created_at")
	private Timestamp createdAt;

    @JsonProperty("updated_at")
    @Column(name="updated_at")
	private Timestamp updatedAt;	

	public Users() {
	}

	public Users(Integer id, String name, String username, String password, String accessToken, byte type,
            Timestamp createdAt, Timestamp updatedAt) {
        this.id = id;
        this.name = name;
        this.username = username;
        this.password = password;
        this.accessToken = accessToken;
        this.type = type;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getAccessToken() {
		return this.accessToken;
	}

	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}

	public Timestamp getCreatedAt() {
		return this.createdAt;
	}

	public void setCreatedAt(Timestamp createdAt) {
		this.createdAt = createdAt;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
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

	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
}