package br.beans.produto;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.primefaces.event.FileUploadEvent;

import br.Empresa.Empresa;
import br.Produto.Pizza;
import br.Produto.ProdutoRN;
import br.beans.empresa.EmpresaBean;
import br.util.FileUpload;

@ManagedBean
@ViewScoped
public class ManterProdutoPizzaBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@ManagedProperty(value = "#{empresaBean}")
	private EmpresaBean empresaBean;

	private Empresa empresa;
	private List<Pizza> pizzas;
	private Pizza pizza;
	private FileUpload arquivo;

	public ManterProdutoPizzaBean() {
		arquivo = new FileUpload();
	}

	public List<Pizza> getPizzas() {
		return pizzas;
	}

	public void setPizzas(List<Pizza> pizzas) {
		this.pizzas = pizzas;
	}

	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}

	public Pizza getPizza() {
		return pizza;
	}

	public void setPizza(Pizza pizza) {
		this.pizza = pizza;
	}

	public void novoPizza() {
		pizza = new Pizza();
		arquivo = new FileUpload();
	}

	public void salvarPizza() {
		pizza.setEmpresa(empresa);

		ProdutoRN produtoRN = new ProdutoRN();
		produtoRN.salve(pizza);

		this.arquivo.gravarArquivoTomCat("produto/pizza/",
				Integer.toString(pizza.getIdProduto()));
		this.arquivo.gravarArquivoProjeto("produto/pizza/",
				Integer.toString(pizza.getIdProduto()));
		construct();
	}

	public Empresa getEmpresa() {
		return empresa;
	}

	public void atualizaProdutoPizza() {

		ProdutoRN produtoRN = new ProdutoRN();
		produtoRN.atualizarProduto(pizza);
		construct();
	}

	public void uploadAction(FileUploadEvent event) {
		this.arquivo.fileUpload(event);
	}

	@PostConstruct
	public void construct() {
		empresa = empresaBean.getEmpresa();
		novoPizza();
		ProdutoRN produtoRN = new ProdutoRN();
		pizzas = produtoRN.listarPizza(empresa.getIdEmpresa());
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
