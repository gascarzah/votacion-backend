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
import com.gafahtec.model.Voto;
import com.gafahtec.service.IVotoService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/votos")
@AllArgsConstructor
public class VotoController {
	
private IVotoService iVotoService;
	
	@GetMapping
	public ResponseEntity<List<Voto>> listar() throws Exception{
		List<Voto> lista = iVotoService.listar();
		return new ResponseEntity<List<Voto>>(lista, HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Voto> listarPorId(@PathVariable("id") Integer id) throws Exception{
		Voto obj = iVotoService.listarPorId(id);
		
		if(obj.getIdVoto() == null) {
			throw new ModeloNotFoundException("Id no encontrado " + id );
		}
		
		return new ResponseEntity<Voto>(obj, HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<Voto> registrar(@Valid @RequestBody Voto p) throws Exception{
		Voto obj = iVotoService.registrar(p);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getIdVoto()).toUri();
		return ResponseEntity.created(location).build();
	}
	
	@PutMapping
	public ResponseEntity<Voto> modificar(@Valid @RequestBody Voto p) throws Exception{
		Voto obj = iVotoService.modificar(p);
		return new ResponseEntity<Voto>(obj, HttpStatus.OK);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> eliminar(@PathVariable("id") Integer id) throws Exception{
		Voto obj = iVotoService.listarPorId(id);
		
		if(obj.getIdVoto() == null) {
			throw new ModeloNotFoundException("ID NO ENCONTRADO "+id);
		}
			
		iVotoService.eliminar(id);
		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}
}
