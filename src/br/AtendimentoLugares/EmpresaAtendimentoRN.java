package br.AtendimentoLugares;

import java.util.List;

import br.util.DAOFactoy;

public class EmpresaAtendimentoRN {
	private EmpresaAtendimentoDAO empresaAtendimentoDAO;

	public EmpresaAtendimentoRN() {
		this.empresaAtendimentoDAO = DAOFactoy.criarEmpresaAtendimento();
	}

	public EmpresaAtendimento getEmpresaAtendimento(int id) {
		return this.empresaAtendimentoDAO.getUnico(id);
	}

	public void salvar(EmpresaAtendimento empresaAtendimento) {
		this.empresaAtendimentoDAO.salve(empresaAtendimento);
	}

	public List<EmpresaAtendimento> listar() {
		return this.empresaAtendimentoDAO.lista();
	}

	public EmpresaAtendimento empresaAtendimentoEmpresaComBairro(
			int idEmpresa, String cidade, String bairro) {
		return this.empresaAtendimentoDAO.empresaAtendimentoEmpresaComBairro(
				idEmpresa, cidade, bairro);
	}
}
