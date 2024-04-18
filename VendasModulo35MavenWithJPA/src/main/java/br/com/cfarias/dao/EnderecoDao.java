package br.com.cfarias.dao;

import br.com.cfarias.dao.generic.GenericDao;
import br.com.cfarias.dao.interfaces.IEnderecoDao;
import br.com.cfarias.entity.Endereco;


public class EnderecoDao extends GenericDao<Endereco, Long> implements IEnderecoDao {
	
	public EnderecoDao() {
		super(Endereco.class);
	}

}
