package br.builders;

import br.Empresa.FormaDePagamento.FormaDePagamento;
import br.Empresa.FormaDePagamento.FormaDePagamentoDao;
import br.util.DAOFactoy;

public class FormaDePagamentoBuilder {

	private FormaDePagamentoDao dao;

	public FormaDePagamentoBuilder() {
		dao = DAOFactoy.criarFormaDePagamento();
	}

	public void criar() {

		FormaDePagamento formaDePagamentoDinheiro = new FormaDePagamento();
		formaDePagamentoDinheiro.setTipo("Dinheiro");
		dao.salve(formaDePagamentoDinheiro);

		FormaDePagamento formaDePagamentoVisa = new FormaDePagamento();
		formaDePagamentoVisa.setTipo("Visa");
		dao.salve(formaDePagamentoVisa);

	}
}
