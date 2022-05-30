package com.nttdata.bootcamp.apigateway.service;

import java.util.List;

import com.nttdata.bootcamp.apigateway.entity.ProductDto;
import com.nttdata.bootcamp.apigateway.entity.RequestproductDto;
import com.nttdata.bootcamp.apigateway.entity.ResponseDelete;

public interface IServiceProduct {

	List<ProductDto> getAll();
	ProductDto getOne(String id); 	
	ProductDto save(RequestproductDto body);
	ProductDto update(RequestproductDto body);
	ResponseDelete delete(String id);
}
