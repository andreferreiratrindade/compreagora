package br.Produto;

import java.util.List;

public interface IProdutoRN {
	public List<Lanche> listarLanche(int idEmpresa);

	public List<Bebida> listarBebida(int idEmpresa);

	public List<Marmitex> listarMarmitex(int idEmpresa);

	public List<Pizza> listarPizza(int idEmpresa);

	public List<Agua> listarAgua(int idEmpresa);

	public List<Gas> listarGas(int idEmpresa);
}
