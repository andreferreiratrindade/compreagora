package br.builders;

import br.AtendimentoLugares.Bairro;
import br.AtendimentoLugares.BairroDAO;
import br.AtendimentoLugares.Cidade;
import br.AtendimentoLugares.CidadeDAO;
import br.util.DAOFactoy;

public class CidadeEBairroBuilder {

	private CidadeDAO cidadeDao;
	private BairroDAO bairroDao;

	public CidadeEBairroBuilder() {
		cidadeDao = DAOFactoy.criarCidade();
		bairroDao = DAOFactoy.criarBairro();
	}

	public void criar() {
		// Salvando uma cidade no banco para o teste
		Cidade cidade = new Cidade();
		cidade.setDescCidade("Governador Valadares");

		cidadeDao.salve(cidade);

		// Salvando um bairro no banco para o teste
		Bairro bairro1 = new Bairro();
		bairro1.setCidade(cidade);
		bairro1.setDescBairro("Nova Vila Bretas");
		bairroDao.salve(bairro1);

		Bairro bairro2 = new Bairro();
		bairro2.setCidade(cidade);
		bairro2.setDescBairro("Centro");
		bairroDao.salve(bairro2);

		Bairro bairro3 = new Bairro();
		bairro3.setCidade(cidade);
		bairro3.setDescBairro("Jardim Perola");
		bairroDao.salve(bairro3);

		Bairro bairro4 = new Bairro();
		bairro4.setCidade(cidade);
		bairro4.setDescBairro("Vila Isa");
		bairroDao.salve(bairro4);
	}
}
