package br.beans;

import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.mail.MessagingException;

import br.util.Email.EmailConfing;
import br.util.Email.SendEmail;

@ManagedBean
@ViewScoped
public class EmailFaleConosco implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String menssagem;
	private String nome;

	public String getMenssagem() {
		return menssagem;
	}

	public void setMenssagem(String menssagem) {
		this.menssagem = menssagem;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public void sendEmail() {
		EmailConfing mj = new EmailConfing();
		mj.setSmtpHostMail("smtp.gmail.com");
		mj.setSmtpPortMail("587"); // 587 ou 465
		mj.setSmtpAuth("true");
		mj.setSmtpStarttls("true");
		mj.setUserMail("lanchonetprojeto@gmail.com");
		mj.setFromNameMail("LanchoNet - Fale Conosco");
		mj.setPassMail("88627387");
		mj.setCharsetMail("ISO-8859-1");
		mj.setSubjectMail("Fale conosco");
		mj.setBodyMail(menssagem);
		mj.setTypeTextMail(EmailConfing.TYPE_TEXT_HTML);

		Map<String, String> map = new HashMap<String, String>();
		map.put("lanchonetprojeto@gmail.com", "email gmail");

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
