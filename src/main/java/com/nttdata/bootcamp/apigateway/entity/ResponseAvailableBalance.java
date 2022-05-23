package com.nttdata.bootcamp.apigateway.entity;

import java.io.Serializable;

import lombok.Data;


@Data
public class ResponseAvailableBalance implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -345313346774288366L;
	
	private String idclient;
	private String idproduct;
	private double available_balance;
	private String number_descrip_product;
	
}
