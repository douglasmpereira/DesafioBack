package br.com.ilab.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;


import br.com.ilab.model.PedidoItem;



public interface PedidoItemRepository extends JpaRepository<PedidoItem, Integer> {
	
	public List<PedidoItem> findByPedidoId(Integer idPedido);

}
