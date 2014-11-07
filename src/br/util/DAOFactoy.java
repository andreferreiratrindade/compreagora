package br.util;

import br.AtendimentoLugares.BairroDAO;
import br.AtendimentoLugares.CidadeDAO;
import br.AtendimentoLugares.EmpresaAtendimentoDAO;
import br.Cliente.ClienteDAO;
import br.Empresa.DiaDaSemanaDAO;
import br.Empresa.EmpresaDAO;
import br.Empresa.HorarioFuncionamentoDAO;
import br.Empresa.Categoria.CategoriaDao;
import br.Empresa.Categoria.CategoriaEmpresaDao;
import br.Empresa.FormaDePagamento.FormaDePagamentoDao;
import br.Endereco.EnderecoDAO;
import br.Pedido.PedidoDAO;
import br.PedidoProduto.PedidoProdutoDAO;
import br.Permissao.PermissaoDAO;
import br.Produto.ProdutoDAO;
import br.ProdutoAvulso.AvulsoDAO;

public class DAOFactoy {
	/*
	 * Para testar um codigo eu alterei o metodo
	 * HibernateUtil.getSessionFactory() openSession()); Para .openSession();
	 */
	private DAOFactoy() {

	}

	public static ClienteDAO criarCliente() {
		ClienteDAO dao = new ClienteDAO();
		dao.setSession(JpaUtil.getEntityManager());
		return dao;
	}

	public static EnderecoDAO criarEndereco() {
		EnderecoDAO dao = new EnderecoDAO();
		dao.setSession(JpaUtil.getEntityManager());
		return dao;
	}

	public static EmpresaDAO criarEmpresa() {
		EmpresaDAO dao = new EmpresaDAO();
		dao.setSession(JpaUtil.getEntityManager());
		return dao;
	}

	public static ProdutoDAO criarProduto() {
		ProdutoDAO dao = new ProdutoDAO();
		dao.setEm(JpaUtil.getEntityManager());

		return dao;
	}

	public static PedidoDAO criarPedido() {
		PedidoDAO dao = new PedidoDAO();
		dao.setSession(JpaUtil.getEntityManager());
		return dao;
	}

	public static PermissaoDAO criarPermissao() {
		PermissaoDAO dao = new PermissaoDAO();
		dao.setSession(JpaUtil.getEntityManager());
		return dao;
	}

	public static EmpresaAtendimentoDAO criarEmpresaAtendimento() {
		EmpresaAtendimentoDAO dao = new EmpresaAtendimentoDAO();
		dao.setSession(JpaUtil.getEntityManager());
		return dao;
	}

	public static CidadeDAO criarCidade() {
		CidadeDAO dao = new CidadeDAO();
		dao.setSession(JpaUtil.getEntityManager());
		return dao;
	}

	public static BairroDAO criarBairro() {
		BairroDAO dao = new BairroDAO();
		dao.setSession(JpaUtil.getEntityManager());
		return dao;
	}

	public static HorarioFuncionamentoDAO criarHorarioFuncionamento() {
		HorarioFuncionamentoDAO dao = new HorarioFuncionamentoDAO();
		dao.setSession(JpaUtil.getEntityManager());
		return dao;
	}

	public static DiaDaSemanaDAO criarDiaDaSemana() {
		DiaDaSemanaDAO dao = new DiaDaSemanaDAO();
		dao.setSession(JpaUtil.getEntityManager());
		return dao;
	}

	public static PedidoProdutoDAO criarPedidoProduto() {
		PedidoProdutoDAO dao = new PedidoProdutoDAO();
		dao.setSession(JpaUtil.getEntityManager());
		return dao;
	}

	public static AvulsoDAO criarAvulso() {
		AvulsoDAO dao = new AvulsoDAO();
		dao.setSession(JpaUtil.getEntityManager());
		return dao;
	}

	public static CategoriaDao criarCategoria() {
		CategoriaDao dao = new CategoriaDao();
		dao.setSession(JpaUtil.getEntityManager());
		return dao;
	}

	public static CategoriaEmpresaDao criarCategoriaEmpresa() {
		CategoriaEmpresaDao dao = new CategoriaEmpresaDao();
		dao.setSession(JpaUtil.getEntityManager());
		return dao;
	}

	public static FormaDePagamentoDao criarFormaDePagamento() {

		FormaDePagamentoDao dao = new FormaDePagamentoDao();
		dao.setSession(JpaUtil.getEntityManager());
		return dao;

	}

}
