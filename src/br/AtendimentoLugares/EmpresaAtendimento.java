package br.AtendimentoLugares;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.sun.xml.bind.CycleRecoverable;

import br.Empresa.Empresa;
import br.AtendimentoLugares.Bairro;

@Entity(name = "empresaatendimento")
public class EmpresaAtendimento implements Serializable, CycleRecoverable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	private int idEmpresaAtendimento;

	@ManyToOne
	@JoinColumn(name = "idEmpresa")
	private Empresa empresa;

	@ManyToOne
	@JoinColumn(name = "idBairro")
	private Bairro bairro;

	private boolean ativo;
	private float taxa;
	private int tempoEspera;

	public int getTempoEspera() {
		return tempoEspera;
	}

	public void setTempoEspera(int tempoEspera) {
		this.tempoEspera = tempoEspera;
	}

	public EmpresaAtendimento() {
		ativo = true;
	}

	public int getIdEmpresaAtendimento() {
		return idEmpresaAtendimento;
	}

	public void setIdEmpresaAtendimento(int idEmpresaAtendimento) {
		this.idEmpresaAtendimento = idEmpresaAtendimento;
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

	public Bairro getBairro() {
		return bairro;
	}

	public void setBairro(Bairro Bairro) {
		this.bairro = Bairro;
	}

	public float getTaxa() {
		return taxa;
	}

	public void setTaxa(float taxa) {
		this.taxa = taxa;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (ativo ? 1231 : 1237);
		result = prime * result + ((bairro == null) ? 0 : bairro.hashCode());
		result = prime * result + ((empresa == null) ? 0 : empresa.hashCode());
		result = prime * result + idEmpresaAtendimento;
		result = prime * result + Float.floatToIntBits(taxa);
		result = prime * result + tempoEspera;
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
		EmpresaAtendimento other = (EmpresaAtendimento) obj;
		if (ativo != other.ativo)
			return false;
		if (bairro == null) {
			if (other.bairro != null)
				return false;
		} else if (!bairro.equals(other.bairro))
			return false;
		if (empresa == null) {
			if (other.empresa != null)
				return false;
		} else if (!empresa.equals(other.empresa))
			return false;
		if (idEmpresaAtendimento != other.idEmpresaAtendimento)
			return false;
		if (Float.floatToIntBits(taxa) != Float.floatToIntBits(other.taxa))
			return false;
		if (tempoEspera != other.tempoEspera)
			return false;
		return true;
	}

	@Override
	public Object onCycleDetected(Context arg0) {

		EmpresaAtendimento emp = new EmpresaAtendimento();
		emp.setIdEmpresaAtendimento(this.idEmpresaAtendimento);

		return emp;
	}

}
