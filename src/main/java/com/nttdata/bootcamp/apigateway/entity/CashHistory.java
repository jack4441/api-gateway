package com.nttdata.bootcamp.apigateway.entity;

import lombok.Data;

@Data
public class CashHistory {
	
	private String id;
	
	private String idcliente;
	
	private String fullname;
	
	private String iddetail;
	
	private String idproduct;
	
	private String bank_account;
	
	private String credit_card;
	
	private String type;
	
	private double amount;
	
	private double current_balance;
	
	private String destination;
	
	private String date;
	
	private String time;
	
	private String state;
	
	private boolean last_balance_day;
	
	private boolean pay_commission;
	
	private double amount_commission;
}
