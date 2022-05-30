package com.nttdata.bootcamp.apigateway.feignclient;

import java.util.List;

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
import com.nttdata.bootcamp.apigateway.entity.ResponseDelete;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


@FeignClient(name = "service-product")
public interface ProductOpenFeign {

	@GetMapping(path = "/product/v1/allproduct", produces = MediaType.APPLICATION_JSON_VALUE)
	List<ProductDto> getAll();
	
	@GetMapping("/product/v1/allproductbyid/{id}")
	ProductDto getOne(@PathVariable String id);
	
	@PostMapping(path = "/product/v1/saveproduct", produces = MediaType.APPLICATION_JSON_VALUE
			, consumes = MediaType.APPLICATION_JSON_VALUE)
	ProductDto save(@RequestBody RequestproductDto body);
	
	@PutMapping(path = "/product/v1/updateclient", produces = MediaType.APPLICATION_JSON_VALUE
			, consumes = MediaType.APPLICATION_JSON_VALUE)
	ProductDto update(@RequestBody RequestproductDto body);
	
	@DeleteMapping(path = "/product/v1/deleteclient/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	ResponseDelete delete(@PathVariable String id);
	
}
