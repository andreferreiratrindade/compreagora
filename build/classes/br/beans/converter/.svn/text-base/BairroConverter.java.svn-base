package br.beans.converter;

import java.io.Serializable;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import br.AtendimentoLugares.Bairro;
import br.AtendimentoLugares.BairroRN;


@FacesConverter(value = "converterBairro")
public class BairroConverter implements Converter,Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public Object getAsObject(FacesContext fc, UIComponent uic, String string) {
		BairroRN bairroRN = new BairroRN();
		Bairro bairro = bairroRN.buscarPorDescricao(string);
		return bairro;
	}

	@Override
	public String getAsString(FacesContext fc, UIComponent uic, Object o) {
		Bairro bairro = new Bairro();
		bairro = (Bairro) o;
		return bairro.getDescBairro();
	}

}
