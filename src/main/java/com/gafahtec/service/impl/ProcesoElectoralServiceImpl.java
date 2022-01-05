package com.gafahtec.service.impl;

import org.springframework.stereotype.Service;

import com.gafahtec.model.ProcesoElectoral;
import com.gafahtec.repository.IGenericRepo;
import com.gafahtec.repository.IProcesoElectoralRepo;
import com.gafahtec.service.IProcesoElectoralService;

import lombok.AllArgsConstructor;
@AllArgsConstructor
@Service
public class ProcesoElectoralServiceImpl extends CRUDImpl<ProcesoElectoral, Integer>  implements IProcesoElectoralService{

	private IProcesoElectoralRepo repo;
	
	@Override
	protected IGenericRepo<ProcesoElectoral, Integer> getRepo() {
		// TODO Auto-generated method stub
		return repo;
	}
}
