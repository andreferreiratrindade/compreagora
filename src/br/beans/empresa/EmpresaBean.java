package br.beans.empresa;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;

import org.primefaces.event.FileUploadEvent;
import org.springframework.util.DigestUtils;

import br.AtendimentoLugares.EmpresaAtendimento;
import br.AtendimentoLugares.EmpresaAtendimentoRN;
import br.Cliente.Cliente;
import br.Cliente.ClienteRN;
import br.Empresa.DiaDaSemana;
import br.Empresa.EmpresaDAO;
import br.Empresa.EmpresaRN;
import br.Empresa.HorarioFuncionamento;
import br.Empresa.HorarioFuncionamentoDAO;
import br.Empresa.Empresa;
import br.Empresa.FormaDePagamento.FormaDePagamento;
import br.Endereco.Endereco;
import br.util.DAOFactoy;
import br.util.FileUpload;

@ManagedBean(name = "empresaBean")
@SessionScoped
public class EmpresaBean implements Serializable {
	/**
	 * 
	 */

	private static final long serialVersionUID = 1L;
	private Empresa empresa;
	private Endereco endereco;
	private DataModel<Empresa> empresasDM;
	private HorarioFuncionamento horarioFuncionamento;
	private List<HorarioFuncionamento> horarioFuncionamentoList;
	private FileUpload arquivo = new FileUpload();
	private DiaDaSemana diaDaSemana;
	private DataModel<DiaDaSemana> diasDaSemanaDM;
	private List<EmpresaAtendimento> empresaAtendimentos;
	private EmpresaAtendimento empresaAtendimento;
	private Cliente cliente; // Apenas para Criar um login para a Empresa
	private String confEmail;
	private FormaDePagamento formaDePagamento;
	private List<FormaDePagamento> formasDePagamento;

	public FormaDePagamento getFormaDePagamento() {
		return formaDePagamento;
	}

	public void setFormaDePagamento(FormaDePagamento formaDePagamento) {
		this.formaDePagamento = formaDePagamento;
	}

	public List<FormaDePagamento> getFormasDePagamento() {
		return formasDePagamento;
	}

	public void setFormasDePagamento(List<FormaDePagamento> formasDePagamento) {
		this.formasDePagamento = formasDePagamento;
	}

	public String getConfEmail() {
		return confEmail;
	}

	public void setConfEmail(String confEmail) {
		this.confEmail = confEmail;
	}

	public List<EmpresaAtendimento> getEmpresaAtendimentos() {
		atualizaFormLocalidade();
		return empresaAtendimentos;
	}

	public Cliente getCliente() {

		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public void setEmpresaAtendimentos(
			List<EmpresaAtendimento> empresaAtendimentos) {
		this.empresaAtendimentos = empresaAtendimentos;
	}

	public EmpresaAtendimento getEmpresaAtendimento() {
		return empresaAtendimento;
	}

	public void setEmpresaAtendimento(EmpresaAtendimento empresaAtendimento) {
		this.empresaAtendimento = empresaAtendimento;
	}

	public List<HorarioFuncionamento> getHorarioFuncionamentoList() {
		EmpresaRN empresaRN = new EmpresaRN();
		empresa = empresaRN.getEmpresa(empresa.getIdEmpresa());
		horarioFuncionamentoList = empresa.getHorarioFuncionamento();
		return horarioFuncionamentoList;
	}

	public void setHorarioFuncionamentoList(
			List<HorarioFuncionamento> horarioFuncionamentoList) {
		this.horarioFuncionamentoList = horarioFuncionamentoList;
	}

	public DataModel<DiaDaSemana> getDiasDaSemanaDM() {
		return diasDaSemanaDM;
	}

	public void setDiasDaSemanaDM(DataModel<DiaDaSemana> diasDaSemanaDM) {
		this.diasDaSemanaDM = diasDaSemanaDM;
	}

	public DataModel<DiaDaSemana> getdiasDaSemanaDM() {

		return diasDaSemanaDM;
	}

	public void setdiasDaSemanaDM(DataModel<DiaDaSemana> diasDaSemanaDM) {
		this.diasDaSemanaDM = diasDaSemanaDM;
	}

	public HorarioFuncionamento getHorarioFuncionamento() {
		return horarioFuncionamento;
	}

	public void setHorarioFuncionamento(
			HorarioFuncionamento horarioFuncionamento) {
		this.horarioFuncionamento = horarioFuncionamento;
	}

	public DiaDaSemana getDiaDaSemana() {
		return diaDaSemana;
	}

	public void setDiaDaSemana(DiaDaSemana diaDaSemana) {
		this.diaDaSemana = diaDaSemana;
	}

	public FileUpload getArquivo() {
		return arquivo;
	}

	public void setArquivo(FileUpload arquivo) {
		this.arquivo = arquivo;
	}

	public Empresa getEmpresa() {
		return empresa;
	}

	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}

