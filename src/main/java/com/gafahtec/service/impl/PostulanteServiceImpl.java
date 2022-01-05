package com.gafahtec.service.impl;

import org.springframework.stereotype.Service;

import com.gafahtec.model.Postulante;
import com.gafahtec.repository.IGenericRepo;
import com.gafahtec.repository.IPostulanteRepo;
import com.gafahtec.service.IPostulanteService;

import lombok.AllArgsConstructor;
@AllArgsConstructor
@Service
public class PostulanteServiceImpl extends CRUDImpl<Postulante, Integer>  implements IPostulanteService{

	private IPostulanteRepo repo;
	
	@Override
	protected IGenericRepo<Postulante, Integer> getRepo() {
		// TODO Auto-generated method stub
		return repo;
	}

}
