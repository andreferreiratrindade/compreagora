package br.Empresa;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

import br.AtendimentoLugares.EmpresaAtendimento;
import br.Empresa.Categoria.Categoria;
import br.Empresa.FormaDePagamento.FormaDePagamento;
import br.Pedido.Pedido;

import com.sun.xml.bind.CycleRecoverable;

@Entity(name = "empresa")
public class Empresa implements Serializable, IEmpresaDiaDaSemana,
		CycleRecoverable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idEmpresa;
	private String logradouro;
	private String cep;
	private String numero;
	private String complemento;
	private String bairro;
	private String cidade;
	private String UF;
	private String CNPJ;
	private String razaoSocial;
	private String nomeFant;
	private String telefone;
	private boolean aberto;
	private boolean ativo;

	@OneToMany
	@JoinColumn(name = "idEmpresa")
	private List<EmpresaAtendimento> empresaAtendimentos = new ArrayList<EmpresaAtendimento>();

	@OneToMany
	@JoinColumn(name = "idEmpresa")
	private List<HorarioFuncionamento> horarioFuncionamento = new ArrayList<HorarioFuncionamento>();

	@OneToMany
	@JoinColumn(name = "idEmpresa")
	private List<Pedido> pedidos = new ArrayList<Pedido>();

	@ManyToMany
	@JoinTable(name = "empresa_formadepagamento", joinColumns = { @JoinColumn(name = "idEmpresa") }, inverseJoinColumns = { @JoinColumn(name = "idFormaDePagamento") })
	private List<FormaDePagamento> formasDePagamento = new ArrayList<FormaDePagamento>();

	@ManyToMany
	@JoinTable(name = "empresa_categoria", joinColumns = { @JoinColumn(name = "idEmpresa") }, inverseJoinColumns = { @JoinColumn(name = "idCategoria") })
	private List<Categoria> categorias = new ArrayList<Categoria>();

	public String getLogradouro() {
		return logradouro;
	}

	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public String getComplemento() {
		return complemento;
	}

	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public String getUF() {
		return UF;
	}

	public void setUF(String uF) {
		UF = uF;
	}

	public Empresa() {
		this.ativo = true;
	}

	public void addFormaDePagamento(FormaDePagamento obj) {

		formasDePagamento.add(obj);
	}

	public void removeFormaDePagamento(FormaDePagamento obj) {
		formasDePagamento.remove(obj);
	}

	public List<FormaDePagamento> getFormasDePagamento() {
		return formasDePagamento;
	}

	public void setFormasDePagamento(List<FormaDePagamento> formasDePagamento) {
		this.formasDePagamento = formasDePagamento;
	}

	public void addCategoria(Categoria categoria) {

		categorias.add(categoria);

	}

	public boolean isAtivo() {
		return ativo;
	}

	public void setAtivo(boolean ativo) {
		this.ativo = ativo;
	}

	public List<HorarioFuncionamento> getHorarioFuncionamento() {
		return horarioFuncionamento;
	}

	public void setHorarioFuncionamento(
			List<HorarioFuncionamento> horarioFuncionamento) {
		this.horarioFuncionamento = horarioFuncionamento;
	}

	public List<Pedido> getPedidos() {
		return pedidos;
	}

	public void setPedidos(List<Pedido> pedidos) {
		this.pedidos = pedidos;
	}

	public int getIdEmpresa() {
		return idEmpresa;
	}

	public void setIdEmpresa(int idEmpresa) {
		this.idEmpresa = idEmpresa;
	}

	public String getCNPJ() {
		return CNPJ;
	}

	public void setCNPJ(String cNPJ) {
		CNPJ = cNPJ;
	}

	public String getRazaoSocial() {
		return razaoSocial;
	}

	public void setRazaoSocial(String razaoSocial) {
		this.razaoSocial = razaoSocial;
	}

	public String getNomeFant() {
		return nomeFant;
	}

	public void setNomeFant(String nomeFant) {
		this.nomeFant = nomeFant;
	}

	public List<EmpresaAtendimento> getEmpresaAtendimentos() {
		return empresaAtendimentos;
	}

	public void setEmpresaAtendimentos(
			List<EmpresaAtendimento> empresaAtendimentos) {
		this.empresaAtendimentos = empresaAtendimentos;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public boolean isAberto() {
		return aberto;
	}

	public void setAberto(boolean aberto) {
		this.aberto = aberto;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((CNPJ == null) ? 0 : CNPJ.hashCode());
		result = prime * result + ((UF == null) ? 0 : UF.hashCode());
		result = prime * result + (aberto ? 1231 : 1237);
		result = prime * result + (ativo ? 1231 : 1237);
		result = prime * result + ((bairro == null) ? 0 : bairro.hashCode());
		result = prime * result
				+ ((categorias == null) ? 0 : categorias.hashCode());
		result = prime * result + ((cep == null) ? 0 : cep.hashCode());
		result = prime * result + ((cidade == null) ? 0 : cidade.hashCode());
		result = prime * result
				+ ((complemento == null) ? 0 : complemento.hashCode());
		result = prime
				* result
				+ ((empresaAtendimentos == null) ? 0 : empresaAtendimentos
						.hashCode());
		result = prime
				* result
				+ ((formasDePagamento == null) ? 0 : formasDePagamento
						.hashCode());
		result = prime
				* result
				+ ((horarioFuncionamento == null) ? 0 : horarioFuncionamento
						.hashCode());
		result = prime * result + idEmpresa;
		result = prime * result
				+ ((logradouro == null) ? 0 : logradouro.hashCode());
		result = prime * result
				+ ((nomeFant == null) ? 0 : nomeFant.hashCode());
		result = prime * result + ((numero == null) ? 0 : numero.hashCode());
		result = prime * result + ((pedidos == null) ? 0 : pedidos.hashCode());
		result = prime * result
				+ ((razaoSocial == null) ? 0 : razaoSocial.hashCode());
		result = prime * result
				+ ((telefone == null) ? 0 : telefone.hashCode());
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
		Empresa other = (Empresa) obj;
		if (CNPJ == null) {
			if (other.CNPJ != null)
				return false;
		} else if (!CNPJ.equals(other.CNPJ))
			return false;
		if (UF == null) {
			if (other.UF != null)
				return false;
		} else if (!UF.equals(other.UF))
			return false;
		if (aberto != other.aberto)
			return false;
		if (ativo != other.ativo)
			return false;
		if (bairro == null) {
			if (other.bairro != null)
				return false;
		} else if (!bairro.equals(other.bairro))
			return false;
		if (categorias == null) {
			if (other.categorias != null)
				return false;
		} else if (!categorias.equals(other.categorias))
			return false;
		if (cep == null) {
			if (other.cep != null)
				return false;
		} else if (!cep.equals(other.cep))
			return false;
		if (cidade == null) {
			if (other.cidade != null)
				return false;
		} else if (!cidade.equals(other.cidade))
			return false;
		if (complemento == null) {
			if (other.complemento != null)
				return false;
		} else if (!complemento.equals(other.complemento))
			return false;
		if (empresaAtendimentos == null) {
			if (other.empresaAtendimentos != null)
				return false;
		} else if (!empresaAtendimentos.equals(other.empresaAtendimentos))
			return false;
		if (formasDePagamento == null) {
			if (other.formasDePagamento != null)
				return false;
		} else if (!formasDePagamento.equals(other.formasDePagamento))
			return false;
		if (horarioFuncionamento == null) {
			if (other.horarioFuncionamento != null)
				return false;
		} else if (!horarioFuncionamento.equals(other.horarioFuncionamento))
			return false;
		if (idEmpresa != other.idEmpresa)
			return false;
		if (logradouro == null) {
			if (other.logradouro != null)
				return false;
		} else if (!logradouro.equals(other.logradouro))
			return false;
		if (nomeFant == null) {
			if (other.nomeFant != null)
				return false;
		} else if (!nomeFant.equals(other.nomeFant))
			return false;
		if (numero == null) {
			if (other.numero != null)
				return false;
		} else if (!numero.equals(other.numero))
			return false;
		if (pedidos == null) {
			if (other.pedidos != null)
				return false;
		} else if (!pedidos.equals(other.pedidos))
			return false;
		if (razaoSocial == null) {
			if (other.razaoSocial != null)
				return false;
		} else if (!razaoSocial.equals(other.razaoSocial))
			return false;
		if (telefone == null) {
			if (other.telefone != null)
				return false;
		} else if (!telefone.equals(other.telefone))
			return false;
		return true;
	}

	public List<Categoria> getCategorias() {
		return categorias;
	}

	public void setCategorias(List<Categoria> categorias) {
		this.categorias = categorias;
	}

	@Override
	public String domingo() {

		HorarioFuncionamento x = horarioFuncionamento.get(0);
		String info = x.isAtivo() ? x.getHoraInicio() + " - " + x.getHoraFim()
				: "--------------------";

		return info;
	}

	@Override
	public String segunda() {
		HorarioFuncionamento x = horarioFuncionamento.get(1);
		String info = x.isAtivo() ? x.getHoraInicio() + " - " + x.getHoraFim()
				: "--------------------";

		return info;
	}

	@Override
	public String terca() {
		HorarioFuncionamento x = horarioFuncionamento.get(2);
		String info = x.isAtivo() ? x.getHoraInicio() + " - " + x.getHoraFim()
				: "--------------------";

		return info;
	}

	@Override
	public String quarta() {
		HorarioFuncionamento x = horarioFuncionamento.get(3);
		String info = x.isAtivo() ? x.getHoraInicio() + " - " + x.getHoraFim()
				: "--------------------";

		return info;
	}

	@Override
	public String quinta() {
		HorarioFuncionamento x = horarioFuncionamento.get(4);
		String info = x.isAtivo() ? x.getHoraInicio() + " - " + x.getHoraFim()
				: "--------------------";

		return info;
	}

	@Override
	public String sexta() {
		HorarioFuncionamento x = horarioFuncionamento.get(5);
		String info = x.isAtivo() ? x.getHoraInicio() + " - " + x.getHoraFim()
				: "--------------------";

		return info;
	}

	@Override
	public String sabado() {
		HorarioFuncionamento x = horarioFuncionamento.get(6);
		String info = x.isAtivo() ? x.getHoraInicio() + " - " + x.getHoraFim()
				: "--------------------";

		return info;
	}

	public void removeCategoria(Categoria cat) {
		categorias.remove(cat);

	}

	@Override
	public Object onCycleDetected(Context arg0) {

		Empresa e = new Empresa();
		e.setIdEmpresa(this.idEmpresa);

		return e;
	}

	public void abrirOuFechar() {
		this.aberto = !this.aberto;
	}

}
