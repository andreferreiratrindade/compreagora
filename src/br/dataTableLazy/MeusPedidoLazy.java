package br.dataTableLazy;

import java.util.List;
import java.util.Map;

import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

import br.Pedido.Pedido;
import br.Pedido.PedidoRN;

public class MeusPedidoLazy extends LazyDataModel<Pedido> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private List<Pedido> pedidos;
	private int idCliente;

	public MeusPedidoLazy(int idCliente) {
		this.idCliente = idCliente;
	}

	@Override
	public List<Pedido> load(int startingAt, int maxPerPage, String sortField,
			SortOrder sortOrder, Map<String, String> filters) {
		
		String ordenacao = sortOrder.toString();
		
		if(SortOrder.UNSORTED.equals(sortOrder)){
			ordenacao = SortOrder.ASCENDING.toString();
		}
		
		
		
		PedidoRN pedidoRN = new PedidoRN();
		pedidos = pedidoRN.buscaPorPaginacao(startingAt, maxPerPage, idCliente);

		if (getRowCount() <= 0) {
			setRowCount(pedidoRN.countPedido(idCliente));
		}
		setPageSize(maxPerPage);
		return pedidos;
	}

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

	public List<Pedido> getPedidos() {
		return pedidos;
	}

	public void setPedidos(List<Pedido> pedidos) {
		this.pedidos = pedidos;
	}
}
