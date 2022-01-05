package com.gafahtec.service.impl;

import org.springframework.stereotype.Service;

import com.gafahtec.model.Elector;
import com.gafahtec.repository.IElectorRepo;
import com.gafahtec.repository.IGenericRepo;
import com.gafahtec.service.IElectorService;

import lombok.AllArgsConstructor;
@AllArgsConstructor
@Service
public class ElectorServiceImpl extends CRUDImpl<Elector, Integer>  implements IElectorService{
	
	private IElectorRepo repo;
	
	@Override
	protected IGenericRepo<Elector, Integer> getRepo() {
		// TODO Auto-generated method stub
		return repo;
	}

}
