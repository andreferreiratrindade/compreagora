package br.Produto;

import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;

import br.Empresa.Categoria.CategoriaENUM;

@Entity(name = "pizza")
@PrimaryKeyJoinColumn(name = "idProduto")
public class Pizza extends Produto {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String ingredientes;

	public Pizza() {
		setQualificacao(CategoriaENUM.Pizza);
		setAtivo(true);
	}

	public String getIngredientes() {
		return ingredientes;
	}

	public void setIngredientes(String ingredientes) {
		this.ingredientes = ingredientes;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result
				+ ((ingredientes == null) ? 0 : ingredientes.hashCode());
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
		Pizza other = (Pizza) obj;
		if (ingredientes == null) {
			if (other.ingredientes != null)
				return false;
		} else if (!ingredientes.equals(other.ingredientes))
			return false;
		return true;
	}

	@Override
	public Object onCycleDetected(Context arg0) {
		Pizza pizza = new Pizza();
		pizza.setIdProduto(getIdProduto());
		return pizza;
	}

}
