package br.beans;

import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;

import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

import br.Cliente.Cliente;
import br.Cliente.ClienteRN;
import br.Empresa.Empresa;
import br.Empresa.EmpresaRN;
import br.Pedido.Pedido;
import br.Pedido.PedidoRN;
import br.relatorio.RelatorioCliente;
import br.relatorio.RelatorioFacade;
import br.relatorio.RelatorioFaturamento;
import br.relatorio.RelatorioTodosProdutos;
import br.relatorio.impressora.ImpressoraEntrega;
import br.relatorio.impressora.ImpressoraNaoFiscal;
import br.relatorio.impressora.ImpressoraPreparo;

@ManagedBean(name = "relatorioBean")
@ViewScoped
public class RelatorioBean {

	private int tipoRelatorio;
	private RelatorioFacade relatorioFacade;
	private Empresa empresa;
	private Date dataInicio;
	private Date dataFim;
	private Cliente cliente;
	private StreamedContent file;

	public void downloadFileTxt(String arq) {
		InputStream stream = ((ServletContext) FacesContext
				.getCurrentInstance().getExternalContext().getContext())
				.getResourceAsStream("/relatorio/" + arq + ".txt");
		file = new DefaultStreamedContent(stream, "document/text", arq + ".txt");
	}

	@PostConstruct
	public void init() {
		empresa = null;
		cliente = null;

		empresa = getEmpresa();
	}

	public void allClientes() {

		relatorioFacade = new RelatorioCliente();
		relatorioFacade.setTipoRelatorio(tipoRelatorio);

		relatorioFacade.gerarRelatorio();

	}

	public void cantina() {

		PedidoRN pedidoRN = new PedidoRN();
		Pedido pedido = pedidoRN.getPedido(10);
		empresa = pedido.getEmpresa();
		ImpressoraNaoFiscal imp = new ImpressoraPreparo(pedido, empresa);

		imp.gerarArquivo();
		downloadFileTxt(imp.getNomeArquivo());
		imp.apagarArquivo();
	}

	public void entrega() {
		PedidoRN pedidoRN = new PedidoRN();
		Pedido pedido = pedidoRN.getPedido(10);
		empresa = pedido.getEmpresa();

		ImpressoraNaoFiscal imp = new ImpressoraEntrega(pedido, empresa);

		imp.gerarArquivo();
		downloadFileTxt(imp.getNomeArquivo());
		imp.apagarArquivo();
	}

	public void allProdutos() {
		Map<String, String> parametros = new HashMap();

		parametros.put("empresa", empresa.getRazaoSocial());

		relatorioFacade = new RelatorioTodosProdutos(empresa.getIdEmpresa(),
				parametros);

		relatorioFacade.setTipoRelatorio(1);

		relatorioFacade.gerarRelatorio();

	}

	public void faturamento() {

		SimpleDateFormat formatador = new SimpleDateFormat("dd/MM/yyyy");
		String rDataInicio = new SimpleDateFormat("dd/MM/yyyy")
				.format(dataInicio);

		String rDataFim = new SimpleDateFormat("dd/MM/yyyy").format(dataFim);

		Map<String, String> parametros = new HashMap<String, String>();
		parametros.put("razaoSocial", empresa.getRazaoSocial());
		parametros.put("dataIn", rDataInicio);
		parametros.put("dataFim", rDataFim);
		relatorioFacade = new RelatorioFaturamento(empresa.getIdEmpresa(),
				dataInicio, dataFim, parametros);

		relatorioFacade.setTipoRelatorio(1);

		relatorioFacade.gerarRelatorio();

	}

	public Empresa getEmpresa() {
		if (empresa == null) {
			FacesContext context = FacesContext.getCurrentInstance();
			ExternalContext external = context.getExternalContext();
			String login = external.getRemoteUser();

			if (this.cliente == null || !login.equals(this.cliente.getEmail())) {

				if (login != null) {
					ClienteRN usuarioRN = new ClienteRN();
					this.cliente = usuarioRN.buscarPorEmail(login);
					EmpresaRN empresaRN = new EmpresaRN();
					empresa = empresaRN.getEmpresa(Integer.parseInt(cliente
							.getLogin()));
				}
			}
		}
		return empresa;
	}

	public int getTipoRelatorio() {
		return tipoRelatorio;
	}

	public void setTipoRelatorio(int tipoRelatorio) {
		this.tipoRelatorio = tipoRelatorio;
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

	public StreamedContent getFile() {
		return file;
	}

	public void setFile(StreamedContent file) {
		this.file = file;
	}

}