package br.Empresa;

import java.util.ArrayList;
import java.util.List;

import br.Cliente.Cliente;
import br.Cliente.ClienteRN;
import br.Empresa.Categoria.Categoria;
import br.Empresa.Categoria.CategoriaENUM;
import br.util.DAOFactoy;

public class EmpresaRN {
	private EmpresaDAO empresaDAO;

	public EmpresaDAO getEmpresaDAO() {
		return empresaDAO;
	}

	public void setEmpresaDAO(EmpresaDAO empresaDAO) {
		this.empresaDAO = empresaDAO;
	}

	public EmpresaRN() {
		this.empresaDAO = DAOFactoy.criarEmpresa();

	}

	public Empresa getEmpresa(Integer id) {
		return this.empresaDAO.getUnico(id);
	}

	public void salvar(Empresa empresa, Cliente cliente) {

		empresa.setPedidos(null);
		empresa.setEmpresaAtendimentos(null);

		this.empresaDAO.salve(empresa);

		ClienteRN clienteRN = new ClienteRN();
		cliente.setNome(empresa.getRazaoSocial());
		cliente.setLogin(ultimoElementoAdicionado().toString());
		clienteRN.salvarLoginEmpresa(cliente);
	}

	public List<Empresa> listar() {
		return this.empresaDAO.lista();
	}

	// public List<Empresa> getEmpresaAtendiemento(String bairro) {
	//
	// List<Empresa> empresas = listar();
	//
	// List<Empresa> temp = new ArrayList<Empresa>();
	// for (Empresa x : empresas) {
	// for (EmpresaAtendimento y : x.getEmpresaAtendimentos()) {
	// if ((y.getBairro().getDescBairro().equals(bairro))
	// && (y.isAtivo())) {
	// temp.add(x);
	//
	// }
	// }
	// }
	//
	// return temp;
	// }

	public Integer ultimoElementoAdicionado() {
		return this.empresaDAO.ultimoElementoAdicionado();
	}

	public void remove(Empresa empresa) {
		this.empresaDAO.remove(empresa);
	}

	public void update(Empresa empresa) {
		this.empresaDAO.update(empresa);
	}

	public List<Empresa> listaEmpresasPeloBairro(int idBairro) {
		return this.empresaDAO.listaEmpresaPeloBairroAtendimento(idBairro);
	}


	public List<Empresa> listaEmpresasPeloBairroECategoria(int idBairro,
			CategoriaENUM cat) {

		List<Empresa> tempEmpresas = new ArrayList<Empresa>();
		List<Empresa> empresas = listaEmpresasPeloBairro(idBairro);

		for (Empresa e : empresas) {
			for (Categoria c : e.getCategorias()) {
				if (c.getTipoCategoria() == cat) {
					tempEmpresas.add(e);
					break;
				}
			}
		}

		return tempEmpresas;
	}
}
