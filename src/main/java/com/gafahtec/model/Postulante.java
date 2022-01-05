package com.gafahtec.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Table
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Postulante {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idPostulante;
	
	@Schema(description = "nombre del candidato")
	@Size(min = 10, message = "{nombre.size}")
	@Column(name = "nombre", nullable = false, length = 70)
	private String nombre;
	
	@Schema(description = "descripcion del candidato")
	@Size(min = 10, message = "{descripcion.size}")
	@Column(name = "descripcion", nullable = false, length = 70)
	private String descripcion;
	
	@Schema(description = "foto del candidato")
	@Column(name = "url_foto", nullable = false, length = 70)
	private String urlFoto;
	
	@OneToMany(mappedBy = "postulante", cascade = { CascadeType.ALL }, orphanRemoval = true)
	private List<Candidato> candidatos ;
}
