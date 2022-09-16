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

import br.com.ilab.model.Transportadora;
import br.com.ilab.service.TransportadoraService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import javassist.NotFoundException;

@CrossOrigin(origins = "", allowedHeaders = "")
@RestController
@RequestMapping("/transportadoras")
public class TransportadoraController {
	
	@Autowired
	private TransportadoraService transportadoraService;
	
	@ApiOperation(value = "Listar todos os transportadoras", notes = "Listagem de transportadoras")
	@ApiResponses(value = { 
		@ApiResponse(code = 200, message = "Retorna todos os transportadoras"),
		@ApiResponse(code = 401, message = "Erro de autenticação"),
		@ApiResponse(code = 403, message = "Recurso proibido"),
		@ApiResponse(code = 404, message = "Recurso não encontrado"),
		@ApiResponse(code = 500, message = "Erro de servidor") })
	@GetMapping
	public ResponseEntity<List<Transportadora>> listar() {
		return ResponseEntity.ok(transportadoraService.listar());
	}
	
	@GetMapping("/{id}")
	@ApiOperation(value = "Obter transportadora por ID", notes = "Busca de uma transportadora pelo nº do ID")
	@ApiResponses(value = { 
		@ApiResponse(code = 200, message = "Retorna uma transportadora"),
		@ApiResponse(code = 401, message = "Erro de autenticação"),
		@ApiResponse(code = 403, message = "Recurso proibido"),
		@ApiResponse(code = 404, message = "Recurso não encontrado"),
		@ApiResponse(code = 500, message = "Erro de servidor") })
	public ResponseEntity<Optional<Transportadora>> obterPorId(@PathVariable Integer id) throws NotFoundException {
		if (transportadoraService.buscarId(id) != null) {
			return ResponseEntity.ok(transportadoraService.buscarId(id));
		}
		return ResponseEntity.notFound().build();
	}
	
	@PostMapping("/adicionar")
	@ResponseStatus(HttpStatus.CREATED)
	@ApiOperation(value = "Inserir transportadora", notes = "Inserção")
	@ApiResponses(value = { 
		@ApiResponse(code = 201, message = "Ok (uma transportadora foi inserida)"),
		@ApiResponse(code = 400, message = "Dados inválidos"),		
		@ApiResponse(code = 401, message = "Erro de autenticação/Não autorizado"),
		@ApiResponse(code = 403, message = "Recurso proibido"),
		@ApiResponse(code = 404, message = "Recurso não encontrado"),
		@ApiResponse(code = 500, message = "Erro de servidor/Método não permitido") })
	public Transportadora inserir(@Valid @RequestBody Transportadora transportadora) {
		return transportadoraService.inserir(transportadora);
	}
	
	@PutMapping("/{id}")
	@ApiOperation(value = "Atualizar uma transportadora por ID", notes = "Atualização de um cliente pelo nº do ID")
	@ApiResponses(value = { 
		@ApiResponse(code = 200, message = "Atualiza uma transportadora"),
		@ApiResponse(code = 401, message = "Erro de autenticação"),
		@ApiResponse(code = 403, message = "Recurso proibido"),
		@ApiResponse(code = 404, message = "Recurso não encontrado"),
		@ApiResponse(code = 500, message = "Erro de servidor") })
	public ResponseEntity<Object> atualizar(@PathVariable Integer id,
			@Valid @RequestBody Transportadora transportadoraRequest) throws NotFoundException {
		
		
		try {
				Transportadora transportadora = transportadoraService.atualizar(id, transportadoraRequest);
			if (transportadora != null && transportadora.getId() != null) {;
				
				return ResponseEntity.ok(transportadora);
			}
		} catch (IOException e) {
			return ResponseEntity.unprocessableEntity().body(e.getMessage());
		}
		return ResponseEntity.notFound().build();
	}
	
	@DeleteMapping("/{id}")
	@ApiOperation(value = "Deletar transportadora por id", notes = "Exclusão")
	@ApiResponses(value = { 
		@ApiResponse(code = 204, message = "Nenhum resultado (a exclusão da transportadora foi feita, não tem nenhum conteúdo para retornar)"),
		@ApiResponse(code = 400, message = "Dados inválidos"),		
		@ApiResponse(code = 401, message = "Erro de autenticação/Não autorizado"),
		@ApiResponse(code = 403, message = "Recurso proibido"),
		@ApiResponse(code = 404, message = "Recurso não encontrado"),
		@ApiResponse(code = 500, message = "Erro de servidor/Método não permitido") })
	public ResponseEntity<Void> deletar(@PathVariable Integer id) throws NotFoundException {
		
		System.out.println("antes do if"+id);
		if(transportadoraService.buscarId(id) != null) {
			System.out.println("depois do if"+id);
			transportadoraService.deletar(id);
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.notFound().build();
	}

}
