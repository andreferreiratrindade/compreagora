package br.builders;

import br.AtendimentoLugares.Bairro;
import br.AtendimentoLugares.BairroDAO;
import br.AtendimentoLugares.EmpresaAtendimento;
import br.AtendimentoLugares.EmpresaAtendimentoDAO;
import br.Empresa.Empresa;
import br.Empresa.EmpresaDAO;
import br.Empresa.Categoria.Categoria;
import br.Empresa.Categoria.CategoriaDao;
import br.Empresa.Categoria.CategoriaENUM;
import br.Empresa.FormaDePagamento.FormaDePagamento;
import br.Empresa.FormaDePagamento.FormaDePagamentoDao;
import br.Endereco.Endereco;
import br.util.DAOFactoy;

public class EmpresaBuilder {
	private CategoriaDao categoriaDao;
	private FormaDePagamentoDao fdpDao;
	private EmpresaDAO empresaDAO;
	private BairroDAO bairroDao;
	private EmpresaAtendimentoDAO empresaAtendimentoDao;

	private void initValores() {
		CategoriaBuilder categoriaBuilder = new CategoriaBuilder();
		categoriaBuilder.criar();

		FormaDePagamentoBuilder formaDePagamentoBuilder = new FormaDePagamentoBuilder();
		formaDePagamentoBuilder.criar();

		CidadeEBairroBuilder cidadeEBairroBuilder = new CidadeEBairroBuilder();
		cidadeEBairroBuilder.criar();

		PermissaoBuilder permissaoBuilder = new PermissaoBuilder();
		permissaoBuilder.criar();
	}

	public EmpresaBuilder() {
		initValores();
	}

	public void criar() {

		categoriaDao = DAOFactoy.criarCategoria();

		fdpDao = DAOFactoy.criarFormaDePagamento();

		bairroDao = DAOFactoy.criarBairro();
		empresaAtendimentoDao = DAOFactoy.criarEmpresaAtendimento();
		criaEmpresa1();
		criaEmpresa2();
		criaEmpresa3();
		criaEmpresa4();
	}

	private void criaEmpresa1() {
		Categoria categoria = categoriaDao
				.getCategoriaComEnum(CategoriaENUM.Lanche);

		FormaDePagamento fdp = fdpDao.pesquisaPeloTipo("Dinheiro");

		Bairro bairro = bairroDao.buscarPorDescricao("Nova Vila Bretas");

		Endereco endereco = new Endereco();
		endereco.setLogradouro("Rua 1");
		endereco.setBairroCidade(bairro);

		Empresa empresa = new Empresa();
		empresa.setRazaoSocial("Empresa 1");
		empresa.addCategoria(categoria);
		empresa.addCategoria(categoriaDao
				.getCategoriaComEnum(CategoriaENUM.Bebida));
		empresa.addFormaDePagamento(fdp);
		empresa.setEndereco(endereco);

		empresaDAO = DAOFactoy.criarEmpresa();
		empresaDAO.salve(empresa);

		EmpresaAtendimento empresaAtendimento = new EmpresaAtendimento();

		empresaAtendimento.setBairro(bairro);
		empresaAtendimento.setEmpresa(empresa);
		empresaAtendimentoDao.salve(empresaAtendimento);
	}

	private void criaEmpresa2() {
		Categoria categoria = categoriaDao
				.getCategoriaComEnum(CategoriaENUM.Lanche);
		FormaDePagamento fdp = fdpDao.pesquisaPeloTipo("Dinheiro");

		Bairro bairro = bairroDao.buscarPorDescricao("Centro");

		Endereco endereco = new Endereco();
		endereco.setLogradouro("Rua 1");
		endereco.setBairroCidade(bairro);

		Empresa empresa = new Empresa();
		empresa.setRazaoSocial("Empresa 2");
		empresa.addCategoria(categoria);
		empresa.addFormaDePagamento(fdp);
		empresa.setEndereco(endereco);

		empresaDAO = DAOFactoy.criarEmpresa();
		empresaDAO.salve(empresa);

		EmpresaAtendimento empresaAtendimento = new EmpresaAtendimento();

		empresaAtendimento.setBairro(bairro);
		empresaAtendimento.setEmpresa(empresa);
		empresaAtendimentoDao.salve(empresaAtendimento);
	}

	private void criaEmpresa3() {
		Categoria categoria = categoriaDao
				.getCategoriaComEnum(CategoriaENUM.Lanche);
		FormaDePagamento fdp = fdpDao.pesquisaPeloTipo("Dinheiro");

		Bairro bairro = bairroDao.buscarPorDescricao("Nova Vila Bretas");

		Endereco endereco = new Endereco();
		endereco.setLogradouro("Rua 1");
		endereco.setBairroCidade(bairro);

		Empresa empresa = new Empresa();
		empresa.setRazaoSocial("Empresa 3");
		empresa.addCategoria(categoria);
		empresa.addFormaDePagamento(fdp);
		empresa.setEndereco(endereco);

		empresaDAO = DAOFactoy.criarEmpresa();
		empresaDAO.salve(empresa);

		EmpresaAtendimento empresaAtendimento = new EmpresaAtendimento();

		empresaAtendimento.setBairro(bairro);
		empresaAtendimento.setEmpresa(empresa);
		empresaAtendimentoDao.salve(empresaAtendimento);
	}

	private void criaEmpresa4() {
		Categoria categoria = categoriaDao
				.getCategoriaComEnum(CategoriaENUM.Lanche);
		FormaDePagamento fdp = fdpDao.pesquisaPeloTipo("Dinheiro");

		Bairro bairro = bairroDao.buscarPorDescricao("Nova Vila Bretas");

		Endereco endereco = new Endereco();
		endereco.setLogradouro("Rua 1");
		endereco.setBairroCidade(bairro);

		Empresa empresa = new Empresa();
		empresa.setRazaoSocial("Empresa 4");
		empresa.addCategoria(categoria);
		empresa.addFormaDePagamento(fdp);
		empresa.setEndereco(endereco);

		empresaDAO = DAOFactoy.criarEmpresa();
		empresaDAO.salve(empresa);

		EmpresaAtendimento empresaAtendimento = new EmpresaAtendimento();

		empresaAtendimento.setBairro(bairro);
		empresaAtendimento.setEmpresa(empresa);
		empresaAtendimentoDao.salve(empresaAtendimento);
	}
}
