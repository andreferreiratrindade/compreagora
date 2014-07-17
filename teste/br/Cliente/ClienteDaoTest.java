package br.Cliente;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import java.util.ArrayList;
import java.util.List;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import br.util.DAOFactoy;
import br.util.JpaUtil;
public class ClienteDaoTest {

	private static List<Cliente> clientes;

	@BeforeClass
	public static void setUp() {
		JpaUtil.getEntityManager().getTransaction().begin();
		clientes = new ArrayList<Cliente>();
		iniciaListaClientes();
		ClienteDAO clienteDAO = DAOFactoy.criarCliente();
		for (Cliente x : clientes) {
			clienteDAO.salve(x);
		}

	}

	@AfterClass
	public static void setDown() {
		JpaUtil.getEntityManager().getTransaction().commit();
		JpaUtil.closeEntityManager();
	}

	public static void iniciaListaClientes() {
		Cliente cliente = new Cliente();
		cliente.setEmail("andre@gmail.com");
		cliente.setNome("André Ferreira Trindade");

		clientes.add(cliente);

		cliente = new Cliente();
		cliente.setEmail("gugu@gmail.com");
		clientes.add(cliente);

		cliente = new Cliente();
		cliente.setEmail("Cezinha@gmail.com");
		clientes.add(cliente);
	}

	@Test
	public void deveBuscarPeloEmail() {

		ClienteDAO clienteDAO = DAOFactoy.criarCliente();

		Cliente cliente = clienteDAO.buscarPorEmail("andre@gmail.com");
		assertEquals("André Ferreira Trindade", cliente.getNome());

	}

	@Test
	public void deveVerificarSeEmailFoiCadastrado() {
		ClienteDAO clienteDAO = DAOFactoy.criarCliente();
		boolean cadastrado = clienteDAO
				.verificaEmailCadastrado("andre@gmail.com");
		assertFalse(cadastrado);
	}

}
