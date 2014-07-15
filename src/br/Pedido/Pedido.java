package br.Pedido;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.codehaus.jackson.annotate.JsonIgnore;

import com.sun.xml.bind.CycleRecoverable;

import br.Empresa.Empresa;
import br.Empresa.FormaDePagamento.FormaDePagamento;
import br.EnderecoCliente.EnderecoCliente;
import br.PedidoProduto.PedidoProduto;
import br.statusPedido.Aguardando;
import br.statusPedido.Concluido;
import br.statusPedido.Enviado;
import br.statusPedido.Processando;
import br.statusPedido.StatusInterface;

@Entity(name = "pedido")
public class Pedido implements Serializable, Comparable<Pedido>,
		CycleRecoverable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	private int idPedido;
	@ManyToOne
	@JoinColumn(name = "idEnderecoCliente")
	private EnderecoCliente enderecoCliente;
	@ManyToOne
	@JoinColumn(name = "idEmpresa")
	private Empresa empresa;
	private Date dataHoraIn;
	private Date dataHoraFim;
	private float valorTotal;
	private float troco;
	private int statusPedido;
	private float taxa;
	private int tempoEspera;
	@ManyToOne
	@JoinColumn(name = "idFormaDePagamento")
	private FormaDePagamento formaDePagamento;

	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "idPedido")
	@JsonIgnore
	private List<PedidoProduto> pedidoProdutos = new ArrayList<PedidoProduto>();

	public FormaDePagamento getFormaDePagamento() {
		return formaDePagamento;
	}

	public void setFormaDePagamento(FormaDePagamento formaDePagamento) {
		this.formaDePagamento = formaDePagamento;
	}

	public int getTempoEspera() {
		return tempoEspera;
	}

	public void setTempoEspera(int tempoEspera) {
		this.tempoEspera = tempoEspera;
	}

	public List<PedidoProduto> getPedidoProdutos() {
		return pedidoProdutos;
	}

	public void setPedidoProdutos(List<PedidoProduto> pedidoProdutos) {
		this.pedidoProdutos = pedidoProdutos;
	}

	public void inserirPedidoNoPedidoProdutos() {
		for (int i = 0; i < pedidoProdutos.size(); i++) {
			pedidoProdutos.get(i).setPedido(this);
		}
	}

	public void calcularTotal() {
		valorTotal = 0;

		for (PedidoProduto x : pedidoProdutos) {
			valorTotal += x.valorTotal();

		}

	}

	public Pedido() {
		statusPedido = 5;
		valorTotal = 0;
	}

	public float getTroco() {
		return troco;
	}

	public float getTaxa() {
		return taxa;
	}

	public void setTaxa(float taxa) {
		this.taxa = taxa;
	}

	public void setTroco(float troco) {
		this.troco = troco;
	}

	@JsonIgnore
	public EnderecoCliente getEnderecoCliente() {
		return enderecoCliente;
	}

	public void setEnderecoCliente(EnderecoCliente enderecoCliente) {
		this.enderecoCliente = enderecoCliente;
	}

	public float getValorTotal() {

		return valorTotal;
	}

	public void setValorTotal(float valorTotal) {
		if (valorTotal < 0)
			this.valorTotal = 0;
		else
			this.valorTotal = valorTotal;
	}

	public int getIdPedido() {
		return idPedido;
	}

	public void setIdPedido(int idPedido) {
		this.idPedido = idPedido;
	}

	public Empresa getEmpresa() {
		return empresa;
	}

	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}

	public Date getDataHoraIn() {

		return dataHoraIn;
	}

	public void setDataHoraIn(Date dataHoraIn) {
		this.dataHoraIn = dataHoraIn;
	}

	public Date getDataHoraFim() {
		return dataHoraFim;
	}

	public void setDataHoraFim(Date dataHoraFim) {
		this.dataHoraFim = dataHoraFim;
	}

	public int getStatusPedido() {
		return statusPedido;
	}

	public void setStatusPedido(int statusPedido) {
		this.statusPedido = statusPedido;
	}

	public void adicionarValor(float valor) {
		BigDecimal big1 = new BigDecimal(Float.toString(valor));
		BigDecimal big2 = new BigDecimal(Float.toString(valorTotal));
		valorTotal = big2.add(big1).floatValue();
	}

	public void removeValor(float valor) {
		valorTotal -= valor;
	}

	public String statusAtual() {
		Map<Integer, StatusInterface> atual = new HashMap<Integer, StatusInterface>();
		atual.put(1, new Aguardando());
		atual.put(2, new Processando());
		atual.put(3, new Enviado());
		atual.put(4, new Concluido());
		atual.put(5, new Aguardando()); // Selecionado apenas para inicializar

		StatusInterface stPedido = (StatusInterface) atual.get(statusPedido);
		return stPedido.atual();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((dataHoraFim == null) ? 0 : dataHoraFim.hashCode());
		result = prime * result
				+ ((dataHoraIn == null) ? 0 : dataHoraIn.hashCode());
		result = prime * result + ((empresa == null) ? 0 : empresa.hashCode());
		result = prime * result
				+ ((enderecoCliente == null) ? 0 : enderecoCliente.hashCode());
		result = prime
				* result
				+ ((formaDePagamento == null) ? 0 : formaDePagamento.hashCode());
		result = prime * result + idPedido;
		result = prime * result
				+ ((pedidoProdutos == null) ? 0 : pedidoProdutos.hashCode());
		result = prime * result + statusPedido;
		result = prime * result + Float.floatToIntBits(taxa);
		result = prime * result + tempoEspera;
		result = prime * result + Float.floatToIntBits(troco);
		result = prime * result + Float.floatToIntBits(valorTotal);
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
		Pedido other = (Pedido) obj;
		if (dataHoraFim == null) {
			if (other.dataHoraFim != null)
				return false;
		} else if (!dataHoraFim.equals(other.dataHoraFim))
			return false;
		if (dataHoraIn == null) {
			if (other.dataHoraIn != null)
				return false;
		} else if (!dataHoraIn.equals(other.dataHoraIn))
			return false;
		if (empresa == null) {
			if (other.empresa != null)
				return false;
		} else if (!empresa.equals(other.empresa))
			return false;
		if (enderecoCliente == null) {
			if (other.enderecoCliente != null)
				return false;
		} else if (!enderecoCliente.equals(other.enderecoCliente))
			return false;
		if (formaDePagamento == null) {
			if (other.formaDePagamento != null)
				return false;
		} else if (!formaDePagamento.equals(other.formaDePagamento))
			return false;
		if (idPedido != other.idPedido)
			return false;
		if (pedidoProdutos == null) {
			if (other.pedidoProdutos != null)
				return false;
		} else if (!pedidoProdutos.equals(other.pedidoProdutos))
			return false;
		if (statusPedido != other.statusPedido)
			return false;
		if (Float.floatToIntBits(taxa) != Float.floatToIntBits(other.taxa))
			return false;
		if (tempoEspera != other.tempoEspera)
			return false;
		if (Float.floatToIntBits(troco) != Float.floatToIntBits(other.troco))
			return false;
		if (Float.floatToIntBits(valorTotal) != Float
				.floatToIntBits(other.valorTotal))
			return false;
		return true;
	}

	@Override
	public int compareTo(Pedido o) {
		if (this.statusPedido < o.statusPedido) {
			return -1;
		}
		if (this.statusPedido > o.statusPedido) {
			return 1;
		}
		return 0;
	}

	@SuppressWarnings("deprecation")
	public void calcularTempoEspera(int tempo) {

		for (PedidoProduto x : pedidoProdutos) {
			tempo += x.tempoEsperaTotal();
		}

		dataHoraFim = new Date();
		dataHoraFim.setMinutes(dataHoraFim.getMinutes() + tempo);

		tempoEspera = tempo;

	}

	public void calculoTaxaValorTotal() {

		valorTotal = getTaxa() + getValorTotal();

	}

	@Override
	public Object onCycleDetected(Context arg0) {

		Pedido p = new Pedido();
		p.setIdPedido(this.idPedido);
		return p;
	}

}
