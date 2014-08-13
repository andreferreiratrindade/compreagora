package br.beans;

import java.io.Serializable;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;

import org.springframework.util.DigestUtils;

import br.Cliente.Cliente;
import br.Cliente.ClienteDAO;
import br.Cliente.ClienteRN;
import br.Empresa.Empresa;
import br.Empresa.EmpresaRN;
import br.Endereco.Endereco;
import br.EnderecoCliente.EnderecoCliente;
import br.beans.menssagem.Menssagem;
import br.util.Email.Email;
import br.util.Email.EmailDestino;

@ManagedBean(name = "clienteBean")
@ViewScoped
public class ClienteBean implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Cliente cliente;
	private String confEmail;
	private Endereco endereco;
	private EnderecoCliente enderecoCliente;
	private DataModel<Cliente> listaCliente;
	private String email = null;

	public ClienteBean() {
		cliente = new Cliente();
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Cliente getCliente() {
		if (cliente == null) {
			cliente = new Cliente();
		}
		return cliente;
	}

	public Cliente getClienteLogado() {
		FacesContext context = FacesContext.getCurrentInstance();
		ExternalContext external = context.getExternalContext();
		String login = external.getRemoteUser();

		if (this.cliente == null || !login.equals(this.cliente.getEmail())) {

			if (login != null) {
				ClienteRN usuarioRN = new ClienteRN();
				this.cliente = usuarioRN.buscarPorEmail(login);
			}
		}
		return cliente;
	}

	public void preparaAdicionarCliente() {
		cliente = new Cliente();
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public DataModel<Cliente> getListaCliente() {
		List<Cliente> lista = new ClienteDAO().lista();
		listaCliente = new ListDataModel<Cliente>(lista);
		return listaCliente;
	}

	public void setListaCliente(DataModel<Cliente> listaCliente) {
		this.listaCliente = listaCliente;
	}

	public String cancelar() {
		cliente = new Cliente();
		endereco = new Endereco();
		enderecoCliente = new EnderecoCliente();
		enderecoCliente.setDescEndereco("Minha Casa");
		return "cadastroCliente.jsf?faces-redirect=true";
	}

	public EnderecoCliente getEnderecoCliente() {
		if (enderecoCliente == null) {
			enderecoCliente = new EnderecoCliente();
			enderecoCliente.setDescEndereco("Minha Casa");
		}
		return enderecoCliente;
	}

	public String getConfEmail() {
		return confEmail;
	}

	public void setConfEmail(String confEmail) {
		this.confEmail = confEmail;
	}

	public void setEnderecoCliente(EnderecoCliente enderecoCliente) {
		this.enderecoCliente = enderecoCliente;
	}

	public String salvar() {

		if (cliente.getEmail().equals(confEmail)) {

			ClienteRN clienteRN = new ClienteRN();

			if (clienteRN.verificaEmailCadastrado(cliente.getEmail())) {

				String senha = this.cliente.getSenha();

				String senhaCripto = DigestUtils.md5DigestAsHex(senha
						.getBytes());
				this.cliente.setSenha(senhaCripto);

				clienteRN.salvar(this.cliente, this.endereco,
						this.enderecoCliente);

				emailCadastro(senha);
				return "login.jsf?faces-redirect=true";
			} else {
				FacesContext.getCurrentInstance().addMessage(
						null,
						new FacesMessage(FacesMessage.SEVERITY_ERROR,
								"E-mail já foi Cadastrado!!",
								"Informe outro E-mail"));
				return null;
			}
		} else {
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR,
							"E-mail confirmado errado!!", " "));
			return null;
		}
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

	public String altCliente() {
		return "paginas/publico/altCliente/alterarCliente";
	}

	public String alterarCliente() {
		FacesContext context = FacesContext.getCurrentInstance();
		ExternalContext external = context.getExternalContext();
		String login = external.getRemoteUser();

		if (this.cliente == null || !login.equals(this.cliente.getEmail())) {

			if (login != null) {
				ClienteRN usuarioRN = new ClienteRN();
				this.cliente = usuarioRN.buscarPorEmail(login);
				enderecoCliente = cliente.getEnderecoCliente().get(0);
				endereco = cliente.getEnderecoCliente().get(0).getEndereco();

			}
		}
		confEmail = null;
		
		return "/paginas/publico/alterarCliente.jsf?faces-redirect=true";
	}

	public String atualizarSalvarCliente() {
		String senha = this.cliente.getSenha();
		String senhaCripto = DigestUtils.md5DigestAsHex(senha.getBytes());
		this.cliente.setSenha(senhaCripto);
		ClienteRN clienteRN = new ClienteRN();
		clienteRN.atualizarCliente(cliente, endereco, enderecoCliente);
		return "login.jsf?faces-redirect=true";
	}

	public void emailCadastro(String senha) {
		String mensagem = Menssagem.cadastro(cliente.getNome(),
				cliente.getEmail(), senha);
		EmailDestino emailDestino = new EmailDestino();
		emailDestino.setEmail(cliente.getEmail());
		emailDestino.setNome(cliente.getNome());
		emailDestino.setMensagem(mensagem);
		emailDestino.setAssunto("Cadastro realizado com Sucesso");
		sendEmail(emailDestino);
	}

	public void sendEmail(EmailDestino emailDestino) {

		Email enviarEmail = new Email(emailDestino);
		enviarEmail.sendEmail();
	}

	public void emailEsqueciMinhaSenha() {
		String senha = gerarSenhaAleatoria();

		String menssagem;

		menssagem = Menssagem.recuperarSenha(cliente.getNome(),
				cliente.getEmail(), senha);
		senha = DigestUtils.md5DigestAsHex(senha.getBytes());
		cliente.setSenha(senha);

		String htmlInicio = "<html> <head></head><body>";
		String htmlFim = " </body></html>";

		EmpresaRN empresaRN = new EmpresaRN();
		List<Empresa> empresas = empresaRN.listar();

		menssagem = "______________________________________";
		for (Empresa e : empresas) {
			menssagem += "<br/>| " + e.getNomeFant() + "|";
		}

		menssagem = htmlInicio + menssagem + htmlFim;
		EmailDestino emailDestino = new EmailDestino();
		emailDestino.setEmail(cliente.getEmail());
		emailDestino.setNome(cliente.getNome());
		emailDestino.setMensagem(menssagem);
		emailDestino.setAssunto("Nova Senha");
		sendEmail(emailDestino);
	}

	public String gerarSenhaAleatoria() {
		int cont = 0, b = 0, c = 0, num = 0;
		String i = "";
		String[] Vetor = new String[7];

		String[] ArrayString = { "2", "4", "5", "6", "7", "8", "9", "0", "1",
				"3", "a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "l",
				"m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "x", "w",
				"y", "z", "A", "B", "C", "D", "E", "F", "G", "H", "I", "J",
				"L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "X",
				"W", "Y", "Z" };

		for (c = 0; c < 4; c++) {
			num = (int) (Math.random() * 10);

			cont = (int) (Math.random() * ArrayString.length);

			for (b = 0; b <= ArrayString.length; b++) {

				if (b == cont)
					i = ArrayString[b];

			}

			Vetor[c] = (i += num);
			i = "";
			num = 0;
		}

		String senha = "";
		String p1 = "";
		String p2 = "";
		String p3 = "";
		String p4 = "";

		p1 = Vetor[0];
		p2 = Vetor[1];
		p3 = Vetor[2];
		p4 = Vetor[3];

		senha += p1 + p2 + p3 + p4;

		return senha;
	}

	public void esqueciMinhaSenha(ActionEvent actionEvent) {
		;
		try {
			ClienteRN usuarioRN = new ClienteRN();
			this.cliente = usuarioRN.buscarPorEmail(email);
			emailEsqueciMinhaSenha();
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_INFO,
							"E-mail enviado com sucesso!!", ""));
		} catch (Exception e) {
			// TODO: handle exception
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR,
							"Login não encontrado!!", ""));
		}
	}

}
