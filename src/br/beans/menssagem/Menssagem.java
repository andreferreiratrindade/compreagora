package br.beans.menssagem;

public class Menssagem {

	public static String cadastro(final String nome, final String login,
			final String senha) {
		return "<html>"
				+ "<head>"
				+ "<title>CONFIRMAÇÃO DE CADASTRO!</title>"
				+ "</head>"
				+ "<body>"
				+ "<div>"
				+ "Olá "
				+ nome
				+ "!<br/>"
				+ "Seu cadastro no site LanchoNet foi feito com sucesso.<br/>"
				+ "Seu login: "
				+ login

				+ "<br/>Sua senha: "
				+ senha

				+ "<br/>Agora, você poderá pedir lanches a vontade nos restaurantes cadastrados no sistema. "
				+ "<br/>Para confirmar o seu cadastramento no site e poder usufruir totalmente do LanchoNet,"
				+ "<br/>por favor clique no link a seguir."
				+ "</div>"
				+ "<div style='width:28%; height:50px;' align='center'>"
				+ "<br/>"
				+ "<a href='env-1816413.jelastic.websolute.net.br/lanchonet/'> LanchoNet </a> </body>"
				+ "</html>";
	}

	public static String recuperarSenha(final String nome, final String login,
			final String senha) {
		return "<html>"
				+ "<head>"
				+ "<title>NOVA SENHA!</title>"
				+ "</head>"
				+ "<body>"
				+ "<div>"
				+ "Olá "
				+ nome
				+ "!"
				+ "<br/>Você enviou uma solicitação de nova senha para o LachoNet<br/>"
				+ "Seu login: "
				+ login
				+ "<br/>Sua senha: "
				+ senha
				+ "<br/>Para trocar de senha apenas faça o login e altere sua senha.<br/>"
				+ "<div style='width:28%; height:50px;' align='center'>"
				+ "<br/>"
				+ "<a href='env-1816413.jelastic.websolute.net.br/lanchonet/'> LanchoNet </a> </body>"
				+ "</html>";
	}

}
