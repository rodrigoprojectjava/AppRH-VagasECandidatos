package com.AppRH.AppRH.models;

import java.io.Serializable;
import java.util.List;

import org.hibernate.annotations.ValueGenerationType;
import org.hibernate.validator.constraints.br.CPF;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Entity
@Data
public class Funcionario implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@NotEmpty
	private String nome;
	
	@NotEmpty
	private String data;
	
	@Email @NotNull
	private String email;

	
	@OneToMany(mappedBy = "funcionario", cascade = CascadeType.REMOVE)
	private List<Dependentes>dependentes;
}
