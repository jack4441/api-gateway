package com.nttdata.bootcamp.apigateway.feignclient;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.nttdata.bootcamp.apigateway.entity.ProductDto;
import com.nttdata.bootcamp.apigateway.entity.RequestproductDto;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


@FeignClient(name = "service-product")
public interface ProductOpenFeign {

	@GetMapping(path = "/allproduct", produces = MediaType.APPLICATION_JSON_VALUE)
	Flux<ProductDto> getAll();
	
	@GetMapping("/allproductbyid/{id}")
	Mono<ProductDto> getOne(@PathVariable String id);
	
	@PostMapping(path = "/saveproduct", produces = MediaType.APPLICATION_JSON_VALUE)
	Mono<ProductDto> save(@RequestBody RequestproductDto body);
	
	@PutMapping(path = "/updateclient", produces = MediaType.APPLICATION_JSON_VALUE)
	Mono<ProductDto> update(@RequestBody RequestproductDto body);
	
	@DeleteMapping("/deleteclient/{id}")
	Mono<Void> delete(@PathVariable String id);
	
}
