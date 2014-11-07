package br.builders;

import br.AtendimentoLugares.Bairro;
import br.AtendimentoLugares.BairroDAO;
import br.AtendimentoLugares.Cidade;
import br.AtendimentoLugares.CidadeDAO;
import br.Cliente.Cliente;
import br.Cliente.ClienteRN;
import br.Permissao.Permissao;
import br.Permissao.PermissaoEnum;
import br.Permissao.PermissaoRN;
import br.util.DAOFactoy;

public class ClienteBuilder {
	private Cliente cliente;

	public void criar() {
		Permissao permissao = new Permissao();
		permissao.setPermissao(PermissaoEnum.ROLE_CLI.name());
		PermissaoRN permissaoRN = new PermissaoRN();
		permissaoRN.salve(permissao);

		// Salvando uma cidade no banco para o teste
		Cidade cidade = new Cidade();
		cidade.setDescCidade("Governador Valadares");

		CidadeDAO cidadeDao = DAOFactoy.criarCidade();
		cidadeDao.salve(cidade);

		// Salvando um bairro no banco para o teste
		Bairro bairro = new Bairro();
		bairro.setCidade(cidade);
		bairro.setDescBairro("Nova Vila Bretas");

		BairroDAO bairroDao = DAOFactoy.criarBairro();
		bairroDao.salve(bairro);

		Cliente tempCliente = new Cliente();
		tempCliente.setAtivo(true);
		tempCliente.setEmail("dede@gmail.com");
		tempCliente.setNome("André Ferreira Trindade");

		ClienteRN clienteRN = new ClienteRN();
		clienteRN.salvar(tempCliente);
	}
}
