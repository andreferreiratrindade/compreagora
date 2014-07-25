package br.relatorio.impressora;

import java.io.File;

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
