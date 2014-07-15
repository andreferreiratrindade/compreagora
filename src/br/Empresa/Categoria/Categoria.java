package br.Empresa.Categoria;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.EntityMode;

import com.sun.xml.bind.CycleRecoverable;

import br.Empresa.Empresa;

@Entity(name = "categoria")
public class Categoria implements Serializable, CycleRecoverable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idCategoria;

	@Enumerated(EnumType.STRING)
	private CategoriaENUM tipoCategoria;

	@OneToMany(fetch = FetchType.EAGER)
	@JoinColumn(name = "idCategoria")
	private List<CategoriaEmpresa> categoriasEmpresa = new ArrayList<CategoriaEmpresa>();

	public List<CategoriaEmpresa> getCategoriasEmpresa() {
		return categoriasEmpresa;
	}

	public void setCategoriasEmpresa(List<CategoriaEmpresa> categoriasEmpresa) {
		this.categoriasEmpresa = categoriasEmpresa;
	}

	public CategoriaENUM getTipoCategoria() {
		return tipoCategoria;
	}

	public void setTipoCategoria(CategoriaENUM tipoCategoria) {
		this.tipoCategoria = tipoCategoria;
	}

	public int getIdCategoria() {
		return idCategoria;
	}

	public void setIdCategoria(int idCategoria) {
		this.idCategoria = idCategoria;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime
				* result
				+ ((categoriasEmpresa == null) ? 0 : categoriasEmpresa
						.hashCode());
		result = prime * result + idCategoria;
		result = prime * result
				+ ((tipoCategoria == null) ? 0 : tipoCategoria.hashCode());
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
		Categoria other = (Categoria) obj;
		if (categoriasEmpresa == null) {
			if (other.categoriasEmpresa != null)
				return false;
		} else if (!categoriasEmpresa.equals(other.categoriasEmpresa))
			return false;
		if (idCategoria != other.idCategoria)
			return false;
		if (tipoCategoria != other.tipoCategoria)
			return false;
		return true;
	}

	@Override
	public Object onCycleDetected(Context arg0) {

		Categoria c = new Categoria();
		c.setIdCategoria(this.idCategoria);
		return c;
	}
}
