package com.nttdata.bootcamp.apigateway.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nttdata.bootcamp.apigateway.entity.Client;
import com.nttdata.bootcamp.apigateway.entity.RequestClientDto;
import com.nttdata.bootcamp.apigateway.entity.RequestClientUpdateDto;
import com.nttdata.bootcamp.apigateway.entity.ResponseAvailableBalance;
import com.nttdata.bootcamp.apigateway.entity.ResponseDelete;
import com.nttdata.bootcamp.apigateway.feignclient.ClientOpenFeign;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import io.github.resilience4j.timelimiter.annotation.TimeLimiter;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


@Slf4j
@Service
public class ServiceClient implements IServiceClient {

	@Autowired
	ClientOpenFeign feign;
	
	@CircuitBreaker(name = "mycircuitbreaker", fallbackMethod = "fallbackgetAll")
	@Retry(name = "myRetry")
	@Override
	public List<Client> clientFindAll() {
		// TODO Auto-generated method stub
		return feign.getAll();
	}

	@CircuitBreaker(name = "mycircuitbreaker", fallbackMethod = "fallbackgetClient")
	@Retry(name = "myRetry")
	@Override
	public Client clientFindOne(String id) {
		// TODO Auto-generated method stub
		return feign.getClient(id);
	}

	@CircuitBreaker(name = "mycircuitbreaker", fallbackMethod = "fallbacksave")
	@Retry(name = "myRetry")
	@Override
	public Client clientSave(RequestClientDto request) {
		// TODO Auto-generated method stub
		return feign.save(request);
	}

	@CircuitBreaker(name = "mycircuitbreaker", fallbackMethod = "fallbackupdate")
	@Retry(name = "myRetry")
	@Override
	public Client clientUpdate(RequestClientUpdateDto request) {
		// TODO Auto-generated method stub
		return feign.update(request);
	}

	@CircuitBreaker(name = "mycircuitbreaker", fallbackMethod = "fallbackdelete")
	@Retry(name = "myRetry")
	@Override
	public ResponseDelete clientDelete(String id) {
		// TODO Auto-generated method stub
		log.info("Entrando al método clientDelete en el servicio ServiceClient");	
		return feign.delete(id);
	}	

    public List<Client> fallbackgetAll(Exception e) {
    	log.info("Entrando al método fallbackgetAll en el servicio ServiceClient");
    	log.info("message Error: " + e.getMessage());
        return new ArrayList<Client>();
    }
    
    public Client fallbackgetClient(Exception e) {
    	log.info("Entrando al método fallbackgetClient en el servicio ServiceClient");
    	log.info("message Error: " + e.getMessage());
        return new Client();
    }
    
    public Client fallbacksave(Exception e) {
    	log.info("Entrando al método fallbacksave en el servicio ServiceClient");
    	log.info("message Error: " + e.getMessage());
        return new Client();
    }
    
    public Client fallbackupdate(Exception e) {
    	log.info("Entrando al método fallbackupdate en el servicio ServiceClient");
    	log.info("message Error: " + e.getMessage());
        return new Client();
    }
    
    public ResponseDelete fallbackdelete(Exception e) {
    	log.info("Entrando al método fallbackdelete en el servicio ServiceClient");
    	log.info("message Error: " + e.getMessage());
        return new ResponseDelete();
    }

}
