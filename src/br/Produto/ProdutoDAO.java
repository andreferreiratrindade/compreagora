package br.Produto;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import br.Pedido.Pedido;
import br.dao.Dao;

import com.uaihebert.factory.EasyCriteriaFactory;
import com.uaihebert.model.EasyCriteria;

public class ProdutoDAO implements Dao<Produto> {

	private EntityManager em;

	public EntityManager getEm() {
		return em;
	}

	public void setEm(EntityManager em) {
		this.em = em;
	}

	@Override
	public void salve(Produto obj) {
		em.persist(obj);
	}

	@Override
	public void remove(Produto obj) {

	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Produto> lista() {
		Query query = em
				.createQuery(" SELECT e FROM produto e where idEmpresa = 25");
		return (List<Produto>) query.getResultList();
	}

	@SuppressWarnings("unchecked")
	public List<Lanche> listaLanche(int idEmpresa) {

		EasyCriteria<Lanche> easyCriteria = EasyCriteriaFactory
				.createQueryCriteria(em, Lanche.class);
		easyCriteria.innerJoinFetch("empresa").andEquals("empresa.idEmpresa",
				idEmpresa);
		return (List<Lanche>) easyCriteria.getResultList();
	}

	@SuppressWarnings("unchecked")
	public List<Bebida> listaBebida(int idEmpresa) {

		EasyCriteria<Bebida> easyCriteria = EasyCriteriaFactory
				.createQueryCriteria(em, Bebida.class);
		easyCriteria.innerJoinFetch("empresa").andEquals("empresa.idEmpresa",
				idEmpresa);
		return (List<Bebida>) easyCriteria.getResultList();
	}

	@SuppressWarnings("unchecked")
	public List<Produto> listaProduto(int idEmpresa) {

		EasyCriteria<Produto> easyCriteria = EasyCriteriaFactory
				.createQueryCriteria(em, Produto.class);
		easyCriteria.innerJoinFetch("empresa").andEquals("empresa.idEmpresa",
				idEmpresa);

		return (List<Produto>) easyCriteria.getResultList();
	}

	@Override
	public void update(Produto obj) {
		em.merge(obj);
	}

	@Override
	public Produto getUnico(int id) {

		return this.em.find(Produto.class, id);

	}

	public Integer ultimoElementoAdicionado() {

		String hql = "select max(idProduto) from produto";

		Query query = em.createQuery(hql);
		return (Integer) query.getResultList().get(0);
	}

	public List<Pizza> listaPizza(int idEmpresa) {
		EasyCriteria<Pizza> easyCriteria = EasyCriteriaFactory
				.createQueryCriteria(em, Pizza.class);
		easyCriteria.innerJoinFetch("empresa").andEquals("empresa.idEmpresa",
				idEmpresa);
		return (List<Pizza>) easyCriteria.getResultList();
	}

	public List<Gas> listaGas(int idEmpresa) {
		EasyCriteria<Gas> easyCriteria = EasyCriteriaFactory
				.createQueryCriteria(em, Gas.class);
		easyCriteria.innerJoinFetch("empresa").andEquals("empresa.idEmpresa",
				idEmpresa);
		return (List<Gas>) easyCriteria.getResultList();
	}

	public List<Agua> listaAgua(int idEmpresa) {
		EasyCriteria<Agua> easyCriteria = EasyCriteriaFactory
				.createQueryCriteria(em, Agua.class);
		easyCriteria.innerJoinFetch("empresa").andEquals("empresa.idEmpresa",
				idEmpresa);
		return (List<Agua>) easyCriteria.getResultList();
	}

	public List<Marmitex> listaMarmitex(int idEmpresa) {
		EasyCriteria<Marmitex> easyCriteria = EasyCriteriaFactory
				.createQueryCriteria(em, Marmitex.class);
		easyCriteria.innerJoinFetch("empresa").andEquals("empresa.idEmpresa",
				idEmpresa);
		return (List<Marmitex>) easyCriteria.getResultList();
	}

	public List<Lanche> buscaPorPaginacaoLanche(int startingAt, int maxPerPage,
			int idEmpresa) {
		EasyCriteria<Lanche> easyCriteria = EasyCriteriaFactory
				.createQueryCriteria(em, Lanche.class);
		easyCriteria.innerJoinFetch("empresa").andEquals("empresa.idEmpresa",
				idEmpresa);
		easyCriteria.setFirstResult(startingAt);
		easyCriteria.setMaxResults(maxPerPage);

		return (List<Lanche>) easyCriteria.getResultList();
	}
}
