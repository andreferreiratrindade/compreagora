package br.util;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.faces.context.FacesContext;

import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;

public class FileUpload {
	private String caminhoBase;
	private String caminho;
	private File file;
	private UploadedFile arq;

	public void fileUpload(FileUploadEvent event) {
		try {

			// Cria um arquivo UploadFile, para receber o arquivo do evento
			arq = event.getFile();
			caminhoBase = FacesContext.getCurrentInstance()
					.getExternalContext().getRealPath("")
					+ "/resources/imagens/";

		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public void gravarArquivoTomCat(String caminho, String nome) {
		this.caminho = caminho;
		InputStream in = null;
		try {
			in = new BufferedInputStream(arq.getInputstream());
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		String[] formato = arq.getFileName().split("\\.");

		File file = new File(caminhoBase + this.caminho + nome + "."
				+ formato[1]);

		// String caminho = file.getAbsolutePath();

		FileOutputStream fout = null;
		try {
			fout = new FileOutputStream(file);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			while (in.available() != 0) {
				fout.write(in.read());
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				fout.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public void gravarArquivoProjeto(String caminho, String nome) {

		this.caminho = caminho;
		InputStream in = null;
		try {
			in = new BufferedInputStream(arq.getInputstream());
		} catch (IOException e) {
			e.printStackTrace();
		}
		String[] formato = arq.getFileName().split("\\.");

		File file = new File(
				"D:/Project/git/Compre Agora/tcc/WebContent/resources/imagens/"
						+ this.caminho + nome + "." + formato[1]);

		FileOutputStream fout = null;
		try {
			fout = new FileOutputStream(file);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		try {
			while (in.available() != 0) {
				fout.write(in.read());
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				fout.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}

	public String getCaminhoBase() {
		return caminhoBase;
	}

	public void setCaminhoBase(String caminhoBase) {
		this.caminhoBase = caminhoBase;
	}

	public String getCaminho() {
		return caminho;
	}

	public void setCaminho(String caminho) {
		this.caminho = caminho;
	}

	public void setFile(File file) {
		this.file = file;
	}

	public File getFile() {
		return file;
	}
}
