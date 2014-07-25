package br.beans;


import java.util.List;

import javax.faces.model.ListDataModel;

import org.primefaces.model.SelectableDataModel;

import br.Pedido.Pedido;

public class PedidoDataModel extends ListDataModel<Pedido> implements SelectableDataModel<Pedido>{

	public PedidoDataModel(){
		
	}
	public PedidoDataModel(List<Pedido> data){
		super(data);
	}
	@Override
	public Pedido getRowData(String rowKey) {
		// TODO Auto-generated method stub
		@SuppressWarnings("unchecked")
		List<Pedido> pedidos = (List<Pedido>) getWrappedData();
		for(Pedido pedido : pedidos){
			if(pedido.getIdPedido() == Integer.parseInt(rowKey));
			return pedido;
		}
		return null;
	}

	@Override
	public Object getRowKey(Pedido pedido) {
		// TODO Auto-generated method stub
		
		return pedido.getIdPedido();
	}

}
