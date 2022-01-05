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
public class ProcesoElectoral {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idProcesoElectoral;
	
	@Schema(description = "titulo del proceso")
	@Size(min = 10, message = "{nombre.size}")
	@Column(name = "nombre", nullable = false, length = 100)
	private String nombre;
	
	
	@Column(name = "activo", nullable = false)
	private boolean activo;

	@OneToMany(mappedBy = "procesoElectoral", cascade = { CascadeType.ALL }, orphanRemoval = true)
	private List<Candidato> candidatos ;
}
