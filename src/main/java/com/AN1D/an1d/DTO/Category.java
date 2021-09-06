package com.AN1D.an1d.DTO;
import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * The persistent class for the category database table.
 * 
 */
@Entity
@NamedQuery(name="Category.findAll", query="SELECT c FROM Category c")
public class Category implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

    @JsonProperty("name")
	@Column(name="name")
	private String name;

    @JsonProperty("created_at")
	@Column(name="created_at")
	private Timestamp createdAt;

    @JsonProperty("deleted")
    @Column(name="deleted")
	private byte deleted;

    @JsonProperty("updated_at")
	@Column(name="updated_at")
	private Timestamp updatedAt;

	public Category() {
	}

	public Category(int id, String name, Timestamp createdAt, byte deleted, Timestamp updatedAt) {
        this.id = id;
        this.name = name;
        this.createdAt = createdAt;
        this.deleted = deleted;
        this.updatedAt = updatedAt;
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

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Timestamp getUpdatedAt() {
		return this.updatedAt;
	}

	public void setUpdatedAt(Timestamp updatedAt) {
		this.updatedAt = updatedAt;
	}
}