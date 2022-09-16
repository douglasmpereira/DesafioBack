package br.com.ilab.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.ilab.model.Cliente;
import br.com.ilab.repository.ClienteRepository;
import javassist.NotFoundException;


@Service
public class ClienteService {
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	public List<Cliente> listar() {
        List<Cliente> clientes = clienteRepository.findAll();     
        return clientes;
    }
	
	public Optional<Cliente> buscarId(Integer id) {
		Optional<Cliente> cliente = clienteRepository.findById(id);
		return cliente;
	}
	
	public Cliente inserir(Cliente cliente) {	
			
		Cliente newCliente = new Cliente();
		newCliente.setNome(cliente.getNome());
		clienteRepository.save(newCliente);
		
		return newCliente;
	}
	
	public void deletar(Integer id) throws NotFoundException  {
		if (clienteRepository.existsById(id)) {
			clienteRepository.deleteById(id);
		}
		throw new NotFoundException("id");
	}
		
}

