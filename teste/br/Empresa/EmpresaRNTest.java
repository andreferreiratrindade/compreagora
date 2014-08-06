package br.Empresa;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import br.Empresa.Categoria.Categoria;
import br.Empresa.Categoria.CategoriaDao;
import br.Empresa.Categoria.CategoriaENUM;
import br.Empresa.FormaDePagamento.FormaDePagamento;
import br.Empresa.FormaDePagamento.FormaDePagamentoRN;
import br.Endereco.Endereco;
import br.ProdutoAvulso.Avulso;
import br.ProdutoAvulso.AvulsoRN;
import br.util.DAOFactoy;
import br.util.JpaUtil;

public class EmpresaRNTest {

	@Before
	public void setUp() {
		JpaUtil.getEntityManager().getTransaction().begin();

		// EmpresaBuilder empresaBuilder = new EmpresaBuilder();
		// empresaBuilder.criar();

		// iniciandoCategoriaNoBanco();
		// initFormaDePagamento();
		// iniciandoEmpresa();
	}

	@After
	public void setDown() {
		JpaUtil.getEntityManager().getTransaction().commit();
		JpaUtil.closeEntityManager();
	}

	public static void iniciandoCategoriaNoBanco() {

		CategoriaDao categDAO = DAOFactoy.criarCategoria();

		Categoria categoria = new Categoria();
		categoria.setTipoCategoria(CategoriaENUM.Lanche);

		categDAO.salve(categoria);

		Categoria categoria2 = new Categoria();
		categoria2.setTipoCategoria(CategoriaENUM.Marmitex);
		categDAO.salve(categoria2);
	}

	public static void initFormaDePagamento() {
		FormaDePagamento formaDePagamento = new FormaDePagamento();
		formaDePagamento.setTipo("Dinheiro");

		FormaDePagamentoRN fdpRN = new FormaDePagamentoRN();
		fdpRN.salvar(formaDePagamento);

		fdpRN = new FormaDePagamentoRN();
		formaDePagamento = new FormaDePagamento();
		formaDePagamento.setTipo("Visa Crédito");
		fdpRN.salvar(formaDePagamento);

		fdpRN = new FormaDePagamentoRN();
		formaDePagamento = new FormaDePagamento();
		formaDePagamento.setTipo("Visa Debito");
		fdpRN.salvar(formaDePagamento);
	}

	public static void iniciandoEmpresa() {

		// Permissao permissao = new Permissao();
		// permissao.setPermissao(PermissaoEnum.ROLE_ADM.name());
		//
		// PermissaoDAO permissaoDAO = DAOFactoy.criarPermissao();
		// permissaoDAO.salve(permissao);
		//
		// // Salvando uma cidade no banco para o teste
		// Cidade cidade = new Cidade();
		// cidade.setDescCidade("Governador Valadares");
		// CidadeDAO cidadeDao = DAOFactoy.criarCidade();
		//
		// cidadeDao.salve(cidade);
		//
		// // Salvando um bairro no banco para o teste
		// Bairro bairro = new Bairro();
		// bairro.setCidade(cidade);
		// bairro.setDescBairro("Nova Vila Bretas");
		// BairroDAO bairroDao = DAOFactoy.criarBairro();
		//
		// bairroDao.salve(bairro);
		//
		// // Salvando um bairro no banco para o teste
		//
		// Empresa empresa = new Empresa();
		// empresa.setNomeFant("Rei do Hamburguer");
		//
		// Endereco endereco = new Endereco();
		// endereco.setLogradouro("Rua 1");
		// endereco.setBairroCidade(bairro);
		// empresa.setEndereco(endereco);
		//
		// Cliente cliente = new Cliente();
		// cliente.setNome("Empresa1");
		// cliente.setEmail("empresa@gmail.com");
		// CategoriaDao categoriaDAO = DAOFactoy.criarCategoria();
		//
		// Categoria categoria = categoriaDAO
		// .getCategoriaComEnum(CategoriaENUM.Lanche);
		//
		// // inserindo tipos de categoria na empresa
		//
		// empresa.addCategoria(categoria);
		// FormaDePagamentoRN formaDePagamentoRN = new FormaDePagamentoRN();
		//
		// FormaDePagamento fdp = formaDePagamentoRN.getFormaDePagamento(1);
		// empresa.addFormaDePagamento(fdp);
		//
		// EmpresaRN empresaRN = new EmpresaRN();
		//
		// empresaRN.salvar(empresa, cliente);
		//
		// EmpresaAtendimentoDAO empresaAtendimentoDao = DAOFactoy
		// .criarEmpresaAtendimento();
		// EmpresaAtendimento empresaAtendimento = new EmpresaAtendimento();
		//
		// empresaAtendimento.setBairro(bairro);
		// empresaAtendimento.setEmpresa(empresa);
		// empresaAtendimentoDao.salve(empresaAtendimento);
		//
		// AvulsoRN avulsoRN = new AvulsoRN();
		// Avulso avulso = new Avulso();
		// avulso.setDescricao("Batata Frita");
		// avulso.setValor(10);
		// avulso.setTipoAvulso(CategoriaENUM.Lanche);
		// avulso.setEmpresa(empresa);
		// avulsoRN.salvarAvulso(avulso);

	}

