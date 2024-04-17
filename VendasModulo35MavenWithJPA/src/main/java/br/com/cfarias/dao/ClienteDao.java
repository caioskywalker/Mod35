package br.com.cfarias.dao;

import br.com.cfarias.dao.generic.GenericDao;
import br.com.cfarias.dao.interfaces.IClienteDao;
import br.com.cfarias.entity.Cliente;

public class ClienteDao extends GenericDao<Cliente, Long> implements IClienteDao {

	public ClienteDao() {
		super(Cliente.class);
	}
	
	
}
