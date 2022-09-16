package br.com.ilab.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.ilab.model.Email;
import br.com.ilab.model.Fornecedor;
import br.com.ilab.service.EmailService;
import br.com.ilab.service.FornecedorService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import javassist.NotFoundException;

@CrossOrigin(origins = "", allowedHeaders = "")
@RestController
@RequestMapping("/emails")
public class EmailController {
	
	@Autowired
	EmailService emailService;
	
	@Autowired
	FornecedorService fornecedorService;
	
	
	@ApiOperation(value = "Listar todos os emails", notes = "Listagem de emails")
	@ApiResponses(value = { 
		@ApiResponse(code = 200, message = "Retorna todos os emails"),
		@ApiResponse(code = 401, message = "Erro de autenticação"),
		@ApiResponse(code = 403, message = "Recurso proibido"),
		@ApiResponse(code = 404, message = "Recurso não encontrado"),
		@ApiResponse(code = 500, message = "Erro de servidor") })
	@GetMapping
	public ResponseEntity<List<Email>> listar() {
		return ResponseEntity.ok(emailService.listar());
	}
	
	@GetMapping("/fornecedor/{id}")
	@ApiOperation(value = "Obter email por id do fornecedor", notes = "Busca de um email pelo nº do ID do fornecedor")
	@ApiResponses(value = { 
		@ApiResponse(code = 200, message = "Retorna uma lista de emails"),
		@ApiResponse(code = 401, message = "Erro de autenticação"),
		@ApiResponse(code = 403, message = "Recurso proibido"),
		@ApiResponse(code = 404, message = "Recurso não encontrado"),
		@ApiResponse(code = 500, message = "Erro de servidor") })
	public  ResponseEntity<List<Email>>  listarPorFornecedorId(@PathVariable Integer id) throws NotFoundException {
		if (fornecedorService.buscarId(id) != null) {
			return ResponseEntity.ok(emailService.listarPorIdFornecedor(id));
		}
		return ResponseEntity.notFound().build();
	}
	
	@PostMapping("/adicionar")
	@ResponseStatus(HttpStatus.CREATED)
	@ApiOperation(value = "Inserir email", notes = "Inserção")
	@ApiResponses(value = { 
		@ApiResponse(code = 201, message = "Ok (um email foi inserido)"),
		@ApiResponse(code = 400, message = "Dados inválidos"),		
		@ApiResponse(code = 401, message = "Erro de autenticação/Não autorizado"),
		@ApiResponse(code = 403, message = "Recurso proibido"),
		@ApiResponse(code = 404, message = "Recurso não encontrado"),
		@ApiResponse(code = 500, message = "Erro de servidor/Método não permitido") })
	public Email inserir(@Valid @RequestBody Email email) {
		System.out.println("controler"+email);
		return emailService.inserir(email);
	}
	
	@DeleteMapping("/{id}")
	@ApiOperation(value = "Deletar email por id", notes = "Exclusão")
	@ApiResponses(value = { 
		@ApiResponse(code = 204, message = "Nenhum resultado (a exclusão do email foi feita, não tem nenhum conteúdo para retornar)"),
		@ApiResponse(code = 400, message = "Dados inválidos"),		
		@ApiResponse(code = 401, message = "Erro de autenticação/Não autorizado"),
		@ApiResponse(code = 403, message = "Recurso proibido"),
		@ApiResponse(code = 404, message = "Recurso não encontrado"),
		@ApiResponse(code = 500, message = "Erro de servidor/Método não permitido") })
	public ResponseEntity<Void> deletar(@PathVariable Integer id) throws NotFoundException {
		if(emailService.buscarId(id) != null) {
			emailService.deletar(id);
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.notFound().build();
	}
	

}