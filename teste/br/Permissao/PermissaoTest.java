package br.Permissao;

import static org.junit.Assert.assertEquals;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import br.util.JpaUtil;

public class PermissaoTest {
	@BeforeClass
	public static void setUp() {
		JpaUtil.getEntityManager().getTransaction().begin();
	}
	@AfterClass
	public static void setDown() {
		JpaUtil.getEntityManager().getTransaction().commit();
		JpaUtil.closeEntityManager();
	}
	
	// Se não existir a permissão, o sistema deverar cadastra-la.
	@Test
	public void deveVerificarPermissao(){
		PermissaoRN permissaoRN = new PermissaoRN();
		Permissao permissao = new Permissao();
		permissao.setPermissao(PermissaoEnum.ROLE_CLI.name());
		permissaoRN.salve(permissao);
		
		Permissao permissao2 = new Permissao();
		permissao2 = permissaoRN.getPermissaoByDescricao("ROLE_CLI");
		
		assertEquals(1, permissao2.getIdPermissao());
	}

	
}
