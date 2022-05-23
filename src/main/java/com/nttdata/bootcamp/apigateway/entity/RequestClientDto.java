package com.nttdata.bootcamp.apigateway.entity;

import java.io.Serializable;

import lombok.Data;


@Data
public class RequestClientDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7056921064414777375L;
	
	private String id;
	private String name;
	private String lastname;
	private String dni;
	private String type;
	private Product product;
	
}
