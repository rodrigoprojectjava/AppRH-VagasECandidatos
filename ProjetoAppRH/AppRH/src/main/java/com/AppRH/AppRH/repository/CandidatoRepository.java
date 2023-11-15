package com.AppRH.AppRH.repository;

import org.springframework.data.repository.CrudRepository;

import com.AppRH.AppRH.models.Candidato;
import com.AppRH.AppRH.models.Vaga;
import java.util.List;


public interface CandidatoRepository extends CrudRepository<Candidato, Long>{
	
	Iterable<Candidato>findByVaga(Vaga vaga);
	
	Candidato findByRg(String rg);
	
	Candidato findById(long id);
	
	List<Candidato>findByNomeCandidato(String nomeCandidato);
}
