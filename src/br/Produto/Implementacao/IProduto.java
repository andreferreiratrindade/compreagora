package br.Produto.Implementacao;

import java.util.List;

import br.Produto.Produto;

public interface IProduto {
	public void atualizar(int idProduto);

	public List<Produto> getList();

	public boolean verificaEstoque(int idProduto, int quantidade);
}
