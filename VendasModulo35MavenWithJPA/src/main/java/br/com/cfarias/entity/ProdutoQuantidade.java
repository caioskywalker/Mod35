package br.com.cfarias.entity;

import java.math.BigDecimal;

import javax.persistence.*;

@Entity
@Table(name = "tb_produto_quantidade")
public class ProdutoQuantidade {
	
	private Long idProdutoQuantidade;
	
	private Produto produto;
	
	private Integer quantidade;
	
	private BigDecimal valorTotal;
	
	private Venda venda;
	
	

}