	@Test
	public void deveListarAvulsosCadastrados() {
		AvulsoRN avulsoRN = new AvulsoRN();
		List<Avulso> avulsos = avulsoRN.listar(1, CategoriaENUM.Lanche);
		assertEquals(0, avulsos.size());
	}

	@Test
	public void deveListarEmpresas() {
		// Testando Empresa
		EmpresaRN empresaRN = new EmpresaRN();
		List<Empresa> empresas = empresaRN.listar();

		assertEquals(4, empresas.size());

	}

	@Test
	public void deveRetornarEmpresaPeloId() {
		EmpresaRN empresaRN = new EmpresaRN();
		Empresa tempEmpresa = empresaRN.getEmpresa(1);

		assertEquals("Empresa 1", tempEmpresa.getRazaoSocial());

		// Testando Endereco da Empresa
		Endereco tempEndereco = tempEmpresa.getEndereco();
		assertEquals("Nova Vila Bretas", tempEndereco.getBairroCidade()
				.getDescBairro());

		// Testando Tipos de Categoria
		List<Categoria> tempCateogiraEmpresa = tempEmpresa.getCategorias();
		assertEquals(1, tempCateogiraEmpresa.size());

		Categoria tempCategoria = tempCateogiraEmpresa.get(0);
		assertEquals(CategoriaENUM.Lanche, tempCategoria.getTipoCategoria());

	}

	@Test
	public void deveRetornarEmpresasQueAtendeBairro() {
		EmpresaRN empresaRN = new EmpresaRN();
		List<Empresa> empresas = empresaRN.listaEmpresasPeloBairro(1);
		assertEquals(3, empresas.size());
	}

	@Test
	public void deveListarFormaDePagamento() {
		EmpresaRN empresaRN = new EmpresaRN();
		Empresa empresa = empresaRN.getEmpresa(1);

		List<FormaDePagamento> fpds = empresa.getFormasDePagamento();
		assertEquals(1, fpds.size());
		FormaDePagamento fdp = fpds.get(0);

		assertEquals("Dinheiro", fdp.getTipo());
	}

	@Test
	public void deveRetornarEmpresaAtendimentoValido() {

	}

	@Test
	public void deveRetornarEmpresasDeBairroSelecionado() {
		EmpresaRN empresaRN = new EmpresaRN();

		int idBairro = 1;

		List<Empresa> empresas = empresaRN.listaEmpresasPeloBairroECategoria(
				idBairro, CategoriaENUM.values()[0]);

		assertEquals(3, empresas.size());

	}

	@Test
	public void deveAtualizarFormaDePagamentoEmpresa() {
		EmpresaRN empresaRN = new EmpresaRN();
		Empresa empresa = empresaRN.getEmpresa(1);

		FormaDePagamentoRN formaDePagamentoRN = new FormaDePagamentoRN();

		FormaDePagamento fdp = formaDePagamentoRN.getFormaDePagamento(1);
		empresa.addFormaDePagamento(fdp);

		empresaRN.update(empresa);

		empresa = empresaRN.getEmpresa(1);
		List<FormaDePagamento> fdps = empresa.getFormasDePagamento();
		assertEquals(2, fdps.size());
	}

	@Test
	public void deveListarEmpresasPeloBairroEPelaCategoria() {
		EmpresaRN empresaRN = new EmpresaRN();
		List<Empresa> empresas = empresaRN.listaEmpresasPeloBairroECategoria(1,
				CategoriaENUM.Lanche);

		assertEquals(3, empresas.size());
	}
}
