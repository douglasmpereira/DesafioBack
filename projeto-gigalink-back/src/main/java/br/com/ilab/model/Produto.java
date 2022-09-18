package br.com.ilab.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;



@Entity
public class Produto {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;
	
	@Column(name = "nome")
	private String nome;
	
	@Column(name = "descricao")
	private String descricao;
	
	@Column(name = "precoUnit")
	private Double precoUnit;
	
	@Column(name = "qtdEstoque")
	private Integer qtdEstoque;
	
	@Column(name = "url")
	private String url;
	

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "id_fornecedor", nullable = false )
	private Fornecedor fornecedor;

	public Produto() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Produto(String nome, String descricao, Double precoUnit, Integer qtdEstoque, String url,
			Fornecedor fornecedor) {
		super();
		this.nome = nome;
		this.descricao = descricao;
		this.precoUnit = precoUnit;
		this.qtdEstoque = qtdEstoque;
		this.url = url;
		this.fornecedor = fornecedor;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Double getPrecoUnit() {
		return precoUnit;
	}

	public void setPrecoUnit(Double precoUnit) {
		this.precoUnit = precoUnit;
	}

	public Integer getQtdEstoque() {
		return qtdEstoque;
	}

	public void setQtdEstoque(Integer qtdEstoque) {
		this.qtdEstoque = qtdEstoque;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Fornecedor getFornecedor() {
		return fornecedor;
	}

	public void setFornecedor(Fornecedor fornecedor) {
		this.fornecedor = fornecedor;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
	
	
	

}
