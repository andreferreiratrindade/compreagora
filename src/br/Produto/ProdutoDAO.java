package br.Produto;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import br.Produto.Filtro.IFiltroProduto;
import br.Produto.Filtro.PadraoProduto;
import br.dao.Dao;

import com.uaihebert.factory.EasyCriteriaFactory;
import com.uaihebert.model.EasyCriteria;

public class ProdutoDAO implements Dao<Produto> {

	private EntityManager em;
	private IFiltroProduto filtro;

	public ProdutoDAO() {
		this.filtro = new PadraoProduto();
	}

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

	
	public List<Lanche> listaLanche(int idEmpresa) {

		return filtro.listarLanche(idEmpresa, em);

	}

	
	public List<Bebida> listaBebida(int idEmpresa) {

		return filtro.listarBebida(idEmpresa, em);
	}

	
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
		return filtro.listarPizza(idEmpresa, em);
	}

	public List<Gas> listaGas(int idEmpresa) {
		return filtro.listarGas(idEmpresa, em);
	}

	public List<Agua> listaAgua(int idEmpresa) {
		return filtro.listarAgua(idEmpresa, em);
	}

	public List<Marmitex> listaMarmitex(int idEmpresa) {
		return filtro.listarMarmitex(idEmpresa, em);
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

	public void alteraFiltro(IFiltroProduto filtro) {
		this.filtro = filtro;
	}
}
