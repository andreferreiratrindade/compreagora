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
import br.Produto.Marmitex;
import br.Produto.Pizza;
import br.Produto.Produto;
import br.Produto.ProdutoRN;

@ManagedBean
@ViewScoped
public class EmpresaManterProdutoMarmitexBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private DataModel<Marmitex> marmitexsDM;
	private Marmitex marmitex;
	private Empresa empresa;
	private Cliente cliente;

	@PostConstruct
	public void construct() {
		if (empresaLogado()) {
			ProdutoRN produtoRN = new ProdutoRN();
			marmitexsDM = new ListDataModel<Marmitex>(
					produtoRN.listarMarmitex(empresa.getIdEmpresa()));
		}
	}

	public void atualizaStatus() {

		Produto produto;
		ProdutoRN produtoRN = new ProdutoRN();
		produto = (Marmitex) (marmitexsDM.getRowData());
		produto.alteraStatus();
		produtoRN.atualizarProduto(produto);
	}

	public Marmitex getMarmitex() {
		if (marmitex == null) {
			marmitex = new Marmitex();
		}
		return marmitex;
	}

	public void setMarmitex(Marmitex marmitex) {
		this.marmitex = marmitex;
	}

	public DataModel<Marmitex> getMarmitexsDM() {
		if (marmitexsDM == null) {
			marmitexsDM = new ListDataModel<Marmitex>();
		}
		return marmitexsDM;
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
