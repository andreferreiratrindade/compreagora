package br.beans.produto;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.primefaces.event.FileUploadEvent;

import br.Empresa.Empresa;
import br.Produto.Agua;
import br.Produto.ProdutoRN;
import br.beans.empresa.EmpresaBean;
import br.util.FileUpload;

@ManagedBean
@ViewScoped
public class ManterProdutoAguaBean implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@ManagedProperty(value = "#{empresaBean}")
	private EmpresaBean empresaBean;

	private Empresa empresa;
	private List<Agua> listAgua;
	private Agua agua;
	private FileUpload arquivo;

	public EmpresaBean getEmpresaBean() {
		return empresaBean;
	}

	public void setEmpresaBean(EmpresaBean empresaBean) {
		this.empresaBean = empresaBean;
	}

	public ManterProdutoAguaBean() {
		arquivo = new FileUpload();
	}

	@PostConstruct
	public void construct() {
		empresa = empresaBean.getEmpresa();
		novoAgua();
		ProdutoRN produtoRN = new ProdutoRN();
		listAgua = produtoRN.listarAgua(empresa.getIdEmpresa());
	}

	public void salvarAgua() {
		agua.setEmpresa(empresa);

		ProdutoRN produtoRN = new ProdutoRN();
		produtoRN.salve(agua);

		this.arquivo.gravarArquivoTomCat("produto/agua/",
				Integer.toString(agua.getIdProduto()));// produtoRN
		// .ultimoElementoAdicionado().toString());
		this.arquivo.gravarArquivoProjeto("produto/agua/",
				Integer.toString(agua.getIdProduto()));// produtoRN
		// .ultimoElementoAdicionado().toString());
		construct();
	}

	public void novoAgua() {
		agua = new Agua();
		arquivo = new FileUpload();
	}

	public void uploadAction(FileUploadEvent event) {
		this.arquivo.fileUpload(event);
	}

	public Empresa getEmpresa() {
		return empresa;
	}

	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}

	public List<Agua> getListAgua() {
		return listAgua;
	}

	public void setListAgua(List<Agua> listAgua) {
		this.listAgua = listAgua;
	}

	public Agua getAgua() {
		return agua;
	}

	public void setAgua(Agua agua) {
		this.agua = agua;
	}

	public FileUpload getArquivo() {
		return arquivo;
	}

	public void setArquivo(FileUpload arquivo) {
		this.arquivo = arquivo;
	}

	public void atualizaProdutoAgua() {

		ProdutoRN produtoRN = new ProdutoRN();
		produtoRN.atualizarProduto(agua);
		construct();
	}

}
