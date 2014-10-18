package br.Produto.Implementacao;

import java.util.List;

import br.Produto.Agua;
import br.Produto.Produto;
import br.Produto.ProdutoRN;

public class AguaImplementacao implements IProduto {

	@Override
	public void atualizar(int idProduto) {
		ProdutoRN produtoRN = new ProdutoRN();
		try {
			Agua agua = (Agua) produtoRN.getProduto(idProduto);
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

	@Override
	public boolean verificaEstoque(int idProduto,int quantidade) {
		// TODO Auto-generated method stub
		return false;
	}

}
