package com.nttdata.bootcamp.apigateway.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nttdata.bootcamp.apigateway.entity.CashHistory;
import com.nttdata.bootcamp.apigateway.entity.Movements;
import com.nttdata.bootcamp.apigateway.entity.RequestMovementsDto;
import com.nttdata.bootcamp.apigateway.entity.ResponseDelete;
import com.nttdata.bootcamp.apigateway.feignclient.MovementsOpenFeign;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class ServiceMovements implements IServiceMovements {

	@Autowired
	MovementsOpenFeign feign;

	@CircuitBreaker(name = "mycircuitbreaker", fallbackMethod = "fallbackgetAll")
	@Retry(name = "myRetry")
	@Override
	public List<Movements> movementsFindAll() {
		// TODO Auto-generated method stub
		return feign.getAll();
	}

	@CircuitBreaker(name = "mycircuitbreaker", fallbackMethod = "fallbackgetfindAll")
	@Retry(name = "myRetry")
	@Override
	public List<Movements> findByIdcliente(String idclient, String idproduct) {
		// TODO Auto-generated method stub
		return feign.getAll(idclient, idproduct);
	}

	@CircuitBreaker(name = "mycircuitbreaker", fallbackMethod = "fallbacksave")
	@Retry(name = "myRetry")
	@Override
	public Movements movementsSave(RequestMovementsDto request) {
		// TODO Auto-generated method stub
		return feign.save(request);
	}

	@CircuitBreaker(name = "mycircuitbreaker", fallbackMethod = "fallbackgetupdate")
	@Retry(name = "myRetry")
	@Override
	public Movements movementsUpdate(RequestMovementsDto request) {
		// TODO Auto-generated method stub
		return feign.update(request);
	}

	@CircuitBreaker(name = "mycircuitbreaker", fallbackMethod = "fallbackgetdelete")
	@Retry(name = "myRetry")
	@Override
	public ResponseDelete movementsDelete(String id) {
		// TODO Auto-generated method stub
		return feign.delete(id);
	}

	@CircuitBreaker(name = "mycircuitbreaker", fallbackMethod = "fallbackgetcommissions")
	@Retry(name = "myRetry")
	@Override
	public List<Movements> movementsFindAllCommissions(String idproduct, String initdate, String enddate) {
		// TODO Auto-generated method stub
		return feign.movementsFindAllCommissions(idproduct, initdate, enddate);
	}
	
	@CircuitBreaker(name = "mycircuitbreaker", fallbackMethod = "fallbackgetaveragebalance")
	@Retry(name = "myRetry")
	@Override
	public List<CashHistory> movementsFindAllAverageBalanceCreditBankAccountsPerMonth(String idclient) {
		// TODO Auto-generated method stub
		return feign.movementsFindAllAverageBalanceCreditBankAccountsPerMonth(idclient);
	}
	
	@CircuitBreaker(name = "mycircuitbreaker", fallbackMethod = "fallbackfindTenMovementsDebCred")
	@Retry(name = "myRetry")
	@Override
	public List<Movements> findTenMovementsDebCred(String idclient) {
		// TODO Auto-generated method stub
		return feign.findTenMovementsDebCred(idclient);
	}
	
    public List<Movements> fallbackfindTenMovementsDebCred(Exception e) {
    	log.info("Entrando al método fallbackfindTenMovementsDebCred en el servicio ServiceMovements");
    	log.info("message Error: " + e.getMessage());
        return new ArrayList<Movements>();
    }
	
    public List<Movements> fallbackgetAll(Exception e) {
    	log.info("Entrando al método fallbackgetAll en el servicio ServiceMovements");
    	log.info("message Error: " + e.getMessage());
        return new ArrayList<Movements>();
    }
    
    public List<Movements> fallbackgetfindAll(Exception e) {
    	log.info("Entrando al método fallbackgetfindAll en el servicio ServiceMovements");
    	log.info("message Error: " + e.getMessage());
        return new ArrayList<Movements>();
    }
    
    public Movements fallbacksave(Exception e) {
    	log.info("Entrando al método fallbacksave en el servicio ServiceMovements");
    	log.info("message Error: " + e.getMessage());
        return new Movements();
    }
    
    public Movements fallbackgetupdate(Exception e) {
    	log.info("Entrando al método fallbackgetupdate en el servicio ServiceMovements");
    	log.info("message Error: " + e.getMessage());
        return new Movements();
    }
    
    public ResponseDelete fallbackgetdelete(Exception e) {
    	log.info("Entrando al método fallbackgetdelete en el servicio ServiceMovements");
    	log.info("message Error: " + e.getMessage());
        return new ResponseDelete();
    }
    
    public List<Movements> fallbackgetcommissions(Exception e) {
    	log.info("Entrando al método fallbackgetcommissions en el servicio ServiceMovements");
    	log.info("message Error: " + e.getMessage());
        return new ArrayList<Movements>();
    }
    
    public List<CashHistory> fallbackgetaveragebalance(Exception e)
    {
    	log.info("Entrando al método fallbackgetaveragebalance en el servicio ServiceMovements");
    	log.info("message Error: " + e.getMessage());
    	return new ArrayList<CashHistory>();
    }

}
