package br.Empresa;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

public class DiaDaSemanaDAO {
	private EntityManager session;

	public EntityManager getSession() {
		return session;
	}

	public void setSession(EntityManager session) {
		this.session = session;
	}

	@SuppressWarnings("unchecked")
	public List<DiaDaSemana> listarDiaDaSemana() {

		Query query = session.createQuery(" SELECT e FROM diadasemana e");
		return (List<DiaDaSemana>) query.getResultList();
	}
}
