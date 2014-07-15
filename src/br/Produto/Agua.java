package br.Produto;

import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;

import br.Empresa.Categoria.CategoriaENUM;

@Entity(name = "agua")
@PrimaryKeyJoinColumn(name = "idProduto")
public class Agua extends Produto {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int estoque;

	public int getEstoque() {
		return estoque;
	}

	public void setEstoque(int estoque) {
		this.estoque = estoque;
	}

	public Agua() {
		setQualificacao(CategoriaENUM.Agua);
		setAtivo(true);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + estoque;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Agua other = (Agua) obj;
		if (estoque != other.estoque)
			return false;
		return true;
	}

	public void implementaEstoque() {

		estoque = estoque - 1;
	}

	@Override
	public Object onCycleDetected(Context arg0) {

		Agua a = new Agua();
		a.setIdProduto(getIdProduto());

		return a;
	}

}
