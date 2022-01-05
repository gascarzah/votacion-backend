package com.gafahtec.service.impl;

import org.springframework.stereotype.Service;

import com.gafahtec.model.Voto;
import com.gafahtec.repository.IGenericRepo;
import com.gafahtec.repository.IVotoRepo;
import com.gafahtec.service.IVotoService;

import lombok.AllArgsConstructor;
@AllArgsConstructor
@Service
public class VotoServiceImpl extends CRUDImpl<Voto, Integer>  implements IVotoService{

	private IVotoRepo repo;
	
	@Override
	protected IGenericRepo<Voto, Integer> getRepo() {
		// TODO Auto-generated method stub
		return repo;
	}

}
