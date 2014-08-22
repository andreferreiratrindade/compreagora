package br.beans.empresa;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.model.ListDataModel;

import br.Empresa.Empresa;
import br.Empresa.EmpresaRN;
import br.Empresa.FormaDePagamento.FormaDePagamento;
import br.Empresa.FormaDePagamento.FormaDePagamentoRN;

@ManagedBean(name = "fdpEmpresaBean")
@ViewScoped
public class FormaDePagamentoEmpresaBean {
	@ManagedProperty(value = "#{empresaBean}")
	private EmpresaBean empresaBean;
	private Empresa empresa = null;
	private ListDataModel<FormaDePagamento> formasDePagamento;
	private ListDataModel<FormaDePagamento> formasDePagamentoEmpresa;

	public ListDataModel<FormaDePagamento> getFormasDePagamentoEmpresa() {
		return formasDePagamentoEmpresa;
	}

	public void setFormasDePagamentoEmpresa(
			ListDataModel<FormaDePagamento> formasDePagamentoEmpresa) {
		this.formasDePagamentoEmpresa = formasDePagamentoEmpresa;
	}

	public ListDataModel<FormaDePagamento> getFormasDePagamento() {
		return formasDePagamento;
	}

	public void setFormasDePagamento(
			ListDataModel<FormaDePagamento> formasDePagamento) {
		this.formasDePagamento = formasDePagamento;
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
			formasDePagamentoEmpresa = new ListDataModel<FormaDePagamento>(
					empresa.getFormasDePagamento());
			FormaDePagamentoRN formaDePagamentoRN = new FormaDePagamentoRN();

			formasDePagamento = new ListDataModel<FormaDePagamento>(
					formaDePagamentoRN.listar());

		}

	}

	public void addFormaDePagamento() {

		FormaDePagamento fdp = (FormaDePagamento) (formasDePagamento
				.getRowData());

		if (!empresa.getFormasDePagamento().contains(fdp)) {
			empresa.addFormaDePagamento(fdp);

			EmpresaRN empresaRN = new EmpresaRN();
			empresaRN.update(empresa);
			formasDePagamentoEmpresa = new ListDataModel<FormaDePagamento>(
					empresa.getFormasDePagamento());
		}

	}

	public void removerFormaDePagamento() {
		FormaDePagamento fdp = (FormaDePagamento) (formasDePagamentoEmpresa
				.getRowData());

		if (empresa.getFormasDePagamento().contains(fdp)) {
			empresa.removeFormaDePagamento(fdp);

			EmpresaRN empresaRN = new EmpresaRN();
			empresaRN.update(empresa);
			FormaDePagamentoRN formaDePagamentoRN = new FormaDePagamentoRN();

			formasDePagamento = new ListDataModel<FormaDePagamento>(
					formaDePagamentoRN.listar());
		}
	}

}
