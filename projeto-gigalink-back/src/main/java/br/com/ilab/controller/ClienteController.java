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

import br.com.ilab.model.Cliente;
import br.com.ilab.service.ClienteService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import javassist.NotFoundException;

@CrossOrigin(origins = "", allowedHeaders = "")
@RestController
@RequestMapping("/clientes")
public class ClienteController {
	
	@Autowired
	private ClienteService clienteService;
	
	
	@ApiOperation(value = "Listar todos os clientes", notes = "Listagem de clientes")
	@ApiResponses(value = { 
		@ApiResponse(code = 200, message = "Retorna todos os clientes"),
		@ApiResponse(code = 401, message = "Erro de autenticação"),
		@ApiResponse(code = 403, message = "Recurso proibido"),
		@ApiResponse(code = 404, message = "Recurso não encontrado"),
		@ApiResponse(code = 500, message = "Erro de servidor") })
	@GetMapping
	public ResponseEntity<List<Cliente>> listar() {
		return ResponseEntity.ok(clienteService.listar());
	}
	
	@GetMapping("/{id}")
	@ApiOperation(value = "Obter cliente por ID", notes = "Busca de um cliente pelo nº do ID")
	@ApiResponses(value = { 
		@ApiResponse(code = 200, message = "Retorna um cliente"),
		@ApiResponse(code = 401, message = "Erro de autenticação"),
		@ApiResponse(code = 403, message = "Recurso proibido"),
		@ApiResponse(code = 404, message = "Recurso não encontrado"),
		@ApiResponse(code = 500, message = "Erro de servidor") })
	public ResponseEntity<Optional<Cliente>> obterPorId(@PathVariable Integer id) throws NotFoundException {
		if (clienteService.buscarId(id) != null) {
			return ResponseEntity.ok(clienteService.buscarId(id));
		}
		return ResponseEntity.notFound().build();
	}
	
	@PostMapping("/adicionar")
	@ResponseStatus(HttpStatus.CREATED)
	@ApiOperation(value = "Inserir cliente", notes = "Inserção")
	@ApiResponses(value = { 
		@ApiResponse(code = 201, message = "Ok (um cliente foi inserido)"),
		@ApiResponse(code = 400, message = "Dados inválidos"),		
		@ApiResponse(code = 401, message = "Erro de autenticação/Não autorizado"),
		@ApiResponse(code = 403, message = "Recurso proibido"),
		@ApiResponse(code = 404, message = "Recurso não encontrado"),
		@ApiResponse(code = 500, message = "Erro de servidor/Método não permitido") })
	public Cliente inserir(@Valid @RequestBody Cliente cliente) {
		return clienteService.inserir(cliente);
	}
	
	@DeleteMapping("/{id}")
	@ApiOperation(value = "Deletar o cliente por id", notes = "Exclusão")
	@ApiResponses(value = { 
		@ApiResponse(code = 204, message = "Nenhum resultado (a exclusão do cliente foi feita, não tem nenhum conteúdo para retornar)"),
		@ApiResponse(code = 400, message = "Dados inválidos"),		
		@ApiResponse(code = 401, message = "Erro de autenticação/Não autorizado"),
		@ApiResponse(code = 403, message = "Recurso proibido"),
		@ApiResponse(code = 404, message = "Recurso não encontrado"),
		@ApiResponse(code = 500, message = "Erro de servidor/Método não permitido") })
	public ResponseEntity<Void> deletar(@PathVariable Integer id) throws NotFoundException {
		if(clienteService.buscarId(id) != null) {
			clienteService.deletar(id);
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.notFound().build();
	}

}
