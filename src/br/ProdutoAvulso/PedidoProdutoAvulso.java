package br.ProdutoAvulso;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import br.Empresa.Categoria.CategoriaENUM;

@Entity(name = "pedidoprodutoavulso")
public class PedidoProdutoAvulso implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	private int idPedidoProdutoAvulso;
	private String descricao;
	private float valor;
	private int idAvulso;
	@Enumerated(EnumType.STRING)
	private CategoriaENUM qualificacao;

	public CategoriaENUM getQualificacao() {
		return qualificacao;
	}

	public void setQualificacao(CategoriaENUM qualificacao) {
		this.qualificacao = qualificacao;
	}

	public int getIdAvulso() {
		return idAvulso;
	}

	public void setIdAvulso(int idAvulso) {
		this.idAvulso = idAvulso;
	}

	public int getIdPedidoProdutoAvulso() {
		return idPedidoProdutoAvulso;
	}

	public void setIdPedidoProdutoAvulso(int idPedidoProdutoAvulso) {
		this.idPedidoProdutoAvulso = idPedidoProdutoAvulso;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public float getValor() {
		return valor;
	}

	public void setValor(float valor) {
		this.valor = valor;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((descricao == null) ? 0 : descricao.hashCode());
		result = prime * result + idAvulso;
		result = prime * result + idPedidoProdutoAvulso;
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
		PedidoProdutoAvulso other = (PedidoProdutoAvulso) obj;
		if (descricao == null) {
			if (other.descricao != null)
				return false;
		} else if (!descricao.equals(other.descricao))
			return false;
		if (idAvulso != other.idAvulso)
			return false;
		if (idPedidoProdutoAvulso != other.idPedidoProdutoAvulso)
			return false;
		if (Float.floatToIntBits(valor) != Float.floatToIntBits(other.valor))
			return false;
		return true;
	}
}
