package br.com.ilab.controller;

import java.util.List;

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


import br.com.ilab.model.Telefone;
import br.com.ilab.service.FornecedorService;
import br.com.ilab.service.TelefoneService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import javassist.NotFoundException;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/telefones")
public class TelefoneController {
	
	@Autowired
	TelefoneService telefoneService;
	
	@Autowired
	FornecedorService fornecedorService;
	
	@ApiOperation(value = "Listar todos os telefones", notes = "Listagem de telefones")
	@ApiResponses(value = { 
		@ApiResponse(code = 200, message = "Retorna todos os telefones"),
		@ApiResponse(code = 401, message = "Erro de autenticação"),
		@ApiResponse(code = 403, message = "Recurso proibido"),
		@ApiResponse(code = 404, message = "Recurso não encontrado"),
		@ApiResponse(code = 500, message = "Erro de servidor") })
	@GetMapping
	public ResponseEntity<List<Telefone>> listar() {
		return ResponseEntity.ok(telefoneService.listar());
	}
	
	@GetMapping("/fornecedor/{id}")
	@ApiOperation(value = "Obter telefone por id do fornecedor", notes = "Busca de uma lista de telefones pelo nº do ID do fornecedor")
	@ApiResponses(value = { 
		@ApiResponse(code = 200, message = "Retorna uma lista de telefones"),
		@ApiResponse(code = 401, message = "Erro de autenticação"),
		@ApiResponse(code = 403, message = "Recurso proibido"),
		@ApiResponse(code = 404, message = "Recurso não encontrado"),
		@ApiResponse(code = 500, message = "Erro de servidor") })
	public  ResponseEntity<List<Telefone>>  listarPorFornecedorId(@PathVariable Integer id) throws NotFoundException {
		if (fornecedorService.buscarId(id) != null) {
			return ResponseEntity.ok(telefoneService.listarPorIdFornecedor(id));
		}
		return ResponseEntity.notFound().build();
	}
	
	@PostMapping("/adicionar")
	@ResponseStatus(HttpStatus.CREATED)
	@ApiOperation(value = "Inserir telefone", notes = "Inserção")
	@ApiResponses(value = { 
		@ApiResponse(code = 201, message = "Ok (um telefone foi inserido)"),
		@ApiResponse(code = 400, message = "Dados inválidos"),		
		@ApiResponse(code = 401, message = "Erro de autenticação/Não autorizado"),
		@ApiResponse(code = 403, message = "Recurso proibido"),
		@ApiResponse(code = 404, message = "Recurso não encontrado"),
		@ApiResponse(code = 500, message = "Erro de servidor/Método não permitido") })
	public Telefone inserir(@Valid @RequestBody Telefone telefone) {
		return telefoneService.inserir(telefone);
	}
	
	@DeleteMapping("/{id}")
	@ApiOperation(value = "Deletar telefone por id", notes = "Exclusão")
	@ApiResponses(value = { 
		@ApiResponse(code = 204, message = "Nenhum resultado (a exclusão do telefone foi feita, não tem nenhum conteúdo para retornar)"),
		@ApiResponse(code = 400, message = "Dados inválidos"),		
		@ApiResponse(code = 401, message = "Erro de autenticação/Não autorizado"),
		@ApiResponse(code = 403, message = "Recurso proibido"),
		@ApiResponse(code = 404, message = "Recurso não encontrado"),
		@ApiResponse(code = 500, message = "Erro de servidor/Método não permitido") })
	public ResponseEntity<Void> deletar(@PathVariable Integer id) throws NotFoundException {
		if(telefoneService.buscarId(id) != null) {
			telefoneService.deletar(id);
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.notFound().build();
	}

}
