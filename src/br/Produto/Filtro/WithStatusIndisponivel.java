package br.Produto.Filtro;

import java.util.List;

import javax.persistence.EntityManager;

import br.Produto.Agua;
import br.Produto.Bebida;
import br.Produto.Gas;
import br.Produto.Lanche;
import br.Produto.Marmitex;
import br.Produto.Pizza;

import com.uaihebert.factory.EasyCriteriaFactory;
import com.uaihebert.model.EasyCriteria;

public class WithStatusIndisponivel implements IFiltroProduto{

	@Override
	public List<Lanche> listarLanche(int idEmpresa, EntityManager em) {
		EasyCriteria<Lanche> easyCriteria = EasyCriteriaFactory
				.createQueryCriteria(em, Lanche.class);
		easyCriteria.innerJoinFetch("empresa").andEquals("empresa.idEmpresa",
				idEmpresa).andEquals("ativo", true);
		return (List<Lanche>) easyCriteria.getResultList();
	}

	@Override
	public List<Bebida> listarBebida(int idEmpresa, EntityManager em) {
		EasyCriteria<Bebida> easyCriteria = EasyCriteriaFactory
				.createQueryCriteria(em, Bebida.class);
		easyCriteria.innerJoinFetch("empresa").andEquals("empresa.idEmpresa",
				idEmpresa);
		return (List<Bebida>) easyCriteria.getResultList();
	}

	@Override
	public List<Marmitex> listarMarmitex(int idEmpresa, EntityManager em) {
		EasyCriteria<Marmitex> easyCriteria = EasyCriteriaFactory
				.createQueryCriteria(em, Marmitex.class);
		easyCriteria.innerJoinFetch("empresa").andEquals("empresa.idEmpresa",
				idEmpresa);
		return (List<Marmitex>) easyCriteria.getResultList();
	}

	@Override
	public List<Pizza> listarPizza(int idEmpresa, EntityManager em) {
		EasyCriteria<Pizza> easyCriteria = EasyCriteriaFactory
				.createQueryCriteria(em, Pizza.class);
		easyCriteria.innerJoinFetch("empresa").andEquals("empresa.idEmpresa",
				idEmpresa);
		return (List<Pizza>) easyCriteria.getResultList();
	}

	@Override
	public List<Agua> listarAgua(int idEmpresa, EntityManager em) {
		EasyCriteria<Agua> easyCriteria = EasyCriteriaFactory
				.createQueryCriteria(em, Agua.class);
		easyCriteria.innerJoinFetch("empresa").andEquals("empresa.idEmpresa",
				idEmpresa);
		return (List<Agua>) easyCriteria.getResultList();
	}

	@Override
	public List<Gas> listarGas(int idEmpresa, EntityManager em) {
		EasyCriteria<Gas> easyCriteria = EasyCriteriaFactory
				.createQueryCriteria(em, Gas.class);
		easyCriteria.innerJoinFetch("empresa").andEquals("empresa.idEmpresa",
				idEmpresa);
		return (List<Gas>) easyCriteria.getResultList();
	}



}
