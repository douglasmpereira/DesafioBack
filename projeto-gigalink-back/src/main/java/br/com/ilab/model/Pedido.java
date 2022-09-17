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
public class Pedido {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;
	
	@Column(name = "dataHora")
	private String dataHora;
	
	@Column(name = "notaFiscal")
	private String notaFiscal;
	
	@Column(name = "frete")
	private Double frete;
	
	@Column(name = "desconto")
	private Double desconto;
	
	@Column(name = "valorTotal")
	private Double valorTotal;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "id_transportadora")
	private Transportadora transportadora;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "id_cliente")
	private Cliente cliente;

	public Pedido() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Pedido(Integer id, String dataHora, String notaFiscal, Double frete, Double desconto, Double valorTotal,
			Transportadora transportadora, Cliente cliente) {
		super();
		this.id = id;
		this.dataHora = dataHora;
		this.notaFiscal = notaFiscal;
		this.frete = frete;
		this.desconto = desconto;
		this.valorTotal = valorTotal;
		this.transportadora = transportadora;
		this.cliente = cliente;
	}

	public Pedido(String dataHora, String notaFiscal, Double precoUnit, Double desconto, Double valorTotal,
			Transportadora transportadora, Cliente cliente) {
		super();
		this.dataHora = dataHora;
		this.notaFiscal = notaFiscal;
		this.frete = precoUnit;
		this.desconto = desconto;
		this.valorTotal = valorTotal;
		this.transportadora = transportadora;
		this.cliente = cliente;
	}

	public String getDataHora() {
		return dataHora;
	}

	public void setDataHora(String dataHora) {
		this.dataHora = dataHora;
	}

	public String getNotaFiscal() {
		return notaFiscal;
	}

	public void setNotaFiscal(String notaFiscal) {
		this.notaFiscal = notaFiscal;
	}

	public Double getFrete() {
		return frete;
	}

	public void setFrete(Double frete) {
		this.frete = frete;
	}

	public Double getDesconto() {
		return desconto;
	}

	public void setDesconto(Double desconto) {
		this.desconto = desconto;
	}

	public Double getValorTotal() {
		return valorTotal;
	}

	public void setValorTotal(Double valorTotal) {
		this.valorTotal = valorTotal;
	}

	public Transportadora getTransportadora() {
		return transportadora;
	}

	public void setTransportadora(Transportadora transportadora) {
		this.transportadora = transportadora;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getId() {
		return id;
	}

}
