package br.PedidoProduto;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.codehaus.jackson.annotate.JsonIgnore;

import br.Pedido.Pedido;
import br.Produto.Produto;
import br.Produto.ProdutoAvulso.ProdutoAvulso;
import br.ProdutoAvulso.Avulso;

import com.sun.xml.bind.CycleRecoverable;

@Entity(name = "pedidoproduto")
public class PedidoProduto implements Serializable, CycleRecoverable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	private int idPedidoProduto;
	@ManyToOne
	@JoinColumn(name = "idPedido")
	@JsonIgnore
	private Pedido Pedido;
	@ManyToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name = "idProdutoAvulso")
	private ProdutoAvulso produtoAvulso;

	private String observacao;

	public void addProduto(Produto produto) {
		if (produtoAvulso == null) {
			produtoAvulso = new ProdutoAvulso();
			produtoAvulso.setProduto(produto);
		} else
			produtoAvulso.setProduto(produto);
	}

	public float valorTotal() {
		return produtoAvulso.valorTotal();
	}

	public int tempoEsperaTotal() {
		return produtoAvulso.tempoEsperaTotal();
	}

	public void addAvulso(Avulso avulso) {
		produtoAvulso.addAvulso(avulso);
	}

	public int getIdPedidoProduto() {
		return idPedidoProduto;
	}

	public void setIdPedidoProduto(int idPedidoProduto) {
		this.idPedidoProduto = idPedidoProduto;
	}

	public Pedido getPedido() {
		return Pedido;
	}

	public void setPedido(Pedido pedido) {
		Pedido = pedido;
	}

	public ProdutoAvulso getProdutoAvulso() {
		return produtoAvulso;
	}

	public void setProdutoAvulso(ProdutoAvulso produtoAvulso) {
		this.produtoAvulso = produtoAvulso;
	}

	public String getObservacao() {
		return observacao;
	}

	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((Pedido == null) ? 0 : Pedido.hashCode());
		result = prime * result + idPedidoProduto;
		result = prime * result
				+ ((observacao == null) ? 0 : observacao.hashCode());
		result = prime * result
				+ ((produtoAvulso == null) ? 0 : produtoAvulso.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PedidoProduto other = (PedidoProduto) obj;
		if (Pedido == null) {
			if (other.Pedido != null)
				return false;
		} else if (!Pedido.equals(other.Pedido))
			return false;
		if (idPedidoProduto != other.idPedidoProduto)
			return false;
		if (observacao == null) {
			if (other.observacao != null)
				return false;
		} else if (!observacao.equals(other.observacao))
			return false;
		if (produtoAvulso == null) {
			if (other.produtoAvulso != null)
				return false;
		} else if (!produtoAvulso.equals(other.produtoAvulso))
			return false;
		return true;
	}

	@Override
	public Object onCycleDetected(Context arg0) {
		PedidoProduto p = new PedidoProduto();
		p.setIdPedidoProduto(this.idPedidoProduto);

		return p;
	}
}
