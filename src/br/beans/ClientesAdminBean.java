package br.beans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import org.primefaces.event.FileUploadEvent;

import br.Cliente.Cliente;
import br.Cliente.ClienteRN;
import br.util.FileUpload;
import br.util.Email.Email;
import br.util.Email.EmailDestino;

@ManagedBean(name = "clientesAdminBean")
@RequestScoped
public class ClientesAdminBean implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String menssagem;
	private String assunto;
	private List<Cliente> clientes;
	private FileUpload arquivo;

	@PostConstruct
	public void construct() {
		ClienteRN clienteRN = new ClienteRN();
		clienteRN.listar();
		clientes = clienteRN.listar();
		arquivo = new FileUpload();
	}

	public List<Cliente> getClientes() {
		return clientes;
	}

	public void setClientes(List<Cliente> clientes) {
		this.clientes = clientes;
	}

	public String getMenssagem() {
		return menssagem;
	}

	public void setMenssagem(String menssagem) {
		this.menssagem = menssagem;
	}

	public void uploadAction(FileUploadEvent event) {
		this.arquivo.fileUpload(event);
	}

	public void enviarEmailParaTodosClientes() {
		List<EmailDestino> emailsDestino = new ArrayList<EmailDestino>();
		EmailDestino emailDestino = null;
		for (Cliente x : clientes) {

			if (x.getEmail().contains("@")) {
				emailDestino = new EmailDestino();
				emailDestino.setNome(x.getNome());
				emailDestino.setEmail(x.getEmail());
				emailsDestino.add(emailDestino);
			}
			System.out.println(x.getNome());
		}
		Email email = new Email();
		email.enviarVariosEmails(emailsDestino, assunto, menssagem);
	}

	public String getAssunto() {
		return assunto;
	}

	public void setAssunto(String assunto) {
		this.assunto = assunto;
	}
}
