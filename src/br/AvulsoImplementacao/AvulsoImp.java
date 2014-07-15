package br.AvulsoImplementacao;

import java.util.List;

import br.ProdutoAvulso.Avulso;

public interface AvulsoImp {
	public List<Avulso> getAvulsos(int idEmpresa,
			List<Avulso> pedidoProdutoAvulsos);
}
