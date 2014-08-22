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
import br.Produto.Gas;
import br.Produto.Produto;
import br.Produto.ProdutoRN;

@ManagedBean
@ViewScoped
public class EmpresaManterProdutoGasBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private DataModel<Gas> gasDM;
	private Gas gas;
	private Cliente cliente;
	private Empresa empresa;

	@PostConstruct
	public void construct() {
		if (empresaLogado()) {
			ProdutoRN produtoRN = new ProdutoRN();
			gasDM = new ListDataModel<Gas>(produtoRN.listarGas(empresa
					.getIdEmpresa()));
		}
	}

	public void atualizaStatus() {

		Produto produto;
		ProdutoRN produtoRN = new ProdutoRN();
		produto = (Gas) (gasDM.getRowData());
		produto.alteraStatus();
		produtoRN.atualizarProduto(produto);
	}

	public Gas getGas() {
		if (gas == null) {
			gas = new Gas();
		}
		return gas;
	}

	public void setGas(Gas gas) {
		this.gas = gas;
	}

	public DataModel<Gas> getGasDM() {
		if (gasDM == null) {
			gasDM = new ListDataModel<Gas>();
		}
		return gasDM;
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
