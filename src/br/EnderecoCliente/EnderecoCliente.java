package br.EnderecoCliente;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.codehaus.jackson.annotate.JsonIgnore;

import br.Cliente.Cliente;
import br.Endereco.Endereco;
import br.Pedido.Pedido;

import com.sun.xml.bind.CycleRecoverable;

@Entity(name = "enderecocliente")
public class EnderecoCliente implements Serializable, CycleRecoverable {
	/**
	 * 
	 */

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idEnderecoCliente;
	@ManyToOne
	@JoinColumn(name = "idCliente")
	private Cliente cliente;
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "idEndereco")
	private Endereco endereco;
	private String descEndereco;
	@OneToMany
	@JoinColumn(name = "idEnderecoCliente")
	@JsonIgnore
	private List<Pedido> pedidos = new ArrayList<Pedido>();

	public List<Pedido> getPedidos() {
		return pedidos;
	}

	public void setPedidos(List<Pedido> pedidos) {
		this.pedidos = pedidos;
	}

	public int getIdEnderecoCliente() {
		return idEnderecoCliente;
	}

	public void setIdEnderecoCliente(int idEnderecoCliente) {
		this.idEnderecoCliente = idEnderecoCliente;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;

	}

	public Endereco getEndereco() {
		if (endereco == null) {
			endereco = new Endereco();
		}
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}

	public String getDescEndereco() {
		return descEndereco;
	}

	public void setDescEndereco(String descEndereco) {
		this.descEndereco = descEndereco;
	}

	public String toString() {
		return getDescEndereco();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cliente == null) ? 0 : cliente.hashCode());
		result = prime * result
				+ ((descEndereco == null) ? 0 : descEndereco.hashCode());
		result = prime * result
				+ ((endereco == null) ? 0 : endereco.hashCode());
		result = prime * result + idEnderecoCliente;
		result = prime * result + ((pedidos == null) ? 0 : pedidos.hashCode());
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
		EnderecoCliente other = (EnderecoCliente) obj;
		if (cliente == null) {
			if (other.cliente != null)
				return false;
		} else if (!cliente.equals(other.cliente))
			return false;
		if (descEndereco == null) {
			if (other.descEndereco != null)
				return false;
		} else if (!descEndereco.equals(other.descEndereco))
			return false;
		if (endereco == null) {
			if (other.endereco != null)
				return false;
		} else if (!endereco.equals(other.endereco))
			return false;
		if (idEnderecoCliente != other.idEnderecoCliente)
			return false;
		if (pedidos == null) {
			if (other.pedidos != null)
				return false;
		} else if (!pedidos.equals(other.pedidos))
			return false;
		return true;
	}

	@Override
	public Object onCycleDetected(Context arg0) {

		EnderecoCliente end = new EnderecoCliente();
		end.setIdEnderecoCliente(this.idEnderecoCliente);

		return end;
	}

}
