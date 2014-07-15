package br.relatorio;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Map;

import net.sf.jasperreports.engine.JRException;
import br.Pedido.Pedido;
import br.Pedido.PedidoRN;

public class RelatorioFaturamento extends RelatorioFacade {

	private static final String faturamento = "faturamento";
	private int idEmpresa;
	private Date dataInicio;
	private Date dataFim;

	public RelatorioFaturamento(int idEmpresa, Date dataInicio, Date dataFim,
			Map parametros) {
		this.idEmpresa = idEmpresa;
		this.dataInicio = dataInicio;
		this.dataFim = dataFim;
		relatorio = new Relatorio();
		relatorio.setParametros(parametros);
		relatorio.setNomeArq(faturamento);
	}

	@Override
	public void gerarRelatorio() {
		PedidoRN pedidoRN = new PedidoRN();
		List<Pedido> pedidos = pedidoRN.relatorioDeFaturamento(idEmpresa,
				dataInicio, dataFim);

		relatorio.setLista(pedidos);
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

	public int getIdEmpresa() {
		return idEmpresa;
	}

	public void setIdEmpresa(int idEmpresa) {
		this.idEmpresa = idEmpresa;
	}

	public Date getDataInicio() {
		return dataInicio;
	}

	public void setDataInicio(Date dataInicio) {
		this.dataInicio = dataInicio;
	}

	public Date getDataFim() {
		return dataFim;
	}

	public void setDataFim(Date dataFim) {
		this.dataFim = dataFim;
	}

}
