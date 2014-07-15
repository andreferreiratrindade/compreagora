package br.Pedido;

import static org.junit.Assert.assertEquals;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.junit.Test;
import br.AtendimentoLugares.Bairro;
import br.AtendimentoLugares.BairroDAO;
import br.AtendimentoLugares.Cidade;
import br.AtendimentoLugares.CidadeDAO;
import br.AtendimentoLugares.EmpresaAtendimento;
import br.AtendimentoLugares.EmpresaAtendimentoDAO;
import br.Cliente.Cliente;
import br.Cliente.ClienteRN;
import br.Empresa.Empresa;
import br.Empresa.EmpresaDAO;
import br.Empresa.EmpresaRN;
import br.Empresa.FormaDePagamento.FormaDePagamento;
import br.Empresa.FormaDePagamento.FormaDePagamentoRN;
import br.Endereco.Endereco;
import br.EnderecoCliente.EnderecoCliente;
import br.EnderecoCliente.EnderecoClienteDAO;
import br.PedidoProduto.PedidoProduto;
import br.Permissao.Permissao;
import br.Permissao.PermissaoDAO;
import br.Produto.Lanche;
import br.Produto.ProdutoDAO;
import br.Produto.ProdutoRN;
import br.util.DAOFactoy;
import br.util.JpaUtil;

public class PedidoRNTest {

	public static void setUp() {
		JpaUtil.getEntityManager().getTransaction().begin();
		initBairroCidade();
		initFormaDePagamento();
		initEmrpesa();
		initProduto();
		initCliente();
		initSalvaPedido();
		JpaUtil.getEntityManager().getTransaction().commit();
		JpaUtil.getEntityManager().close();
		JpaUtil.getEntityManager().getTransaction().begin();
	}

	public static void setDown() {
		JpaUtil.getEntityManager().getTransaction().commit();
		JpaUtil.closeEntityManager();
	}

	public static void initSalvaPedido() {

		EmpresaRN empresaRN = new EmpresaRN();
		Empresa empresa = empresaRN.getEmpresa(1);

		ProdutoRN produtoRN = new ProdutoRN();

		Lanche lanche = (Lanche) produtoRN.getProduto(1);

		EnderecoClienteDAO endClienteDao = DAOFactoy.criarEnderecoCliente();
		EnderecoCliente enderecoCliente = endClienteDao.getUnico(1);

		PedidoProduto pedidoProduto = new PedidoProduto();
		pedidoProduto.addProduto(lanche);

		List<PedidoProduto> pedidosProdutos = new ArrayList<PedidoProduto>();
		pedidosProdutos.add(pedidoProduto);

		FormaDePagamentoRN formaDePagamentoRN = new FormaDePagamentoRN();
		FormaDePagamento formaDePagamento = formaDePagamentoRN
				.peloTipo("Dinheiro");

		Pedido pedido = new Pedido();
		pedido.setPedidoProdutos(pedidosProdutos);
		pedido.calcularTotal();
		pedido.setFormaDePagamento(formaDePagamento);
		PedidoRN pedidoRN = new PedidoRN();
		pedidoRN.salvar(enderecoCliente, empresa, pedido);

	}

	public static void initFormaDePagamento() {

		FormaDePagamento fDinheiro = new FormaDePagamento();
		fDinheiro.setTipo("Dinheiro");
		fDinheiro.setImagem("dinheiro.gif");

		FormaDePagamento fCartao = new FormaDePagamento();
		fCartao.setTipo("Visa Credito");
		fCartao.setImagem("visaCredito.gif");

		FormaDePagamentoRN formaDePagamentoRN = new FormaDePagamentoRN();
		formaDePagamentoRN.salvar(fDinheiro);
		formaDePagamentoRN.salvar(fCartao);

	}

	public static void initEmrpesa() {

		FormaDePagamentoRN formaDePagamentoRN = new FormaDePagamentoRN();
		FormaDePagamento formaDePagamento = formaDePagamentoRN
				.peloTipo("Dinheiro");

		FormaDePagamento formaDePagamento2 = formaDePagamentoRN
				.peloTipo("Visa Credito");
		Empresa empresa = new Empresa();
		empresa.setNomeFant("Lanchonete Doce Mel");

		empresa.addFormaDePagamento(formaDePagamento);
		empresa.addFormaDePagamento(formaDePagamento2);

		BairroDAO bairroDao = DAOFactoy.criarBairro();
		Bairro bairro = bairroDao.getUnico(1);

		EmpresaAtendimentoDAO empresaAtendimentoDao = DAOFactoy
				.criarEmpresaAtendimento();
		EmpresaAtendimento empresaAtendimento = new EmpresaAtendimento();

		empresaAtendimento.setBairro(bairro);
		empresaAtendimento.setTempoEspera(7); // Tempo em minutos...

		EmpresaDAO empresaDAO = DAOFactoy.criarEmpresa();
		empresaDAO.salve(empresa);

		empresaAtendimento.setEmpresa(empresa);
		empresaAtendimentoDao.salve(empresaAtendimento);
	}

