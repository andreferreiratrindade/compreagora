package br.beans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import org.primefaces.event.SelectEvent;

import br.AtendimentoLugares.Bairro;
import br.AtendimentoLugares.Cidade;
import br.AtendimentoLugares.CidadeRN;
import br.Empresa.Empresa;
import br.Empresa.EmpresaRN;
import br.Empresa.Categoria.CategoriaENUM;

@ManagedBean
@ViewScoped
public class AutoCompleteController implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Cidade cidade;
	private List<Cidade> cidades;
	private Bairro bairro;
	private List<Bairro> bairros;
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

		cidades = new ArrayList<Cidade>();
		bairros = new ArrayList<Bairro>();
		empresas = new ArrayList<Empresa>();

	}

	public List<Empresa> getEmpresas() {

		return empresas;
	}

	public void setEmpresas(List<Empresa> empresas) {
		this.empresas = empresas;
	}

	public Bairro getBairro() {
		return bairro;
	}

	public void setBairro(Bairro bairro) {
		this.bairro = bairro;
	}

	public List<Bairro> getBairros() {
		return bairros;
	}

	public void setBairros(List<Bairro> bairros) {
		this.bairros = bairros;
	}

	public void carregaBairro() {
		this.bairros = cidade.getBairros();
	}

	public List<Bairro> completaBairro(String query) {

		this.bairros = this.cidade.getBairros();

		List<Bairro> sugestoes = new ArrayList<Bairro>();

		for (Bairro j : this.bairros) {
			if (j.getDescBairro().toUpperCase().startsWith(query.toUpperCase())) {
				sugestoes.add(j);
			}
		}

		return sugestoes;
	}

	public void handleSelectBairro(SelectEvent event) {
		bairro = (Bairro) event.getObject();
	}

	public void atualizaSelecaoEmpresa() {

		empresas = new ArrayList<Empresa>();

		EmpresaRN empresaRN = new EmpresaRN();

		empresas = empresaRN.listaEmpresasPeloBairroECategoria(
				bairro.getIdBairro(),
				CategoriaENUM.values()[contextUtil.getCategoriaEmpresa()]);

		for (Empresa x : empresas) {
			x.getHorarioFuncionamento().size();
		}

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
		this.cidades = cidadeRN.listar();
		List<Cidade> sugestoes = new ArrayList<Cidade>();

		for (Cidade j : this.cidades) {
			if (j.getDescCidade().toUpperCase().startsWith(query.toUpperCase())) {
				sugestoes.add(j);
			}
		}

		return sugestoes;
	}

	public Cidade getCidade() {
		if (cidade == null) {
			cidade = new Cidade();
		}
		return cidade;
	}

	public void setCidade(Cidade cidade) {
		this.cidade = cidade;
		bairros = cidade.getBairros();
	}

	public List<Cidade> getCidades() {
		return cidades;
	}

	public void setCidades(List<Cidade> cidades) {
		this.cidades = cidades;
	}

	public ContextUtil getContextUtil() {
		return contextUtil;
	}

	public void setContextUtil(ContextUtil contextUtil) {
		this.contextUtil = contextUtil;
	}

}
