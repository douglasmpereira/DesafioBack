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

import br.com.ilab.model.Produto;
import br.com.ilab.service.ProdutoService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import javassist.NotFoundException;

@CrossOrigin(origins = "", allowedHeaders = "")
@RestController
@RequestMapping("/produtos")
public class ProdutoController {
	
	@Autowired
	private ProdutoService produtoService;
	
	@ApiOperation(value = "Listar todos os produtos", notes = "Listagem de produtos")
	@ApiResponses(value = { 
		@ApiResponse(code = 200, message = "Retorna todos os produtos"),
		@ApiResponse(code = 401, message = "Erro de autenticação"),
		@ApiResponse(code = 403, message = "Recurso proibido"),
		@ApiResponse(code = 404, message = "Recurso não encontrado"),
		@ApiResponse(code = 500, message = "Erro de servidor") })
	@GetMapping
	public ResponseEntity<List<Produto>> listar() {
		List<Produto> listaProdutos = produtoService.listar();
		
		return ResponseEntity.ok(listaProdutos );
	}
	
	@GetMapping("/{id}")
	@ApiOperation(value = "Obter produto por ID", notes = "Busca de um produto pelo nº do ID")
	@ApiResponses(value = { 
		@ApiResponse(code = 200, message = "Retorna um produto"),
		@ApiResponse(code = 401, message = "Erro de autenticação"),
		@ApiResponse(code = 403, message = "Recurso proibido"),
		@ApiResponse(code = 404, message = "Recurso não encontrado"),
		@ApiResponse(code = 500, message = "Erro de servidor") })
	public ResponseEntity<Optional<Produto>> obterPorId(@PathVariable Integer id) throws NotFoundException {
		if (produtoService.buscarId(id) != null) {
			return ResponseEntity.ok(produtoService.buscarId(id));
		}
		return ResponseEntity.notFound().build();
	}
	
	@PostMapping("/adicionar")
	@ResponseStatus(HttpStatus.CREATED)
	@ApiOperation(value = "Inserir produto", notes = "Inserção")
	@ApiResponses(value = { 
		@ApiResponse(code = 201, message = "Ok (um produto foi inserido)"),
		@ApiResponse(code = 400, message = "Dados inválidos"),		
		@ApiResponse(code = 401, message = "Erro de autenticação/Não autorizado"),
		@ApiResponse(code = 403, message = "Recurso proibido"),
		@ApiResponse(code = 404, message = "Recurso não encontrado"),
		@ApiResponse(code = 500, message = "Erro de servidor/Método não permitido") })
	public Produto inserir(@Valid @RequestBody Produto produto) {
		
		System.out.println("produtoc"+produto);
		return produtoService.inserir(produto);
	}
	
	@PutMapping("/{id}")
	@ApiOperation(value = "Atualizar um produto por ID", notes = "Atualização de produto  pelo nº do ID")
	@ApiResponses(value = { 
		@ApiResponse(code = 200, message = "Atualiza um produto "),
		@ApiResponse(code = 401, message = "Erro de autenticação"),
		@ApiResponse(code = 403, message = "Recurso proibido"),
		@ApiResponse(code = 404, message = "Recurso não encontrado"),
		@ApiResponse(code = 500, message = "Erro de servidor") })
	public ResponseEntity<Object> atualizar(@PathVariable Integer id,
			@Valid @RequestBody Produto produtoRequest) throws NotFoundException {
		
		try {
			Produto produto  = produtoService.atualizar(id, produtoRequest);
			if (produto != null && produto .getId() != null) {;
				
				return ResponseEntity.ok(produto);
			}
		} catch (IOException e) {
			return ResponseEntity.unprocessableEntity().body(e.getMessage());
		}
		return ResponseEntity.notFound().build();
	}
	
	@DeleteMapping("/{id}")
	@ApiOperation(value = "Deletar produto por id", notes = "Exclusão")
	@ApiResponses(value = { 
		@ApiResponse(code = 204, message = "Nenhum resultado (a exclusão da produto foi feita, não tem nenhum conteúdo para retornar)"),
		@ApiResponse(code = 400, message = "Dados inválidos"),		
		@ApiResponse(code = 401, message = "Erro de autenticação/Não autorizado"),
		@ApiResponse(code = 403, message = "Recurso proibido"),
		@ApiResponse(code = 404, message = "Recurso não encontrado"),
		@ApiResponse(code = 500, message = "Erro de servidor/Método não permitido") })
	public ResponseEntity<Void> deletar(@PathVariable Integer id) throws NotFoundException {
		if(produtoService.buscarId(id) != null) {
			produtoService.deletar(id);
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.notFound().build();
	}

}
