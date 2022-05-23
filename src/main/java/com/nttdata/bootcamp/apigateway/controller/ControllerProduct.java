package com.nttdata.bootcamp.apigateway.controller;

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
import com.nttdata.bootcamp.apigateway.entity.ProductDto;
import com.nttdata.bootcamp.apigateway.entity.RequestproductDto;
import com.nttdata.bootcamp.apigateway.feignclient.ProductOpenFeign;

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
	ProductOpenFeign feign;
	
	@CircuitBreaker(name = "mycircuitbreaker", fallbackMethod = "fallbackallproduct")
	@TimeLimiter(name = "timelimit")
	@Retry(name = "myRetry")
	@GetMapping(path = "/allproduct", produces = MediaType.APPLICATION_JSON_VALUE)
	public Flux<ProductDto> getAll(){
		return feign.getAll();
	}
	
	@CircuitBreaker(name = "mycircuitbreaker", fallbackMethod = "fallbackgetOne")
	@TimeLimiter(name = "timelimit")
	@Retry(name = "myRetry")
	@GetMapping("/allproductbyid/{id}")
	public Mono<ProductDto> getOne(@PathVariable String id){
		return feign.getOne(id);
	}
	
	@CircuitBreaker(name = "mycircuitbreaker", fallbackMethod = "fallbackgetsave")
	@TimeLimiter(name = "timelimit")
	@Retry(name = "myRetry")
	@PostMapping(path = "/saveproduct", produces = MediaType.APPLICATION_JSON_VALUE)
	public Mono<ProductDto> save(@RequestBody RequestproductDto body)
	{
		return feign.save(body);
	}
	
	@CircuitBreaker(name = "mycircuitbreaker", fallbackMethod = "fallbackupdate")
	@TimeLimiter(name = "timelimit")
	@Retry(name = "myRetry")
	@PutMapping(path = "/updateclient", produces = MediaType.APPLICATION_JSON_VALUE)
	public Mono<ProductDto> update(@RequestBody RequestproductDto body){
		return feign.update(body);
	}
	
	@CircuitBreaker(name = "mycircuitbreaker", fallbackMethod = "fallbackdelete")
	@TimeLimiter(name = "timelimit")
	@Retry(name = "myRetry")
	@DeleteMapping("/deleteclient/{id}")
	public Mono<Void> delete(@PathVariable String id){
		return feign.delete(id);
	}
	
    public Flux<ProductDto> fallbackallproduct(Exception e) {
    	log.info("Entrando al método fallbackallproduct en el controllador ControllerProduct");
    	log.info("message Error: " + e.getMessage());
        return Flux.empty();
    }
    
    public Mono<ProductDto> fallbackgetOne(Exception e) {
    	log.info("Entrando al método fallbackgetOne en el controllador ControllerProduct");
    	log.info("message Error: " + e.getMessage());
        return Mono.empty();
    }
    
    public Mono<ProductDto> fallbackgetsave(Exception e) {
    	log.info("Entrando al método fallbackallproduct en el controllador ControllerProduct");
    	log.info("message Error: " + e.getMessage());
        return Mono.empty();
    }
    
    public Mono<ProductDto> fallbackupdate(Exception e) {
    	log.info("Entrando al método fallbackupdate en el controllador ControllerProduct");
    	log.info("message Error: " + e.getMessage());
        return Mono.empty();
    }
    
    public Mono<ProductDto> fallbackdelete(Exception e) {
    	log.info("Entrando al método fallbackdelete en el controllador ControllerProduct");
    	log.info("message Error: " + e.getMessage());
        return Mono.empty();
    }
	
}
