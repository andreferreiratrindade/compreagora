package br.PedidoProduto;

import java.util.List;

import javax.persistence.EntityManager;

import br.dao.Dao;

public class PedidoProdutoDAO implements Dao<PedidoProduto> {
	private EntityManager session;

	public EntityManager getSession() {
		return session;
	}

	public void setSession(EntityManager session) {
		this.session = session;
	}

	@Override
	public void salve(PedidoProduto obj) {
		session.persist(obj);
	}

	@Override
	public void remove(PedidoProduto obj) {

	}

	@Override
	public List<PedidoProduto> lista() {
		return null;
	}

	@Override
	public void update(PedidoProduto obj) {
		session.merge(obj);
	}

	@Override
	public PedidoProduto getUnico(int id) {
		return session.find(PedidoProduto.class, id);
	}

}
