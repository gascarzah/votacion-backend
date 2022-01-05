package com.gafahtec.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
public class Candidato {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idCandidato;

	@ManyToOne
	@JoinColumn(name = "id_proceso_electoral", nullable = false, foreignKey = @ForeignKey(name = "FK_candidato_procesoElectoral"))
	private ProcesoElectoral procesoElectoral;
	@ManyToOne
	@JoinColumn(name = "id_postulante", nullable = false, foreignKey = @ForeignKey(name = "FK_candidato_postulante"))
	private Postulante postulante;
	
	@Schema(description = "titulo del padron")
	@Size(min = 10, message = "{titulo.size}")
	@Column(name = "titulo", nullable = false, length = 100)
	private String titulo;

	@Schema(description = "nombre del padron")
	@Size(min = 10, message = "{nombre.size}")
	@Column(name = "nombre", nullable = false, length = 100)
	private String nombre;
	
	@Column(name = "foto", nullable = false, length = 100)
	private String foto;
	
	@Column(name = "activo", nullable = false)
	private boolean activo;

	@OneToMany(mappedBy = "candidato", cascade = { CascadeType.ALL }, orphanRemoval = true)
	private List<Voto> votos ;
}
