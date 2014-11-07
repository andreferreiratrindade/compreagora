package br.beans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import org.primefaces.event.SelectEvent;

import br.AtendimentoLugares.Bairro;
import br.AtendimentoLugares.BairroRN;
import br.AtendimentoLugares.Cidade;
import br.AtendimentoLugares.CidadeRN;
import br.Empresa.Empresa;
import br.Empresa.EmpresaRN;
import br.Empresa.Categoria.CategoriaENUM;

@ManagedBean(name = "autoCompleteController")
@ViewScoped
public class AutoCompleteController implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Cidade cidade;
	private Bairro bairro;
	private int idBairro;
	private List<Empresa> empresas;
	private int categoriaEmpresa;
	private String urlPedido;


	public String getUrlPedido() {
		return urlPedido;
	}

	public void setUrlPedido(String urlPedido) {
		this.urlPedido = urlPedido;
	}

	public int getIdBairro() {
		return idBairro;
	}

	public void setIdBairro(int idBairro) {
		this.idBairro = idBairro;
	}

	public int getCategoriaEmpresa() {
		return categoriaEmpresa;
	}

	public void setCategoriaEmpresa(int categoriaEmpresa) {
		this.categoriaEmpresa = categoriaEmpresa;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	private Empresa empresa;

	public Empresa getEmpresa() {
		return empresa;
	}

	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}

	public List<Empresa> getEmpresas() {
		if (empresas == null) {
			empresas = new ArrayList<Empresa>();
		}
		return empresas;
	}

	public void setEmpresas(List<Empresa> empresas) {
		this.empresas = empresas;
	}

	public Bairro getBairro() {
		if (bairro == null) {
			bairro = new Bairro();
		}
		return bairro;
	}

	public void setBairro(Bairro bairro) {
		this.bairro = bairro;
	}

	public List<Bairro> completaBairro(String query) {

		BairroRN bairroRN = new BairroRN();

		return bairroRN.getByDescription(this.cidade.getIdCidade(), query);
	}

	public void handleSelectBairro(SelectEvent event) {
		bairro = (Bairro) event.getObject();
	}

	public void atualizaSelecaoEmpresa() {

		if (bairro != null) {
			empresas = new ArrayList<Empresa>();

			EmpresaRN empresaRN = new EmpresaRN();

			empresas = empresaRN.listaEmpresasPeloBairroECategoria(
					bairro.getIdBairro(),
					CategoriaENUM.values()[categoriaEmpresa]);

			for (Empresa x : empresas) {
				x.getHorarioFuncionamento().size();
			}
			idBairro = bairro.getIdBairro();
		}

	}

	public String adicionaEmpresa() {

		Map<Integer, String> mapTipoEnum = new HashMap<Integer, String>();
		mapTipoEnum.put(CategoriaENUM.Lanche.ordinal(), mudaTelaLanche());
		mapTipoEnum.put(CategoriaENUM.Pizza.ordinal(), mudaTelaPizza());
		mapTipoEnum.put(CategoriaENUM.Gas.ordinal(), mudaTelaGas());
		mapTipoEnum.put(CategoriaENUM.Bebida.ordinal(), mudaTelaBebida());
		mapTipoEnum.put(CategoriaENUM.Agua.ordinal(), mudaTelaAgua());
		mapTipoEnum.put(CategoriaENUM.Marmitex.ordinal(), mudaTelaMarmitex());

		return mapTipoEnum.get(categoriaEmpresa);
	}

	public String mudaTelaMarmitex() {

		return "/paginas/categoria/escolhaProduto/SelecionaProdutoMarmitex?faces-redirect=true";

	}

	public String mudaTelaBebida() {

		return "/paginas/categoria/escolhaProduto/SelecionaProdutoBebida?faces-redirect=true";

	}

	public String mudaTelaLanche() {

		return "/paginas/categoria/escolhaProduto/SelecionaProdutoLanche?faces-redirect=true";

	}

	public String mudaTelaPizza() {

		return "/paginas/categoria/escolhaProduto/SelecionaProdutoPizza?faces-redirect=true";
	}

	public String mudaTelaAgua() {

		return "/paginas/categoria/escolhaProduto/SelecionaProdutoAgua?faces-redirect=true";

	}

	public String mudaTelaGas() {

		return "/paginas/categoria/escolhaProduto/SelecionaProdutoGas?faces-redirect=true";

	}

	public void verificaCidadeBairroMenssagem(ActionEvent actionEvent) {

		FacesContext.getCurrentInstance().addMessage(null,
				new FacesMessage("Informação necessaria"));
	}

	public void handleSelect(SelectEvent event) {

		this.cidade = (Cidade) event.getObject();

	}

	public List<Cidade> completaCidade(String query) {
		CidadeRN cidadeRN = new CidadeRN();

		return cidadeRN.getByDescription(query);
	}

	public Cidade getCidade() {
		if (cidade == null) {
			cidade = new Cidade();
		}
		return cidade;
	}

	public void setCidade(Cidade cidade) {
		this.cidade = cidade;
	}

	public void menuSelecionaLanche() {
		categoriaEmpresa = CategoriaENUM.Lanche.ordinal();
		atualizaSelecaoEmpresa();
	}

	public void menuSelecionaBebida() {
		categoriaEmpresa = CategoriaENUM.Bebida.ordinal();
		atualizaSelecaoEmpresa();
	}

	public void menuSelecionaRestaurante() {
		categoriaEmpresa = CategoriaENUM.Marmitex.ordinal();
		atualizaSelecaoEmpresa();
	}

	public void menuSelecionaPizzaria() {
		categoriaEmpresa = CategoriaENUM.Pizza.ordinal();
		atualizaSelecaoEmpresa();
	}

	public void menuSelecionaGas() {
		categoriaEmpresa = CategoriaENUM.Gas.ordinal();
		atualizaSelecaoEmpresa();
	}

	public void menuSelecionaAgua() {
		categoriaEmpresa = CategoriaENUM.Agua.ordinal();
		atualizaSelecaoEmpresa();
	}

}
