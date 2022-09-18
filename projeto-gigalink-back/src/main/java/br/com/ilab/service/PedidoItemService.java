package br.com.ilab.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.ilab.model.Pedido;
import br.com.ilab.model.PedidoItem;
import br.com.ilab.model.Produto;
import br.com.ilab.repository.PedidoItemRepository;
import br.com.ilab.repository.PedidoRepository;
import br.com.ilab.repository.ProdutoRepository;
import javassist.NotFoundException;

@Service
public class PedidoItemService {
	
	@Autowired
	private PedidoItemRepository pedidoItemRepository;
	
	@Autowired
	private ProdutoRepository produtoRepository;
	
	@Autowired
	private PedidoRepository pedidoRepository;
	
	public List<PedidoItem> listar() {
        List<PedidoItem> pedidosItens = pedidoItemRepository.findAll();     
        return pedidosItens;
    }
	
	public Optional<PedidoItem> buscarId(Integer id) {
		Optional<PedidoItem> pedidoItem= pedidoItemRepository.findById(id);
		return pedidoItem;
	}
	
	public List<PedidoItem> listarPorIdPedido(Integer idPedido) {
        List<PedidoItem> pedidosItens = pedidoItemRepository.findByPedidoId(idPedido);     
        return pedidosItens;
    }
	
	public List<PedidoItem> inserir(@Valid List<PedidoItem> pedidosItens) {	
		
		Double valorTotal = 0.0d;
		
		Integer idPedido = pedidosItens.get(0).getPedido().getId();
		
		List<PedidoItem> pedItemInseridos = new ArrayList<PedidoItem>();
		
		
		for(PedidoItem pedItem: pedidosItens) {
			
			//Analisar regra de negocio p definir como o produto item fica no banco de dados
			
			PedidoItem newPedidoItem = new PedidoItem();
			Optional<Produto> produto = produtoRepository.findById(pedItem.getProduto().getId());
			
			newPedidoItem.setQuantidade(pedItem.getQuantidade());
			newPedidoItem.setPedido(pedItem.getPedido());
			newPedidoItem.setProduto(pedItem.getProduto());
			newPedidoItem.setSubTotal(produto.get().getPrecoUnit() * pedItem.getQuantidade());
			
			valorTotal += (produto.get().getPrecoUnit() * pedItem.getQuantidade());
			

			pedidoItemRepository.save(newPedidoItem);
			
			pedItemInseridos.add(newPedidoItem);
		}
		
		Optional<Pedido> pedido = pedidoRepository.findById(idPedido);
		
		if(pedido.isPresent()) {
			Double desconto;
			desconto = valorTotal * 0.15;
			pedido.get().setDesconto(desconto);
			pedido.get().setValorTotal(valorTotal + pedido.get().getFrete()- desconto);
			pedidoRepository.save(pedido.get());
		}
		
//		Optional<Produto> produto = produtoRepository.findById(pedidosItens.getProduto().getId());
//					
//		newPedidoItem.setQuantidade(pedidosItens.getQuantidade());
//		newPedidoItem.setPedido(pedidosItens.getPedido());
//		newPedidoItem.setProduto(pedidosItens.getProduto());
//		newPedidoItem.setSubTotal(produto.get().getPrecoUnit() * pedidosItens.getQuantidade());
//
//		pedidoItemRepository.save(newPedidoItem);
		
//		return newPedidoItem;
		
		return pedItemInseridos;
		
	}
	
	public PedidoItem atualizar(Integer id, PedidoItem pedidoItem) throws IOException, NotFoundException {
		
		Optional<Produto> produto = produtoRepository.findById(pedidoItem.getProduto().getId());
		
		if (pedidoItemRepository.existsById(id)) {
			
			PedidoItem pedidoItemEditado = new PedidoItem();
			pedidoItemEditado.setId(id);
			pedidoItemEditado.setQuantidade(pedidoItem.getQuantidade());
			pedidoItemEditado.setPedido(pedidoItem.getPedido());
			pedidoItemEditado.setProduto(pedidoItem.getProduto());
//			newPedidoItem.setSubTotal(pedidoItem.getProduto().getPrecoUnit() * pedidoItem.getQuantidade());
			pedidoItemEditado.setSubTotal(produto.get().getPrecoUnit() * pedidoItem.getQuantidade());
			
			pedidoItemRepository.save(pedidoItemEditado);
			return pedidoItemRepository.save(pedidoItemEditado);
			
	}
	throw new NotFoundException("id");
	}
	public void deletar(Integer id) throws NotFoundException  {
		if (pedidoItemRepository.existsById(id)) {
			pedidoItemRepository.deleteById(id);
		}else {
		throw new NotFoundException("id");
		}
	}

	

}
