package com.gafahtec.controller;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.gafahtec.exception.ModeloNotFoundException;
import com.gafahtec.model.ProcesoElectoral;
import com.gafahtec.service.IProcesoElectoralService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/procesos")
@AllArgsConstructor
public class ProcesoElectoralController {
	
private IProcesoElectoralService iProcesoElectoralService;
	
	@GetMapping
	public ResponseEntity<List<ProcesoElectoral>> listar() throws Exception{
		List<ProcesoElectoral> lista = iProcesoElectoralService.listar();
		return new ResponseEntity<List<ProcesoElectoral>>(lista, HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<ProcesoElectoral> listarPorId(@PathVariable("id") Integer id) throws Exception{
		ProcesoElectoral obj = iProcesoElectoralService.listarPorId(id);
		
		if(obj.getIdProcesoElectoral() == null) {
			throw new ModeloNotFoundException("Id no encontrado " + id );
		}
		
		return new ResponseEntity<ProcesoElectoral>(obj, HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<ProcesoElectoral> registrar(@Valid @RequestBody ProcesoElectoral p) throws Exception{
		ProcesoElectoral obj = iProcesoElectoralService.registrar(p);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getIdProcesoElectoral()).toUri();
		return ResponseEntity.created(location).build();
	}
	
	@PutMapping
	public ResponseEntity<ProcesoElectoral> modificar(@Valid @RequestBody ProcesoElectoral p) throws Exception{
		ProcesoElectoral obj = iProcesoElectoralService.modificar(p);
		return new ResponseEntity<ProcesoElectoral>(obj, HttpStatus.OK);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> eliminar(@PathVariable("id") Integer id) throws Exception{
		ProcesoElectoral obj = iProcesoElectoralService.listarPorId(id);
		
		if(obj.getIdProcesoElectoral() == null) {
			throw new ModeloNotFoundException("ID NO ENCONTRADO "+id);
		}
			
		iProcesoElectoralService.eliminar(id);
		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}
}
