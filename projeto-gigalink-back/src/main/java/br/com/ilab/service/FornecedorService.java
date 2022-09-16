package br.com.ilab.service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.ilab.model.Fornecedor;
import br.com.ilab.model.Transportadora;
import br.com.ilab.repository.FornecedorRepository;
import br.com.ilab.repository.TelefoneRepository;
import javassist.NotFoundException;

@Service
public class FornecedorService {
	
	@Autowired
	FornecedorRepository fornecedorRepository;
	
	@Autowired
	TelefoneRepository telefoneRepository;
	
	public List<Fornecedor> listar() {
        List<Fornecedor> fornecedores = fornecedorRepository.findAll();     
        return fornecedores;
    }
	
	public Optional<Fornecedor> buscarId(Integer id) {
		Optional<Fornecedor> fornecedor= fornecedorRepository.findById(id);
		return fornecedor;
	}
	
	public Fornecedor inserir(Fornecedor fornecedor) {	
		
		if(fornecedor.getTelefone().isEmpty()) {
			
			 System.out.println("Telefone vazio, não pode adicionar um fornecedor!");
			return null ;
		}
		
		Fornecedor newFornecedor = new Fornecedor();
		newFornecedor.setNome(fornecedor.getNome());
		newFornecedor.setDescricao(fornecedor.getDescricao());
		newFornecedor.setCidade(fornecedor.getCidade());
		newFornecedor.setEndereco(fornecedor.getEndereco());
		newFornecedor.setBairro(fornecedor.getBairro());
		newFornecedor.setNumero(fornecedor.getNumero());
		
		fornecedorRepository.save(newFornecedor);
		telefoneRepository.save(fornecedor.getTelefone().iterator().next());
		
		return newFornecedor;
	}
	
	public Fornecedor atualizar(Integer id, Fornecedor fornecedor) throws IOException, NotFoundException {
			
			if (fornecedorRepository.existsById(id)) {
				Fornecedor fornecedorEdit = new Fornecedor();
				fornecedorEdit.setId(id);
				fornecedorEdit.setNome(fornecedor.getNome());
				fornecedorEdit.setDescricao(fornecedor.getDescricao());
				fornecedorEdit.setCidade(fornecedor.getCidade());
				fornecedorEdit.setEndereco(fornecedor.getEndereco());
				fornecedorEdit.setBairro(fornecedor.getBairro());
				fornecedorEdit.setNumero(fornecedor.getNumero());
				
				return fornecedorRepository.save(fornecedorEdit);
				
		}
		throw new NotFoundException("id");
	}
	
	public void deletar(Integer id) throws NotFoundException  {
		if (fornecedorRepository.existsById(id)) {
			fornecedorRepository.deleteById(id);
		}else {
		throw new NotFoundException("id");
		}
	}
}
