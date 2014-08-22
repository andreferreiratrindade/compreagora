package br.Empresa.Categoria;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import com.uaihebert.factory.EasyCriteriaFactory;
import com.uaihebert.model.EasyCriteria;

import br.Empresa.Empresa;
import br.dao.Dao;

public class CategoriaEmpresaDao implements Dao<CategoriaEmpresa> {

	private EntityManager session;

	public EntityManager getSession() {
		return session;
	}

	public void setSession(EntityManager session) {
		this.session = session;
	}

	@Override
	public void salve(CategoriaEmpresa obj) {
		session.persist(obj);
	}

	@Override
	public void remove(CategoriaEmpresa obj) {
		// TODO Auto-generated method stub

	}

	@SuppressWarnings("unchecked")
	@Override
	public List<CategoriaEmpresa> lista() {
		Query query = session.createQuery(" SELECT e FROM CategoriaEmpresa e");
		return (List<CategoriaEmpresa>) query.getResultList();
	}

	@Override
	public void update(CategoriaEmpresa obj) {
		// TODO Auto-generated method stub

	}

	@Override
	public CategoriaEmpresa getUnico(int id) {
		// TODO Auto-generated method stub
		return null;
	}


}
