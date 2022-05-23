package com.nttdata.bootcamp.apigateway.service;

import java.util.List;

import com.nttdata.bootcamp.apigateway.entity.Client;
import com.nttdata.bootcamp.apigateway.entity.RequestClientDto;
import com.nttdata.bootcamp.apigateway.entity.RequestClientUpdateDto;
import com.nttdata.bootcamp.apigateway.entity.ResponseAvailableBalance;
import com.nttdata.bootcamp.apigateway.entity.ResponseDelete;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface IServiceClient {

	List<Client> clientFindAll();
	
	Client clientFindOne(String id);
	
	Client clientSave(RequestClientDto request);
	
	Client clientUpdate(RequestClientUpdateDto request);
	
	ResponseDelete clientDelete(String id);
	
}
