package br.beans.converter;



import java.io.Serializable;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import br.ProdutoAvulso.Avulso;
import br.ProdutoAvulso.AvulsoRN;

@FacesConverter(value = "converterAvulso")
public class AvulsoConverter implements Converter, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public Object getAsObject(FacesContext fc, UIComponent uic, String string) {
		AvulsoRN avulsoRN = new AvulsoRN();
		Avulso avulso = avulsoRN.buscarPorDescricao(string);

		return avulso;
	}

	@Override
	public String getAsString(FacesContext fc, UIComponent uic, Object o) {

		Avulso avulso = new Avulso();
		avulso = (Avulso) o;

		return avulso.getDescricao();

	}

}
