package br.Empresa;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import br.AtendimentoLugares.EmpresaAtendimento;
import br.Empresa.Categoria.CategoriaENUM;
import br.dao.Dao;

import com.uaihebert.factory.EasyCriteriaFactory;
import com.uaihebert.model.EasyCriteria;

public class EmpresaDAO implements Dao<Empresa> {
	private EntityManager session;

	public Integer ultimoElementoAdicionado() {
		String hql = "select max(idEmpresa) from empresa";

		Query query = session.createQuery(hql);
		return (Integer) query.getResultList().get(0);

	}

	@Override
	public void salve(Empresa obj) {
		session.persist(obj);
	}

	public EntityManager getSession() {
		return session;
	}

	public void setSession(EntityManager session) {
		this.session = session;
	}

	@Override
	public void remove(Empresa obj) {
		session.remove(obj);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Empresa> lista() {
		EasyCriteria<Empresa> easyCriteriaEmp = EasyCriteriaFactory
				.createQueryCriteria(session, Empresa.class);
		return easyCriteriaEmp.getResultList();
	}

	@Override
	public void update(Empresa obj) {
		this.session.merge(obj);

	}

	@Override
	public Empresa getUnico(int id) {

		return this.session.find(Empresa.class, id);
	}

	public List<Empresa> listaEmpresaPeloBairroAtendimento(int idBairro) {
		EasyCriteria<EmpresaAtendimento> easyCriteria = EasyCriteriaFactory
				.createQueryCriteria(session, EmpresaAtendimento.class);
		easyCriteria.innerJoin("bairro").andEquals("bairro.idBairro", idBairro);

		List<EmpresaAtendimento> empresasAt = easyCriteria.getResultList();

		List<Empresa> empresas = new ArrayList<Empresa>();
		for (EmpresaAtendimento x : empresasAt) {
			empresas.add(x.getEmpresa());
		}
		return empresas;
	}

	public List<Empresa> listaEmpresasPeloBairroECategoria(int idBairro,
			CategoriaENUM categoria) {
		EasyCriteria<Empresa> easyCriteriaEmp = EasyCriteriaFactory
				.createQueryCriteria(session, Empresa.class);
		
		easyCriteriaEmp.andEquals("ativo", true);
		easyCriteriaEmp.innerJoin("categorias");
		easyCriteriaEmp.andEquals("categorias.tipoCategoria", categoria);
	
		easyCriteriaEmp.innerJoin("empresaAtendimentos.bairro");
		easyCriteriaEmp.andEquals("empresaAtendimentos.bairro.idBairro",
				idBairro);
		
		

		return easyCriteriaEmp.getResultList();
	}

}
