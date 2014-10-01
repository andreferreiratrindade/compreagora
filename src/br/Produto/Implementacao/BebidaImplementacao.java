package br.Produto.Implementacao;

import java.util.List;

import br.Produto.Bebida;
import br.Produto.Produto;
import br.Produto.ProdutoRN;

public class BebidaImplementacao implements IProduto {
	private List<Produto> lista;

	@Override
	public void atualizar(int idProduto) {
		ProdutoRN produtoRN = new ProdutoRN();
		try {
			Bebida bebida = (Bebida) produtoRN.getProduto(idProduto);
			bebida.implementaEstoque();

			produtoRN.atualizarProduto(bebida);
		} catch (Exception e) {
			System.out.println("Terminou com erro --- ");
		}
	}

	@Override
	public List<Produto> getList() {
		return lista;
	}
}
