package br.com.ilab.model;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Fornecedor {
	

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
		
		@OneToMany(cascade = CascadeType.REMOVE, mappedBy = "fornecedor", fetch = FetchType.EAGER )
		private Set<Telefone> telefone;
		
		@OneToMany(cascade = CascadeType.REMOVE, mappedBy = "fornecedor", fetch = FetchType.EAGER  )
		private Set<Email> email;


		public Fornecedor() {
			super();
			// TODO Auto-generated constructor stub
		}
		

		public Fornecedor(String nome, String descricao, String cidade, String endereco, String bairro, Integer numero,
				Set<Telefone> telefone, Set<Email> email) {
			super();
			this.nome = nome;
			this.descricao = descricao;
			this.cidade = cidade;
			this.endereco = endereco;
			this.bairro = bairro;
			this.numero = numero;
			this.telefone = telefone;
			this.email = email;
		}

		public Integer getId() {
			return id;
		}

		public void setId(Integer id) {
			this.id = id;
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

		public String getCidade() {
			return cidade;
		}

		public void setCidade(String cidade) {
			this.cidade = cidade;
		}

		public String getEndereco() {
			return endereco;
		}

		public void setEndereco(String endereco) {
			this.endereco = endereco;
		}

		public String getBairro() {
			return bairro;
		}

		public void setBairro(String bairro) {
			this.bairro = bairro;
		}

		public Integer getNumero() {
			return numero;
		}

		public void setNumero(Integer numero) {
			this.numero = numero;
		}


		public Set<Telefone> getTelefone() {
			return telefone;
		}


		public void setTelefone(Set<Telefone> telefone) {
			this.telefone = telefone;
		}


		public Set<Email> getEmail() {
			return email;
		}


		public void setEmail(Set<Email> email) {
			this.email = email;
		}
			
		
	}

