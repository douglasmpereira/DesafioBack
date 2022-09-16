package br.com.ilab.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.ilab.model.Email;
import br.com.ilab.repository.EmailRepository;
import javassist.NotFoundException;

@Service
public class EmailService {
	
		@Autowired
		private EmailRepository emailRepository;
		
		
		public List<Email> listar() {
	        List<Email> fornecedores = emailRepository.findAll();     
	        return fornecedores;
	    }
		
		public Optional<Email> buscarId(Integer id) {
			Optional<Email> email = emailRepository.findById(id);
			return email;
		}
		
		public List<Email> listarPorIdFornecedor(Integer idFornecedor) {
	        List<Email> emails = emailRepository.findByFornecedorId(idFornecedor);     
	        return emails;
	    }
		
		public Email inserir(Email email) {	
			
			Email newEmail = new Email();
			newEmail.setEmail(email.getEmail());
			newEmail.setFornecedor(email.getFornecedor());
			
			emailRepository.save(newEmail);
			
			return newEmail;
		}
		
		public void deletar(Integer id) throws NotFoundException  {
			if (emailRepository.existsById(id)) {
				emailRepository.deleteById(id);
			}else {
			throw new NotFoundException("id");
			}
		}

}