	public static void initProduto() {
		EmpresaDAO empresaDAO = DAOFactoy.criarEmpresa();
		Empresa empresa = empresaDAO.getUnico(1);

		ProdutoRN produtoRN = new ProdutoRN();

		Lanche lanche = new Lanche();
		lanche.setTempoEspera(15); // colocar em minutos
		lanche.setEmpresa(empresa);
		produtoRN.salve(lanche);
	}

	public static void initBairroCidade() {
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
	}

	public static void initCliente() {
		Permissao permissao = new Permissao();
		permissao.setPermissao("ROLE_CLI");

		PermissaoDAO permissaoDAO = DAOFactoy.criarPermissao();
		permissaoDAO.salve(permissao);

		BairroDAO bairroDao = DAOFactoy.criarBairro();
		Bairro bairro = bairroDao.getUnico(1);

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

	public void deveTestarEmpresa() {
		EmpresaDAO empresaDAO = DAOFactoy.criarEmpresa();

		Empresa empresa = empresaDAO.getUnico(1);

		assertEquals("Lanchonete Doce Mel", empresa.getNomeFant());

	}

	public void deveTestarProduto() {
		EmpresaDAO empresaDAO = DAOFactoy.criarEmpresa();

		Empresa empresa = empresaDAO.getUnico(1);

		ProdutoDAO produtoDAO = DAOFactoy.criarProduto();

		List<Lanche> produtos = produtoDAO.listaLanche(empresa.getIdEmpresa());

		assertEquals(1, produtos.size());
	}

	public void deveTempoEsperaTotalDoPedido() {
		PedidoRN pedidoRN = new PedidoRN();
		Pedido pedido = pedidoRN.getPedido(1);

		int tempo = pedido.getTempoEspera();

		assertEquals(22, tempo);
	}

	public void deveListarPedidos() {

		ClienteRN clienteRN = new ClienteRN();
		Cliente cliente = clienteRN.buscarPorEmail("dede@gmail.com");

		List<EnderecoCliente> enderecosCliente = cliente.getEnderecoCliente();
		EnderecoCliente enderecoCliente = enderecosCliente.get(0);

		List<Pedido> pedidos = enderecoCliente.getPedidos();

		assertEquals(1, pedidos.size());

	}

	public void deveRetornarTempoDePercurso() {
		PedidoRN pedidoRN = new PedidoRN();

		EmpresaRN empresaRN = new EmpresaRN();

		EnderecoClienteDAO enderecoCliente = DAOFactoy.criarEnderecoCliente();

		int tempo = pedidoRN.calculaTempoDePercurso(1, 1);
		assertEquals(7, tempo);
	}

	public void deveCalcularTempo() {

		PedidoRN pedidoRN = new PedidoRN();
		int tempo = pedidoRN.calculaTempoDeTodosPedidos(1);
		assertEquals(22, tempo);
	}

	public void deveRetornarFormaDePagamentoDaEmpresa() {
		EmpresaRN empresaRN = new EmpresaRN();
		Empresa empresa = empresaRN.getEmpresa(1);

		List<FormaDePagamento> formasDePagamento = empresa
				.getFormasDePagamento();

		assertEquals(2, formasDePagamento.size());
	}

	public void deveVerificarFormaDePagamentoNoPedido() {
		EmpresaRN empresaRN = new EmpresaRN();
		Empresa empresa = empresaRN.getEmpresa(1);

		List<Pedido> pedidos = empresa.getPedidos();

		Pedido pedido = pedidos.get(0);

		FormaDePagamento fdp = pedido.getFormaDePagamento();
		assertEquals("Dinheiro", fdp.getTipo());
	}

	@Test
	public void deveRetornarRelatorioDeFaturamento() throws ParseException {

		int idEmpresa = 23;
		SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
		Date dataInicio = new java.util.Date(format.parse("01/01/2014")
				.getTime());
		Date dataFim = new java.util.Date(format.parse("01/03/2014").getTime());

		PedidoRN pedidoRN = new PedidoRN();
		List<Pedido> pedidos = pedidoRN.relatorioDeFaturamento(idEmpresa,
				dataInicio, dataFim);

		assertEquals(2, pedidos.size());
	}

}
