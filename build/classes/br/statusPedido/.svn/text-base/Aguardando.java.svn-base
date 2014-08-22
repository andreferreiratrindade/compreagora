package br.statusPedido;

import br.Empresa.Empresa;
import br.Pedido.Pedido;
import br.Pedido.PedidoRN;
import br.relatorio.impressora.ImpressoraNaoFiscal;
import br.relatorio.impressora.ImpressoraPreparo;

public class Aguardando implements StatusInterface {

	private ImpressoraNaoFiscal imp;

	@Override
	public int status() {
		// TODO Auto-generated method stub
		return 2;
	}

	@Override
	public void atualiza(Pedido pedido) {
		// TODO Auto-generated method stub
		pedido.setStatusPedido(status());
		PedidoRN pedidoRN = new PedidoRN();
		pedidoRN.atualizar(pedido);

		gerarArquivo(pedido);
	}

	@Override
	public String atual() {
		// TODO Auto-generated method stub

		return "Aguardando";
	}

	private void gerarArquivo(Pedido pedido) {

		Empresa empresa = pedido.getEmpresa();
		imp = new ImpressoraPreparo(pedido, empresa);

		imp.gerarArquivo();

	}

	@Override
	public void apagarArquivo() {
		imp.apagarArquivo();
	}

	@Override
	public String getArquivo() {
		return imp.getNomeArquivo();
	}

}
