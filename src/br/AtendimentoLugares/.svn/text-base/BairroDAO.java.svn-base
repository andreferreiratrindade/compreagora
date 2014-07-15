package br.AtendimentoLugares;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.hibernate.Session;

import com.uaihebert.factory.EasyCriteriaFactory;
import com.uaihebert.model.EasyCriteria;

import br.EnderecoCliente.EnderecoCliente;
import br.dao.Dao;

public class BairroDAO implements Dao<Bairro> {
	private EntityManager session;

	public EntityManager getSession() {
		return session;
	}

	public void setSession(EntityManager session) {
		this.session = session;
	}

	@Override
	public void salve(Bairro obj) {
		this.session.persist(obj);
	}

	@Override
	public void remove(Bairro obj) {
		this.session.remove(obj);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Bairro> lista() {
		Query query = session.createQuery(" SELECT e FROM bairro e");
		return (List<Bairro>) query.getResultList();
	}

	@Override
	public void update(Bairro obj) {
		// TODO Auto-generated method stub

	}

	@Override
	public Bairro getUnico(int id) {
		return (Bairro) this.session.find(Bairro.class, id);
	}

	public Bairro buscarPorDescricao(String string) {
		EasyCriteria<Bairro> easyCriteria = EasyCriteriaFactory
				.createQueryCriteria(session, Bairro.class);
		easyCriteria.andEquals("descBairro", string);

		return easyCriteria.getResultList().get(0);
	}
}
