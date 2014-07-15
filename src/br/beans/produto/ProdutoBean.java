package br.beans.produto;

import java.io.Serializable;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import org.primefaces.event.FileUploadEvent;
import br.Cliente.Cliente;
import br.Cliente.ClienteRN;
import br.Empresa.EmpresaRN;
import br.Empresa.Empresa;
import br.Produto.Lanche;
import br.Produto.Bebida;
import br.Produto.ProdutoRN;
import br.util.FileUpload;

@ManagedBean
@SessionScoped
public class ProdutoBean implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Empresa empresa;
	private DataModel<Empresa> empresas;
	private List<Lanche> lanches;
	private List<Bebida> bebidas;
	private Lanche lanche = new Lanche();
	private Bebida bebida = new Bebida();
	private Cliente cliente;
	private FileUpload arquivo = new FileUpload();

	public List<Lanche> getLanches() {
		return lanches;
	}

	public void setLanches(List<Lanche> lanches) {
		this.lanches = lanches;
	}

	public List<Bebida> getBebidas() {
		return bebidas;
	}

	public void setBebidas(List<Bebida> bebidas) {
		this.bebidas = bebidas;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public void setEmpresas(DataModel<Empresa> empresas) {
		this.empresas = empresas;
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

	public String selecionaEmpresaProduto() {
		lanche.setEmpresa(empresa);
		return "adminManterProdutoLanche?faces-redirect=true";
	}

	public void novoBebida() {
		bebida = new Bebida();
		bebida.setEmpresa(empresa);
	}

	public void novoLanche() {
		lanche = new Lanche();
		lanche.setEmpresa(empresa);
	}

	public void salvarLanche() {
		ProdutoRN produtoRN = new ProdutoRN();
		produtoRN.salve(lanche);

		this.arquivo.gravarArquivoTomCat("produto/lanche/", produtoRN
				.ultimoElementoAdicionado().toString());
		this.arquivo.gravarArquivoProjeto("produto/lanche/", produtoRN
				.ultimoElementoAdicionado().toString());
		novoLanche();
	}

	public void salvarBebida() {

		ProdutoRN produtoRN = new ProdutoRN();
		produtoRN.salve(bebida);

		this.arquivo.gravarArquivoTomCat("produto/bebida/", produtoRN
				.ultimoElementoAdicionado().toString());
		this.arquivo.gravarArquivoProjeto("produto/bebida/", produtoRN
				.ultimoElementoAdicionado().toString());
		novoBebida();
	}

	public DataModel<Empresa> getEmpresas() {
		EmpresaRN empresaRN = new EmpresaRN();
		List<Empresa> lista = empresaRN.listar();
		empresas = new ListDataModel<Empresa>(lista);
		return empresas;
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

	public void atualizaProdutoLanche() {
		System.out.println("-----------" + lanche.getDescricao());
		ProdutoRN produtoRN = new ProdutoRN();
		produtoRN.salve(lanche);
	}

	public void atualizaProdutoBebida() {

		ProdutoRN produtoRN = new ProdutoRN();
		produtoRN.salve(bebida);
	}

	public void uploadAction(FileUploadEvent event) {
		this.arquivo.fileUpload(event);
	}

	public String paginaCadastroLanche() {
		novoLanche();
		ProdutoRN produtoRN = new ProdutoRN();
		lanches = produtoRN.listarLanche(empresa.getIdEmpresa());

		return "adminManterProdutoLanche.jsf?faces-redirect=true";
	}

	public String paginaCadastroBebida() {
		novoBebida();
		ProdutoRN produtoRN = new ProdutoRN();
		bebidas = produtoRN.listarBebida(empresa.getIdEmpresa());

		return "adminManterProdutoBebida.jsf?faces-redirect=true";
	}
}
