package br.AtendimentoLugares;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

@Entity(name = "cidade")
public class Cidade implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idCidade;
	private String descCidade;
	@OneToMany(mappedBy="cidade")
	
	private List<Bairro> bairros = new ArrayList<Bairro>();

	public int getIdCidade() {
		return idCidade;
	}

	public void setIdCidade(int idCidade) {
		this.idCidade = idCidade;
	}

	public String getDescCidade() {
		return descCidade;
	}

	public void setDescCidade(String descCidade) {
		this.descCidade = descCidade;
	}

	public List<Bairro> getBairros() {
		return bairros;
	}

	public void setBairros(List<Bairro> bairros) {
		this.bairros = bairros;
	}

	public String toString() {
		return descCidade;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((bairros == null) ? 0 : bairros.hashCode());
		result = prime * result
				+ ((descCidade == null) ? 0 : descCidade.hashCode());
		result = prime * result + idCidade;
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
		Cidade other = (Cidade) obj;
		if (bairros == null) {
			if (other.bairros != null)
				return false;
		} else if (!bairros.equals(other.bairros))
			return false;
		if (descCidade == null) {
			if (other.descCidade != null)
				return false;
		} else if (!descCidade.equals(other.descCidade))
			return false;
		if (idCidade != other.idCidade)
			return false;
		return true;
	}

}
