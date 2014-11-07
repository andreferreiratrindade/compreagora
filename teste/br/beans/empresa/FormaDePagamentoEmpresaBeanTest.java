package br.beans.empresa;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import br.Empresa.Empresa;
import br.Empresa.EmpresaDAO;
import br.Empresa.EmpresaRN;
import br.Empresa.FormaDePagamento.FormaDePagamento;
import br.Empresa.FormaDePagamento.FormaDePagamentoRN;
import br.util.DAOFactoy;
import br.util.JpaUtil;

public class FormaDePagamentoEmpresaBeanTest {

	@BeforeClass
	public static void setUp() {
		JpaUtil.getEntityManager().getTransaction().begin();
		initFormaDePagamento();
		iniciandoEmpresa();
	}

	private static void iniciandoEmpresa() {

		Empresa empresa = new Empresa();
		empresa.setNomeFant("Empresa Teste");

		FormaDePagamentoRN formaDePagamentoRN = new FormaDePagamentoRN();

		FormaDePagamento fdp = formaDePagamentoRN.getFormaDePagamento(1);
		empresa.addFormaDePagamento(fdp);

		EmpresaDAO empresaDao = DAOFactoy.criarEmpresa();
		empresaDao.salve(empresa);
	}

	public static void initFormaDePagamento() {
		FormaDePagamento formaDePagamento = new FormaDePagamento();
		formaDePagamento.setTipo("Dinheiro");

		FormaDePagamentoRN fdpRN = new FormaDePagamentoRN();
		fdpRN.salvar(formaDePagamento);

		fdpRN = new FormaDePagamentoRN();
		formaDePagamento = new FormaDePagamento();
		formaDePagamento.setTipo("Visa Crédito");
		fdpRN.salvar(formaDePagamento);

		fdpRN = new FormaDePagamentoRN();
		formaDePagamento = new FormaDePagamento();
		formaDePagamento.setTipo("Visa Debito");
		fdpRN.salvar(formaDePagamento);

	}

	@AfterClass
	public static void setDown() {
		JpaUtil.getEntityManager().getTransaction().commit();
		JpaUtil.closeEntityManager();
	}

	@Test
	public void deveRetornarListaDeFdpDaEmpresa() {

		EmpresaRN empresaRN = new EmpresaRN();
		Empresa empresa = empresaRN.getEmpresa(1);
		List<FormaDePagamento> fdps = empresa.getFormasDePagamento();

		assertEquals(1, fdps.size());

	}

}
