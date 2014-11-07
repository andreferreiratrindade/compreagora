package br.Empresa.Categoria;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import br.Empresa.Empresa;

import com.sun.xml.bind.CycleRecoverable;

@Entity(name = "categoriaempresa")
public class CategoriaEmpresa implements Serializable, CycleRecoverable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idCategoriaEmpresa;
	@ManyToOne
	@JoinColumn(name = "idCategoria")
	private Categoria categoria;
	@ManyToOne
	@JoinColumn(name = "idEmpresa")
	private Empresa empresa;

	public int getIdCategoriaEmpresa() {
		return idCategoriaEmpresa;
	}

	public void setIdCategoriaEmpresa(int idCategoriaEmpresa) {
		this.idCategoriaEmpresa = idCategoriaEmpresa;
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
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
		result = prime * result
				+ ((categoria == null) ? 0 : categoria.hashCode());
		result = prime * result + ((empresa == null) ? 0 : empresa.hashCode());
		result = prime * result + idCategoriaEmpresa;
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
		CategoriaEmpresa other = (CategoriaEmpresa) obj;
		if (categoria == null) {
			if (other.categoria != null)
				return false;
		} else if (!categoria.equals(other.categoria))
			return false;
		if (empresa == null) {
			if (other.empresa != null)
				return false;
		} else if (!empresa.equals(other.empresa))
			return false;
		if (idCategoriaEmpresa != other.idCategoriaEmpresa)
			return false;
		return true;
	}

	@Override
	public Object onCycleDetected(Context arg0) {

		CategoriaEmpresa c = new CategoriaEmpresa();
		c.setIdCategoriaEmpresa(this.idCategoriaEmpresa);
		return c;
	}

}
