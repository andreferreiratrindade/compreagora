package br.Empresa.Categoria;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import br.util.DAOFactoy;
import br.util.JpaUtil;

public class CategoriaTest {

	@BeforeClass
	public static void setUp() {

		JpaUtil.getEntityManager().getTransaction().begin();
		inserindoValoresNoBanco();
	}

	@AfterClass
	public static void setDown() {
		JpaUtil.getEntityManager().getTransaction().commit();
		JpaUtil.closeEntityManager();
	}

	public static void inserindoValoresNoBanco() {

		Categoria categoria = new Categoria();
		categoria.setTipoCategoria(CategoriaENUM.Lanche);

		CategoriaDao categoriaDao = DAOFactoy.criarCategoria();

		categoriaDao.salve(categoria);

		categoria = new Categoria();
		categoria.setTipoCategoria(CategoriaENUM.Lanche);

		categoriaDao.salve(categoria);
	}

	@Test
	public void deveReceberCategoriaPeloTipo() {
		CategoriaDao categoriaDao = DAOFactoy.criarCategoria();

		Categoria categoria = categoriaDao
				.getCategoriaComEnum(CategoriaENUM.Lanche);

		assertEquals("Lanche", categoria.getTipoCategoria().name());
	}

	@Test
	public void deveReceberCategoriaPeloId() {
		CategoriaDao categoriaDao = DAOFactoy.criarCategoria();
		Categoria categoria = categoriaDao.getUnico(1);

		assertEquals("Lanche", categoria.getTipoCategoria().name());

	}

	@Test
	public void deveListarCategorias() {
		CategoriaDao categoriaDao = DAOFactoy.criarCategoria();
		List<Categoria> categorias = categoriaDao.lista();
		assertEquals(2, categorias.size());
	}

}
