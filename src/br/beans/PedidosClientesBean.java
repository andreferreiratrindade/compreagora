package br.beans;

import java.io.InputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;

import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.StreamedContent;

import br.Cliente.Cliente;
import br.Cliente.ClienteRN;
import br.Empresa.Empresa;
import br.Empresa.EmpresaRN;
import br.Pedido.Pedido;
import br.Pedido.PedidoRN;
import br.PedidoProduto.PedidoProduto;
import br.Produto.Produto;
import br.ProdutoAvulso.Avulso;
import br.dataTableLazy.PedidosClientesLazy;
import br.statusPedido.Aguardando;
import br.statusPedido.Concluido;
import br.statusPedido.Enviado;
import br.statusPedido.Processando;
import br.statusPedido.StatusInterface;

@ManagedBean
@ViewScoped
public class PedidosClientesBean implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Pedido pedido;
	private Produto produto = null;
	private Empresa empresa = null;
	private Cliente cliente = null;
	private PedidoProduto pedidoProduto;
	private List<PedidoProduto> pedidoProdutos;
	private List<Avulso> avulsos;
	private StreamedContent file;
	private StatusInterface stPedido;
	private LazyDataModel<Pedido> pedidosLazy;
	private int idPedidoTemp;

	public LazyDataModel<Pedido> getPedidosLazy() {
		if (pedidosLazy == null) {

			pedidosLazy = new PedidosClientesLazy(empresa.getIdEmpresa());

		}
		return pedidosLazy;
	}

	public StreamedContent getFile() {
		return file;
	}

	public void setFile(StreamedContent file) {
		this.file = file;
	}

	public void downloadFileTxt(String arq) {
		InputStream stream = ((ServletContext) FacesContext
				.getCurrentInstance().getExternalContext().getContext())
				.getResourceAsStream("/relatorio/" + arq + ".txt");
		file = new DefaultStreamedContent(stream, "document/text", arq + ".txt");
	}

	@PostConstruct
	public void construct() {
		getEmpresa();
		pedido = new Pedido();
	}

	public List<Avulso> getAvulsos() {
		if (avulsos == null) {
			avulsos = new ArrayList<Avulso>();
		}
		return avulsos;
	}

	public void setAvulsos(List<Avulso> avulsos) {
		this.avulsos = avulsos;
	}

	public PedidoProduto getPedidoProduto() {

		if (pedidoProduto == null) {
			pedidoProduto = new PedidoProduto();
		}

		return pedidoProduto;
	}

	public void setPedidoProduto(PedidoProduto pedidoProduto) {

		this.pedidoProduto = pedidoProduto;
		// atualizaCampoAvulso();
	}

	public List<PedidoProduto> getPedidoProdutos() {
		if (pedidoProdutos == null) {
			pedidoProdutos = new ArrayList<PedidoProduto>();
		}
		return pedidoProdutos;
	}

	public void setPedidoProdutos(List<PedidoProduto> pedidoProdutos) {
		this.pedidoProdutos = pedidoProdutos;
	}

	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public void atualizaStatus() {

		Map<Integer, StatusInterface> mapStatus = new HashMap<Integer, StatusInterface>();
		mapStatus.put(1, new Aguardando());
		mapStatus.put(2, new Processando());
		mapStatus.put(3, new Enviado());
		mapStatus.put(4, new Concluido());

		stPedido = (StatusInterface) mapStatus.get(pedido.getStatusPedido());

		stPedido.atualiza(pedido);

	}

	public void imprimirPedido() {

		if (stPedido.getArquivo() != null) {
			downloadFileTxt(stPedido.getArquivo());
			stPedido.apagarArquivo();
		}

	}

	public Empresa getEmpresa() {
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

		return empresa;
	}

	public void atualizaProdutos() {
		if (pedido != null) {
			if (pedido.getIdPedido() != 0) {
				PedidoRN pedidoRN = new PedidoRN();
				pedido = pedidoRN.getPedido(pedido.getIdPedido());
				pedido.getPedidoProdutos().size();
				idPedidoTemp = pedido.getIdPedido();
				pedidoProdutos = pedido.getPedidoProdutos();
			} else if (idPedidoTemp != 0) {
				PedidoRN pedidoRN = new PedidoRN();

				pedido = pedidoRN.getPedido(idPedidoTemp);
				pedido.getPedidoProdutos().size();

				pedidoProdutos = pedido.getPedidoProdutos();
			}
		}
	}

	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}

	public Pedido getPedido() {
		if (pedido == null) {
			pedido = new Pedido();
		}
		return pedido;
	}

	public void setPedido(Pedido pedido) {
		this.pedido = pedido;
	}

}
