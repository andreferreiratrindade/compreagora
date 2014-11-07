package br.Produto.Filtro;

import java.util.List;

import javax.persistence.EntityManager;

import br.Produto.Agua;
import br.Produto.Bebida;
import br.Produto.Gas;
import br.Produto.Lanche;
import br.Produto.Marmitex;
import br.Produto.Pizza;

public interface IFiltroProduto {
	public List<Lanche> listarLanche(int idEmpresa, EntityManager em);

	public List<Bebida> listarBebida(int idEmpresa, EntityManager em);

	public List<Marmitex> listarMarmitex(int idEmpresa, EntityManager em);

	public List<Pizza> listarPizza(int idEmpresa, EntityManager em);

	public List<Agua> listarAgua(int idEmpresa, EntityManager em);

	public List<Gas> listarGas(int idEmpresa, EntityManager em);
}
