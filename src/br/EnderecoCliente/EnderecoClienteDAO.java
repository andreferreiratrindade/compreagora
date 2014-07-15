package br.EnderecoCliente;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.hibernate.Session;

import br.Cliente.Cliente;
import br.Endereco.Endereco;
import br.dao.Dao;

public class EnderecoClienteDAO implements Dao<EnderecoCliente> {
	private EntityManager session;

	public void setSession(EntityManager session) {
		this.session = session;
	}

	@Override
	public void salve(EnderecoCliente obj) {
		this.session.persist(obj);

	}

	@Override
	public void remove(EnderecoCliente obj) {
		this.session.remove(obj);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<EnderecoCliente> lista() {
		Query query = session.createQuery(" SELECT e FROM enderecocliente e");
		return (List<EnderecoCliente>) query.getResultList();
	}

	@Override
	public void update(EnderecoCliente obj) {
		// TODO Criar metodo para atualizar este campo

	}

	@Override
	public EnderecoCliente getUnico(int id) {
		return (EnderecoCliente) this.session.find(EnderecoCliente.class, id);
	}


	
}
