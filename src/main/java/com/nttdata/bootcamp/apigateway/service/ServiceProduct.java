package com.nttdata.bootcamp.apigateway.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nttdata.bootcamp.apigateway.entity.ProductDto;
import com.nttdata.bootcamp.apigateway.entity.RequestproductDto;
import com.nttdata.bootcamp.apigateway.entity.ResponseDelete;
import com.nttdata.bootcamp.apigateway.feignclient.ProductOpenFeign;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class ServiceProduct implements IServiceProduct {

	@Autowired
	ProductOpenFeign feign;
	
	@CircuitBreaker(name = "mycircuitbreaker", fallbackMethod = "fallbackallproduct")
	@Retry(name = "myRetry")
	@Override
	public List<ProductDto> getAll() {
		// TODO Auto-generated method stub
		return feign.getAll();
	}

	@CircuitBreaker(name = "mycircuitbreaker", fallbackMethod = "fallbackgetOne")
	@Retry(name = "myRetry")
	@Override
	public ProductDto getOne(String id) {
		// TODO Auto-generated method stub
		return feign.getOne(id);
	}

	@CircuitBreaker(name = "mycircuitbreaker", fallbackMethod = "fallbackgetsave")
	@Retry(name = "myRetry")
	@Override
	public ProductDto save(RequestproductDto body) {
		// TODO Auto-generated method stub
		return feign.save(body);
	}

	@CircuitBreaker(name = "mycircuitbreaker", fallbackMethod = "fallbackupdate")
	@Retry(name = "myRetry")
	@Override
	public ProductDto update(RequestproductDto body) {
		// TODO Auto-generated method stub
		return feign.update(body);
	}
	
	@CircuitBreaker(name = "mycircuitbreaker", fallbackMethod = "fallbackdelete")
	@Retry(name = "myRetry")
	@Override
	public ResponseDelete delete(String id) {
		// TODO Auto-generated method stub
		return feign.delete(id);
	}
	
    public List<ProductDto> fallbackallproduct(Exception e) {
    	log.info("Entrando al método fallbackallproduct en el servicio ServiceProduct");
    	log.info("message Error: " + e.getMessage());
        return new ArrayList<>();
    }
    
    public ProductDto fallbackgetOne(Exception e) {
    	log.info("Entrando al método fallbackgetOne en el servicio ServiceProduct");
    	log.info("message Error: " + e.getMessage());
        return new ProductDto();
    }
    
    public ProductDto fallbackgetsave(Exception e) {
    	log.info("Entrando al método fallbackallproduct en el servicio ServiceProduct");
    	log.info("message Error: " + e.getMessage());
        return new ProductDto();
    }
    
    public ProductDto fallbackupdate(Exception e) {
    	log.info("Entrando al método fallbackupdate en el servicio ServiceProduct");
    	log.info("message Error: " + e.getMessage());
        return new ProductDto();
    }
    
    public ResponseDelete fallbackdelete(Exception e) {
    	log.info("Entrando al método fallbackdelete en el servicio ServiceProduct");
    	log.info("message Error: " + e.getMessage());
        return new ResponseDelete();
    }

}
