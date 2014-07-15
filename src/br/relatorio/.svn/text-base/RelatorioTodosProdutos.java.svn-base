package br.relatorio;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import net.sf.jasperreports.engine.JRException;
import br.Produto.Produto;
import br.Produto.ProdutoRN;

public class RelatorioTodosProdutos extends RelatorioFacade {
	public final static String RELATORIO_PRODUTOS = "produtos"; // produtos
	private int idEmpresa;

	public RelatorioTodosProdutos(int idEmpresa, Map parametros) {
		this.idEmpresa = idEmpresa;
		relatorio = new Relatorio();
		relatorio.setNomeArq(RELATORIO_PRODUTOS);
		relatorio.setParametros(parametros);

	}

	@Override
	public void gerarRelatorio() {


		
		ProdutoRN produtoRN = new ProdutoRN();
		List<Produto> produtos = produtoRN.listarProdutos(idEmpresa);
		relatorio.setLista(produtos);
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
