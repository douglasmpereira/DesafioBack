package br.com.ilab.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.ilab.model.Email;

public interface EmailRepository extends JpaRepository<Email, Integer> {
	
	public List<Email> findByFornecedorId(Integer idFornecedor);

}
