package br.Cliente;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import br.AtendimentoLugares.Bairro;
import br.AtendimentoLugares.BairroDAO;
import br.AtendimentoLugares.Cidade;
import br.AtendimentoLugares.CidadeDAO;
import br.Endereco.Endereco;
import br.EnderecoCliente.EnderecoCliente;
import br.EnderecoCliente.EnderecoClienteDAO;
import br.Permissao.Permissao;
import br.Permissao.PermissaoDAO;
import br.util.DAOFactoy;
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
		Permissao permissao = new Permissao();
		permissao.setPermissao("ROLE_CLI");

		PermissaoDAO permissaoDAO = DAOFactoy.criarPermissao();
		permissaoDAO.salve(permissao);

		// Salvando uma cidade no banco para o teste
		Cidade cidade = new Cidade();
		cidade.setDescCidade("Governador Valadares");
		
		CidadeDAO cidadeDao = DAOFactoy.criarCidade();
		cidadeDao.salve(cidade);

		// Salvando um bairro no banco para o teste
		Bairro bairro = new Bairro();
		bairro.setCidade(cidade);
		bairro.setDescBairro("Nova Vila Bretas");
		
		BairroDAO bairroDao = DAOFactoy.criarBairro();
		bairroDao.salve(bairro);

		Endereco endereco = new Endereco();
		endereco.setBairroCidade(bairro);

		EnderecoCliente enderecoCliente = new EnderecoCliente();
		enderecoCliente.setDescEndereco("Minha Casa");

		Cliente tempCliente = new Cliente();
		tempCliente.setAtivo(true);
		tempCliente.setEmail("dede@gmail.com");
		tempCliente.setNome("André Ferreira Trindade");

		ClienteRN clienteRN = new ClienteRN();
		clienteRN.salvar(tempCliente, endereco, enderecoCliente);
	}

	@Test
	public void deveListarClientesCadastrados() {

		ClienteRN clienteRN = new ClienteRN();

		List<Cliente> clientes = clienteRN.listar();

		assertEquals(1, clientes.size());

	}

	@Test
	public void deveListarEnderecoClientes() {
		EnderecoClienteDAO enderecoClienteDao = DAOFactoy
				.criarEnderecoCliente();
		List<EnderecoCliente> enderecoClientes = enderecoClienteDao.lista();

		assertEquals(2, enderecoClientes.size());
		assertEquals("André Ferreira Trindade", enderecoClientes.get(0)
				.getCliente().getNome());
	}

	@Test
	public void deveVerificarEnderecos() {
		ClienteRN clienteRN = new ClienteRN();

		Cliente cliente = clienteRN.buscarPorEmail("dede@gmail.com");

		List<EnderecoCliente> enderecoClientes = cliente.getEnderecoCliente();

		assertEquals(2, enderecoClientes.size());


	}

	@Test
	public void deveBuscarPorEmail() {
		ClienteRN clienteRN = new ClienteRN();
		Cliente cliente = clienteRN.buscarPorEmail("dede@gmail.com");

		assertEquals("André Ferreira Trindade", cliente.getNome());
	}
}
