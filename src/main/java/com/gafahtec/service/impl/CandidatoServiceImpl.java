package com.gafahtec.service.impl;

import org.springframework.stereotype.Service;

import com.gafahtec.model.Candidato;
import com.gafahtec.repository.ICandidatoRepo;
import com.gafahtec.repository.IGenericRepo;
import com.gafahtec.service.ICandidatoService;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class CandidatoServiceImpl extends CRUDImpl<Candidato, Integer>  implements ICandidatoService{

	private ICandidatoRepo repo;
	
	@Override
	protected IGenericRepo<Candidato, Integer> getRepo() {
		// TODO Auto-generated method stub
		return repo;
	}

}
