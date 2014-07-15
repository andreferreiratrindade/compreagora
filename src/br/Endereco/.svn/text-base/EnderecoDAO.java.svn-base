package br.Endereco;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import br.Endereco.Endereco;
import br.dao.Dao;


public class EnderecoDAO implements Dao<Endereco> {
	
	private EntityManager session;

	public EntityManager getSession() {
		return session;
	}

	public void setSession(EntityManager session) {
		this.session = session;
	}

	@Override
	public void salve(Endereco obj) {
		session.persist(obj);
	}

	@Override
	public void remove(Endereco obj) {
		session.remove(obj);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Endereco> lista() {
		Query query = session.createQuery(" SELECT e FROM endereco e");
		return (List<Endereco>) query.getResultList();
	}

	@Override
	public void update(Endereco obj) {
		// TODO Auto-generated method stub

	}

	@Override
	public Endereco getUnico(int id) {
		return this.session.find(Endereco.class, id);
	}


}
