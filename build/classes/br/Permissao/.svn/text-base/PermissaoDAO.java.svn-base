package br.Permissao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import br.dao.Dao;

public class PermissaoDAO implements Dao<Permissao> {

	private EntityManager session;

	public EntityManager getSession() {
		return session;
	}

	public void setSession(EntityManager session) {
		this.session = session;
	}

	@Override
	public void salve(Permissao obj) {
		session.persist(obj);

	}

	@Override
	public void remove(Permissao obj) {
		session.remove(obj);

	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Permissao> lista() {
		Query query = session.createQuery(" SELECT e FROM permissao e");
		return (List<Permissao>) query.getResultList();
	}

	@Override
	public void update(Permissao obj) {
		// TODO Auto-generated method stub

	}

	@Override
	public Permissao getUnico(int id) {

		return this.session.find(Permissao.class, id);
	}



}
