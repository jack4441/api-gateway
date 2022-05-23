package com.nttdata.bootcamp.apigateway.entity;

import java.io.Serializable;
import java.util.List;

import lombok.Data;

@Data
public class RequestClientUpdateDto implements Serializable  {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7039447829085491004L;
	
	private String id;
	private String name;
	private String lastname;
	private String dni;
	private String type;
	private List<Product> detail;
	
}
