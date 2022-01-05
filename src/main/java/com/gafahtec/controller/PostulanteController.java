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
import com.gafahtec.model.Postulante;
import com.gafahtec.service.IPostulanteService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/postulantes")
@AllArgsConstructor
public class PostulanteController {
	
private IPostulanteService iPostulanteService;
	
	@GetMapping
	public ResponseEntity<List<Postulante>> listar() throws Exception{
		List<Postulante> lista = iPostulanteService.listar();
		return new ResponseEntity<List<Postulante>>(lista, HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Postulante> listarPorId(@PathVariable("id") Integer id) throws Exception{
		Postulante obj = iPostulanteService.listarPorId(id);
		
		if(obj.getIdPostulante() == null) {
			throw new ModeloNotFoundException("Id no encontrado " + id );
		}
		
		return new ResponseEntity<Postulante>(obj, HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<Postulante> registrar(@Valid @RequestBody Postulante p) throws Exception{
		Postulante obj = iPostulanteService.registrar(p);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getIdPostulante()).toUri();
		return ResponseEntity.created(location).build();
	}
	
	@PutMapping
	public ResponseEntity<Postulante> modificar(@Valid @RequestBody Postulante p) throws Exception{
		Postulante obj = iPostulanteService.modificar(p);
		return new ResponseEntity<Postulante>(obj, HttpStatus.OK);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> eliminar(@PathVariable("id") Integer id) throws Exception{
		Postulante obj = iPostulanteService.listarPorId(id);
		
		if(obj.getIdPostulante() == null) {
			throw new ModeloNotFoundException("ID NO ENCONTRADO "+id);
		}
			
		iPostulanteService.eliminar(id);
		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}
}
