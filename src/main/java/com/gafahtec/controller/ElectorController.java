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

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.gafahtec.exception.ModeloNotFoundException;
import com.gafahtec.model.Elector;
import com.gafahtec.service.IElectorService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/electores")
@AllArgsConstructor
public class ElectorController {
	
private IElectorService iElectorService;
	
	@GetMapping
	public ResponseEntity<List<Elector>> listar() throws Exception{
		List<Elector> lista = iElectorService.listar();
		return new ResponseEntity<List<Elector>>(lista, HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Elector> listarPorId(@PathVariable("id") Integer id) throws Exception{
		Elector obj = iElectorService.listarPorId(id);
		
		if(obj.getIdElector() == null) {
			throw new ModeloNotFoundException("Id no encontrado " + id );
		}
		
		return new ResponseEntity<Elector>(obj, HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<Elector> registrar(@Valid @RequestBody Elector p) throws Exception{
		Elector obj = iElectorService.registrar(p);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getIdElector()).toUri();
		return ResponseEntity.created(location).build();
	}
	
	@PutMapping
	public ResponseEntity<Elector> modificar(@Valid @RequestBody Elector p) throws Exception{
		Elector obj = iElectorService.modificar(p);
		return new ResponseEntity<Elector>(obj, HttpStatus.OK);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> eliminar(@PathVariable("id") Integer id) throws Exception{
		Elector obj = iElectorService.listarPorId(id);
		
		if(obj.getIdElector() == null) {
			throw new ModeloNotFoundException("ID NO ENCONTRADO "+id);
		}
			
		iElectorService.eliminar(id);
		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}
	
	public ResponseEntity<Void> subir()throws Exception{
		Cloudinary cloudinary = new Cloudinary(ObjectUtils.asMap(
				"cloud_name", "gascarzah",
				"api_key", "138741554239847",
				"api_secret", "SjYSfV4HA5zQsKBfBWXz8zpErqU"));  
		
		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}
}
