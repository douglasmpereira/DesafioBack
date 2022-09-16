package br.com.ilab.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.ilab.model.Fornecedor;

public interface FornecedorRepository extends JpaRepository<Fornecedor, Integer> {

}
