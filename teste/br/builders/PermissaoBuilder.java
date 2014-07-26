package br.builders;

import br.Permissao.Permissao;
import br.Permissao.PermissaoDAO;
import br.Permissao.PermissaoEnum;
import br.util.DAOFactoy;

public class PermissaoBuilder {

	private PermissaoDAO permissaoDAO;

	public PermissaoBuilder() {
		permissaoDAO = DAOFactoy.criarPermissao();
	}

	public void criar() {
		Permissao permissao = new Permissao();
		permissao.setPermissao(PermissaoEnum.ROLE_ADM.name());

		permissaoDAO.salve(permissao);
	}
}
