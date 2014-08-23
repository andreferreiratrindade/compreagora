package br.Produto.ProdutoAvulso;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

import br.Produto.Produto;
import br.ProdutoAvulso.Avulso;

import com.sun.xml.bind.CycleRecoverable;

@Entity(name = "produtoavulso")
public class ProdutoAvulso implements Serializable, CycleRecoverable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue
	private int idProdutoAvulso;
	@ManyToOne
	@JoinColumn(name = "idProduto")
	private Produto produto;
	@ManyToMany
	private List<Avulso> avulsos = new ArrayList<Avulso>();

	public int getIdProdutoAvulso() {
		return idProdutoAvulso;
	}

	public void setIdProdutoAvulso(int idProdutoAvulso) {
		this.idProdutoAvulso = idProdutoAvulso;
	}

	public ProdutoAvulso() {
		avulsos = new ArrayList<Avulso>();
	}

	public void addAvulso(Avulso avulso) {

		avulsos.add(avulso);
	}

	public void addProduto(Produto produto) {
		this.produto = produto;
	}

	public BigDecimal valorTotal() {

		BigDecimal valorTotal = produto.getValor();
		
		if (avulsos != null)
			for (Avulso x : avulsos) {
				valorTotal=	valorTotal.add(x.getValor());
			}

		return valorTotal;
	}

	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}

	public List<Avulso> getAvulsos() {
		return avulsos;
	}

	public void setAvulsos(List<Avulso> avulsos) {
		this.avulsos = avulsos;
	}

	public int tempoEsperaTotal() {

		int tempo = produto.getTempoEspera();

		for (Avulso x : avulsos) {
			tempo += x.getTempoEspera();
		}

		return tempo;
	}

	@Override
	public Object onCycleDetected(Context arg0) {
		ProdutoAvulso p = new ProdutoAvulso();
		p.setIdProdutoAvulso(getIdProdutoAvulso());

		return p;
	}

}
