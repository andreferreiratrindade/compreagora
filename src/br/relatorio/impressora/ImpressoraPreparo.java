package br.relatorio.impressora;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;

import br.Empresa.Empresa;
import br.Pedido.Pedido;
import br.PedidoProduto.PedidoProduto;
import br.ProdutoAvulso.Avulso;

public class ImpressoraPreparo extends ImpressoraNaoFiscal {
	public final static String COZINHA = "Cozinha";
	private Pedido pedido;
	private Empresa empresa;

	public ImpressoraPreparo(Pedido pedido, Empresa empresa) {
		this.pedido = pedido;
		this.empresa = empresa;
	}

	@Override
	public void gerarArquivo() {

		try {
			ExternalContext externalContext = FacesContext.getCurrentInstance()
					.getExternalContext();
			ServletContext context = (ServletContext) externalContext
					.getContext();

			gerarNomeArquivo();

			String caminho = context.getRealPath("relatorio/" + nomeArquivo
					+ ".txt");

			arquivo = new File(caminho);
			arquivo.createNewFile();

			FileWriter arquivoTxt = new FileWriter(arquivo, true);
			PrintWriter linhasTxt = new PrintWriter(arquivoTxt);
			// ACREDITO QUE SO PODE TER 42 CARACTERES
			linhasTxt.println(LINHA);
			linhasTxt.println(empresa.getRazaoSocial());
			linhasTxt.println(LINHA);

			linhasTxt.println("PEDIDO: " + pedido.getIdPedido() + "    "
					+ StringToDateFormart(pedido.getDataHoraIn()));

			linhasTxt.println(LINHA);
			linhasTxt.println("********** PRODUTOS *********");
			linhasTxt.println(LINHA);

			for (PedidoProduto x : pedido.getPedidoProdutos()) {
				linhasTxt.println(x.getProdutoAvulso().getProduto()
						.getDescricao());

				for (Avulso y : x.getProdutoAvulso().getAvulsos()) {
					linhasTxt.println("## " + y.getDescricao());
				}
				linhasTxt.println(x.getObservacao() == null ? " " : "> "
						+ x.getObservacao());
				linhasTxt.println(LINHA_SEPARA);
			}

			int i = 0;
			while (i < 10) {
				i++;
				linhasTxt.println();
			}
			arquivoTxt.close();
			// emiteComanda();

		} catch (IOException error) {
			System.out.println("nao encontrei arquivo");
		}

	}

	private String StringToDateFormart(Date date) {

		return new SimpleDateFormat("dd/MM/yyyy hh:mm").format(date).toString();

	}

	@Override
	public void gerarNomeArquivo() {

		nomeArquivo = COZINHA + pedido.getIdPedido();

	}

	@Override
	public void apagarArquivo() {
		arquivo.delete();

	}

}
