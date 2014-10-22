package br.Pedido;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.apache.poi.hssf.record.formula.functions.AggregateFunction;
import org.primefaces.model.SortOrder;

import br.Pedido.Filtro.IFiltroPedido;
import br.Pedido.Filtro.PedidoWithFilterByClient;
import br.dao.Dao;

import com.uaihebert.factory.EasyCriteriaFactory;
import com.uaihebert.model.EasyCriteria;

public class PedidoDAO implements Dao<Pedido> {

	private EntityManager session;
	private IFiltroPedido filtro;

	public PedidoDAO() {
		alterarFiltro(new PedidoWithFilterByClient());
	}

	public EntityManager getSession() {
		return session;
	}

	public void alterarFiltro(IFiltroPedido filtro) {
		this.filtro = filtro;
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
			int id, String sortFiel, SortOrder sortOrder) {
		return filtro.buscaPorPaginacao(session, startingAt, maxPerPage, id,
				sortFiel, sortOrder);
	}

	public int countPedido(int id) {
		return filtro.countPedido(session, id);
	}

	public int countPedidosTotal() {

		Query query = session.createQuery("select COUNT(p) from Pedido p");

		Number result = (Number) query.getSingleResult();

		return result.intValue();
	}

	public List<Pedido> pedidosPeloStatus(int idEmpresa) {

		EasyCriteria<Pedido> easyCriteria = EasyCriteriaFactory
				.createQueryCriteria(session, Pedido.class);

		easyCriteria.andBetween("statusPedido", 1, 3);

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
