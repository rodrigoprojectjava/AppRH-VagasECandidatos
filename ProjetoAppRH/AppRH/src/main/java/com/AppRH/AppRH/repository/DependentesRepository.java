package com.AppRH.AppRH.repository;

import org.springframework.data.repository.CrudRepository;

import com.AppRH.AppRH.models.Dependentes;
import com.AppRH.AppRH.models.Funcionario;

import java.util.List;

public interface DependentesRepository extends CrudRepository<Dependentes, Long>{
	
	Iterable<Dependentes> findByFuncionario(Funcionario funcionario);
	
	//PENSANDO NO METODO DELETE
	Dependentes findByCpf(String cpf);
	Dependentes findById(long id);
	
	//CRIADO PARA IMPLEMENTAR
	List<Dependentes> findByNome(String nome);
}
