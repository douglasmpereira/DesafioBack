package br.com.ilab.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.ilab.model.PedidoItem;
import br.com.ilab.model.Produto;
import br.com.ilab.repository.ProdutoRepository;
import javassist.NotFoundException;

@Service
public class ProdutoService {
	
	@Autowired
	private ProdutoRepository produtoRepository;
	
	public List<Produto> listar() {
        List<Produto> produtos = produtoRepository.findAll();     
        return produtos;
    }
	
	public Optional<Produto> buscarId(Integer id) {
		Optional<Produto> produto = produtoRepository.findById(id);
		return produto;
	}
	
	public List<Produto> inserir(List<Produto> listaProdutos) {	
		
		List<Produto> produtosInseridos = new ArrayList<Produto>();
		
		for(Produto produto: listaProdutos) {
			
		
		Produto newProduto = new Produto();
		newProduto.setNome(produto.getNome());
		newProduto.setDescricao(produto.getDescricao());
		newProduto.setPrecoUnit(produto.getPrecoUnit());
		newProduto.setQtdEstoque(produto.getQtdEstoque());
		newProduto.setUrl(produto.getUrl());
		newProduto.setFornecedor(produto.getFornecedor());
		
		produtoRepository.save(newProduto);
		
		produtosInseridos.add(newProduto);
		
		}
		return produtosInseridos;
	}
	
	public Produto atualizar(Integer id, Produto produto) throws IOException, NotFoundException {
		
		if (produtoRepository.existsById(id)) {
			Produto produtoEditado = new Produto();
			produtoEditado.setId(id);
			produtoEditado.setNome(produto.getNome());
			produtoEditado.setDescricao(produto.getDescricao());
			produtoEditado.setPrecoUnit(produto.getPrecoUnit());
			produtoEditado.setQtdEstoque(produto.getQtdEstoque());
			produtoEditado.setUrl(produto.getUrl());
			produtoEditado.setFornecedor(produto.getFornecedor());
			
			return produtoRepository.save(produtoEditado);
			
	}
	throw new NotFoundException("id");
	}
	
	public void deletar(Integer id) throws NotFoundException  {
		if (produtoRepository.existsById(id)) {
			produtoRepository.deleteById(id);
		}else {
		throw new NotFoundException("id");
		}
	}
	
}
