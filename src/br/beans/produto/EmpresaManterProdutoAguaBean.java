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
import br.Produto.Produto;
import br.Produto.ProdutoRN;

@ManagedBean
@ViewScoped
public class EmpresaManterProdutoAguaBean implements Serializable {

	/**
	 * 
	 */

	private static final long serialVersionUID = 1L;
	private DataModel<Agua> aguaDM;
	private Agua agua;
	private Empresa empresa;
	private Cliente cliente;

	@PostConstruct
	public void construct() {
		if (empresaLogado()) {
			ProdutoRN produtoRN = new ProdutoRN();
			aguaDM = new ListDataModel<Agua>(produtoRN.listarAgua(empresa
					.getIdEmpresa()));
		}
	}

	public void atualizaStatus() {

		Produto produto;
		ProdutoRN produtoRN = new ProdutoRN();
		produto = (Agua) (aguaDM.getRowData());
		produto.alteraStatus();
		produtoRN.atualizarProduto(produto);

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

	public Agua getAgua() {
		if (agua == null) {
			agua = new Agua();
		}
		return agua;
	}

	public void setAgua(Agua agua) {
		this.agua = agua;
	}

	public DataModel<Agua> getAguaDM() {
		if (aguaDM == null) {
			aguaDM = new ListDataModel<Agua>();
		}
		return aguaDM;
	}

}
