package br.beans.produto;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.primefaces.event.FileUploadEvent;

import br.Empresa.Empresa;
import br.Produto.Lanche;
import br.Produto.ProdutoRN;
import br.beans.empresa.EmpresaBean;
import br.util.FileUpload;

@ManagedBean
@ViewScoped
public class ManterProdutoLancheBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@ManagedProperty(value = "#{empresaBean}")
	private EmpresaBean empresaBean;

	private Empresa empresa;
	private List<Lanche> lanches;
	private Lanche lanche;
	private FileUpload arquivo;

	public ManterProdutoLancheBean() {
		arquivo = new FileUpload();
	}

	public List<Lanche> getLanches() {
		return lanches;
	}

	public void setLanches(List<Lanche> lanches) {
		this.lanches = lanches;
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

	public void novoLanche() {
		lanche = new Lanche();
		arquivo = new FileUpload();
	}

	public void salvarLanche() {
		lanche.setEmpresa(empresa);

		ProdutoRN produtoRN = new ProdutoRN();
		produtoRN.salve(lanche);

		this.arquivo.gravarArquivoTomCat("produto/lanche/",
				Integer.toString(lanche.getIdProduto()));// produtoRN
		// .ultimoElementoAdicionado().toString());
		this.arquivo.gravarArquivoProjeto("produto/lanche/",
				Integer.toString(lanche.getIdProduto()));// produtoRN
		// .ultimoElementoAdicionado().toString());
		construct();
	}

	public Empresa getEmpresa() {
		return empresa;
	}

	public void atualizaProdutoLanche() {

		ProdutoRN produtoRN = new ProdutoRN();
		produtoRN.atualizarProduto(lanche);
		construct();
	}

	public void uploadAction(FileUploadEvent event) {
		this.arquivo.fileUpload(event);
	}

	@PostConstruct
	public void construct() {
		empresa = empresaBean.getEmpresa();
		novoLanche();
		ProdutoRN produtoRN = new ProdutoRN();
		lanches = produtoRN.listarLanche(empresa.getIdEmpresa());
	}

	public EmpresaBean getEmpresaBean() {
		return empresaBean;
	}

	public void setEmpresaBean(EmpresaBean empresaBean) {
		this.empresaBean = empresaBean;
	}

	public FileUpload getArquivo() {
		return arquivo;
	}

	public void setArquivo(FileUpload arquivo) {
		this.arquivo = arquivo;
	}

}
