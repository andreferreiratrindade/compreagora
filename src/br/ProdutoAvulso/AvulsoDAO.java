package br.ProdutoAvulso;

import java.util.List;

import javax.persistence.EntityManager;

import br.Empresa.Categoria.CategoriaENUM;
import br.dao.Dao;

import com.uaihebert.factory.EasyCriteriaFactory;
import com.uaihebert.model.EasyCriteria;

public class AvulsoDAO implements Dao<Avulso> {

	private EntityManager session;

	public EntityManager getSession() {
		return session;
	}

	public void setSession(EntityManager session) {
		this.session = session;
	}

	@Override
	public void salve(Avulso obj) {
		session.persist(obj);
	}

	@Override
	public void remove(Avulso obj) {

	}

	@Override
	public List<Avulso> lista() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void update(Avulso obj) {
		session.merge(obj);
	}

	@Override
	public Avulso getUnico(int id) {
		return session.find(Avulso.class, id);
	}

	public Avulso buscarPorDescricao(String string) {
		EasyCriteria<Avulso> easyCriteria = EasyCriteriaFactory
				.createQueryCriteria(session, Avulso.class);
		easyCriteria.andEquals("descricao", string);
		Avulso avulso = (Avulso) easyCriteria.getResultList().get(0);
		return avulso;

	}

	public List<Avulso> listar(Integer idEmpresa, CategoriaENUM tipoAvulso) {

		EasyCriteria<Avulso> easyCriteria = EasyCriteriaFactory
				.createQueryCriteria(session, Avulso.class);
		easyCriteria.innerJoinFetch("empresa")
				.andEquals("empresa.idEmpresa", idEmpresa)
				.andEquals("tipoAvulso", tipoAvulso);

		return easyCriteria.getResultList();

	}

	public List<Avulso> listarTodos(Integer idEmpresa) {

		EasyCriteria<Avulso> easyCriteria = EasyCriteriaFactory
				.createQueryCriteria(session, Avulso.class);
		easyCriteria.innerJoinFetch("empresa").andEquals("empresa.idEmpresa",
				idEmpresa);

		return easyCriteria.getResultList();

	}

}
