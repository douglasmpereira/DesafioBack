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
public class PedidoItem {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;
	
	@Column(name = "quantidade")
	private Integer quantidade;
	
	@Column(name = "subTotal")
	private Double subTotal;
	

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "id_produto", nullable = false )
	private Produto produto;
	
	
	@ManyToOne( fetch = FetchType.EAGER)
	@JoinColumn(name = "id_pedido", nullable = false )
	private Pedido pedido;

	public PedidoItem() {
		super();
		// TODO Auto-generated constructor stub
	}
	

	public PedidoItem(Integer quantidade, Double subTotal, Produto produto, Pedido pedido) {
		super();
		this.quantidade = quantidade;
		this.subTotal = subTotal;
		this.produto = produto;
		this.pedido = pedido;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}

	public Double getSubTotal() {
		return subTotal;
	}

	public void setSubTotal(Double subTotal) {
		this.subTotal = subTotal;
	}

	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}

	public Pedido getPedido() {
		return pedido;
	}

	public void setPedido(Pedido pedido) {
		this.pedido = pedido;
	}
	
	
}
