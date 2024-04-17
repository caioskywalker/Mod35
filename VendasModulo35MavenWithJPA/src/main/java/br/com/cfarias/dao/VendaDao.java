package br.com.cfarias.dao;

import br.com.cfarias.dao.generic.GenericDao;
import br.com.cfarias.dao.interfaces.IVendaDao;
import br.com.cfarias.entity.Venda;

public class VendaDao extends GenericDao<Venda, Long> implements IVendaDao{

	public VendaDao() {
		super(Venda.class);
	}
}
