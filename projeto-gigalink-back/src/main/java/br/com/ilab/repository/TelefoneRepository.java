package br.com.ilab.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.ilab.model.Telefone;


public interface TelefoneRepository extends JpaRepository<Telefone, Integer> {
	
	public List<Telefone> findByFornecedorId(Integer idTelefone);
}
