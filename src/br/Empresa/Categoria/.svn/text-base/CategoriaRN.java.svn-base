package br.Empresa.Categoria;

import java.util.List;

import br.util.DAOFactoy;

public class CategoriaRN {
	private CategoriaDao dao;

	public CategoriaRN() {
		dao = DAOFactoy.criarCategoria();
	}

	public List<Categoria> listar() {
		return this.dao.lista();
	}

	public void salvar(Categoria cat) {
		this.dao.salve(cat);
	}
}
