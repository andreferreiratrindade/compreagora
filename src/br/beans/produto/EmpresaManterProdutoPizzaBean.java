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
import br.Produto.Pizza;
import br.Produto.Produto;
import br.Produto.ProdutoRN;

@ManagedBean
@ViewScoped
public class EmpresaManterProdutoPizzaBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private DataModel<Pizza> pizzasDM;
	private Pizza pizza;
	private Empresa empresa;
	private Cliente cliente;

	@PostConstruct
	public void construct() {
		if (empresaLogado()) {
			ProdutoRN produtoRN = new ProdutoRN();
			pizzasDM = new ListDataModel<Pizza>(produtoRN.listarPizza(empresa
					.getIdEmpresa()));
		}
	}

	public void atualizaStatus() {
		Produto produto;
		ProdutoRN produtoRN = new ProdutoRN();
		produto = (Pizza) (pizzasDM.getRowData());
		produto.alteraStatus();
		produtoRN.atualizarProduto(produto);
	}

	public Pizza getPizza() {
		if (pizza == null) {
			pizza = new Pizza();
		}
		return pizza;
	}

	public void setPizza(Pizza pizza) {
		this.pizza = pizza;
	}

	public DataModel<Pizza> getPizzasDM() {
		if (pizzasDM == null) {
			pizzasDM = new ListDataModel<Pizza>();
		}
		return pizzasDM;
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
