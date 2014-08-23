package br.AtendimentoLugares;

import java.util.List;

import br.util.DAOFactoy;

import com.uaihebert.factory.EasyCriteriaFactory;
import com.uaihebert.model.EasyCriteria;

public class CidadeRN {
	private CidadeDAO cidadeDAO;

	public CidadeRN() {
		this.cidadeDAO = DAOFactoy.criarCidade();
	}

	public Cidade getCidade(int id) {
		return this.cidadeDAO.getUnico(id);
	}

	public void salvar(Cidade cidade) {
		this.cidadeDAO.salve(cidade);
	}

	public List<Cidade> listar() {
		return this.cidadeDAO.lista();
	}

	public Cidade buscarPorDescricao(String string) {
		EasyCriteria<Cidade> easyCriteria = EasyCriteriaFactory
				.createQueryCriteria(cidadeDAO.getSession(), Cidade.class);
		easyCriteria.andEquals("descCidade", string);
		Cidade cidade = (Cidade) easyCriteria.getResultList().get(0);

		return cidade;
	}

	public List<Cidade> getByDescription(String term) {

		return this.cidadeDAO.getByDescription(term);
	}
}
