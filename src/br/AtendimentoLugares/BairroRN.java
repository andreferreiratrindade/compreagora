package br.AtendimentoLugares;

import java.util.List;

import br.util.DAOFactoy;

import com.uaihebert.factory.EasyCriteriaFactory;
import com.uaihebert.model.EasyCriteria;

public class BairroRN {

	private BairroDAO bairroDAO;

	public BairroRN() {
		this.bairroDAO = DAOFactoy.criarBairro();
	}

	public Bairro getBairro(int id) {
		return this.bairroDAO.getUnico(id);
	}

	public void salvar(Bairro bairro) {
		this.bairroDAO.salve(bairro);
	}

	public List<Bairro> listar() {
		return this.bairroDAO.lista();
	}

	public List<Bairro> getByDescription(int idCidade, String term) {
		return this.bairroDAO.getByDescription(idCidade, term);
	}

	public Bairro buscarPorDescricao(String string) {

		EasyCriteria<Bairro> easyCriteria = EasyCriteriaFactory
				.createQueryCriteria(bairroDAO.getSession(), Bairro.class);
		easyCriteria.andEquals("descBairro", string);
		Bairro bairro = (Bairro) easyCriteria.getResultList().get(0);
		return bairro;
	}

	public Bairro buscarPorCidade(String string) {
		EasyCriteria<Bairro> easyCriteria = EasyCriteriaFactory
				.createQueryCriteria(bairroDAO.getSession(), Bairro.class);
		easyCriteria.andEquals("descBairro", string);
		Bairro bairro = (Bairro) easyCriteria.getResultList().get(0);
		return bairro;
	}
}
