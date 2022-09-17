package br.com.ilab.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.ilab.model.Produto;

public interface ProdutoRepository extends JpaRepository<Produto, Integer> {

}
