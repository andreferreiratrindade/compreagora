package br.Permissao;

import br.util.DAOFactoy;

public class PermissaoRN {
	private PermissaoDAO session;

	public PermissaoRN() {
		this.session = DAOFactoy.criarPermissao();
	}

	public void salve(Permissao permissao) {
		session.salve(permissao);
	}

	public Permissao getPermissaoByDescricao(String descricao) {
		return session.getPermissaoByDescricao(descricao);
	}
}
