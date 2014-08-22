package br.statusPedido;

import br.Pedido.Pedido;
import br.Pedido.PedidoRN;

public class Enviado implements StatusInterface {

	@Override
	public int status() {
		// TODO Auto-generated method stub
		return 4;
	}

	@Override
	public void atualiza(Pedido pedido) {
		// TODO Auto-generated method stub
		pedido.setStatusPedido(status());
		PedidoRN pedidoRN = new PedidoRN();
		pedidoRN.atualizar(pedido);
	}

	@Override
	public String atual() {
		// TODO Auto-generated method stub
		return "Enviado";
	}

	@Override
	public void apagarArquivo() {
		// TODO Auto-generated method stub

	}

	@Override
	public String getArquivo() {
		// TODO Auto-generated method stub
		return null;
	}

}
