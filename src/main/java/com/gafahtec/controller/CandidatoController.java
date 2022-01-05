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
import com.gafahtec.model.Candidato;
import com.gafahtec.service.ICandidatoService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/candidatos")
@AllArgsConstructor
public class CandidatoController {
	
private ICandidatoService iCandidatoService;
	
	@GetMapping
	public ResponseEntity<List<Candidato>> listar() throws Exception{
		List<Candidato> lista = iCandidatoService.listar();
		return new ResponseEntity<List<Candidato>>(lista, HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Candidato> listarPorId(@PathVariable("id") Integer id) throws Exception{
		Candidato obj = iCandidatoService.listarPorId(id);
		
		if(obj.getIdCandidato() == null) {
			throw new ModeloNotFoundException("Id no encontrado " + id );
		}
		
		return new ResponseEntity<Candidato>(obj, HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<Candidato> registrar(@Valid @RequestBody Candidato p) throws Exception{
		Candidato obj = iCandidatoService.registrar(p);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getIdCandidato()).toUri();
		return ResponseEntity.created(location).build();
	}
	
	@PutMapping
	public ResponseEntity<Candidato> modificar(@Valid @RequestBody Candidato p) throws Exception{
		Candidato obj = iCandidatoService.modificar(p);
		return new ResponseEntity<Candidato>(obj, HttpStatus.OK);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> eliminar(@PathVariable("id") Integer id) throws Exception{
		Candidato obj = iCandidatoService.listarPorId(id);
		
		if(obj.getIdCandidato() == null) {
			throw new ModeloNotFoundException("ID NO ENCONTRADO "+id);
		}
			
		iCandidatoService.eliminar(id);
		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}
}
