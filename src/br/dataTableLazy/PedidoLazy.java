package br.dataTableLazy;

import java.util.List;
import java.util.Map;

import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

import br.Pedido.Pedido;

public abstract class PedidoLazy extends LazyDataModel<Pedido> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected List<Pedido> pedidos;
	protected int id;

	public PedidoLazy(int id) {
		this.id = id;
	}

	public PedidoLazy() {

	}

	@Override
	public abstract List<Pedido> load(int startingAt, int maxPerPage,
			String sortField, SortOrder sortOrder, Map<String, String> filters);

	@Override
	public Object getRowKey(Pedido pedido) {
		return pedido.getIdPedido();
	}

	@Override
	public Pedido getRowData(String pedidoId) {
		Integer id = Integer.valueOf(pedidoId);

		for (Pedido pedido : pedidos) {
			if (id.equals(pedido.getIdPedido())) {
				return pedido;
			}
		}

		return null;
	}

}
