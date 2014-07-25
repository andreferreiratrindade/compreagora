package br.statusPedido;

import br.Empresa.Empresa;
import br.EnderecoCliente.EnderecoCliente;
import br.Pedido.Pedido;
import br.Pedido.PedidoRN;
import br.relatorio.impressora.ImpressoraEntrega;
import br.relatorio.impressora.ImpressoraNaoFiscal;

public class Processando implements StatusInterface {

	private ImpressoraNaoFiscal imp;

	@Override
	public int status() {
		return 3;
	}

	@Override
	public void atualiza(Pedido pedido) {
		pedido.setStatusPedido(status());
		PedidoRN pedidoRN = new PedidoRN();
		pedidoRN.atualizar(pedido);
		gerarArquivo(pedido);
	}

	@Override
	public String atual() {
		return "Processando Pedido";
	}

	private void gerarArquivo(Pedido pedido) {

		Empresa empresa = pedido.getEmpresa();
		EnderecoCliente endCliente = pedido.getEnderecoCliente();
		imp = new ImpressoraEntrega(pedido, empresa, endCliente);

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
