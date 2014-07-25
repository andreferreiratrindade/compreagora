package br.dataTableLazy;

import java.util.List;
import java.util.Map;

import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

import br.Produto.Lanche;
import br.Produto.ProdutoRN;

public class LancheLazy extends LazyDataModel<Lanche> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<Lanche> listLanche;
	private int idEmpresa;

	public LancheLazy(int id) {

		idEmpresa = id;

	}

	@Override
	public List<Lanche> load(int startingAt, int maxPerPage, String sortField,
			SortOrder sortOrder, Map<String, String> filters) {
		ProdutoRN produtoRN = new ProdutoRN();
		listLanche = produtoRN.buscaPorPaginacaoLanche(startingAt, maxPerPage,
				idEmpresa);

		setRowCount(listLanche.size());
		return listLanche;
	}

	@Override
	public Object getRowKey(Lanche obj) {
		return obj.getIdProduto();
	}

	@Override
	public Lanche getRowData(String idLanche) {
		Integer id = Integer.valueOf(idLanche);

		for (Lanche lanche : listLanche) {
			if (id.equals(lanche.getIdProduto())) {
				return lanche;
			}
		}

		return null;
	}
}
