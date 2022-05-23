package com.nttdata.bootcamp.apigateway.entity;

import java.io.Serializable;

import lombok.Data;


@Data
public class Product implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4398670380172096400L;
	
	private String iddetail;
	private String id;
	private double cash;
	private String modality;
	private String bank_account;
	private String inter_bank_account;
	private String company;
	private double credit;
	private String fee;
	private double payperfee;
	private String credit_card;
	private String ccv;
	private String expiration_date;
	private String state;	
	private double counter_transaction;
	
}
