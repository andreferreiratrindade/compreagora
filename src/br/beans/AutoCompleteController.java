package br.beans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
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
import br.beans.util.ContextUtil;

@ManagedBean(name="autoCompleteController")
@SessionScoped
public class AutoCompleteController implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Cidade cidade;
	private Bairro bairro;
	private List<Empresa> empresas;
	@ManagedProperty(value = "#{contextUtil}")
	private ContextUtil contextUtil;
	private Empresa empresa;

	public Empresa getEmpresa() {
		return empresa;
	}

	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}

	@PostConstruct
	public void init() {

		empresas = new ArrayList<Empresa>();

	}

	public List<Empresa> getEmpresas() {

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
	
	public String atualizaSelecaoEmpresaReturn() {

		if (bairro != null) {
			empresas = new ArrayList<Empresa>();

			EmpresaRN empresaRN = new EmpresaRN();

			empresas = empresaRN.listaEmpresasPeloBairroECategoria(
					bairro.getIdBairro(),
					CategoriaENUM.values()[contextUtil.getCategoriaEmpresa()]);

			for (Empresa x : empresas) {
				x.getHorarioFuncionamento().size();
			}
		}
		
		return "/principal.jsf?faces-redirect=true";
	}

	public void atualizaSelecaoEmpresa() {

		if (bairro != null) {
			empresas = new ArrayList<Empresa>();

			EmpresaRN empresaRN = new EmpresaRN();

			empresas = empresaRN.listaEmpresasPeloBairroECategoria(
					bairro.getIdBairro(),
					CategoriaENUM.values()[contextUtil.getCategoriaEmpresa()]);

			for (Empresa x : empresas) {
				x.getHorarioFuncionamento().size();
			}
		}
		
		System.out.println("----"+contextUtil.getCategoriaEmpresa());
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

	public ContextUtil getContextUtil() {
		return contextUtil;
	}

	public void setContextUtil(ContextUtil contextUtil) {
		this.contextUtil = contextUtil;
	}

}
