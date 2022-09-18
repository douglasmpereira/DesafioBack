package br.com.ilab.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Transportadora {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;
	
	@Column(name = "nome")
	private String nome;
	
	@Column(name = "precoPorKm")
	private String precoPorKm;
	
	@Column(name = "telefone")
	private String telefone;

	public Transportadora() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Transportadora(String nome, String precoPorKm, String telefone) {
		super();
		this.nome = nome;
		this.precoPorKm = precoPorKm;
		this.telefone = telefone;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getPrecoPorKm() {
		return precoPorKm;
	}

	public void setPrecoPorKm(String precoPorKm) {
		this.precoPorKm = precoPorKm;
	}

	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}
	
	
	
}
