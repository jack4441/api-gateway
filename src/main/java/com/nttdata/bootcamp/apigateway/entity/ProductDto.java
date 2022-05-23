package com.nttdata.bootcamp.apigateway.entity;

import java.io.Serializable;

import lombok.Data;


@Data
public class ProductDto implements Serializable  {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1078403678628374322L;

	private String id;
	private String description;
	private String category;
	
}
