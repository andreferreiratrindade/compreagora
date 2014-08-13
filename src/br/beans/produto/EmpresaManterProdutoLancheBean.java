package br.beans.produto;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;

import br.Cliente.Cliente;
import br.Cliente.ClienteRN;
import br.Empresa.Empresa;
import br.Empresa.EmpresaRN;
import br.Produto.Agua;
import br.Produto.Lanche;
import br.Produto.Pizza;
import br.Produto.Produto;
import br.Produto.ProdutoRN;

@ManagedBean
@ViewScoped
public class EmpresaManterProdutoLancheBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private DataModel<Lanche> lanchesDM;
	private Lanche lanche;
	private Empresa empresa;
	private Cliente cliente;

	@PostConstruct
	public void construct() {
		if (empresaLogado()) {
			ProdutoRN produtoRN = new ProdutoRN();
			lanchesDM = new ListDataModel<Lanche>(
					produtoRN.listarLanche(empresa.getIdEmpresa()));
		}
	}

	public void atualizaStatus() {

		Produto produto;
		ProdutoRN produtoRN = new ProdutoRN();
		produto = (Lanche) (lanchesDM.getRowData());
		produto.alteraStatus();
		produtoRN.atualizarProduto(produto);
	}

	public Lanche getLanche() {
		if (lanche == null) {
			lanche = new Lanche();
		}
		return lanche;
	}

	public void setLanche(Lanche lanche) {
		this.lanche = lanche;
	}

	public DataModel<Lanche> getLanchesDM() {
		if (lanchesDM == null) {
			lanchesDM = new ListDataModel<Lanche>();
		}
		return lanchesDM;
	}

	public boolean empresaLogado() {
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
				return true;
			}
		}
		return false;
	}

}
