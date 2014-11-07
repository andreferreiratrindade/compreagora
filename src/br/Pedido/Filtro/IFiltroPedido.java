package br.Pedido.Filtro;

import java.util.List;

import javax.persistence.EntityManager;

import org.primefaces.model.SortOrder;

import br.Pedido.Pedido;

public interface IFiltroPedido {
	public List<Pedido> buscaPorPaginacao(EntityManager em, int startingAt,
			int maxPerPage, int id, String sortFiel, SortOrder sortOrder);

	public int countPedido(EntityManager em, int id);
}
