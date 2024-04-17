package br.com.cfarias.dao;

import br.com.cfarias.dao.generic.GenericDao;
import br.com.cfarias.dao.interfaces.IProdutoDao;
import br.com.cfarias.entity.Produto;

public class ProdutoDao extends GenericDao<Produto, Long> implements IProdutoDao {
	
	public ProdutoDao() {
		super(Produto.class);
	}

}
