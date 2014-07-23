package br.beans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;

import org.primefaces.model.LazyDataModel;

import br.Cliente.Cliente;
import br.Cliente.ClienteRN;
import br.EnderecoCliente.EnderecoCliente;
import br.Pedido.Pedido;
import br.Pedido.PedidoRN;
import br.PedidoProduto.PedidoProduto;
import br.Produto.ProdutoAvulso.ProdutoAvulso;
import br.Produto.ProdutoAvulso.ProdutoAvulsoDAO;
import br.ProdutoAvulso.Avulso;
import br.dataTableLazy.MeusPedidoLazy;
import br.util.DAOFactoy;

@ManagedBean
@ViewScoped
public class MeusPedidosBean implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Cliente cliente = null;
	private Pedido pedido = null;
	private DataModel<PedidoProduto> produtosDM;
	private List<PedidoProduto> pedidoProdutos;
	private PedidoDataModel pedidoDataModel;
	private List<Pedido> pedidos;
	private PedidoProduto pedidoProduto;
	private List<Avulso> avulsos;
	private LazyDataModel<Pedido> pedidosLazy;

	public LazyDataModel<Pedido> getPedidosLazy() {
		if (pedidosLazy == null) {
			pedidosLazy = new MeusPedidoLazy(cliente.getIdCliente());
		}
		return pedidosLazy;
	}

	@PostConstruct
	public void construct() {
		if (getCliente() != null) {
			pedido = new Pedido();
			pedidos = new ArrayList<Pedido>();

			for (EnderecoCliente x : cliente.getEnderecoCliente()) {
				for (Pedido y : x.getPedidos()) {
					y.getPedidoProdutos().size();
					pedidos.add(y);
				}
			}

			Collections.sort(pedidos);

			pedidoDataModel = new PedidoDataModel(pedidos);
		}
	}

	public PedidoProduto getPedidoProduto() {
		if (pedidoProduto == null) {
			pedidoProduto = new PedidoProduto();
		}
		return pedidoProduto;
	}

	public void setPedidoProduto(PedidoProduto pedidoProduto) {
		this.pedidoProduto = pedidoProduto;
		atualizaCampoAvulso();
	}

	public List<PedidoProduto> getPedidoProdutos() {

		return pedido != null ? pedido.getPedidoProdutos() : pedidoProdutos;
	}

	public void setPedidoProdutos(List<PedidoProduto> pedidoProdutos) {
		this.pedidoProdutos = pedidoProdutos;
	}

	public PedidoDataModel getPedidoDataModel() {

		return pedidoDataModel;
	}

	public void setPedidoDataModel(PedidoDataModel pedidoDataModel) {
		this.pedidoDataModel = pedidoDataModel;
	}

	public List<Pedido> getPedidos() {
		return pedidos;
	}

	public void setPedidos(List<Pedido> pedidos) {
		this.pedidos = pedidos;
	}

	public DataModel<PedidoProduto> getProdutosDM() {
		if (pedido != null) {

			List<PedidoProduto> pedidoProdutos = pedido.getPedidoProdutos();
			produtosDM = new ListDataModel<PedidoProduto>(pedidoProdutos);
		}
		return produtosDM;
	}

	public void setProdutosDM(DataModel<PedidoProduto> produtosDM) {
		this.produtosDM = produtosDM;
	}

	public Pedido getPedido() {

		return pedido;
	}

	public void setPedido(Pedido pedido) {
		this.pedido = pedido;
	}

	public Cliente getCliente() {

		FacesContext context = FacesContext.getCurrentInstance();
		ExternalContext external = context.getExternalContext();
		String login = external.getRemoteUser();

		if ((this.cliente == null) || (!login.equals(this.cliente.getEmail()))) {

			if (login != null) {
				ClienteRN usuarioRN = new ClienteRN();
				this.cliente = usuarioRN.buscarPorEmail(login);
			}
			return cliente;
		}

		return null;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public void atualizaPedidoProdutos() {

		PedidoRN pedidoRN = new PedidoRN();

		pedido = pedidoRN.getPedido(pedido.getIdPedido());

		pedidoProdutos = pedido.getPedidoProdutos();

	}

	public void atualizaCampoAvulso() {
		ProdutoAvulsoDAO produtoTemp = DAOFactoy.criarProdutoAvulso();
		ProdutoAvulso produtoAvulsoTemp = null;
		try {
			produtoAvulsoTemp = produtoTemp.getUnico(pedidoProduto
					.getProdutoAvulso().getIdProdutoAvulso());
		} catch (Exception e) {
			System.out.println("Deu erro");
		}
		avulsos = produtoAvulsoTemp.getAvulsos();
	}

	public List<Avulso> getAvulsos() {
		return avulsos;
	}

	public void setAvulsos(List<Avulso> avulsos) {
		this.avulsos = avulsos;
	}
}
