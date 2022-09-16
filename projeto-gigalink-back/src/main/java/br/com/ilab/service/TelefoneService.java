package br.com.ilab.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.ilab.model.Telefone;
import br.com.ilab.repository.TelefoneRepository;
import javassist.NotFoundException;

@Service
public class TelefoneService {
	
	@Autowired
	private TelefoneRepository telefoneRepository;
	
	public List<Telefone> listar() {
        List<Telefone> telefones = telefoneRepository.findAll();     
        return telefones;
    }
	
	public Optional<Telefone> buscarId(Integer id) {
		Optional<Telefone> telefone = telefoneRepository.findById(id);
		return telefone;
	}
	
	public List<Telefone> listarPorIdFornecedor(Integer idTelefone) {
        List<Telefone> telefones = telefoneRepository.findByFornecedorId(idTelefone);     
        return telefones;
    }
	
	public Telefone inserir(Telefone telefone) {	
		
		Telefone newTelefone = new Telefone();
		newTelefone.setDdd(telefone.getDdd());
		newTelefone.setNumero(telefone.getNumero());
		newTelefone.setFornecedor(telefone.getFornecedor());
		
		telefoneRepository.save(newTelefone);
		
		return newTelefone;
	}
	
	public void deletar(Integer id) throws NotFoundException  {
		if (telefoneRepository.existsById(id)) {
			telefoneRepository.deleteById(id);
		}else {
		throw new NotFoundException("id");
		}
	}
}
