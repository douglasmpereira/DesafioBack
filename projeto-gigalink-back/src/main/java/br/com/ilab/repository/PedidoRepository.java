package br.com.ilab.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.ilab.model.Pedido;

public interface PedidoRepository extends JpaRepository<Pedido, Integer>  {
		

}
