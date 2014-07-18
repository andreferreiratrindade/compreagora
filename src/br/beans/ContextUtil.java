package br.beans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

import br.Cliente.Cliente;
import br.Cliente.ClienteRN;
import br.Empresa.Categoria.Categoria;

@ManagedBean
@SessionScoped
public class ContextUtil implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Cliente clienteLogado = null;
	private int categoriaEmpresa = -1;
	private String categoriaEmpresaStr;

	private Map<Integer, String> mapCategoriaEmp;
	private List<Long> tiposProdutos;

	public void converteTipoProduto(List<Categoria> tiposProdutos) {
		for (Categoria tp : tiposProdutos) {
			this.tiposProdutos.add((long) tp.getTipoCategoria().ordinal());
		}
	}

	public Object verificaTiposProdutos(Long tipoProduto) {
		if (tipoProduto == null) {

		}
		return tiposProdutos.contains(tipoProduto) ? "block" : "none";
	}

	public String getCategoriaEmpresaStr() {
		categoriaEmpresaStr = mapCategoriaEmp.get(categoriaEmpresa);
		return categoriaEmpresaStr;
	}

	public void setCategoriaEmpresaStr(String categoriaEmpresaStr) {
		this.categoriaEmpresaStr = categoriaEmpresaStr;
	}

	@PostConstruct
	public void init() {
		iniciandoMapCategoria();
		tiposProdutos = new ArrayList<Long>();
		categoriaEmpresaStr = mapCategoriaEmp.get(categoriaEmpresa);
	}

	private void iniciandoMapCategoria() {
		mapCategoriaEmp = new HashMap<Integer, String>();
		mapCategoriaEmp.put(-1, "");
		mapCategoriaEmp.put(0, "Lanchonete");
		mapCategoriaEmp.put(1, "Pizzaria");
		mapCategoriaEmp.put(2, "Restaurante");
		mapCategoriaEmp.put(3, "Bebida");
		mapCategoriaEmp.put(4, "Água");
		mapCategoriaEmp.put(5, "Gás");
	}

	public int getCategoriaEmpresa() {
		return categoriaEmpresa;
	}

	public void setCategoriaEmpresa(int categoriaEmpresa) {
		this.categoriaEmpresa = categoriaEmpresa;
	}

	public Cliente getClienteLogado() {

		FacesContext context = FacesContext.getCurrentInstance();
		ExternalContext external = context.getExternalContext();
		String login = external.getRemoteUser();

		if (this.clienteLogado == null
				|| !login.equals(this.clienteLogado.getEmail())) {

			if (login != null) {
				ClienteRN usuarioRN = new ClienteRN();
				this.clienteLogado = usuarioRN.buscarPorEmail(login);
			}
		}

		return clienteLogado;
	}

	public void setClienteLogado(Cliente cliente) {
		this.clienteLogado = cliente;
	}
	
	
}
