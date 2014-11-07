package br.beans.produto;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.primefaces.event.FileUploadEvent;

import br.Empresa.Empresa;
import br.Produto.Gas;
import br.Produto.ProdutoRN;
import br.beans.empresa.EmpresaBean;
import br.util.FileUpload;

@ManagedBean
@ViewScoped
public class ManterProdutoGasBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@ManagedProperty(value = "#{empresaBean}")
	private EmpresaBean empresaBean;

	private Empresa empresa;
	private List<Gas> listGas;
	private Gas gas;
	private FileUpload arquivo;

	public List<Gas> getListGas() {
		return listGas;
	}

	public void setListGas(List<Gas> listGas) {
		this.listGas = listGas;
	}

	public ManterProdutoGasBean() {
		arquivo = new FileUpload();
	}

	public List<Gas> getGass() {
		return listGas;
	}

	public void setGass(List<Gas> listGas) {
		this.listGas = listGas;
	}

	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}

	public Gas getGas() {
		return gas;
	}

	public void setGas(Gas gas) {
		this.gas = gas;
	}

	public void novoGas() {
		gas = new Gas();
		arquivo = new FileUpload();
	}

	public void salvarGas() {
		gas.setEmpresa(empresa);

		ProdutoRN produtoRN = new ProdutoRN();
		produtoRN.salve(gas);

		this.arquivo.gravarArquivoTomCat("produto/gas/",
				Integer.toString(gas.getIdProduto()));// produtoRN
		// .ultimoElementoAdicionado().toString());
		this.arquivo.gravarArquivoProjeto("produto/gas/",
				Integer.toString(gas.getIdProduto()));// produtoRN
		// .ultimoElementoAdicionado().toString());
		construct();
	}

	public Empresa getEmpresa() {
		return empresa;
	}

	public void atualizaProdutoGas() {

		ProdutoRN produtoRN = new ProdutoRN();
		produtoRN.atualizarProduto(gas);
		construct();
	}

	public void uploadAction(FileUploadEvent event) {
		this.arquivo.fileUpload(event);
	}

	@PostConstruct
	public void construct() {
		empresa = empresaBean.getEmpresa();
		novoGas();
		ProdutoRN produtoRN = new ProdutoRN();
		listGas = produtoRN.listarGas(empresa.getIdEmpresa());
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
