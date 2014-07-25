package br.Empresa.FormaDePagamento;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import br.dao.Dao;

import com.uaihebert.factory.EasyCriteriaFactory;
import com.uaihebert.model.EasyCriteria;

public class FormaDePagamentoDao implements Dao<FormaDePagamento> {
	private EntityManager session;

	@Override
	public void salve(FormaDePagamento obj) {
		session.persist(obj);
	}

	@Override
	public void remove(FormaDePagamento obj) {

	}

	@SuppressWarnings("unchecked")
	@Override
	public List<FormaDePagamento> lista() {

		Query query = session.createQuery(" SELECT e FROM formadepagamento e");
		return (List<FormaDePagamento>) query.getResultList();
	}

	@Override
	public void update(FormaDePagamento obj) {

	}

	@Override
	public FormaDePagamento getUnico(int id) {
		return this.session.find(FormaDePagamento.class, id);
	}

	public EntityManager getSession() {
		return session;
	}

	public void setSession(EntityManager session) {
		this.session = session;
	}

	public FormaDePagamento pesquisaPeloTipo(String string) {
		EasyCriteria<FormaDePagamento> easyCriteria = EasyCriteriaFactory
				.createQueryCriteria(session, FormaDePagamento.class);

		easyCriteria.andEquals("tipo", string);
		return easyCriteria.getSingleResult();
	}

	

	
}
