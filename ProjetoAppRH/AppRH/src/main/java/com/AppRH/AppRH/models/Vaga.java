package com.AppRH.AppRH.models;

import java.io.Serializable;
import java.util.List;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Entity
@Data
public class Vaga implements Serializable {

	private static final long serialVersionUID =1L;
	
	@Id 
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	private long codigo;
	
	@NotEmpty
	private String nome;
	
	@NotEmpty
	private String descricao;
	
	@NotEmpty
	private String data;
	
	@NotEmpty
	private String salario;
	
	@OneToMany(mappedBy = "vaga", cascade = CascadeType.REMOVE) //quando deletar uma vaga tbm deleta candidato
	private List<Candidato> candidatos;

	
	
}
