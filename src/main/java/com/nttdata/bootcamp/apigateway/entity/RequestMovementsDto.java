package com.nttdata.bootcamp.apigateway.entity;

import java.io.Serializable;

import lombok.Data;


@Data
public class RequestMovementsDto implements Serializable  {

	/**
	 * 
	 */
	private static final long serialVersionUID = 9033145546222513175L;
	
	private Movements movements;
	
}
