package br.builders;

import br.Empresa.Categoria.Categoria;
import br.Empresa.Categoria.CategoriaDao;
import br.Empresa.Categoria.CategoriaENUM;
import br.util.DAOFactoy;

public class CategoriaBuilder {
	private CategoriaDao dao;

	public CategoriaBuilder() {
		dao = DAOFactoy.criarCategoria();
	}

	public void criar() {

		Categoria categoriaLanche = new Categoria();
		categoriaLanche.setTipoCategoria(CategoriaENUM.Lanche);
		dao.salve(categoriaLanche);

		Categoria categoriaAgua = new Categoria();
		categoriaAgua.setTipoCategoria(CategoriaENUM.Agua);
		dao.salve(categoriaAgua);

		Categoria categoriaBebida = new Categoria();
		categoriaBebida.setTipoCategoria(CategoriaENUM.Bebida);
		dao.salve(categoriaBebida);

		Categoria categoriaGas = new Categoria();
		categoriaGas.setTipoCategoria(CategoriaENUM.Gas);
		dao.salve(categoriaGas);

		Categoria categoriaMarmitex = new Categoria();
		categoriaMarmitex.setTipoCategoria(CategoriaENUM.Marmitex);
		dao.salve(categoriaMarmitex);

		Categoria categoriaPizza = new Categoria();
		categoriaPizza.setTipoCategoria(CategoriaENUM.Pizza);
		dao.salve(categoriaPizza);
	}
}
