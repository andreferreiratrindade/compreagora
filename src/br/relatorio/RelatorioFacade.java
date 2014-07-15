package br.relatorio;

import java.io.IOException;

import net.sf.jasperreports.engine.JRException;

public abstract class RelatorioFacade {

	protected int tipoRelatorio;
	protected Relatorio relatorio;

	public abstract void gerarRelatorio();

	protected void gerarArquivo() throws JRException, IOException {
		switch (tipoRelatorio) {
		case 1:
			relatorio.PDF();
		case 2:
			relatorio.DOCX();
		case 3:
			relatorio.XLSX();
		case 4:
			relatorio.TXT();
		}
	}

	public int getTipoRelatorio() {
		return tipoRelatorio;
	}

	public void setTipoRelatorio(int tipoRelatorio) {
		this.tipoRelatorio = tipoRelatorio;
	}

}
