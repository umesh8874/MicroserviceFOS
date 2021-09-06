package com.AN1D.an1d.DTO;
import java.io.Serializable;
import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.sql.Timestamp;
import com.fasterxml.jackson.annotation.JsonProperty;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.validator.constraints.Range;

/**
 * The persistent class for the product database table.
 */
@Entity
@NamedQuery(name="Product.findAll", query="SELECT p FROM Product p")
public class Product implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	@NotEmpty
	@Size(min = 2, message = "Product name can't be null and atleast have 2 charchters.")
    @JsonProperty("name")
	@Column(name="name", nullable = false)
    private String name;

    @JsonProperty("barcode")
	@Column(name="barcode")
    private String barcode;

    @JsonProperty("description")
	@Column(name="description")
    private String description;

    @JsonProperty("category_id")
	@Column(name="category_id")
	private int categoryId;

	@Range(min = (long) 1.0, max = (long) 10000.0)
    @JsonProperty("mrp")
    @Column(name="mrp")
    private double mrp;

	@Range(min = (long) 1.0, max = (long) 10000.0)
    @JsonProperty("cp")
    @Column(name="cp")
	private double cp;

	@Range(min = (long) 1.0, max = (long) 10000.0)
    @JsonProperty("sp")
    @Column(name="sp")
    private double sp;

    @JsonProperty("image")
    @Column(name="image")
    private String image;

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

	public Product() {
	}

	public Product(int id, String name, String barcode, String description, int categoryId, double mrp, double cp, double sp,
            String image, byte deleted, Timestamp createdAt, Timestamp updatedAt) {
        this.id = id;
        this.name = name;
		this.barcode = barcode;
        this.description = description;
        this.categoryId = categoryId;
        this.mrp = mrp;
        this.cp = cp;
        this.sp = sp;
        this.image = image;
        this.deleted = deleted;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getCategoryId() {
		return this.categoryId;
	}

	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}

	public double getCp() {
		return this.cp;
	}

	public void setCp(double cp) {
		this.cp = cp;
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

	public String getImage() {
		return this.image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public double getMrp() {
		return this.mrp;
	}

	public void setMrp(double mrp) {
		this.mrp = mrp;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getBarcode() {
		return barcode;
	}

	public void setBarcode(String barcode) {
		this.barcode = barcode;
	}

	public double getSp() {
		return this.sp;
	}

	public void setSp(double sp) {
		this.sp = sp;
	}

	public Timestamp getUpdatedAt() {
		return this.updatedAt;
	}

	public void setUpdatedAt(Timestamp updatedAt) {
		this.updatedAt = updatedAt;
	}

}