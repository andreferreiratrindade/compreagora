package br.ProdutoAvulso;

import java.util.List;

import br.Empresa.Categoria.CategoriaENUM;
import br.util.DAOFactoy;

public class AvulsoRN {
	private AvulsoDAO avulsoDAO;

	public AvulsoRN() {
		this.avulsoDAO = DAOFactoy.criarAvulso();
	}

	public Avulso getAvulso(int id) {
		return this.avulsoDAO.getUnico(id);
	}

	public void salvarAvulso(Avulso avulso) {

		this.avulsoDAO.salve(avulso);
	}

	public void atualizarAvulso(Avulso avulso) {
		this.avulsoDAO.update(avulso);
	}

	@SuppressWarnings("unchecked")
	public List<Avulso> listar(Integer idEmpresa, CategoriaENUM tipoAvulso) {

		return this.avulsoDAO.listar(idEmpresa, tipoAvulso);

	}

	@SuppressWarnings("unchecked")
	public List<Avulso> listarTodos(Integer idEmpresa) {

		return this.avulsoDAO.listarTodos(idEmpresa);

	}

	public Avulso buscarPorDescricao(String string) {
		return this.avulsoDAO.buscarPorDescricao(string);

	}

}
