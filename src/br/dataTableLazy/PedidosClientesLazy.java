package br.dataTableLazy;

import java.util.List;
import java.util.Map;

import org.primefaces.model.SortOrder;

import br.Pedido.Pedido;
import br.Pedido.PedidoRN;
import br.Pedido.Filtro.PedidoWithFilterByIdEmpresa;

public class PedidosClientesLazy extends PedidoLazy {

	public PedidosClientesLazy(int id) {
		super(id);
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public List<Pedido> load(int startingAt, int maxPerPage, String sortField,
			SortOrder sortOrder, Map<String, String> filters) {
		
		if (sortField == null) {
			sortField = "statusPedido";
		}

		if (sortOrder == null) {
			sortOrder = SortOrder.ASCENDING;
		}
		PedidoRN pedidoRN = new PedidoRN();
		pedidoRN.alterarFiltro(new PedidoWithFilterByIdEmpresa());
		pedidos = pedidoRN.buscaPorPaginacao(startingAt, maxPerPage, id,
				sortField, sortOrder);

		if (getRowCount() <= 0) {
			setRowCount(pedidoRN.countPedido(id));
		}
		setPageSize(maxPerPage);

		return pedidos;
	}

}
