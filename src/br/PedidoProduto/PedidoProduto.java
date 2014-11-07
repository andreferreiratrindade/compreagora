package br.PedidoProduto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import br.Empresa.Categoria.CategoriaENUM;
import br.Pedido.Pedido;
import br.Produto.Produto;
import br.ProdutoAvulso.Avulso;
import br.ProdutoAvulso.PedidoProdutoAvulso;

@Entity(name = "pedidoproduto")
public class PedidoProduto implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	private int idPedidoProduto;
	private String descricao;
	private float valor;
	private int idProduto;
	@ManyToOne
	@JoinColumn(name = "idPedido")
	private Pedido Pedido;
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "idPedidoProduto")
	private List<PedidoProdutoAvulso> avulsos = new ArrayList<PedidoProdutoAvulso>();
	@Enumerated(EnumType.STRING)
	private CategoriaENUM qualificacao;
	private int tempoEspera;

	public int getTempoEspera() {
		return tempoEspera;
	}

	public void setTempoEspera(int tempoEspera) {
		this.tempoEspera = tempoEspera;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public void setValor(float valor) {
		this.valor = valor;
	}

	public int getIdProduto() {
		return idProduto;
	}

	public void setIdProduto(int idProduto) {
		this.idProduto = idProduto;
	}

	public List<PedidoProdutoAvulso> getAvulsos() {
		return avulsos;
	}

	public List<Avulso> convertToAvulso() {
		List<Avulso> avulsosTemp = new ArrayList<Avulso>();
		for (PedidoProdutoAvulso x : avulsos) {
			Avulso avulso = new Avulso();
			avulso.setDescricao(x.getDescricao());
			avulso.setTipoAvulso(x.getQualificacao());
			avulso.setValor(x.getValor());
			avulso.setIdAvulso(x.getIdAvulso());
			avulsosTemp.add(avulso);
		}
		return avulsosTemp;
	}

	public void setAvulsos(List<PedidoProdutoAvulso> avulsos) {
		this.avulsos = avulsos;
	}

	public CategoriaENUM getQualificacao() {
		return qualificacao;
	}

	public void setQualificacao(CategoriaENUM qualificacao) {
		this.qualificacao = qualificacao;
	}

	private String observacao;

	public void addProduto(Produto produto) {
		this.descricao = produto.getDescricao();
		this.valor = produto.getValor();
		this.idProduto = produto.getIdProduto();
		this.qualificacao = produto.getQualificacao();
		this.tempoEspera = produto.getTempoEspera();
	}

	public float getValor() {

		BigDecimal valorTotal = new BigDecimal(valor);

		if (avulsos != null)
			for (PedidoProdutoAvulso x : avulsos) {
				valorTotal = valorTotal.add(new BigDecimal(x.getValor()));
			}

		return valorTotal.floatValue();
	}

	public void addAvulso(Avulso avulso) {

		PedidoProdutoAvulso ppa = new PedidoProdutoAvulso();
		ppa.setDescricao(avulso.getDescricao());
		ppa.setIdAvulso(avulso.getIdAvulso());
		ppa.setValor(avulso.getValor());
		avulsos.add(ppa);
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
		result = prime * result + ((avulsos == null) ? 0 : avulsos.hashCode());
		result = prime * result
				+ ((descricao == null) ? 0 : descricao.hashCode());
		result = prime * result + idPedidoProduto;
		result = prime * result + idProduto;
		result = prime * result
				+ ((observacao == null) ? 0 : observacao.hashCode());
		result = prime * result
				+ ((qualificacao == null) ? 0 : qualificacao.hashCode());
		result = prime * result + Float.floatToIntBits(valor);
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
		if (avulsos == null) {
			if (other.avulsos != null)
				return false;
		} else if (!avulsos.equals(other.avulsos))
			return false;
		if (descricao == null) {
			if (other.descricao != null)
				return false;
		} else if (!descricao.equals(other.descricao))
			return false;
		if (idPedidoProduto != other.idPedidoProduto)
			return false;
		if (idProduto != other.idProduto)
			return false;
		if (observacao == null) {
			if (other.observacao != null)
				return false;
		} else if (!observacao.equals(other.observacao))
			return false;
		if (qualificacao != other.qualificacao)
			return false;
		if (Float.floatToIntBits(valor) != Float.floatToIntBits(other.valor))
			return false;
		return true;
	}

	public void removeAvulso(Avulso avulso) {

		for (int i = 0; i < avulsos.size(); i++) {
			if (avulso.getIdAvulso() == avulsos.get(i).getIdAvulso()) {
				avulsos.remove(i);
				break;
			}
		}
	}

	public void convertToPedidoProdutoAvulso(List<Avulso> target) {

		avulsos = new ArrayList<PedidoProdutoAvulso>();
		for (Avulso x : target) {
			PedidoProdutoAvulso avulso = new PedidoProdutoAvulso();
			avulso.setDescricao(x.getDescricao());
			avulso.setQualificacao(x.getTipoAvulso());
			avulso.setValor(x.getValor());
			avulso.setIdAvulso(x.getIdAvulso());
			avulsos.add(avulso);
		}

	}
}
