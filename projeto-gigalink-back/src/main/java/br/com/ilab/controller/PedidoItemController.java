package br.com.ilab.controller;

import java.io.IOException;
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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.ilab.model.PedidoItem;
import br.com.ilab.service.PedidoItemService;
import br.com.ilab.service.PedidoService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import javassist.NotFoundException;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/pedidosItens")
public class PedidoItemController {
	
	@Autowired
	private PedidoItemService pedidoItemService;
	
	@Autowired
	private PedidoService pedidoService;
	
	@ApiOperation(value = "Listar todos os pedidoItem", notes = "Listagem de pedidoItem")
	@ApiResponses(value = { 
		@ApiResponse(code = 200, message = "Retorna todos os pedidoItem"),
		@ApiResponse(code = 401, message = "Erro de autenticação"),
		@ApiResponse(code = 403, message = "Recurso proibido"),
		@ApiResponse(code = 404, message = "Recurso não encontrado"),
		@ApiResponse(code = 500, message = "Erro de servidor") })
	@GetMapping
	public ResponseEntity<List<PedidoItem>> listar() {
		return ResponseEntity.ok(pedidoItemService.listar());
	}
	
	@GetMapping("/pedido/{id}")
	@ApiOperation(value = "Obter pedidosItens por id do fornecedor", notes = "Busca de um pedidosItens pelo nº do ID do fornecedor")
	@ApiResponses(value = { 
		@ApiResponse(code = 200, message = "Retorna uma lista de pedidosItens"),
		@ApiResponse(code = 401, message = "Erro de autenticação"),
		@ApiResponse(code = 403, message = "Recurso proibido"),
		@ApiResponse(code = 404, message = "Recurso não encontrado"),
		@ApiResponse(code = 500, message = "Erro de servidor") })
	public  ResponseEntity<List<PedidoItem>>  listarPorFornecedorId(@PathVariable Integer id) throws NotFoundException {
		if (pedidoService.buscarId(id) != null) {
			return ResponseEntity.ok(pedidoItemService.listarPorIdPedido(id));
		}
		return ResponseEntity.notFound().build();
	}
	
	@PostMapping("/adicionar")
	@ResponseStatus(HttpStatus.CREATED)
	@ApiOperation(value = "Inserir pedidoItem", notes = "Inserção")
	@ApiResponses(value = { 
		@ApiResponse(code = 201, message = "Ok (um pedidoItemfoi inserido)"),
		@ApiResponse(code = 400, message = "Dados inválidos"),		
		@ApiResponse(code = 401, message = "Erro de autenticação/Não autorizado"),
		@ApiResponse(code = 403, message = "Recurso proibido"),
		@ApiResponse(code = 404, message = "Recurso não encontrado"),
		@ApiResponse(code = 500, message = "Erro de servidor/Método não permitido") })
	public List<PedidoItem> inserir(@Valid @RequestBody List<PedidoItem> pedidosItens) {
		
		return pedidoItemService.inserir(pedidosItens);
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
			@Valid @RequestBody PedidoItem pedidoItemRequest) throws NotFoundException {
		
		try {
				PedidoItem pedidoItem = pedidoItemService.atualizar(id, pedidoItemRequest);
			if (pedidoItem != null && pedidoItem.getId() != null) {;
				
				return ResponseEntity.ok(pedidoItem);
			}
		} catch (IOException e) {
			return ResponseEntity.unprocessableEntity().body(e.getMessage());
		}
		return ResponseEntity.notFound().build();
	}
	
	@DeleteMapping("/{id}")
	@ApiOperation(value = "Deletar pedidoItem por id", notes = "Exclusão")
	@ApiResponses(value = { 
		@ApiResponse(code = 204, message = "Nenhum resultado (a exclusão do pedidoItem  foi feita, não tem nenhum conteúdo para retornar)"),
		@ApiResponse(code = 400, message = "Dados inválidos"),		
		@ApiResponse(code = 401, message = "Erro de autenticação/Não autorizado"),
		@ApiResponse(code = 403, message = "Recurso proibido"),
		@ApiResponse(code = 404, message = "Recurso não encontrado"),
		@ApiResponse(code = 500, message = "Erro de servidor/Método não permitido") })
	public ResponseEntity<Void> deletar(@PathVariable Integer id) throws NotFoundException {
		if(pedidoItemService.buscarId(id) != null) {
			pedidoItemService.deletar(id);
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.notFound().build();
	}
	

}
