package br.beans;

import java.io.Serializable;
import java.util.ArrayList;
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
import br.Pedido.Pedido;
import br.Pedido.PedidoRN;
import br.PedidoProduto.PedidoProduto;
import br.ProdutoAvulso.Avulso;
import br.dataTableLazy.MeusPedidoLazy;

@ManagedBean(name = "meusPedidosBean")
@ViewScoped
public class MeusPedidosBean implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private int idPedidoTemp;
	private Cliente cliente = null;
	private Pedido pedido = null;
	private DataModel<PedidoProduto> produtosDM;
	private List<PedidoProduto> pedidoProdutos;
	private PedidoProduto pedidoProduto;
	private List<Avulso> avulsos;
	private LazyDataModel<Pedido> pedidosLazy;

	public LazyDataModel<Pedido> getPedidosLazy() {
		if (pedidosLazy == null) {
			pedidosLazy = new MeusPedidoLazy(cliente.getIdCliente());
		}
		return pedidosLazy;
	}

	public String paginaMeusPedidos() {
		return "/paginas/publico/meusPedidos.jsf?faces-redirect=true";
	}

	@PostConstruct
	public void construct() {
		if (getCliente() != null) {

		}
		pedido = new Pedido();
	}

	public PedidoProduto getPedidoProduto() {
		if (pedidoProduto == null) {
			pedidoProduto = new PedidoProduto();
		}
		return pedidoProduto;
	}

	public void setPedidoProduto(PedidoProduto pedidoProduto) {
		this.pedidoProduto = pedidoProduto;
	//	atualizaCampoAvulso();
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
		if (pedido == null) {
			pedido = new Pedido();
		}
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

		if (pedido != null) {

			if ((pedido.getIdPedido() != 0)) {
				PedidoRN pedidoRN = new PedidoRN();

				pedido = pedidoRN.getPedido(pedido.getIdPedido());
				pedido.getPedidoProdutos().size();
				idPedidoTemp = pedido.getIdPedido();
				
			} else if (idPedidoTemp != 0) {
				PedidoRN pedidoRN = new PedidoRN();

				pedido = pedidoRN.getPedido(idPedidoTemp);
				pedido.getPedidoProdutos().size();			
			}
		}

	}

/*	public void atualizaCampoAvulso() {
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
*/
	public List<Avulso> getAvulsos() {
		if (avulsos == null) {
			avulsos = new ArrayList<Avulso>();
		}
		return avulsos;
	}

	public void setAvulsos(List<Avulso> avulsos) {
		this.avulsos = avulsos;
	}
}
