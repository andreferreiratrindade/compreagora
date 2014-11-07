package br.Empresa.FormaDePagamento;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import br.util.DAOFactoy;
import br.util.JpaUtil;

public class FormaDePagamentoDaoTest {

	@BeforeClass
	public static void setUp() {
		JpaUtil.getEntityManager().getTransaction().begin();
		initFormaDePagamento();
	}

	@AfterClass
	public static void setDown() {
		JpaUtil.getEntityManager().getTransaction().commit();
		JpaUtil.closeEntityManager();
	}

	public static void initFormaDePagamento() {

		FormaDePagamento formaDePagamento = new FormaDePagamento();
		formaDePagamento.setTipo("Dinheiro");
		formaDePagamento.setImagem("dinheiro.gif");

		FormaDePagamentoDao formaDePagamentoDao = DAOFactoy
				.criarFormaDePagamento();

		formaDePagamentoDao.salve(formaDePagamento);

		formaDePagamento = new FormaDePagamento();
		formaDePagamento.setTipo("Visa Crédito");
		formaDePagamento.setImagem("visacredito.gif");

		formaDePagamentoDao.salve(formaDePagamento);
	}

	@Test
	public void deveRetornarUnico() {

		FormaDePagamentoRN fdpRN = new FormaDePagamentoRN();
		FormaDePagamento fdp = fdpRN.getFormaDePagamento(1);
		assertEquals("Dinheiro", fdp.getTipo());
	}

	@Test
	public void deveListar() {
		FormaDePagamentoRN fdpRN = new FormaDePagamentoRN();
		List<FormaDePagamento> fdps = fdpRN.listar();
		assertEquals(2, fdps.size());
	}

	@Test
	public void deveRetornarPeloTipo() {
		FormaDePagamentoRN fdpRN = new FormaDePagamentoRN();
		FormaDePagamento fdp = fdpRN.peloTipo("Dinheiro");
		assertEquals("Dinheiro", fdp.getTipo());
	}

}
