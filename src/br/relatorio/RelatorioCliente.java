package br.relatorio;

import java.io.IOException;
import java.util.List;

import net.sf.jasperreports.engine.JRException;
import br.Cliente.Cliente;
import br.Cliente.ClienteRN;

public class RelatorioCliente extends RelatorioFacade {

	public final static String RELATORIO_CLIENTES = "clientes";

	@Override
	public void gerarRelatorio() {
		ClienteRN clienteRN = new ClienteRN();
		List<Cliente> clientes = clienteRN.listar();

		relatorio = new Relatorio(RELATORIO_CLIENTES, clientes);
		try {
			gerarArquivo();
		} catch (JRException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
