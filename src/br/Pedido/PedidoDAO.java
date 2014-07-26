package br.Pedido;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import br.dao.Dao;

import com.uaihebert.factory.EasyCriteriaFactory;
import com.uaihebert.model.EasyCriteria;

public class PedidoDAO implements Dao<Pedido> {

	private EntityManager session;

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

	public List<Pedido> buscaPorPaginacao(int startingAt, int maxPerPage,
			int idCliente) {

		EasyCriteria<Pedido> easyCriteria = EasyCriteriaFactory
				.createQueryCriteria(session, Pedido.class);
		easyCriteria.innerJoin("enderecoCliente")
				.innerJoin("enderecoCliente.cliente")
				.andEquals("enderecoCliente.cliente.idCliente", idCliente).orderByAsc("statusPedido");
		easyCriteria.setFirstResult(startingAt);
		easyCriteria.setMaxResults(maxPerPage);

		return easyCriteria.getResultList();
	}

	public int countPedido(int idCliente) {
		EasyCriteria<Pedido> easyCriteria = EasyCriteriaFactory
				.createQueryCriteria(session, Pedido.class);
		easyCriteria.innerJoin("enderecoCliente")
				.innerJoin("enderecoCliente.cliente")
				.andEquals("enderecoCliente.cliente.idCliente", idCliente);

		List<Pedido> pedidos = easyCriteria.getResultList();
		if (pedidos == null) {
			return 0;
		}
		return pedidos.size();
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
