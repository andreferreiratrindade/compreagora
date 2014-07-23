package br.Pedido;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import br.AtendimentoLugares.EmpresaAtendimento;
import br.AtendimentoLugares.EmpresaAtendimentoRN;
import br.Empresa.Empresa;
import br.Empresa.Categoria.CategoriaENUM;
import br.EnderecoCliente.EnderecoCliente;
import br.PedidoProduto.PedidoProduto;
import br.PedidoProduto.PedidoProdutoDAO;
import br.Produto.Implementacao.AguaImplementacao;
import br.Produto.Implementacao.BebidaImplementacao;
import br.Produto.Implementacao.GasImplementacao;
import br.Produto.Implementacao.IProduto;
import br.Produto.Implementacao.LancheImplementacao;
import br.Produto.Implementacao.PizzaImplementacao;
import br.util.DAOFactoy;

public class PedidoRN {
	private PedidoDAO pedidoDAO;

	public PedidoRN() {
		this.pedidoDAO = DAOFactoy.criarPedido();
	}

	public Pedido getPedido(int id) {
		return this.pedidoDAO.getUnico(id);
	}

	public void atualizaProdutos(Pedido pedido) {

		List<PedidoProduto> pedidoProdutos = pedido.getPedidoProdutos();

		Map<CategoriaENUM, IProduto> mapProduto = new HashMap<CategoriaENUM, IProduto>();
		mapProduto.put(CategoriaENUM.Lanche, new LancheImplementacao());
		mapProduto.put(CategoriaENUM.Bebida, new BebidaImplementacao());
		mapProduto.put(CategoriaENUM.Agua, new AguaImplementacao());
		mapProduto.put(CategoriaENUM.Pizza, new PizzaImplementacao());
		mapProduto.put(CategoriaENUM.Gas, new GasImplementacao());

		IProduto produtoI;

		PedidoProdutoDAO pedidoProdutoDAO = DAOFactoy.criarPedidoProduto();
		for (PedidoProduto x : pedidoProdutos) {

			x.setPedido(pedido);
			produtoI = (IProduto) mapProduto.get(x.getProdutoAvulso()
					.getProduto().getQualificacao());
			produtoI.atualizar(x.getProdutoAvulso().getProduto());
			pedidoProdutoDAO.update(x);
		}

	}

	public void salvar(EnderecoCliente enderecoCliente, Empresa empresa,
			Pedido pedido) {
		int idEmpresa = empresa.getIdEmpresa();
		int idBairro = enderecoCliente.getEndereco().getBairroCidade()
				.getIdBairro();

		int tempo = calculaTempo(idEmpresa, idBairro);

		pedido.setEnderecoCliente(enderecoCliente);
		pedido.calcularTempoEspera(tempo);
		pedido.setEmpresa(empresa);
		pedido.setDataHoraIn(new Date());
		pedido.setStatusPedido(1);
		pedido.inserirPedidoNoPedidoProdutos();

		this.pedidoDAO.salve(pedido);
		atualizaProdutos(pedido);
	}

	public int calculaTempoDeTodosPedidos(int idEmpresa) {
		List<Pedido> pedidos = this.pedidoDAO.pedidosPeloStatus(idEmpresa);
		int tempo = 0;
		for (Pedido x : pedidos) {
			tempo += x.getTempoEspera();
		}
		return tempo;
	}

	public int calculaTempoDePercurso(int idEmpresa, int idBairro) {

		int tempo = 0;

		EmpresaAtendimentoRN empresaAtendimentoRN = new EmpresaAtendimentoRN();

		EmpresaAtendimento empAten = empresaAtendimentoRN
				.empresaAtendimentoEmpresaComBairro(idEmpresa, idBairro);

		if (empAten != null) {
			tempo += empAten.getTempoEspera();
		}

		return tempo;
	}

	public int calculaTempo(int idEmpresa, int idBairro) {
		int tempo = 0;

		tempo += calculaTempoDePercurso(idEmpresa, idBairro);

		tempo += calculaTempoDeTodosPedidos(idEmpresa);

		return tempo;
	}

	public void atualizar(Pedido pedido) {
		this.pedidoDAO.update(pedido);
	}

	public List<Pedido> buscaPorPaginacao(int startingAt, int maxPerPage, int idCliente){
		return this.pedidoDAO.buscaPorPaginacao(startingAt, maxPerPage, idCliente);
	}
	
	public List<Pedido> relatorioDeFaturamento(int idEmpresa, Date dataInicio,

			Date dataFim) {

		List<Pedido> pedidos = this.pedidoDAO.relatorioDeFaturamento(idEmpresa,
				dataInicio, dataFim);

		if (pedidos.size() == 0) {
			pedidos.add(new Pedido());
			return pedidos;
		}

		return pedidos;
	}
	public int countPedido(int idCliente){
		return this.pedidoDAO.countPedido(idCliente);
	}
	
	
}
