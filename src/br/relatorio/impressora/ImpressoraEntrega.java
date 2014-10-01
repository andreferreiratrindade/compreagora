package br.relatorio.impressora;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;

import br.Empresa.Empresa;
import br.Pedido.Pedido;
import br.PedidoProduto.PedidoProduto;
import br.ProdutoAvulso.PedidoProdutoAvulso;

public class ImpressoraEntrega extends ImpressoraNaoFiscal {
	public final static String ENTREGADOR = "Entregador";
	private Pedido pedido;
	private Empresa empresa;

	public ImpressoraEntrega(Pedido pedido, Empresa empresa) {
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
			linhasTxt.println(LINHA_SEPARA);

			for (PedidoProduto x : pedido.getPedidoProdutos()) {
				linhasTxt.println(String.format("%7s  %9s", x.getDescricao(),
						"R$ " + x.getValor()));

				for (PedidoProdutoAvulso y : x.getAvulsos()) {
					linhasTxt.println(String.format("%7s  %9s",
							"## " + y.getDescricao(), "R$ " + y.getValor()));
				}

				linhasTxt.println(LINHA_SEPARA);
			}
			linhasTxt.println(String.format("%7s  %9s", "taxa de entrega: ",
					"R$ " + pedido.getTaxa()));
			linhasTxt.println(String.format("%7s  %9s", "VALOR TOTAL:     ",
					"R$ " + pedido.getValorTotal()));
			linhasTxt.println(String.format("%7s  %9s", "troco para:      ",
					"R$ " + pedido.getTroco()));
			linhasTxt.println(LINHA);

			linhasTxt.println("**********ENDEREÇO*********");
			linhasTxt.println(LINHA_SEPARA);
			linhasTxt.println(pedido.getLogradouro());
			linhasTxt.println("numero: " + pedido.getNumero());
			linhasTxt.println(pedido.getBairro());
			linhasTxt.println(pedido.getCidade());
			linhasTxt.println(pedido.getUF());
			linhasTxt.println("CEP: " + pedido.getCep());
			linhasTxt.println(pedido.getComplemento());

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

		nomeArquivo = ENTREGADOR + pedido.getIdPedido();

	}

	@Override
	public void apagarArquivo() {
		arquivo.delete();

	}

}
