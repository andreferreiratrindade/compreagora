package br.Empresa;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import br.util.DAOFactoy;
import br.util.JpaUtil;

public class EmpresaDaoTest {

	private List<Empresa> empresas;

	@BeforeClass
	public static void setUp() {
		JpaUtil.getEntityManager().getTransaction().begin();
		inicializaEmpresas();
	}

	@AfterClass
	public static void setDown() {
		JpaUtil.getEntityManager().getTransaction().commit();
		JpaUtil.closeEntityManager();
	}

	@Test
	public void testSalve() {
		Empresa empresa = new Empresa();
		empresa.setNomeFant("Empresa1");

		EmpresaDAO dao = DAOFactoy.criarEmpresa();

		empresas = dao.lista();
		assertEquals(2, empresas.size());

	}
	
	public static void inicializaEmpresas() {
		Empresa empresa = new Empresa();
		empresa.setNomeFant("Empresa1");

		EmpresaDAO dao = DAOFactoy.criarEmpresa();

		dao.salve(empresa);

		empresa = new Empresa();
		empresa.setNomeFant("Empresa2");
		dao.salve(empresa);

	}

	@Test
	public void deveListarEmpresa() {
		EmpresaRN empresaRN = new EmpresaRN();
		List<Empresa> empresas = empresaRN.listar();
		assertEquals(2, empresas.size());
	}
}
