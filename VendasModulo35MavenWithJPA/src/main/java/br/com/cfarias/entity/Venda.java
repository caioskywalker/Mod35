package br.com.cfarias.entity;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import javax.persistence.*;

@Entity
@Table(name = "tb_venda")
public class Venda {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "venda_seq")
	@SequenceGenerator(name = "venda_seq", sequenceName = "sq_venda", initialValue = 1, allocationSize = 1)
	private Long idVenda;

	@Column(name = "codigo_venda", nullable = false, unique = true)
	private String codigo;

	@ManyToOne
	@JoinColumn(name = "id_cliente_fk", foreignKey = @ForeignKey(name = "fk_venda_cliente"), referencedColumnName = "idCliente", nullable = false)
	private Cliente cliente;

	@OneToMany(mappedBy = "venda", cascade = CascadeType.ALL)
	private Set<ProdutoQuantidade> produtosQuantidade;

	@Column(name = "valor_total", nullable = false)
	private BigDecimal valorTotal;

	@Column(name = "data_venda", nullable = false)
	private Instant dataVenda;

	@Enumerated(EnumType.STRING)
	@Column(name = "status_venda", nullable = false)
	private Status status;


	public Long getIdVenda() {
		return idVenda;
	}

	public void setIdVenda(Long idVenda) {
		this.idVenda = idVenda;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Set<ProdutoQuantidade> getProdutosQuantidade() {
		return produtosQuantidade;
	}

	public void setProdutosQuantidade(Set<ProdutoQuantidade> produtosQuantidade) {
		this.produtosQuantidade = produtosQuantidade;
	}

	public BigDecimal getValorTotal() {
		return valorTotal;
	}

	public void setValorTotal(BigDecimal valorTotal) {
		this.valorTotal = valorTotal;
	}

	public Instant getDataVenda() {
		return dataVenda;
	}

	public void setDataVenda(Instant dataVenda) {
		this.dataVenda = dataVenda;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public enum Status {
		INICIADA, CONCLUIDA, CANCELADA;

		public static Status getByName(String value) {

			/*
			 * for (Status status : Status.values()) { if (status.name().equals(value)) {
			 * return status; } else { continue; } }
			 * 
			 * return null;
			 */

			return Arrays.stream(Status.values()).filter(s -> s.name().equals(value)).findFirst().orElse(null);
		}

	}

	public Venda( String codigo, Cliente cliente, Set<ProdutoQuantidade> produtosQuantidade,
			BigDecimal valorTotal, Instant dataVenda, Status status) {
		super();
		
		this.codigo = codigo;
		this.cliente = cliente;
		this.produtosQuantidade = produtosQuantidade;
		this.valorTotal = valorTotal;
		this.dataVenda = dataVenda;
		this.status = status;
	}

	public Venda() {
		produtosQuantidade = new HashSet<ProdutoQuantidade>();
	}
	
	public void adicionarProduto(Produto produto, Integer quantidade) {
		validarStatus();
		
		
	}
	
	private void validarStatus() {
		
		if (this.status == Status.CONCLUIDA) {
			throw new UnsupportedOperationException("IMPOSSÍVEL ALTERAR VENDA FINALIZADA");
		}
		
	}
	
	
	
	
	
	

}
