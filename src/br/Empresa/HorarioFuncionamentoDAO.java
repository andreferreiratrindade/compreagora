package br.Empresa;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import br.dao.Dao;

public class HorarioFuncionamentoDAO implements Dao<HorarioFuncionamento> {

	private EntityManager session;

	public EntityManager getSession() {
		return session;
	}

	public void setSession(EntityManager session) {
		this.session = session;
	}

	@Override
	public void salve(HorarioFuncionamento obj) {
		session.persist(obj);
	}

	@Override
	public void remove(HorarioFuncionamento obj) {
		session.remove(obj);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<HorarioFuncionamento> lista() {
		Query query = session
				.createQuery(" SELECT e FROM horariofuncionamento e");
		return (List<HorarioFuncionamento>) query.getResultList();
	}

	@Override
	public void update(HorarioFuncionamento obj) {
		session.merge(obj);
	}

	@Override
	public HorarioFuncionamento getUnico(int id) {

		return session.find(HorarioFuncionamento.class, id);
	}

}
