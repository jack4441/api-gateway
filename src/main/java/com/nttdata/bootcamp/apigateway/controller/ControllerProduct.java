package com.nttdata.bootcamp.apigateway.controller;

import java.util.List;

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

import com.nttdata.bootcamp.apigateway.entity.ProductDto;
import com.nttdata.bootcamp.apigateway.entity.RequestproductDto;
import com.nttdata.bootcamp.apigateway.entity.ResponseDelete;
import com.nttdata.bootcamp.apigateway.service.IServiceProduct;

import io.github.resilience4j.bulkhead.annotation.Bulkhead;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import io.github.resilience4j.timelimiter.annotation.TimeLimiter;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Slf4j
@RestController
@RequestMapping("gateway/product/v1")
public class ControllerProduct {

	@Autowired
	IServiceProduct service;
	

	@GetMapping(path = "/allproduct", produces = MediaType.APPLICATION_JSON_VALUE)
	public Flux<List<ProductDto>> getAll(){
		return Flux.just(service.getAll());
	}
	
	@GetMapping("/allproductbyid/{id}")
	public Mono<ProductDto> getOne(@PathVariable String id){
		return Mono.just(service.getOne(id));
	}
	
	@Bulkhead(name= "bulkFirst")
	@TimeLimiter(name = "timelimit", fallbackMethod = "fallbackgetsave")
	@PostMapping(path = "/saveproduct", produces = MediaType.APPLICATION_JSON_VALUE
		, consumes = MediaType.APPLICATION_JSON_VALUE)
	public Mono<ProductDto> save(@RequestBody RequestproductDto body)
	{
		return Mono.just(service.save(body));
	}
	
	@PutMapping(path = "/updateclient", produces = MediaType.APPLICATION_JSON_VALUE
			, consumes = MediaType.APPLICATION_JSON_VALUE)
	public Mono<ProductDto> update(@RequestBody RequestproductDto body){
		return Mono.just(service.update(body));
	}
	
	@DeleteMapping(path = "/deleteclient/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public Mono<ResponseDelete> delete(@PathVariable String id){
		return Mono.just(service.delete(id));
	}
	
    public Mono<ProductDto> fallbackgetsave(RequestproductDto body, Exception e) {
    	log.info("Entrando al método fallbackallproduct en el servicio ServiceProduct");
    	log.info("message Error: " + e.getMessage());
        return Mono.just(new ProductDto());
    }
	
}
