package br.Empresa;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import br.builders.EmpresaBuilder;
import br.util.DAOFactoy;
import br.util.JpaUtil;

public class EmpresaDAOTest {

	@Before
	public void setUp() {
		JpaUtil.getEntityManager().getTransaction().begin();
		EmpresaBuilder empresaBuilder = new EmpresaBuilder();
		empresaBuilder.criar();
	}

	@After
	public void setDown() {
		JpaUtil.getEntityManager().getTransaction().commit();
		JpaUtil.closeEntityManager();
	}

	@Test
	public void deveRetornarUmaEmpresa() {

		EmpresaDAO empresaDAO = DAOFactoy.criarEmpresa();
		Empresa empresa = empresaDAO.getUnico(3);

		assertEquals("Empresa 1", empresa.getRazaoSocial());
	}
}
