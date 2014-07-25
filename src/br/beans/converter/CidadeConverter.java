package br.beans.converter;

import java.io.Serializable;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import br.AtendimentoLugares.Bairro;
import br.AtendimentoLugares.Cidade;
import br.AtendimentoLugares.CidadeRN;

@FacesConverter(value = "converterCidade")
public class CidadeConverter implements Converter, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public Object getAsObject(FacesContext fc, UIComponent uic, String string) {

		CidadeRN cidadeRN = new CidadeRN();
		Cidade cidade = cidadeRN.buscarPorDescricao(string);
		for (@SuppressWarnings("unused") Bairro x : cidade.getBairros()) {

		}
		return cidade;
	}

	@Override
	public String getAsString(FacesContext fc, UIComponent uic, Object o) {
		Cidade cidade = new Cidade();
		cidade = (Cidade) o;
		return cidade.getDescCidade();
	}

}
