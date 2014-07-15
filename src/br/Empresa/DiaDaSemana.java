package br.Empresa;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import com.sun.xml.bind.CycleRecoverable;

@Entity(name = "diadasemana")
public class DiaDaSemana implements Serializable, CycleRecoverable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue
	private int idDiaDaSemana;
	private String descricao;

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((descricao == null) ? 0 : descricao.hashCode());
		result = prime * result + idDiaDaSemana;
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
		DiaDaSemana other = (DiaDaSemana) obj;
		if (descricao == null) {
			if (other.descricao != null)
				return false;
		} else if (!descricao.equals(other.descricao))
			return false;
		if (idDiaDaSemana != other.idDiaDaSemana)
			return false;
		return true;
	}

	public int getIdDiaDaSemana() {
		return idDiaDaSemana;
	}

	public void setIdDiaDaSemana(int idDiaDaSemana) {
		this.idDiaDaSemana = idDiaDaSemana;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	@Override
	public Object onCycleDetected(Context arg0) {
		DiaDaSemana d = new DiaDaSemana();
		d.setIdDiaDaSemana(this.idDiaDaSemana);
		return d;
	}
}
