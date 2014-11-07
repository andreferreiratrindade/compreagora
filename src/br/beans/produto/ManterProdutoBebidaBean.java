package br.beans.produto;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.primefaces.event.FileUploadEvent;

import br.Empresa.Empresa;
import br.Produto.Bebida;
import br.Produto.ProdutoRN;
import br.beans.empresa.EmpresaBean;
import br.util.FileUpload;

@ManagedBean
@ViewScoped
public class ManterProdutoBebidaBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@ManagedProperty(value = "#{empresaBean}")
	private EmpresaBean empresaBean;

	private Empresa empresa;
	private List<Bebida> bebidas;
	private Bebida bebida;
	private FileUpload arquivo;

	public List<Bebida> getBebidas() {
		return bebidas;
	}

	public void setBebidas(List<Bebida> bebidas) {
		this.bebidas = bebidas;
	}

	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}

	public Bebida getBebida() {
		return bebida;
	}

	public void setBebida(Bebida refri) {
		this.bebida = refri;
	}

	public void novoBebida() {
		bebida = new Bebida();
		bebida.setEmpresa(empresa);
		arquivo = new FileUpload();
	}

	public void salvarBebida() {

		ProdutoRN produtoRN = new ProdutoRN();
		produtoRN.salve(bebida);

		this.arquivo.gravarArquivoTomCat("produto/bebida/", produtoRN
				.ultimoElementoAdicionado().toString());
		this.arquivo.gravarArquivoProjeto("produto/bebida/", produtoRN
				.ultimoElementoAdicionado().toString());
		construct();
	}

	public Empresa getEmpresa() {

		return empresa;
	}

	public void atualizaProdutoBebida() {

		ProdutoRN produtoRN = new ProdutoRN();
		produtoRN.atualizarProduto(bebida);
		construct();
	}

	public void uploadAction(FileUploadEvent event) {
		this.arquivo.fileUpload(event);
	}

	public FileUpload getArquivo() {
		return arquivo;
	}

	public void setArquivo(FileUpload arquivo) {
		this.arquivo = arquivo;
	}

	@PostConstruct
	public void construct() {
		empresa = empresaBean.getEmpresa();
		novoBebida();
		ProdutoRN produtoRN = new ProdutoRN();
		bebidas = produtoRN.listarBebida(empresa.getIdEmpresa());

	}

	public EmpresaBean getEmpresaBean() {
		return empresaBean;
	}

	public void setEmpresaBean(EmpresaBean empresaBean) {
		this.empresaBean = empresaBean;
	}
}
