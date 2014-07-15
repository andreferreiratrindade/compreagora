package br.beans.produto;

import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import br.Cliente.Cliente;
import br.Cliente.ClienteRN;
import br.Empresa.EmpresaRN;
import br.Produto.ProdutoDAO;
import br.Empresa.Empresa;
import br.Produto.Lanche;
import br.Produto.Produto;
import br.Produto.Bebida;
import br.Produto.ProdutoRN;

@ManagedBean
@ViewScoped
public class ProdutoEmpresaManutencaoBean implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Empresa empresa;
	private DataModel<Produto> listaProduto;
	private DataModel<Lanche> lanchesDM;
	private DataModel<Bebida> bebidasDM;
	private Lanche lanche = new Lanche();
	private Bebida bebida = new Bebida();
	private Cliente cliente;

	@PostConstruct
	public void construct() {
		getEmpresa();
		ProdutoRN produtoRN = new ProdutoRN();
		lanchesDM = new ListDataModel<Lanche>(produtoRN.listarLanche(empresa
				.getIdEmpresa()));
		bebidasDM = new ListDataModel<Bebida>(produtoRN.listarBebida(empresa
				.getIdEmpresa()));
	}

	public String paginaProdutoEmpresa() {

		return "/paginas/restrito/Empresa/ManterprodutoLanche.jsf?faces-redirect=true";
	}

	public DataModel<Bebida> getBebidasDM() {
		return bebidasDM;
	}

	public void setBebidasDM(DataModel<Bebida> bebidasDM) {
		this.bebidasDM = bebidasDM;
	}

	public void setListaProduto(DataModel<Produto> listaProduto) {
		this.listaProduto = listaProduto;
	}

	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}

	public Lanche getLanche() {
		return lanche;
	}

	public void setLanche(Lanche lanche) {
		this.lanche = lanche;
	}

	public Bebida getBebida() {
		return bebida;
	}

	public void setBebida(Bebida refri) {
		this.bebida = refri;
	}

	public DataModel<Lanche> getLanchesDM() {

		return lanchesDM;
	}

	
	public DataModel<Bebida> getbebidasDM() {

		return bebidasDM;
	}

	public void setbebidasDM(DataModel<Bebida> listaBebida) {
		this.bebidasDM = listaBebida;
	}

	public void setLanchesDM(DataModel<Lanche> listaLanche) {
		this.lanchesDM = listaLanche;
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

	public void atualizaStatusLanche() {

		Produto produtoLanche;
		ProdutoRN produtoRN = new ProdutoRN();
		produtoLanche = (Lanche) (lanchesDM.getRowData());
		produtoLanche.setAtivo(!produtoLanche.isAtivo());
		produtoRN.atualizarProduto(produtoLanche);
	}

	public void atualizaStatusBebida() {

		Bebida bebida;
		ProdutoRN produtoRN = new ProdutoRN();
		bebida = (Bebida) (bebidasDM.getRowData());
		produtoRN.atualizarProduto(bebida);
	}

	public void atualizarProdutoLanche() {
		lanche = (Lanche) (lanchesDM.getRowData());
	}

	public void atualizaProdutoLancheUpdate() {
		ProdutoRN produtoRN = new ProdutoRN();
		produtoRN.atualizarProduto(lanche);
	}
}
