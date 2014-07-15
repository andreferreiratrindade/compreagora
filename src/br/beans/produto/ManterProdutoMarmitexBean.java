package br.beans.produto;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.primefaces.event.FileUploadEvent;

import br.Empresa.Empresa;
import br.Produto.Marmitex;
import br.Produto.ProdutoRN;
import br.beans.empresa.EmpresaBean;
import br.util.FileUpload;

@ManagedBean
@ViewScoped
public class ManterProdutoMarmitexBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@ManagedProperty(value = "#{empresaBean}")
	private EmpresaBean empresaBean;

	private Empresa empresa;
	private List<Marmitex> marmitexs;
	private Marmitex marmitex;
	private FileUpload arquivo;

	public ManterProdutoMarmitexBean() {
		arquivo = new FileUpload();
	}

	public List<Marmitex> getMarmitexs() {
		return marmitexs;
	}

	public void setMarmitexs(List<Marmitex> marmitexs) {
		this.marmitexs = marmitexs;
	}

	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}

	public Marmitex getMarmitex() {
		return marmitex;
	}

	public void setMarmitex(Marmitex marmitex) {
		this.marmitex = marmitex;
	}

	public void novoMarmitex() {
		marmitex = new Marmitex();
		arquivo = new FileUpload();
	}

	public void salvarMarmitex() {
		marmitex.setEmpresa(empresa);

		ProdutoRN produtoRN = new ProdutoRN();
		produtoRN.salve(marmitex);

		this.arquivo.gravarArquivoTomCat("produto/marmitex/",
				Integer.toString(marmitex.getIdProduto()));// produtoRN
		// .ultimoElementoAdicionado().toString());
		this.arquivo.gravarArquivoProjeto("produto/marmitex/",
				Integer.toString(marmitex.getIdProduto()));// produtoRN
		// .ultimoElementoAdicionado().toString());
		construct();
	}

	public Empresa getEmpresa() {
		return empresa;
	}

	public void atualizaProdutoMarmitex() {

		ProdutoRN produtoRN = new ProdutoRN();
		produtoRN.atualizarProduto(marmitex);
		construct();
	}

	public void uploadAction(FileUploadEvent event) {
		this.arquivo.fileUpload(event);
	}

	@PostConstruct
	public void construct() {
		empresa = empresaBean.getEmpresa();
		novoMarmitex();
		ProdutoRN produtoRN = new ProdutoRN();
		marmitexs = produtoRN.listarMarmitex(empresa.getIdEmpresa());
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
