package br.AtendimentoLugares;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import br.Cliente.Cliente;
import br.Empresa.Empresa;
import br.Empresa.EmpresaRN;
import br.Endereco.Endereco;
import br.Permissao.Permissao;
import br.Permissao.PermissaoDAO;
import br.Permissao.PermissaoEnum;
import br.util.DAOFactoy;
import br.util.JpaUtil;

public class EmpresaAtendimentoRNTest {

	@BeforeClass
	public static void setUp() {
		JpaUtil.getEntityManager().getTransaction().begin();
		initEmpresaAtendimento();
	}

	@AfterClass
	public static void setDown() {
		JpaUtil.getEntityManager().getTransaction().commit();
		JpaUtil.closeEntityManager();
	}

	public static void initEmpresaAtendimento() {

		Permissao permissao = new Permissao();
		permissao.setPermissao(PermissaoEnum.ROLE_ADM.name());
		PermissaoDAO permissaoDAO = DAOFactoy.criarPermissao();
		permissaoDAO.salve(permissao);

		// Salvando uma cidade no banco para o teste
		Cidade cidade = new Cidade();
		cidade.setDescCidade("Governador Valadares");
		CidadeDAO cidadeDao = DAOFactoy.criarCidade();

		cidadeDao.salve(cidade);

		// Salvando um bairro no banco para o teste
		Bairro bairro = new Bairro();
		bairro.setCidade(cidade);
		bairro.setDescBairro("Nova Vila Bretas");
		BairroDAO bairroDao = DAOFactoy.criarBairro();

		bairroDao.salve(bairro);

		// Salvando um bairro no banco para o teste

		Empresa empresa = new Empresa();
		empresa.setNomeFant("Rei do Hamburguer");

		Endereco endereco = new Endereco();
		endereco.setLogradouro("Rua 1");
		endereco.setBairroCidade(bairro);
		empresa.setEndereco(endereco);

		Cliente cliente = new Cliente();
		cliente.setNome("Empresa1");
		cliente.setEmail("empresa@gmail.com");

		EmpresaRN empresaRN = new EmpresaRN();

		empresaRN.salvar(empresa, cliente);

		EmpresaAtendimentoDAO empresaAtendimentoDao = DAOFactoy
				.criarEmpresaAtendimento();
		EmpresaAtendimento empresaAtendimento = new EmpresaAtendimento();

		empresaAtendimento.setBairro(bairro);
		empresaAtendimento.setEmpresa(empresa);
		empresaAtendimento.setTaxa(5);
		empresaAtendimentoDao.salve(empresaAtendimento);

	}

	@Test
	public void deveListarEmpresaAtendimento() {
		EmpresaAtendimentoRN empresaAtendimentoRN = new EmpresaAtendimentoRN();
		List<EmpresaAtendimento> empresaAtendimentos = empresaAtendimentoRN
				.listar();

		assertEquals(1, empresaAtendimentos.size());
		EmpresaAtendimento emAte = empresaAtendimentos.get(0);
		assertEquals("Nova Vila Bretas", emAte.getBairro().getDescBairro());

	}

	@Test
	public void deveVerificarSeEmpresaAtendeBairro() {
		EmpresaAtendimentoRN empresaAtendimentoRN = new EmpresaAtendimentoRN();
		int idEmpresa = 1;
		int idBairro = 1;

		EmpresaAtendimento empresaAtendimento = empresaAtendimentoRN
				.empresaAtendimentoEmpresaComBairro(idEmpresa, idBairro);
		assertEquals(5, empresaAtendimento.getTaxa(), 0.00001);
	}

}
