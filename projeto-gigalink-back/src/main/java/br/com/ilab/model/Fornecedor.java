package br.com.ilab.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Fornecedor {
	
	@Entity
	public class Cliente {

		@Id
		@GeneratedValue(strategy = GenerationType.IDENTITY)
		@Column(name = "id")
		private Integer id;
		
		@Column(name = "nome")
		private String nome;
		
		@Column(name = "descricao")
		private String descricao;
		
		@Column(name = "cidade")
		private String cidade;
		
		@Column(name = "endereco")
		private String endereco;
		
		@Column(name = "bairro")
		private String bairro;
		
		@Column(name = "numero")
		private Integer numero;

		public Cliente() {
			super();
			// TODO Auto-generated constructor stub
		}

		public Cliente(String nome, String descricao, String cidade, String endereco, String bairro, Integer numero) {
			super();
			this.nome = nome;
			this.descricao = descricao;
			this.cidade = cidade;
			this.endereco = endereco;
			this.bairro = bairro;
			this.numero = numero;
		}
		
	}
}
