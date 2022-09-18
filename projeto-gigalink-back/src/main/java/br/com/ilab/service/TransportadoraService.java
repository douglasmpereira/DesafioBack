package br.com.ilab.service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import br.com.ilab.model.Transportadora;
import br.com.ilab.repository.TransportadoraRepository;
import javassist.NotFoundException;

@Service
public class TransportadoraService {
	
	@Autowired
	private TransportadoraRepository transportadoraRepository;
	
	public List<Transportadora> listar() {
        List<Transportadora> transportadoras = transportadoraRepository.findAll();     
        return transportadoras;
    }
	
	public Optional<Transportadora> buscarId(Integer id) {
		Optional<Transportadora> transportadora= transportadoraRepository.findById(id);
		return transportadora;
	}
	public Transportadora inserir(Transportadora transportadora) {	
		
		Transportadora novaTransp = new Transportadora();
		novaTransp.setNome(transportadora.getNome());
		novaTransp.setPrecoPorKm(transportadora.getPrecoPorKm());
		novaTransp.setTelefone(transportadora.getTelefone());
		
		transportadoraRepository.save(novaTransp);
		
		return novaTransp;
	}
	public Transportadora atualizar(Integer id, Transportadora transportadora) throws IOException, NotFoundException {
		
		if (transportadoraRepository.existsById(id)) {
			Transportadora transpEditada = new Transportadora();
			transpEditada.setId(id);
			transpEditada.setNome(transportadora.getNome());
			transpEditada.setPrecoPorKm(transportadora.getPrecoPorKm());
			transpEditada.setTelefone(transportadora.getTelefone());
		
			return transportadoraRepository.save(transpEditada);
			
		}
		throw new NotFoundException("id");
		}
	
		public void deletar(Integer id) throws NotFoundException  {
			if (transportadoraRepository.existsById(id)) {
				transportadoraRepository.deleteById(id);
			}else {
			throw new NotFoundException("id");
			}
		}
	
}
