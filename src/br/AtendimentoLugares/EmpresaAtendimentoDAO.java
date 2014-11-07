package br.AtendimentoLugares;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import br.dao.Dao;

import com.uaihebert.factory.EasyCriteriaFactory;
import com.uaihebert.model.EasyCriteria;

public class EmpresaAtendimentoDAO implements Dao<EmpresaAtendimento> {
	private EntityManager session;

	@Override
	public void salve(EmpresaAtendimento obj) {
		this.session.persist(obj);

	}

	@Override
	public void remove(EmpresaAtendimento obj) {
		this.session.remove(obj);

	}

	@SuppressWarnings("unchecked")
	@Override
	public List<EmpresaAtendimento> lista() {
		Query query = session
				.createQuery(" SELECT e FROM empresaatendimento e");
		return (List<EmpresaAtendimento>) query.getResultList();
	}

	@Override
	public void update(EmpresaAtendimento obj) {
		// TODO Auto-generated method stub

	}

	@Override
	public EmpresaAtendimento getUnico(int id) {
		return (EmpresaAtendimento) this.session.find(EmpresaAtendimento.class,
				id);
	}

	public EmpresaAtendimento getEmpresaAtendimentoPeloBairroEmpresa(
			int idBairro, int idEmpresa) {
		EasyCriteria<EmpresaAtendimento> easyCriteria = EasyCriteriaFactory
				.createQueryCriteria(session, EmpresaAtendimento.class);
		easyCriteria.innerJoin("bairro").andEquals("bairro.idBairro", idBairro)
				.innerJoin("empresa").andEquals("empresa.idEmpresa", idEmpresa);
		return easyCriteria.getSingleResult();
	}

	public EntityManager getSession() {
		return session;
	}

	public void setSession(EntityManager session) {
		this.session = session;
	}

	public EmpresaAtendimento empresaAtendimentoEmpresaComBairro(int idEmpresa,
			String cidade, String bairro) {

		try {

			EasyCriteria<EmpresaAtendimento> easyCriteria = EasyCriteriaFactory
					.createQueryCriteria(session, EmpresaAtendimento.class);

			easyCriteria.innerJoin("bairro")
					.andEquals("bairro.descBairro", bairro)
					.innerJoin("empresa")
					.andEquals("empresa.idEmpresa", idEmpresa)
					.innerJoin("bairro.cidade")
					.andEquals("bairro.cidade.descCidade", cidade);

			List<EmpresaAtendimento> empAtendimentos = easyCriteria
					.getResultList();

			return empAtendimentos.isEmpty() ? null : empAtendimentos.get(0);

		} catch (Exception e) {
			System.out.println("Erro ao buscar Bairro na Empresa: "
					+ e.toString());
			return null;
		}
	}

}
