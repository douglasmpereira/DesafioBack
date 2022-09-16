package br.com.ilab.controller;

import java.io.IOException;
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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.ilab.model.Fornecedor;
import br.com.ilab.model.Transportadora;
import br.com.ilab.service.FornecedorService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import javassist.NotFoundException;

@CrossOrigin(origins = "", allowedHeaders = "")
@RestController
@RequestMapping("/fornecedores")
public class FornecedorController {
	
	@Autowired
	private FornecedorService fornecedorService;
	
	@ApiOperation(value = "Listar todos os fornecedores", notes = "Listagem de fornecedores")
	@ApiResponses(value = { 
		@ApiResponse(code = 200, message = "Retorna todos os fornecedores"),
		@ApiResponse(code = 401, message = "Erro de autenticação"),
		@ApiResponse(code = 403, message = "Recurso proibido"),
		@ApiResponse(code = 404, message = "Recurso não encontrado"),
		@ApiResponse(code = 500, message = "Erro de servidor") })
	@GetMapping
	public ResponseEntity<List<Fornecedor>> listar() {
		List<Fornecedor> listaFornecedores = fornecedorService.listar();
		
		return ResponseEntity.ok(listaFornecedores);
	}
	
	@GetMapping("/{id}")
	@ApiOperation(value = "Obter fornecedor por ID", notes = "Busca de um fornecedor pelo nº do ID")
	@ApiResponses(value = { 
		@ApiResponse(code = 200, message = "Retorna um fornecedor"),
		@ApiResponse(code = 401, message = "Erro de autenticação"),
		@ApiResponse(code = 403, message = "Recurso proibido"),
		@ApiResponse(code = 404, message = "Recurso não encontrado"),
		@ApiResponse(code = 500, message = "Erro de servidor") })
	public ResponseEntity<Optional<Fornecedor>> obterPorId(@PathVariable Integer id) throws NotFoundException {
		if (fornecedorService.buscarId(id) != null) {
			return ResponseEntity.ok(fornecedorService.buscarId(id));
		}
		return ResponseEntity.notFound().build();
	}
	
	@PostMapping("/adicionar")
	@ResponseStatus(HttpStatus.CREATED)
	@ApiOperation(value = "Inserir fornecedor", notes = "Inserção")
	@ApiResponses(value = { 
		@ApiResponse(code = 201, message = "Ok (um fornecedor foi inserido)"),
		@ApiResponse(code = 400, message = "Dados inválidos"),		
		@ApiResponse(code = 401, message = "Erro de autenticação/Não autorizado"),
		@ApiResponse(code = 403, message = "Recurso proibido"),
		@ApiResponse(code = 404, message = "Recurso não encontrado"),
		@ApiResponse(code = 500, message = "Erro de servidor/Método não permitido") })
	public Fornecedor inserir(@Valid @RequestBody Fornecedor fornecedor) {
		return fornecedorService.inserir(fornecedor);
	}
	
	@PutMapping("/{id}")
	@ApiOperation(value = "Atualizar um fornecedor por ID", notes = "Atualização de fornecedor pelo nº do ID")
	@ApiResponses(value = { 
		@ApiResponse(code = 200, message = "Atualiza um fornecedor"),
		@ApiResponse(code = 401, message = "Erro de autenticação"),
		@ApiResponse(code = 403, message = "Recurso proibido"),
		@ApiResponse(code = 404, message = "Recurso não encontrado"),
		@ApiResponse(code = 500, message = "Erro de servidor") })
	public ResponseEntity<Object> atualizar(@PathVariable Integer id,
			@Valid @RequestBody Fornecedor fornecedorRequest) throws NotFoundException {
		
		try {
				Fornecedor fornecedor = fornecedorService.atualizar(id, fornecedorRequest);
			if (fornecedor != null && fornecedor.getId() != null) {;
				
				return ResponseEntity.ok(fornecedor);
			}
		} catch (IOException e) {
			return ResponseEntity.unprocessableEntity().body(e.getMessage());
		}
		return ResponseEntity.notFound().build();
	}
	
	@DeleteMapping("/{id}")
	@ApiOperation(value = "Deletar fornecedor por id", notes = "Exclusão")
	@ApiResponses(value = { 
		@ApiResponse(code = 204, message = "Nenhum resultado (a exclusão da fornecedor foi feita, não tem nenhum conteúdo para retornar)"),
		@ApiResponse(code = 400, message = "Dados inválidos"),		
		@ApiResponse(code = 401, message = "Erro de autenticação/Não autorizado"),
		@ApiResponse(code = 403, message = "Recurso proibido"),
		@ApiResponse(code = 404, message = "Recurso não encontrado"),
		@ApiResponse(code = 500, message = "Erro de servidor/Método não permitido") })
	public ResponseEntity<Void> deletar(@PathVariable Integer id) throws NotFoundException {
		if(fornecedorService.buscarId(id) != null) {
			fornecedorService.deletar(id);
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.notFound().build();
	}


}
