package br.com.cfarias.tests.dao;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Collection;
import java.util.Random;
import java.util.Set;

import br.com.cfarias.entity.*;
import br.com.cfarias.exceptions.DaoException;
import br.com.cfarias.exceptions.MaisDeUmRegistroException;
import br.com.cfarias.exceptions.TableException;
import br.com.cfarias.exceptions.TipoChaveNaoEncontradaException;
import br.com.cfarias.dao.*;

import org.junit.jupiter.api.*;

import br.com.cfarias.dao.interfaces.IClienteDao;

public class DaoTests {
	
private IClienteDao ClienteDao;
	
	private Random rd;
	
	public DaoTests() {
		this.ClienteDao = new ClienteDao();
		rd = new Random();
	}
	
	@AfterAll
	public void end() throws DaoException {
		Collection<Cliente> list = ClienteDao.buscarTodos();
		list.forEach(cli -> {
			try {
				ClienteDao.excluir(cli);
			} catch (DaoException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		});
	}
	
	@Test
	public void pesquisarCliente() throws TipoChaveNaoEncontradaException, DaoException, MaisDeUmRegistroException, TableException {
		Cliente cliente = criarCliente();
		ClienteDao.cadastrar(cliente);
		
		Cliente clienteConsultado = ClienteDao.consultar(cliente.getId());
		assertNotNull(clienteConsultado);
		
	}

	@Test
	public void salvarCliente() throws TipoChaveNaoEncontradaException, MaisDeUmRegistroException, TableException, DaoException {
		Cliente cliente = criarCliente();
		Cliente retorno = ClienteDao.cadastrar(cliente);
		assertNotNull(retorno);
		
		Cliente clienteConsultado = ClienteDao.consultar(retorno.getId());
		assertNotNull(clienteConsultado);
		
		ClienteDao.excluir(cliente);
		
		Cliente clienteConsultado1 = ClienteDao.consultar(retorno.getId());
		assertNull(clienteConsultado1);
	}
	
	@Test
	public void excluirCliente() throws TipoChaveNaoEncontradaException, MaisDeUmRegistroException, TableException, DaoException {
		Cliente cliente = criarCliente();
		Cliente retorno = ClienteDao.cadastrar(cliente);
		assertNotNull(retorno);
		
		Cliente clienteConsultado = ClienteDao.consultar(cliente.getId());
		assertNotNull(clienteConsultado);
		
		ClienteDao.excluir(cliente);
		clienteConsultado = ClienteDao.consultar(cliente.getId());
		assertNull(clienteConsultado);
	}
	
	@Test
	public void alterarCliente() throws TipoChaveNaoEncontradaException, MaisDeUmRegistroException, TableException, DaoException {
		Cliente cliente = criarCliente();
		Cliente retorno = ClienteDao.cadastrar(cliente);
		assertNull(retorno);
		
		Cliente clienteConsultado = ClienteDao.consultar(cliente.getId());
		assertNotNull(clienteConsultado);
		
		clienteConsultado.setNome("Rodrigo Pires");
		ClienteDao.alterar(clienteConsultado);
		
		Cliente clienteAlterado = ClienteDao.consultar(clienteConsultado.getId());
		assertNotNull(clienteAlterado);
		assertEquals("Rodrigo Pires", clienteAlterado.getNome());
		
		ClienteDao.excluir(cliente);
		clienteConsultado = ClienteDao.consultar(clienteAlterado.getId());
		assertNull(clienteConsultado);
	}
	
	@Test
	public void buscarTodos() throws TipoChaveNaoEncontradaException, DaoException {
		Cliente cliente = criarCliente();
		Cliente retorno = ClienteDao.cadastrar(cliente);
		assertNotNull(retorno);
		
		Cliente cliente1 = criarCliente();
		Cliente retorno1 = ClienteDao.cadastrar(cliente1);
		assertNotNull(retorno1);
		
		Collection<Cliente> list = ClienteDao.buscarTodos();
		assertTrue(list != null);
		assertTrue(list.size() == 2);
		
		list.forEach(cli -> {
			try {
				ClienteDao.excluir(cli);
			} catch (DaoException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		});
		
		Collection<Cliente> list1 = ClienteDao.buscarTodos();
		assertTrue(list1 != null);
		assertTrue(list1.size() == 0);
	}
	
	private Cliente criarCliente() {
		Cliente cliente = new Cliente();
		cliente.setCpf(rd.nextLong());
		cliente.setNome("Rodrigo");
		Endereco endereco = new Endereco();
		endereco.setCidade("rj");
		endereco.setEstado("rj");
		endereco.setNomeLogradouro("rua do joao");
		endereco.setNumero(3l);
		Set<Endereco> end = null;
		end.add(endereco);
		cliente.setEnderecos(end);
		return cliente;
	}

}
