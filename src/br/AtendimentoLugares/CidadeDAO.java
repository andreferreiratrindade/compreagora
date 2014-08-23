package br.AtendimentoLugares;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import com.uaihebert.factory.EasyCriteriaFactory;
import com.uaihebert.model.EasyCriteria;

import br.dao.Dao;

public class CidadeDAO implements Dao<Cidade> {

	private EntityManager session;

	public EntityManager getSession() {
		return session;
	}

	public void setSession(EntityManager session) {
		this.session = session;
	}

	@Override
	public void salve(Cidade obj) {
		this.session.persist(obj);

	}

	@Override
	public void remove(Cidade obj) {
		this.session.remove(obj);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Cidade> lista() {
		Query query = session.createQuery(" SELECT e FROM cidade e");
		return (List<Cidade>) query.getResultList();
	}

	public List<Cidade> getByDescription(String term) {

		EasyCriteria<Cidade> easyCriteria = EasyCriteriaFactory
				.createQueryCriteria(session, Cidade.class);

		easyCriteria.andStringLike("descCidade", "%"+term+"%");

		return easyCriteria.getResultList();
	}

	@Override
	public void update(Cidade obj) {
		// TODO Auto-generated method stub

	}

	@Override
	public Cidade getUnico(int id) {
		return (Cidade) this.session.find(Cidade.class, id);
	}

}
