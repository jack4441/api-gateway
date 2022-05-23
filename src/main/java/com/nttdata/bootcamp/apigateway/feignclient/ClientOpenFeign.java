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

import com.nttdata.bootcamp.apigateway.entity.Client;
import com.nttdata.bootcamp.apigateway.entity.RequestClientDto;
import com.nttdata.bootcamp.apigateway.entity.RequestClientUpdateDto;
import com.nttdata.bootcamp.apigateway.entity.ResponseAvailableBalance;
import com.nttdata.bootcamp.apigateway.entity.ResponseDelete;


@FeignClient(name = "service-client")
public interface ClientOpenFeign {
	
	@GetMapping(path = "/client/v1/allclient", produces = MediaType.APPLICATION_JSON_VALUE)
	List<Client> getAll();
	
	@GetMapping(path = "/client/v1/findclient/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	Client getClient(@PathVariable String id);
	
	@GetMapping("/client/v1/findcashorcredit/{id}/{bank_account}/{credit_card}")
	ResponseAvailableBalance getCashOrCredit(@PathVariable String id
			, @PathVariable String bank_account, @PathVariable String credit_card);
	
	@PostMapping(path = "/client/v1/saveclient", produces = MediaType.APPLICATION_JSON_VALUE
			, consumes = MediaType.APPLICATION_JSON_VALUE)
	Client save(@RequestBody RequestClientDto body);
	
	@PutMapping(path = "/client/v1/updateclient", produces = MediaType.APPLICATION_JSON_VALUE
			, consumes = MediaType.APPLICATION_JSON_VALUE)
	Client update(@RequestBody RequestClientUpdateDto body);
	
	@DeleteMapping(path = "/client/v1/deleteclient/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	ResponseDelete delete(@PathVariable String id);
	
}
