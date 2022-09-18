package br.com.ilab.service;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import br.com.ilab.model.Pedido;
import br.com.ilab.repository.PedidoRepository;
import javassist.NotFoundException;

@Service
public class PedidoService  {
	
	@Autowired
	private PedidoRepository pedidoRepository;
	
	@Autowired
	private PedidoItemService pedidoItemService;
	
	public List<Pedido> listar() {
        List<Pedido> pedidos = pedidoRepository.findAll();     
        return pedidos;
    }
	
	public Optional<Pedido> buscarId(Integer id) {
		Optional<Pedido> pedido = pedidoRepository.findById(id);
		return pedido;
	}
	
	public Pedido inserir(Pedido pedido) {	
		
//		Double vlTotal = 0.0;
			
		Pedido newPedido = new Pedido();
		newPedido.setDataHora(pedido.getDataHora());
		newPedido.setNotaFiscal(pedido.getNotaFiscal());
		newPedido.setFrete(pedido.getFrete());
		newPedido.setDesconto(pedido.getDesconto());
		newPedido.setTransportadora(pedido.getTransportadora());
		newPedido.setCliente(pedido.getCliente());
		
//		List<PedidoItem> pedidosItens = pedidoItemService.listarPorIdPedido(newPedido.getId());
//		
//		for (PedidoItem item : pedidosItens) {
//			vlTotal += item.getSubTotal();
//		}
//		System.out.println("valor total"+vlTotal);
//		System.out.println("valor total"+vlTotal);
//		newPedido.setValorTotal(vlTotal);

		pedidoRepository.save(newPedido);

		return newPedido;
	}
	
	public void deletar(Integer id) throws NotFoundException  {
		if (pedidoRepository.existsById(id)) {
			pedidoRepository.deleteById(id);
		}else {
		throw new NotFoundException("id");
		}
	}
		
	
}
