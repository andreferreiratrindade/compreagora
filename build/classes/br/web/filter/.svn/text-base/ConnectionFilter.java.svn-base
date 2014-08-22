package br.web.filter;

import java.io.IOException;

import javax.persistence.EntityManager;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import br.util.JpaUtil;

public class ConnectionFilter implements Filter {

	@Override
	public void destroy() {

	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {

		EntityManager entityManager = JpaUtil.getEntityManager();

		try {
			entityManager.getTransaction().begin();
			chain.doFilter(request, response);
			entityManager.getTransaction().commit();
		} catch (Exception e) {
			if (entityManager.isOpen()) {
				entityManager.getTransaction().rollback();
			}
		} finally {
			if (entityManager.isOpen()) {
				entityManager.close();
				;
			}
		}

	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub

	}

}
