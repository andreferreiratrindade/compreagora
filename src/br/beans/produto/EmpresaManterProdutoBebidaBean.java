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
import br.Produto.Bebida;
import br.Produto.Produto;
import br.Produto.ProdutoRN;

@ManagedBean
@ViewScoped
public class EmpresaManterProdutoBebidaBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private DataModel<Bebida> bebidasDM;
	private Bebida bebida;
	private Cliente cliente;
	private Empresa empresa;

	@PostConstruct
	public void construct() {
		if (empresaLogado()) {
			ProdutoRN produtoRN = new ProdutoRN();
			bebidasDM = new ListDataModel<Bebida>(
					produtoRN.listarBebida(empresa.getIdEmpresa()));
		}
	}

	public void atualizaStatus() {

		Produto produto;
		ProdutoRN produtoRN = new ProdutoRN();
		produto = (Bebida) (bebidasDM.getRowData());
		produto.alteraStatus();
		produtoRN.atualizarProduto(produto);

	}

	public Bebida getBebida() {
		if (bebida == null) {
			bebida = new Bebida();
		}
		return bebida;
	}

	public void setBebida(Bebida bebida) {
		this.bebida = bebida;
	}

	public DataModel<Bebida> getBebidasDM() {
		if (bebidasDM == null) {
			bebidasDM = new ListDataModel<Bebida>();
		}
		return bebidasDM;
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
