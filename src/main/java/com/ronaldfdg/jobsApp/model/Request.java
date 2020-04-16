package com.ronaldfdg.jobsApp.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "solicitud")
public class Request {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(name = "fecha", nullable = false)
	private Date requestDate;
	
	@Column(name = "archivo", nullable = false)
	private String file;
	
	@Column(name = "comentarios", nullable = false)
	private String comments;
	
	@ManyToOne
	@JoinColumn(name = "idVacante")
	private Vacant vacant;
	
	//@ManyToOne
	//@JoinColumn(name = "idUsuario")
	//private User user;
	
	
}
