package com.nttdata.bootcamp.apigateway.entity;

import java.io.Serializable;

import lombok.Data;


@Data
public class RequestproductDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1128293117920188875L;
	
	private Product product;

}
