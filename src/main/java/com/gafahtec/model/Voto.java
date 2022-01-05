package com.gafahtec.model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

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
public class Voto {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idVoto;
	
	@ManyToOne
	@JoinColumn(name = "id_candidato", nullable = false, foreignKey = @ForeignKey(name = "FK_padron_voto"))
	private Candidato candidato;
	
	@ManyToOne
	@JoinColumn(name = "id_elector", nullable = false, foreignKey = @ForeignKey(name = "FK_padron_elector"))
	private Elector elector;
	
	@Column(name = "marca", nullable = false)
	private Integer marca;
	@Column(name = "fecha", nullable = false)
	private LocalDateTime fecha;
}
