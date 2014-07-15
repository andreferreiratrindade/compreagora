package br.ProdutoAvulso;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.sun.xml.bind.CycleRecoverable;

import br.Empresa.Empresa;
import br.Empresa.Categoria.CategoriaENUM;

@Entity(name = "avulso")
@Inheritance(strategy = InheritanceType.JOINED)
public class Avulso implements Serializable, CycleRecoverable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue
	private int idAvulso;
	private String descricao;
	private float valor;
	private boolean ativo;
	private int tempoEspera;
	@Enumerated(EnumType.STRING)
	private CategoriaENUM tipoAvulso;
	@ManyToOne
	@JoinColumn(name = "idEmpresa")
	private Empresa empresa;

	public CategoriaENUM getTipoAvulso() {
		return tipoAvulso;
	}

	public void setTipoAvulso(CategoriaENUM tipoAvulso) {
		this.tipoAvulso = tipoAvulso;
	}

	public int getTempoEspera() {
		return tempoEspera;
	}

	public void setTempoEspera(int tempoEspera) {
		this.tempoEspera = tempoEspera;
	}

	public int getIdAvulso() {
		return idAvulso;
	}

	public void setIdAvulso(int idAvulso) {
		this.idAvulso = idAvulso;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public void setValor(float valor) {
		this.valor = valor;
	}

	public String getDescricao() {
		return descricao;
	}

	public float getValor() {
		return valor;
	}

	public Empresa getEmpresa() {
		return empresa;
	}

	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}

	public boolean isAtivo() {
		return ativo;
	}

	public void setAtivo(boolean ativo) {
		this.ativo = ativo;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (ativo ? 1231 : 1237);
		result = prime * result
				+ ((descricao == null) ? 0 : descricao.hashCode());
		result = prime * result + ((empresa == null) ? 0 : empresa.hashCode());
		result = prime * result + idAvulso;
		result = prime * result + tempoEspera;
		result = prime * result
				+ ((tipoAvulso == null) ? 0 : tipoAvulso.hashCode());
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
		Avulso other = (Avulso) obj;
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
		if (idAvulso != other.idAvulso)
			return false;
		if (tempoEspera != other.tempoEspera)
			return false;
		if (tipoAvulso != other.tipoAvulso)
			return false;
		if (Float.floatToIntBits(valor) != Float.floatToIntBits(other.valor))
			return false;
		return true;
	}

	@Override
	public Object onCycleDetected(Context arg0) {
		Avulso avulso = new Avulso();
		avulso.setIdAvulso(getIdAvulso());

		return avulso;
	}

}
