package br.webservice;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.util.DigestUtils;

import br.Cliente.Cliente;
import br.Cliente.ClienteDAO;
import br.Cliente.ClienteRN;
import br.util.DAOFactoy;
import br.util.JpaUtil;

public class ClienteWSTest {

	@BeforeClass
	public static void setUp() {
		JpaUtil.getEntityManager().getTransaction().begin();
		init();
	}

	public static void init() {
		Cliente tempCliente = new Cliente();
		tempCliente.setAtivo(true);
		tempCliente.setEmail("dede@gmail.com");
		tempCliente.setNome("André Ferreira Trindade");
		tempCliente.setSenha(DigestUtils.md5DigestAsHex("123".getBytes()));
		ClienteDAO clienteDao = DAOFactoy.criarCliente();
		clienteDao.salve(tempCliente);
	}

	@AfterClass
	public static void setDown() {
		JpaUtil.getEntityManager().getTransaction().commit();
		JpaUtil.closeEntityManager();
	}

	@Test
	public void deveRetornarClienteLogado() {
		ClienteRN clienteRN = new ClienteRN();
		String login = "dede@gmail.com";
		String senha = DigestUtils.md5DigestAsHex("123".getBytes());

		Cliente cliente = null;
		cliente = clienteRN.realizarLoginCliente(login, senha);

		assertNotNull(cliente);
	}

	@Test
	public void deveRetornarListaCliente() {
		ClienteRN clienteRN = new ClienteRN();
		List<Cliente> clientes = clienteRN.listar();
		assertEquals(1, clientes.size());
	}
}
