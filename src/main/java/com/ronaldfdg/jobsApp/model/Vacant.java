package com.ronaldfdg.jobsApp.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "vacante")
public class Vacant {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(name = "nombre", nullable = false)
	private String name;
	
	@Column(name = "descripcion", nullable = false)
	private String description;
	
	@Column(name = "fecha", nullable = false)
	private Date publishDate;
	
	@Column(name = "salario", nullable = false)
	private Double salary;
	
	@Column(name = "estatus", nullable = false)
	private String status;
	
	@Column(name = "destacado", nullable = false)
	private boolean highlighted;
	
	@Column(name = "imagen", nullable = false)
	private String image="no-image.png";
	
	@Column(name = "detalles", nullable = false)
	private String details;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="idCategoria")
	private Category category;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getPublishDate() {
		return publishDate;
	}

	public void setPublishDate(Date publishDate) {
		this.publishDate = publishDate;
	}

	public Double getSalary() {
		return salary;
	}

	public void setSalary(Double salary) {
		this.salary = salary;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public boolean isHighlighted() {
		return highlighted;
	}

	public void setHighlighted(boolean highlighted) {
		this.highlighted = highlighted;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getDetails() {
		return details;
	}

	public void setDetails(String details) {
		this.details = details;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	@Override
	public String toString() {
		return "Vacant [id=" + id + ", name=" + name + ", description=" + description + ", publishDate=" + publishDate
				+ ", salary=" + salary + ", status=" + status + ", highlighted=" + highlighted + ", image=" + image
				+ ", details=" + details + "]";
	}
	
}
