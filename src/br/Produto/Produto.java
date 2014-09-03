package br.Produto;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import br.Empresa.Empresa;
import br.Empresa.Categoria.CategoriaENUM;

import com.sun.xml.bind.CycleRecoverable;

@Entity(name = "produto")
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Produto implements Serializable, CycleRecoverable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idProduto;
	@ManyToOne
	@JoinColumn(name = "idEmpresa")
	private Empresa empresa;
	private String descricao;
	private BigDecimal valor;
	protected boolean ativo;
	private int tempoEspera;
	@Enumerated(EnumType.STRING)
	private CategoriaENUM qualificacao;

	public Produto() {
		valor = new BigDecimal(0);
	}

	public int getTempoEspera() {
		return tempoEspera;
	}

	public void setTempoEspera(int tempoEspera) {
		this.tempoEspera = tempoEspera;
	}

	public CategoriaENUM getQualificacao() {
		return qualificacao;
	}

	public void setQualificacao(CategoriaENUM qualificacao) {
		this.qualificacao = qualificacao;
	}

	public boolean isAtivo() {
		return ativo;
	}

	public void setAtivo(boolean ativo) {
		this.ativo = ativo;
	}

	public int getIdProduto() {
		return idProduto;
	}

	public void setIdProduto(int idProduto) {
		this.idProduto = idProduto;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

		
	public BigDecimal getValor() {
		return valor;
	}

	public void setValor(float valor) {

		this.valor = new BigDecimal(Float.toString(valor));
	}

	public Empresa getEmpresa() {
		return empresa;
	}

	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (ativo ? 1231 : 1237);
		result = prime * result
				+ ((descricao == null) ? 0 : descricao.hashCode());
		result = prime * result + ((empresa == null) ? 0 : empresa.hashCode());
		result = prime * result + idProduto;
		result = prime * result
				+ ((qualificacao == null) ? 0 : qualificacao.hashCode());
		result = prime * result + tempoEspera;
		result = prime * result + ((valor == null) ? 0 : valor.hashCode());
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
		Produto other = (Produto) obj;
		if (ativo != other.ativo)
			return false;
		if (descricao == null) {
			if (other.descricao != null)
				return false;
		} else if (!descricao.equals(other.descricao))
			return false;
		if (empresa == null) {
			if (other.empresa != null)
				return false;
		} else if (!empresa.equals(other.empresa))
			return false;
		if (idProduto != other.idProduto)
			return false;
		if (qualificacao != other.qualificacao)
			return false;
		if (tempoEspera != other.tempoEspera)
			return false;
		if (valor == null) {
			if (other.valor != null)
				return false;
		} else if (!valor.equals(other.valor))
			return false;
		return true;
	}

	public void alteraStatus() {
		ativo = !ativo;
	}

}
