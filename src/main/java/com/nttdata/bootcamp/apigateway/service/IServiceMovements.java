package com.nttdata.bootcamp.apigateway.service;

import java.util.List;

import com.nttdata.bootcamp.apigateway.entity.Movements;
import com.nttdata.bootcamp.apigateway.entity.RequestMovementsDto;
import com.nttdata.bootcamp.apigateway.entity.ResponseDelete;

public interface IServiceMovements {
List<Movements>movementsFindAll();
List<Movements> findByIdcliente(String idclient, String idproduct);
Movements movementsSave(RequestMovementsDto request);
Movements movementsUpdate(RequestMovementsDto request);
ResponseDelete movementsDelete(String id);
List<Movements> movementsFindAllCommissions(String idproduct, String initdate, String enddate);	
}
