package br.beans.util;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

@ManagedBean(name = "pagPath")
@RequestScoped
public class PagPath {

	public String novoCadastroDeUsuario() {
		return "/paginas/publico/cadastroCliente.jsf?faces-redirect=true";
	}

	public String administrativo() {
		return "/paginas/restrito_empresa/empresa/PedidosDeClientes.jsf?faces-redirect=true";
	}

	public String login() {
		return "/paginas/publico/login.jsf?faces-redirect=true";
	}

	public String meusPedidos() {
		return "/paginas/publico/meusPedidos.jsf?faces-redirect=true";
	}

	public String selecionaEnderecoFiltro() {
		return "/paginas/categoria/endereco/selecionaEndereco.jsf?faces-redirect=true";
	}

	public String clienteEmailContato() {
		return "/paginas/publico/clienteEmailContato.jsf?faces-redirect=true";
	}

	// Administrativo Mantem Empresa
	public String empresaformaDePagamento() {
		return "/paginas/admin/empresa/configuracoes/empresaFormaDePagamento.jsf?faces-redirect=true";
	}

	public String manterCategoriaEmpresa() {
		return "/paginas/admin/empresa/configuracoes/manterCategoriaEmpresa.jsf?faces-redirect=true";

	}

	public String produtoLanche() {
		return "/paginas/admin/empresa/produto/produtoLanche.jsf?faces-redirect=true";
	}

	public String produtoBebida() {
		return "/paginas/admin/empresa/produto/produtoBebida.jsf?faces-redirect=true";
	}

	public String produtoPizza() {
		return "/paginas/admin/empresa/produto/produtoPizza.jsf?faces-redirect=true";
	}

	public String produtoGas() {
		return "/paginas/admin/empresa/produto/produtoGas.jsf?faces-redirect=true";
	}

	public String produtoAgua() {
		return "/paginas/admin/empresa/produto/produtoAgua.jsf?faces-redirect=true";
	}

	public String produtoMarmitex() {
		return "/paginas/admin/empresa/produto/produtoMarmitex.jsf?faces-redirect=true";
	}

	public String avulsos() {
		return "/paginas/admin/empresa/produto/avulso/avulso.jsf?faces-redirect=true";
	}

	// Empresa mantem produtos
	public String manterProdutoLanche() {
		return "/paginas/restrito_empresa/empresa/manterProdutos/ManterProdutoLanche?faces-redirect=true";
	}

	public String manterProdutoBebida() {
		return "/paginas/restrito_empresa/empresa/manterProdutos/ManterProdutoBebida?faces-redirect=true";
	}

	public String manterProdutoGas() {
		return "/paginas/restrito_empresa/empresa/manterProdutos/ManterProdutoGas?faces-redirect=true";
	}

	public String manterProdutoAgua() {
		return "/paginas/restrito_empresa/empresa/manterProdutos/ManterProdutoAgua?faces-redirect=true";
	}

	public String manterProdutoMarmitex() {
		return "/paginas/restrito_empresa/empresa/manterProdutos/ManterProdutoMarmitex?faces-redirect=true";
	}

	public String manterProdutoPizza() {
		return "/paginas/restrito_empresa/empresa/manterProdutos/ManterProdutoPizza?faces-redirect=true";
	}

	public String relatorioFaturamento() {
		return "//paginas/restrito_empresa/empresa/relatorios/faturamento.jsf?faces-redirect=true";
	}

	public String relatorioPodutosCadastrados() {
		return "/paginas/restrito_empresa/empresa/relatorios/produtosCadastrados.jsf?faces-redirect=true";
	}

	public String pedidosDeClientes() {
		return "/paginas/restrito_empresa/empresa/PedidosDeClientes.jsf?faces-redirect=true";
	}
}
