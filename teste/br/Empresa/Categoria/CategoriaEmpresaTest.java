package br.Empresa.Categoria;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import br.Empresa.Empresa;
import br.Empresa.EmpresaDAO;
import br.Empresa.EmpresaRN;
import br.util.DAOFactoy;
import br.util.JpaUtil;

public class CategoriaEmpresaTest {

	@BeforeClass
	public static void setUp() {
		JpaUtil.getEntityManager().getTransaction().begin();
		inicializaEmpresa();
		inicializaCategorias();

	}

	@AfterClass
	public static void setDown() {
		JpaUtil.getEntityManager().getTransaction().commit();
		JpaUtil.closeEntityManager();

	}

	public static void inicializaCategorias() {
		CategoriaDao categDAO = DAOFactoy.criarCategoria();
		Categoria categoria = new Categoria();
		categoria.setTipoCategoria(CategoriaENUM.Lanche);
		categDAO.salve(categoria);
	}

	public static void inicializaEmpresa() {
		EmpresaDAO empresaDAO = DAOFactoy.criarEmpresa();
		Empresa empresa = new Empresa();
		empresa.setNomeFant("Hamburgueria");
		empresaDAO.salve(empresa);
	}

	@Test
	public void deveVerificarQuantidadeDeCategoriaEmUmaEmpresa() {
		EmpresaRN empresaRN = new EmpresaRN();
		Empresa empresa = empresaRN.getEmpresa(1);

		CategoriaDao categDAO = DAOFactoy.criarCategoria();
		Categoria categoria = categDAO.getUnico(1);

		empresa.addCategoria(categoria);
		empresaRN.update(empresa);

		empresa = empresaRN.getEmpresa(1);

		List<Categoria> categorias = empresa.getCategorias();
		assertEquals(1, categorias.size());

	}
}
