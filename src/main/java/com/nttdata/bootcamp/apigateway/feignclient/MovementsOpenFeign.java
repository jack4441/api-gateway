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

import com.nttdata.bootcamp.apigateway.entity.Movements;
import com.nttdata.bootcamp.apigateway.entity.RequestMovementsDto;
import com.nttdata.bootcamp.apigateway.entity.ResponseDelete;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


@FeignClient(name = "service-movements")
public interface MovementsOpenFeign {

	@GetMapping("/movements/v1/allmovements")
	List<Movements> getAll();
	
	@GetMapping("/movements/v1/allmovementsbyid/{idcliente}/{idproducto}")
	List<Movements> getAll(@PathVariable String idcliente, @PathVariable String idproducto);
	
	@PostMapping(path = "/movements/v1/savemovements", produces = MediaType.APPLICATION_JSON_VALUE
			, consumes = MediaType.APPLICATION_JSON_VALUE)
	Movements save(@RequestBody RequestMovementsDto body);
	
	@PutMapping(path = "/movements/v1/updatemovements", produces = MediaType.APPLICATION_JSON_VALUE
			, consumes = MediaType.APPLICATION_JSON_VALUE)
	Movements update(@RequestBody RequestMovementsDto body);
	
	@DeleteMapping(path = "/movements/v1/deletedetail/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	ResponseDelete delete(@PathVariable String id);
	
	@GetMapping(path = "/movements/v1/getcommissionsperrange/{idproduct}/{initdate}/{enddate}")
	List<Movements> movementsFindAllCommissions(@PathVariable String idproduct, @PathVariable String initdate, @PathVariable String enddate);
	
}
