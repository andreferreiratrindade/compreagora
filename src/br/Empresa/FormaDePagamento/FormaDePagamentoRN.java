package br.Empresa.FormaDePagamento;

import java.util.List;

import br.util.DAOFactoy;

public class FormaDePagamentoRN {
	private FormaDePagamentoDao dao;

	public FormaDePagamentoRN() {
		dao = DAOFactoy.criarFormaDePagamento();
	}

	public FormaDePagamentoDao getDao() {
		return dao;
	}

	public void setDao(FormaDePagamentoDao dao) {
		this.dao = dao;
	}

	public void salvar(FormaDePagamento obj) {
		this.dao.salve(obj);
	}

	public List<FormaDePagamento> listar() {
		return this.dao.lista();
	}

	public FormaDePagamento getFormaDePagamento(int id) {
		return dao.getUnico(id);
	}

	public FormaDePagamento peloTipo(String string) {
		return this.dao.pesquisaPeloTipo(string);
	}

	

}
