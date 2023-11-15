package com.AppRH.AppRH.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@Entity
public class Candidato {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@Column (unique = true) 
	private String rg;
	
	@NotEmpty
	private String nomeCandidato;
	
	@NotEmpty @Email
	private String email;
	
	@ManyToOne
	@JoinColumn(name = "codigo", nullable=false)
	private Vaga vaga;

	
}
