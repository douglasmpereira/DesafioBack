package br.com.ilab.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.ilab.model.Transportadora;


@Repository
public interface TransportadoraRepository extends JpaRepository<Transportadora, Integer> {
	
	

}
