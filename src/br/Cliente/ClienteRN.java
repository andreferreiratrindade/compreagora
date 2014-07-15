package br.Cliente;

import java.util.List;

import br.AtendimentoLugares.Bairro;
import br.Endereco.Endereco;
import br.Endereco.EnderecoDAO;
import br.EnderecoCliente.EnderecoCliente;
import br.EnderecoCliente.EnderecoClienteDAO;
import br.Permissao.Permissao;
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
		Permissao permissao = new Permissao();
		permissao.setPermissao("ROLE_ADM");
		permissao.setIdPermissao(6); // 6

		cliente.setPermissao(permissao);
		this.clienteDAO.salve(cliente);
	}

	public void salvar(Cliente cliente, Endereco endereco,
			EnderecoCliente enderecoCliente) {

		Permissao permissao = new Permissao();
		permissao.setPermissao("ROLE_CLI");
		permissao.setIdPermissao(7);

		cliente.setPermissao(permissao);

		clienteDAO.salve(cliente);

		enderecoCliente.setCliente(cliente);
		enderecoCliente.setEndereco(endereco);

		EnderecoClienteDAO endClienteDAO = DAOFactoy.criarEnderecoCliente();

		endClienteDAO.salve(enderecoCliente);

		// Criando outro endereco
		Bairro bairro = endereco.getBairroCidade();
		endereco = new Endereco();
		endereco.setBairroCidade(bairro);

		enderecoCliente = new EnderecoCliente();
		enderecoCliente.setCliente(cliente);
		enderecoCliente.setEndereco(endereco);
		enderecoCliente.setDescEndereco("Outro Endereço");

		endClienteDAO.salve(enderecoCliente);

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

	public void atualizarCliente(Cliente cliente, Endereco endereco,
			EnderecoCliente enderecoCliente) {
		EnderecoDAO endDAO = DAOFactoy.criarEndereco();
		endDAO.update(endereco);
		EnderecoClienteDAO endClienteDAO = DAOFactoy.criarEnderecoCliente();
		endClienteDAO.update(enderecoCliente);
		this.clienteDAO.update(cliente);
	}

	public Cliente realizarLoginCliente(String login, String senha) {

		return this.clienteDAO.realizarLoginCliente(login, senha);
	}
}
