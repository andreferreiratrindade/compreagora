package br.beans.empresa;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.model.ListDataModel;

import br.Empresa.Empresa;
import br.Empresa.EmpresaRN;
import br.Empresa.Categoria.Categoria;
import br.Empresa.Categoria.CategoriaRN;

@ManagedBean(name = "categoriaEmpresaBean")
@ViewScoped
public class CategoriaEmpresaBean {
	@ManagedProperty(value = "#{empresaBean}")
	private EmpresaBean empresaBean;
	private Empresa empresa = null;
	private ListDataModel<Categoria> categorias;
	private ListDataModel<Categoria> categoriasEmpresa;

	public ListDataModel<Categoria> getCategorias() {
		return categorias;
	}

	public void setCategorias(ListDataModel<Categoria> categorias) {
		this.categorias = categorias;
	}

	public ListDataModel<Categoria> getCategoriasEmpresa() {
		return categoriasEmpresa;
	}

	public void setCategoriasEmpresa(ListDataModel<Categoria> categoriasEmpresa) {
		this.categoriasEmpresa = categoriasEmpresa;
	}

	public EmpresaBean getEmpresaBean() {
		return empresaBean;
	}

	public void setEmpresaBean(EmpresaBean empresaBean) {
		this.empresaBean = empresaBean;
	}

	public Empresa getEmpresa() {
		return empresa;
	}

	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}

	@PostConstruct
	public void init() {
		if (empresa == null) {
			EmpresaRN empresaRN = new EmpresaRN();

			empresa = empresaRN.getEmpresa(empresaBean.getEmpresa()
					.getIdEmpresa());
			empresa.getFormasDePagamento().size();
			categoriasEmpresa = new ListDataModel<Categoria>(
					empresa.getCategorias());

			CategoriaRN ceRN = new CategoriaRN();
			categorias = new ListDataModel<Categoria>(ceRN.listar());

		}

	}

	public void addCategoria() {

		Categoria cat = (Categoria) (categorias.getRowData());

		if (!empresa.getCategorias().contains(cat)) {
			empresa.addCategoria(cat);

			EmpresaRN empresaRN = new EmpresaRN();
			empresaRN.update(empresa);
			categoriasEmpresa = new ListDataModel<Categoria>(
					empresa.getCategorias());
		}
	}

	public void removerCategoria() {
		Categoria catEmpresa = (Categoria) (categoriasEmpresa.getRowData());

		empresa.removeCategoria(catEmpresa);

		EmpresaRN empresaRN = new EmpresaRN();
		empresaRN.update(empresa);
		categoriasEmpresa = new ListDataModel<Categoria>(
				empresa.getCategorias());

	}

}
