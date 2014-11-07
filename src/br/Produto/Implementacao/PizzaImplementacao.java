package br.Produto.Implementacao;

import java.util.List;

import br.Produto.Pizza;
import br.Produto.Produto;
import br.Produto.ProdutoRN;

public class PizzaImplementacao implements IProduto {

	@Override
	public void atualizar(int idProduto) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<Produto> getList() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean verificaEstoque(int idProduto, int quantidade) {
		ProdutoRN produtoRN = new ProdutoRN();
		Pizza pizza = (Pizza) produtoRN.getProduto(idProduto);
		return pizza.isAtivo();
	}

}
