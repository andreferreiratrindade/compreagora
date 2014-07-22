package br.Pedido;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import com.uaihebert.factory.EasyCriteriaFactory;
import com.uaihebert.model.EasyCriteria;

import br.Pedido.Pedido;
import br.dao.Dao;

public class PedidoDAO implements Dao<Pedido> {

	private EntityManager session;

	/*
	 * @SuppressWarnings("unchecked") public List<Pedido> listar() {
	 * 
	 * return this.session.createCriteria(Pedido.class)
	 * .addOrder(Order.asc("status")).list(); }
	 * 
	 * 
	 * public Integer ultimoElementoAdicionado(int idCliente) { String hql =
	 * "select max(idPedido) from pedido"; Query consulta =
	 * this.session.createQuery(hql); return (Integer) consulta.uniqueResult();
	 * 
	 * }
	 */

	public EntityManager getSession() {
		return session;
	}

	public void setSession(EntityManager session) {
		this.session = session;
	}

	@Override
	public void salve(Pedido obj) {
		session.persist(obj);
	}

	@Override
	public void remove(Pedido obj) {
		session.remove(obj);
	}

	@Override
	public List<Pedido> lista() {

		return null;
	}

	@Override
	public void update(Pedido obj) {
		session.merge(obj);
	}

	@Override
	public Pedido getUnico(int id) {

		return this.session.find(Pedido.class, id);
	}

	@SuppressWarnings("unchecked")
	public List<Pedido> buscaPorPaginacao(int startingAt, int maxPerPage) {
				
		// regular query that will search for players in the db
		Query query = session.createQuery("select p from pedido p");
		query.setFirstResult(startingAt);
		query.setMaxResults(maxPerPage);

		return query.getResultList();
	}
	
	public int countPedidosTotal() {
	
		Query query = session.createQuery("select COUNT(p) from Pedido p");

		Number result = (Number) query.getSingleResult();
		
		return result.intValue();
	}	
	
	public List<Pedido> pedidosPeloStatus(int idEmpresa) {

		EasyCriteria<Pedido> easyCriteria = EasyCriteriaFactory
				.createQueryCriteria(session, Pedido.class);

		easyCriteria.andBetween("statusPedido", 1, 4);

		return (List<Pedido>) easyCriteria.getResultList();
	}

	public List<Pedido> relatorioDeFaturamento(int idEmpresa, Date dataInicio,
			Date dataFim) {

		EasyCriteria<Pedido> easyCriteria = EasyCriteriaFactory
				.createQueryCriteria(session, Pedido.class);

		easyCriteria.andBetween("dataHoraIn", dataInicio, dataFim)
				.andEquals("statusPedido", 4).innerJoin("empresa")
				.andEquals("empresa.idEmpresa", idEmpresa);

		return easyCriteria.getResultList();
	}
}
