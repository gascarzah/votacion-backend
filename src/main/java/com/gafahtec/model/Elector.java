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
import javax.validation.constraints.Email;
import javax.validation.constraints.Pattern;

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
public class Elector {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idElector;
	
	@Email(message = "Email formato incorrecto")
	@Pattern(regexp = "^[a-zA-Z0-9_+&*-]+(?:\\\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\\\.)+[a-zA-Z]{2,7}$")
	@Column(name = "email", nullable = true, length = 55)
	private String email;
	
	@OneToMany(mappedBy = "elector", cascade = { CascadeType.ALL }, orphanRemoval = true)
	private List<Voto> votos ;
}
