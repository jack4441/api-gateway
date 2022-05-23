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

import com.nttdata.bootcamp.apigateway.entity.Client;
import com.nttdata.bootcamp.apigateway.entity.Movements;
import com.nttdata.bootcamp.apigateway.entity.RequestMovementsDto;
import com.nttdata.bootcamp.apigateway.entity.ResponseDelete;
import com.nttdata.bootcamp.apigateway.feignclient.MovementsOpenFeign;
import com.nttdata.bootcamp.apigateway.service.IServiceMovements;
import com.nttdata.bootcamp.apigateway.service.ServiceMovements;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import io.github.resilience4j.timelimiter.annotation.TimeLimiter;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Slf4j
@RestController
@RequestMapping("gateway/movements/v1")
public class ControllerMovements {

	@Autowired
	IServiceMovements serviceMovements;
	
	@GetMapping("/allmovements")
	public Flux<List<Movements>> getAll(){
		return Flux.just(serviceMovements.movementsFindAll());
	}
	
	@GetMapping("/allmovementsbyid/{idcliente}/{idproducto}")
	public Flux<List<Movements>> getAll(@PathVariable String idcliente, @PathVariable String idproducto){
		return Flux.just(serviceMovements.findByIdcliente(idcliente, idproducto));
	}
	
	@PostMapping(path = "/savemovements", produces = MediaType.APPLICATION_JSON_VALUE
			, consumes = MediaType.APPLICATION_JSON_VALUE)
	public Mono<Movements> save(@RequestBody RequestMovementsDto body){
		return Mono.just(serviceMovements.movementsSave(body));
	}
	
	@PutMapping(path = "/updatemovements", produces = MediaType.APPLICATION_JSON_VALUE
			, consumes = MediaType.APPLICATION_JSON_VALUE)
	public Mono<Movements> update(@RequestBody RequestMovementsDto body){
		return Mono.just(serviceMovements.movementsUpdate(body));
	}
	
	@DeleteMapping(path = "/deletedetail/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public Mono<ResponseDelete> delete(@PathVariable String id){
		return Mono.just(serviceMovements.movementsDelete(id));
	}
	
	@GetMapping(path = "/getcommissionsperrange/{idproduct}/{initdate}/{enddate}")
	public Flux<List<Movements>> movementsFindAllCommissions(@PathVariable String idproduct, @PathVariable String initdate, @PathVariable String enddate)
	{
		return Flux.just(serviceMovements.movementsFindAllCommissions(idproduct, initdate, enddate));
	}
	
}
