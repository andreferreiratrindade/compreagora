package br.util.Email;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.mail.MessagingException;

public class Email {
	private EmailDestino emailDestino;

	public Email(EmailDestino emailDestino) {
		this.emailDestino = emailDestino;
	}

	public Email() {

	}

	public void sendEmail() {
		EmailConfing mj = new EmailConfing();
		mj.setSmtpHostMail("smtp.gmail.com");
		mj.setSmtpPortMail("587"); // 587 ou 465
		mj.setSmtpAuth("true");
		mj.setSmtpStarttls("true");
		mj.setUserMail("lanchonetprojeto@gmail.com");
		mj.setFromNameMail("LanchoNet");
		mj.setPassMail("88627387");
		mj.setCharsetMail("ISO-8859-1");
		mj.setSubjectMail(emailDestino.getAssunto());
		mj.setBodyMail(emailDestino.getMensagem());
		mj.setTypeTextMail(EmailConfing.TYPE_TEXT_HTML);

		Map<String, String> map = new HashMap<String, String>();
		map.put(emailDestino.getEmail(), "email " + emailDestino.getServidor());

		mj.setToMailsUsers(map);

		try {
			new SendEmail().senderMail(mj);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (MessagingException e) {
			e.printStackTrace();
		}
	}

	public void enviarVariosEmails(List<EmailDestino> emailsDestino,
			String assunto, String mensagem) {
		EmailConfing mj = new EmailConfing();
		mj.setSmtpHostMail("smtp.gmail.com");
		mj.setSmtpPortMail("587"); // 587 ou 465
		mj.setSmtpAuth("true");
		mj.setSmtpStarttls("true");
		mj.setUserMail("lanchonetprojeto@gmail.com");
		mj.setFromNameMail("LanchoNet");
		mj.setPassMail("88627387");
		mj.setCharsetMail("ISO-8859-1");
		mj.setSubjectMail(assunto);
		mj.setBodyMail(mensagem);
		mj.setTypeTextMail(EmailConfing.TYPE_TEXT_HTML);

		Map<String, String> map = new HashMap<String, String>();
		for (EmailDestino x : emailsDestino) {
			map.put(x.getEmail(), "email " + x.getServidor());
		}
		mj.setToMailsUsers(map);

		try {
			new SendEmail().senderMail(mj);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (MessagingException e) {
			e.printStackTrace();
		}
	}
}
