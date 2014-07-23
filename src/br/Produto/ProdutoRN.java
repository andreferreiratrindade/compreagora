package br.Produto;

import java.util.List;

import br.util.DAOFactoy;

public class ProdutoRN {
	private ProdutoDAO produtoDAO;

	public ProdutoRN() {
		this.produtoDAO = DAOFactoy.criarProduto();
	}

	public Produto getProduto(int id) {
		return this.produtoDAO.getUnico(id);
	}

	@SuppressWarnings("unchecked")
	public List<Lanche> listarLanche(int idEmpresa) {

		return this.produtoDAO.listaLanche(idEmpresa);

	}

	@SuppressWarnings("unchecked")
	public List<Bebida> listarBebida(int idEmpresa) {

		List<Bebida> bebidas = this.produtoDAO.listaBebida(idEmpresa);
		return bebidas;
	}

	public void atualizarProduto(Produto produto) {
		this.produtoDAO.update(produto);
	}

	public void excluirProduto() {

	}

	public Integer ultimoElementoAdicionado() {
		return this.produtoDAO.ultimoElementoAdicionado();
	}

	public void salve(Produto obj) {
		this.produtoDAO.salve(obj);
	}

	public List<Produto> listarProdutos(int idEmpresa) {
		return this.produtoDAO.listaProduto(idEmpresa);
	}

	public List<Pizza> listarPizza(int idEmpresa) {
		return this.produtoDAO.listaPizza(idEmpresa);
	}

	public List<Agua> listarAgua(int idEmpresa) {
		return this.produtoDAO.listaAgua(idEmpresa);
	}

	public List<Gas> listarGas(int idEmpresa) {
		return this.produtoDAO.listaGas(idEmpresa);
	}

	public List<Marmitex> listarMarmitex(int idEmpresa) {

		return this.produtoDAO.listaMarmitex(idEmpresa);
	}

	public List<Lanche> buscaPorPaginacaoLanche(int startingAt, int maxPerPage,
			int idEmpresa) {
		
		return this.produtoDAO.buscaPorPaginacaoLanche(startingAt, maxPerPage,
				idEmpresa);
	}

}
