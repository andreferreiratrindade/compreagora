package br.Cliente;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import com.uaihebert.factory.EasyCriteriaFactory;
import com.uaihebert.model.EasyCriteria;

import br.dao.Dao;
import br.util.JpaUtil;

public class ClienteDAO implements Dao<Cliente> {
	private EntityManager session;

	public EntityManager getSession() {
		return session;
	}

	public void startConnection() {
		session.getTransaction().begin();
	}

	public void closeConnection() {
		session.getTransaction().commit();
		session.close();
	}

	public void setSession(EntityManager session) {
		this.session = session;
	}

	public Cliente buscarPorEmail(String email) {
		EasyCriteria<Cliente> easyCriteria = EasyCriteriaFactory
				.createQueryCriteria(session, Cliente.class);
		if (email != null) {
			easyCriteria.andEquals("email", email);
		}

		return (Cliente) easyCriteria.getSingleResult();
	}

	public boolean verificaEmailCadastrado(String email) {
		EasyCriteria<Cliente> easyCriteria = EasyCriteriaFactory
				.createQueryCriteria(session, Cliente.class);
		if (email != null) {
			easyCriteria.andEquals("email", email);
		}

		if (easyCriteria.getResultList().isEmpty()) {
			return true;
		} else {
			return false;
		}
	}

	public Cliente buscarPorLogin(String login) { // TODO Auto-generated
		String hql = "select u from cliente u where u.login = :login";
		Query consulta = this.session.createQuery(hql);
		// consulta.setString("login", login);
		return null;// (Cliente) consulta.uniqueResult();
	}

	@Override
	public void salve(Cliente obj) {
		session.persist(obj);

	}

	@Override
	public void remove(Cliente obj) {
		this.session.remove(obj);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Cliente> lista() {
		Query query = session.createQuery(" SELECT e FROM cliente e");
		return (List<Cliente>) query.getResultList();
	}

	@Override
	public void update(Cliente obj) {
		session.merge(obj);
	}

	@Override
	public Cliente getUnico(int id) {

		return this.session.find(Cliente.class, id);
	}

	public Cliente realizarLoginCliente(String login, String senha) {
		
		EasyCriteria<Cliente> easyCriteria = EasyCriteriaFactory.createQueryCriteria(session, Cliente.class);
		easyCriteria.andEquals("email", login).andEquals("senha", senha);
		
		return easyCriteria.getSingleResult();
	}
}
