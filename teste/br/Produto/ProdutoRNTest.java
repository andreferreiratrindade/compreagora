package br.Produto;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import br.Empresa.Empresa;
import br.Empresa.EmpresaDAO;
import br.Empresa.EmpresaRN;
import br.Produto.Filtro.WithStatusIndisponivel;
import br.util.DAOFactoy;
import br.util.JpaUtil;

public class ProdutoRNTest {

	@BeforeClass
	public static void setUp() {
		JpaUtil.getEntityManager().getTransaction().begin();

		criaEmpresa();
		criaProdutos();
	}

	@AfterClass
	public static void setDown() {
		JpaUtil.getEntityManager().getTransaction().commit();
		JpaUtil.closeEntityManager();
	}

	public static void criaEmpresa() {
		Empresa empresa = new Empresa();
		empresa.setNomeFant("Empresa1");

		EmpresaDAO dao = DAOFactoy.criarEmpresa();
		dao.salve(empresa);
	}

	public static void criaProdutos() {
		EmpresaRN empresaRN = new EmpresaRN();
		Empresa empresa = null;
		empresa = empresaRN.getEmpresa(1);

		Lanche lanche = new Lanche();
		lanche.setDescricao("Hamburguer");
		lanche.setValor((float) 5.9);
		lanche.setIngredientes("Apenas um Teste");
		lanche.setEmpresa(empresa);

		ProdutoRN produtoRN = new ProdutoRN();
		produtoRN.salve(lanche);
	}

	@Test
	public void deveListarLancheDeEmpresa() {

		EmpresaRN empresaRN = new EmpresaRN();

		Empresa tempEmpresa = empresaRN.getEmpresa(1);

		ProdutoRN produtoRN = new ProdutoRN();
		List<Lanche> lanches = produtoRN.listarLanche(tempEmpresa
				.getIdEmpresa());

		assertEquals(1, lanches.size());

	}

	@Test
	public void deveListarProdutos() {
		EmpresaRN empresaRN = new EmpresaRN();

		Empresa tempEmpresa = empresaRN.getEmpresa(1);

		ProdutoRN produtoRN = new ProdutoRN();
		List<Produto> produtos = produtoRN.listarProdutos(tempEmpresa
				.getIdEmpresa());

		assertEquals(1, produtos.size());
	}

	@Test
	public void deveReceberApenasUmLanche() {

		ProdutoDAO produtoDao = DAOFactoy.criarProduto();
		Lanche lanche = (Lanche) produtoDao.getUnico(1);

		assertEquals("Hamburguer", lanche.getDescricao());

	}

	@Test
	public void deveRetornarUltimoElementoAdicionado() {

		ProdutoDAO produtoDao = DAOFactoy.criarProduto();
		int valor = produtoDao.ultimoElementoAdicionado();

		assertEquals(1, valor);
	}

	@Test
	public void deveRetornarListaDeLanchesComFiltro() {

		ProdutoRN produtoRN = new ProdutoRN();
		produtoRN.alterarFiltro(new WithStatusIndisponivel());
		List<Lanche> produtos = produtoRN.listarLanche(1);

		assertEquals(1, produtos.size());
	}

	@Test
	public void deveRetornarValorProduto() {
		ProdutoRN produtoRN = new ProdutoRN();
		Produto produto = produtoRN.getProduto(1);

		assertEquals(5.90, produto.getValor(), 0.00001);

	}
}
