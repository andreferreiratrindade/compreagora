package br.Cliente;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import br.builders.ClienteBuilder;
import br.util.JpaUtil;

public class ClienteRNTest {

	@BeforeClass
	public static void setUp() {
		JpaUtil.getEntityManager().getTransaction().begin();
		inicializaValores();
		JpaUtil.getEntityManager().getTransaction().commit();
		JpaUtil.getEntityManager().close();
		JpaUtil.getEntityManager().getTransaction().begin();
	}

	@AfterClass
	public static void setDown() {
		JpaUtil.getEntityManager().getTransaction().commit();
		JpaUtil.closeEntityManager();
	}

	public static void inicializaValores() {

		ClienteBuilder clienteBuilder = new ClienteBuilder();
		clienteBuilder.criar();
	}

	@Test
	public void deveListarClientesCadastrados() {

		ClienteRN clienteRN = new ClienteRN();

		List<Cliente> clientes = clienteRN.listar();

		assertEquals(1, clientes.size());

	}

	@Test
	public void deveRetornarClienteNull() {
		ClienteRN clienteRN = new ClienteRN();
		Cliente cliente = clienteRN.buscarPorEmail("dedeasdas1@gmail.com");
		Assert.assertNull(cliente);
	}

	@Test
	public void deveRetornarClientePeloEmail() {
		ClienteRN clienteRN = new ClienteRN();
		Cliente cliente = clienteRN.buscarPorEmail("dede@gmail.com");
		assertEquals("André Ferreira Trindade", cliente.getNome());
	}

}
