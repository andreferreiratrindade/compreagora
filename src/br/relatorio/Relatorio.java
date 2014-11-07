package br.relatorio;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.JRTextExporter;
import net.sf.jasperreports.engine.export.JRTextExporterParameter;
import net.sf.jasperreports.engine.export.oasis.JROdtExporter;
import net.sf.jasperreports.engine.export.ooxml.JRDocxExporter;
import net.sf.jasperreports.engine.export.ooxml.JRDocxExporterParameter;
import net.sf.jasperreports.engine.export.ooxml.JRPptxExporter;
import net.sf.jasperreports.engine.export.ooxml.JRXlsxExporter;

public class Relatorio {

	private JasperPrint jasperPrint;

	private String nomeArq;
	private List<?> lista;
	private Map parametros;

	public Relatorio(String nomeArq, List<?> lista) {
		this.nomeArq = nomeArq;
		this.lista = lista;
		parametros = new HashMap();
	}

	public Relatorio() {

	}

	public Relatorio(String nomeArq, List<?> lista, Map parametros) {
		this.nomeArq = nomeArq;
		this.lista = lista;
		this.parametros = parametros;
	}

	public void init() throws JRException {

		ExternalContext externalContext = FacesContext.getCurrentInstance()
				.getExternalContext();
		ServletContext context = (ServletContext) externalContext.getContext();
		String arquivo = context
				.getRealPath("relatorio/" + nomeArq + ".jasper");

		JRBeanCollectionDataSource beanCollectionDataSource = new JRBeanCollectionDataSource(
				lista);

		jasperPrint = JasperFillManager.fillReport(arquivo, parametros,
				beanCollectionDataSource);
	}

	public void PDF() throws JRException, IOException {
		init();
		HttpServletResponse httpServletResponse = (HttpServletResponse) FacesContext
				.getCurrentInstance().getExternalContext().getResponse();
		httpServletResponse.addHeader("Content-Disposition",
				"attachment; filename =" + nomeArq + ".pdf");

		ServletOutputStream servletOutputStream = httpServletResponse
				.getOutputStream();

		JasperExportManager.exportReportToPdfStream(jasperPrint,
				servletOutputStream);

		FacesContext.getCurrentInstance().responseComplete();
	}

	public void TXT() throws JRException, IOException {
		init();
		HttpServletResponse httpServletResponse = (HttpServletResponse) FacesContext
				.getCurrentInstance().getExternalContext().getResponse();
		httpServletResponse.addHeader("Content-Disposition",
				"attachment; filename =" + nomeArq + ".txt");

		ServletOutputStream servletOutputStream = httpServletResponse
				.getOutputStream();

		JRTextExporter exporter = new JRTextExporter();
		exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);

		exporter.setParameter(JRExporterParameter.OUTPUT_STREAM,
				servletOutputStream);
		exporter.setParameter(JRTextExporterParameter.OUTPUT_STREAM,
				servletOutputStream);
		exporter.setParameter(JRTextExporterParameter.CHARACTER_WIDTH,
				new Integer(6));
		exporter.setParameter(JRTextExporterParameter.CHARACTER_HEIGHT,
				new Integer(11));
		exporter.exportReport();

		FacesContext.getCurrentInstance().responseComplete();
	}

	public void DOCX() throws JRException, IOException {
		init();
		HttpServletResponse httpServletResponse = (HttpServletResponse) FacesContext
				.getCurrentInstance().getExternalContext().getResponse();
		httpServletResponse.addHeader("Content-disposition",
				"attachment; filename=report.docx");
		ServletOutputStream servletOutputStream = httpServletResponse
				.getOutputStream();
		JRDocxExporter docxExporter = new JRDocxExporter();
		docxExporter
				.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
		docxExporter.setParameter(JRExporterParameter.OUTPUT_STREAM,
				servletOutputStream);
		docxExporter.setParameter(JRDocxExporterParameter.OUTPUT_STREAM,
				servletOutputStream);
		docxExporter.exportReport();
	}

	public void XLSX() throws JRException, IOException {
		init();
		HttpServletResponse httpServletResponse = (HttpServletResponse) FacesContext
				.getCurrentInstance().getExternalContext().getResponse();
		httpServletResponse.addHeader("Content-disposition",
				"attachment; filename=report.xlsx");
		ServletOutputStream servletOutputStream = httpServletResponse
				.getOutputStream();
		JRXlsxExporter docxExporter = new JRXlsxExporter();
		docxExporter
				.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
		docxExporter.setParameter(JRExporterParameter.OUTPUT_STREAM,
				servletOutputStream);
		docxExporter.exportReport();
	}

	public void ODT() throws JRException, IOException {
		init();
		HttpServletResponse httpServletResponse = (HttpServletResponse) FacesContext
				.getCurrentInstance().getExternalContext().getResponse();
		httpServletResponse.addHeader("Content-disposition",
				"attachment; filename=report.odt");
		ServletOutputStream servletOutputStream = httpServletResponse
				.getOutputStream();
		JROdtExporter docxExporter = new JROdtExporter();
		docxExporter
				.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
		docxExporter.setParameter(JRExporterParameter.OUTPUT_STREAM,
				servletOutputStream);
		docxExporter.exportReport();
	}

	public void PPT() throws JRException, IOException {
		init();
		HttpServletResponse httpServletResponse = (HttpServletResponse) FacesContext
				.getCurrentInstance().getExternalContext().getResponse();
		httpServletResponse.addHeader("Content-disposition",
				"attachment; filename=report.pptx");
		ServletOutputStream servletOutputStream = httpServletResponse
				.getOutputStream();
		JRPptxExporter docxExporter = new JRPptxExporter();
		docxExporter
				.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
		docxExporter.setParameter(JRExporterParameter.OUTPUT_STREAM,
				servletOutputStream);
		docxExporter.exportReport();
	}

	public Map getParametros() {
		return parametros;
	}

	public void setParametros(Map parametros) {
		this.parametros = parametros;
	}

	public String getNomeArq() {
		return nomeArq;
	}

	public void setNomeArq(String nomeArq) {
		this.nomeArq = nomeArq;
	}

	public List<?> getLista() {
		return lista;
	}

	public void setLista(List<?> lista) {
		this.lista = lista;
	}

	// public void gerarRelatorio(String nomeArquivo, List<?> lista) {
	//
	// ExternalContext externalContext = FacesContext.getCurrentInstance()
	// .getExternalContext();
	// ServletContext context = (ServletContext) externalContext.getContext();
	// String arquivo = context.getRealPath("relatorios/" + nomeArquivo
	// + ".jasper");
	//
	// JRDataSource jrds = new JRBeanCollectionDataSource(lista);
	//
	// gerarRelatorioWeb(jrds, null, arquivo);
	//
	// }
	//
	// private void gerarRelatorioWeb(JRDataSource jrds,
	// Map<Object, Object> parametros, String arquivo) {
	//
	// ServletOutputStream servletOutputStream = null;
	// FacesContext context = FacesContext.getCurrentInstance();
	// HttpServletResponse response = (HttpServletResponse) context
	// .getExternalContext().getResponse();
	//
	// try {
	// servletOutputStream = response.getOutputStream();
	// JasperRunManager.runReportToPdfStream(new FileInputStream(new File(
	// arquivo)), response.getOutputStream(), parametros, jrds);
	// response.setContentType("application/pdf");
	// servletOutputStream.flush();
	// servletOutputStream.close();
	// context.renderResponse();
	// context.responseComplete();
	// } catch (Exception e) {
	// e.printStackTrace();
	// }
	//
	// }

}
