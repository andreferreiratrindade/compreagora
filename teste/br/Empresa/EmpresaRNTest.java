package br.Empresa;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import br.AtendimentoLugares.EmpresaAtendimento;
import br.AtendimentoLugares.EmpresaAtendimentoRN;
import br.Empresa.Categoria.Categoria;
import br.Empresa.Categoria.CategoriaDao;
import br.Empresa.Categoria.CategoriaENUM;
import br.Empresa.FormaDePagamento.FormaDePagamento;
import br.Empresa.FormaDePagamento.FormaDePagamentoRN;
import br.ProdutoAvulso.Avulso;
import br.ProdutoAvulso.AvulsoRN;
import br.builders.EmpresaBuilder;
import br.util.DAOFactoy;
import br.util.JpaUtil;

public class EmpresaRNTest {

	@Before
	public void setUp() {
		JpaUtil.getEntityManager().getTransaction().begin();

		EmpresaBuilder empresaBuilder = new EmpresaBuilder();
		empresaBuilder.criar();

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

	

	@Test
	public void deveListarAvulsosCadastrados() {
		AvulsoRN avulsoRN = new AvulsoRN();
		List<Avulso> avulsos = avulsoRN.listar(1, CategoriaENUM.Lanche);
		assertEquals(0, avulsos.size());
	}

	@Test
	public void deveListarEmpresas() {
		
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

		// Testando Tipos de Categoria
		List<Categoria> tempCateogiraEmpresa = tempEmpresa.getCategorias();
		assertEquals(2, tempCateogiraEmpresa.size());

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
	
	@Test
	public void deveVerificarLocalAtendimento(){
		EmpresaAtendimentoRN empresaAtendimentoRN = new EmpresaAtendimentoRN();
		EmpresaAtendimento empresaAtendimento = new EmpresaAtendimento();
		empresaAtendimento = empresaAtendimentoRN.empresaAtendimentoEmpresaComBairro(1, "Governador Valadares", "Nova Vila Bretas");
		assertNotNull(empresaAtendimento);
	}
}
