package com.nttdata.bootcamp.apigateway.entity;

import lombok.Data;


@Data
public class Movements {

	private String id;
	private String idcliente;
	private String fullname; 
	private String idproduct; 
	private String bank_account;
	private String credit_card;
	private String type;
	private double amount;
	private String destination;
	private String date;
	private boolean pay_commission;
	private double amount_commission;
	
}
