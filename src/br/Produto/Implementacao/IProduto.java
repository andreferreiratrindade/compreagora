package br.Produto.Implementacao;


import java.util.List;

import br.Produto.Produto;

public interface IProduto {
	public void atualizar(Produto produto);
	public List<Produto> getList();
}
