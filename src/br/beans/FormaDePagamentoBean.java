package br.beans;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.primefaces.event.FileUploadEvent;

import br.Empresa.FormaDePagamento.FormaDePagamento;
import br.Empresa.FormaDePagamento.FormaDePagamentoRN;
import br.util.FileUpload;

@ManagedBean
@ViewScoped
public class FormaDePagamentoBean {
	private FormaDePagamento formaDePagamento;
	private FileUpload arquivo = new FileUpload();
	private List<FormaDePagamento> formasDePagamento;

	@PostConstruct
	public void construct() {
		FormaDePagamentoRN fdpRN = new FormaDePagamentoRN();
		formasDePagamento = fdpRN.listar();
		formaDePagamento = new FormaDePagamento();
	}


	public List<FormaDePagamento> getFormasDePagamento() {
		return formasDePagamento;
	}

	public void setFormasDePagamento(List<FormaDePagamento> formasDePagamento) {
		this.formasDePagamento = formasDePagamento;
	}

	public void salvar() {
		FormaDePagamentoRN formaDePagamentoRN = new FormaDePagamentoRN();
		formaDePagamentoRN.salvar(formaDePagamento);

		this.arquivo.gravarArquivoTomCat("icones/", "formaDePagamento/"
				+ formaDePagamento.getIdFormaDePagamento());

		this.arquivo.gravarArquivoProjeto("icones/", "formaDePagamento/"
				+ formaDePagamento.getIdFormaDePagamento());

		formaDePagamento = new FormaDePagamento();
	}


	public void uploadAction(FileUploadEvent event) {
		this.arquivo.fileUpload(event);
	}

	public FormaDePagamento getFormaDePagamento() {
		return formaDePagamento;
	}

	public void setFormaDePagamento(FormaDePagamento formaDePagamento) {
		this.formaDePagamento = formaDePagamento;
	}

}
