package br.Produto.ProdutoAvulso;

import java.util.List;

import javax.persistence.EntityManager;

import br.dao.Dao;

public class ProdutoAvulsoDAO implements Dao<ProdutoAvulso> {
	private EntityManager session;

	public EntityManager getSession() {
		return session;
	}

	public void setSession(EntityManager session) {
		this.session = session;
	}

	@Override
	public void salve(ProdutoAvulso obj) {
		session.persist(obj);
	}

	@Override
	public void remove(ProdutoAvulso obj) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<ProdutoAvulso> lista() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void update(ProdutoAvulso obj) {
		session.merge(obj);
	}

	@Override
	public ProdutoAvulso getUnico(int id) {
		return this.session.find(ProdutoAvulso.class, id);
	}


}
