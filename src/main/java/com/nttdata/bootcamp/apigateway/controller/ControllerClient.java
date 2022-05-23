package com.nttdata.bootcamp.apigateway.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nttdata.bootcamp.apigateway.entity.Client;
import com.nttdata.bootcamp.apigateway.entity.RequestClientDto;
import com.nttdata.bootcamp.apigateway.entity.RequestClientUpdateDto;
import com.nttdata.bootcamp.apigateway.entity.ResponseAvailableBalance;
import com.nttdata.bootcamp.apigateway.entity.ResponseDelete;
import com.nttdata.bootcamp.apigateway.service.IServiceClient;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("gateway/client/v1")
public class ControllerClient {

	@Autowired
	IServiceClient serviceClient;
	
	@GetMapping(path = "/allclient", produces = MediaType.APPLICATION_JSON_VALUE)
	public Flux<List<Client>> getAll(){
		return Flux.just(serviceClient.clientFindAll().stream().collect(Collectors.toList()));
	}
	
	@GetMapping("/findclient/{id}")
	public Mono<Client> getClient(@PathVariable String id){
		return Mono.just(serviceClient.clientFindOne(id));
	}

	@PostMapping(path = "/saveclient", produces = MediaType.APPLICATION_JSON_VALUE
			, consumes = MediaType.APPLICATION_JSON_VALUE)
	public Mono<Client> save(@RequestBody RequestClientDto body){
		return Mono.just(serviceClient.clientSave(body));
	}

	@PutMapping(path = "/updateclient", produces = MediaType.APPLICATION_JSON_VALUE
			, consumes = MediaType.APPLICATION_JSON_VALUE)
	public Mono<Client> update(@RequestBody RequestClientUpdateDto body){
		return Mono.just(serviceClient.clientUpdate(body));
	}

	@DeleteMapping(path = "/deleteclient/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public Mono<ResponseDelete> delete(@PathVariable String id){	
		return Mono.just(serviceClient.clientDelete(id));
	}
	
}
