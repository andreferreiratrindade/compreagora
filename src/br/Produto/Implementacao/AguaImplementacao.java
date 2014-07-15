package br.Produto.Implementacao;

import java.util.List;

import br.Produto.Agua;
import br.Produto.Produto;
import br.Produto.ProdutoRN;

public class AguaImplementacao implements IProduto {

	@Override
	public void atualizar(Produto produto) {
		ProdutoRN produtoRN = new ProdutoRN();
		try {
			Agua agua = (Agua) produtoRN.getProduto(produto.getIdProduto());
			agua.implementaEstoque();

			produtoRN.atualizarProduto(agua);
		} catch (Exception e) {
			System.out.println("Terminou com erro --- ");
		}
	}

	@Override
	public List<Produto> getList() {
		// TODO Auto-generated method stub
		return null;
	}

}
