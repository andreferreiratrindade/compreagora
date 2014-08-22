package br.relatorio.impressora;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.util.Scanner;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;
import javax.swing.JOptionPane;

public abstract class ImpressoraNaoFiscal {

	public static final String LINHA = "=============================";
	public static final String LINHA_SEPARA = "_____________________________";
	public static final String SUB_TOTAL = "-----------------------------";
	protected File arquivo;
	protected String nomeArquivo;

	public abstract void gerarArquivo();

	public abstract void gerarNomeArquivo();

	public abstract void apagarArquivo();

	public String getNomeArquivo() {
		return nomeArquivo;
	}

	public void setNomeArquivo(String nomeArquivo) {
		this.nomeArquivo = nomeArquivo;
	}

}