	public String novo() {
		empresa = new Empresa();
		endereco = new Endereco();
		cliente = new Cliente();
		return "/paginas/admin/empresa/configuracoes/cadastrarEmpresa.jsf?faces-redirect=true";
	}

	public String salvar() {
		String senha = this.cliente.getSenha();

		String senhaCripto = DigestUtils.md5DigestAsHex(senha.getBytes());
		this.cliente.setSenha(senhaCripto);

		empresa.setEndereco(endereco);

		EmpresaRN empresaRN = new EmpresaRN();

		empresaRN.salvar(empresa, cliente);
		this.arquivo.gravarArquivoTomCat("empresa/",
				"Empresa" + empresa.getIdEmpresa());
		this.arquivo.gravarArquivoProjeto("empresa/",
				"Empresa" + empresa.getIdEmpresa());
		;
		return "/paginas/admin/empresa/configuracoes/areaAdministrativa.jsf?faces-redirect=true";
	}

	public DataModel<Empresa> getEmpresasDM() {
		List<Empresa> lista = new EmpresaDAO().lista();
		empresasDM = new ListDataModel<Empresa>(lista);
		return empresasDM;
	}

	public void setEmpresasDM(DataModel<Empresa> empresasDM) {
		this.empresasDM = empresasDM;
	}

	public void uploadAction(FileUploadEvent event) {
		this.arquivo.fileUpload(event);
	}

	public void salvarHorario() {
		HorarioFuncionamentoDAO horarioDAO = DAOFactoy
				.criarHorarioFuncionamento();
		for (HorarioFuncionamento x : horarioFuncionamentoList) {
			horarioDAO.update(x);
		}
	}

	public String atualizarHorario() {

		return "/paginas/admin/empresa/configuracoes/horarioFuncionamentoEmpresa.jsf?faces-redirect=true";
	}

	public void atualizaFormLocalidade() {

		EmpresaRN empresaRN = new EmpresaRN();
		empresa = empresaRN.getEmpresa(empresa.getIdEmpresa());
		empresaAtendimentos = empresa.getEmpresaAtendimentos();
	}

	public String atualizarLocalAtendimento() {
		empresaAtendimento = new EmpresaAtendimento();
		return "/paginas/admin/empresa/configuracoes/empresaAtendimento.jsf?faces-redirect=true";
	}

	public void novoLocalAtendimento() {
		empresaAtendimento = new EmpresaAtendimento();
	}

	public String salvarLocalAtendimento() {
		EmpresaAtendimentoRN empresaAtendimentoRN = new EmpresaAtendimentoRN();
		empresaAtendimento.setEmpresa(empresa);
		empresaAtendimentoRN.salvar(empresaAtendimento);
		empresaAtendimento = new EmpresaAtendimento();
		return "/paginas/admin/empresa/configuracoes/empresaAtendimento.jsf?faces-redirect=true";
	}

	public String manterEmpresa() {

		ClienteRN usuarioRN = new ClienteRN();
		this.cliente = usuarioRN.buscarPorLogin(Integer.toString(empresa
				.getIdEmpresa()));

		return "/paginas/admin/empresa/configuracoes/manterEmpresa.jsf";
	}

	public void abrirOufecharEmpresa() {

		EmpresaRN empresaRN = new EmpresaRN();
		if (empresa == null) {
			empresa = getEmpresaLogado();
		}
		empresa.abrirOuFechar();
		empresaRN.update(empresa);
	}

	public Empresa getEmpresaLogado() {
		FacesContext context = FacesContext.getCurrentInstance();
		ExternalContext external = context.getExternalContext();
		String login = external.getRemoteUser();

		if (this.cliente == null || !login.equals(this.cliente.getEmail())) {

			if (login != null) {
				ClienteRN usuarioRN = new ClienteRN();
				this.cliente = usuarioRN.buscarPorEmail(login);
				EmpresaRN empresaRN = new EmpresaRN();
				empresa = empresaRN.getEmpresa(Integer.parseInt(cliente
						.getLogin()));
			}
		}

		return empresa;
	}
}
