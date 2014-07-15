package br.AvulsoImplementacao;

import java.util.List;

import br.ProdutoAvulso.Avulso;
import br.ProdutoAvulso.AvulsoRN;

public class AvulsoLancheImp implements AvulsoImp {

	@Override
	public List<Avulso> getAvulsos(int idEmpresa,
			List<Avulso> pedidoProdutoAvulsos) {
		
		
		
		
		AvulsoRN avulsoRN = new AvulsoRN();
		List<Avulso> avulsos = null;
		// avulsos = avulsoRN.listarLanche(idEmpresa);
		if (pedidoProdutoAvulsos.size() != avulsos.size()) {
			for (Avulso x : pedidoProdutoAvulsos) {

				for (Avulso y : avulsos) {
					if (x.getIdAvulso() == y.getIdAvulso()) {
						avulsos.remove(y);
					}
				}
			}
		} else {
			avulsos.clear();
		}
		return avulsos;
	}

}
