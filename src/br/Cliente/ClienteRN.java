package br.Cliente;

import java.util.List;

import br.Permissao.Permissao;
import br.Permissao.PermissaoEnum;
import br.Permissao.PermissaoRN;
import br.util.DAOFactoy;

public class ClienteRN {
	private ClienteDAO clienteDAO;

	public ClienteRN() {
		this.clienteDAO = DAOFactoy.criarCliente();
	}

	public Cliente getCliente(int id) {
		return this.clienteDAO.getUnico(id);
	}

	public void salvarLoginEmpresa(Cliente cliente) {
		PermissaoRN permissaoRN = new PermissaoRN();

		Permissao permissao = permissaoRN
				.getPermissaoByDescricao(PermissaoEnum.ROLE_ADM.name());

		cliente.setPermissao(permissao);
		this.clienteDAO.salve(cliente);
	}

	public void salvar(Cliente cliente) {
		PermissaoRN permissaoRN = new PermissaoRN();

		Permissao permissao = permissaoRN
				.getPermissaoByDescricao(PermissaoEnum.ROLE_CLI.name());

		cliente.setPermissao(permissao);

		clienteDAO.salve(cliente);
	}

	public List<Cliente> listar() {
		return this.clienteDAO.lista();
	}

	public Cliente buscarPorEmail(String email) {
		return this.clienteDAO.buscarPorEmail(email);
	}

	public boolean verificaEmailCadastrado(String email) {
		return this.clienteDAO.verificaEmailCadastrado(email);
	}

	public Cliente buscarPorLogin(String login) {
		return this.clienteDAO.buscarPorLogin(login);
	}

	public void atualizarCliente(Cliente cliente) {

		this.clienteDAO.update(cliente);
	}

	public Cliente realizarLoginCliente(String login, String senha) {

		return this.clienteDAO.realizarLoginCliente(login, senha);
	}
}
