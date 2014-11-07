package br.util.Email;

public class EmailDestino {
	private String nome;
	private String email;
	private String assunto;
	private String mensagem;
	private String servidor;

	public String getServidor() {

		return servidor;
	}

	public void setServidor(String servidor) {
		this.servidor = servidor;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {

		this.email = email;
		this.servidor = this.email.split("\\@")[1];
		this.servidor = this.servidor.split("\\.")[0];
	}

	public String getAssunto() {
		return assunto;
	}

	public void setAssunto(String assunto) {
		this.assunto = assunto;
	}

	public String getMensagem() {
		return mensagem;
	}

	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}

}
