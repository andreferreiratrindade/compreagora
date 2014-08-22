package br.Empresa.Categoria;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import com.uaihebert.factory.EasyCriteriaFactory;
import com.uaihebert.model.EasyCriteria;

import br.Cliente.Cliente;
import br.Empresa.Empresa;
import br.dao.Dao;

public class CategoriaDao implements Dao<Categoria> {

	private EntityManager session;

	public EntityManager getSession() {
		return session;
	}

	public void setSession(EntityManager session) {
		this.session = session;
	}

	@Override
	public void salve(Categoria obj) {
		this.session.persist(obj);
	}

	@Override
	public void remove(Categoria obj) {
		this.session.remove(obj);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Categoria> lista() {
		Query query = session.createQuery(" SELECT e FROM categoria e");
		return (List<Categoria>) query.getResultList();
	}

	@Override
	public void update(Categoria obj) {
		session.merge(obj);
	}

	@Override
	public Categoria getUnico(int id) {
		return this.session.find(Categoria.class, id);
	}



	public Categoria getCategoriaComEnum(CategoriaENUM tipoCategoria) {

		EasyCriteria<Categoria> easyCriteria = EasyCriteriaFactory
				.createQueryCriteria(session, Categoria.class);
		easyCriteria.andEquals("tipoCategoria", tipoCategoria);

		return easyCriteria.getSingleResult();
	}

}
