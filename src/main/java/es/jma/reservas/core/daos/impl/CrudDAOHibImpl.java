/**
 * 
 */
package es.jma.reservas.core.daos.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.springframework.transaction.annotation.Transactional;

import es.jma.reservas.core.daos.ICrudDAO;

/**
 * Implementación de ICrudDAO
 * @author Jorge Miranda
 *
 */
public class CrudDAOHibImpl implements ICrudDAO {
	protected Session session;
	private Class<?> clase;
	
	protected CrudDAOHibImpl(Class<?> clase)
	{
		this.clase = clase;
	}
	
	@Override
	public Session getSession() {
		return this.session;
	}

	@Override
	public void setSession(Session session) {
		this.session = session;
	}

	@Override
	@Transactional
	public void nuevo(Object objeto) throws Exception {
		try
		{
			session.saveOrUpdate(objeto);
		}
		catch (Exception e)
		{
			throw e;
		}
	}

	@Override
	@Transactional
	public void actualizar(Object objeto) throws Exception {
		try
		{
			session.update(objeto);
		}
		catch (Exception e)
		{
			throw e;
		}
	}

	@Override
	@Transactional
	public void borrar(Object objeto) throws Exception {
		try
		{
			session.delete(objeto);
		}
		catch (Exception e)
		{
			throw e;
		}
	}

	@SuppressWarnings({"rawtypes" })
	@Override
	@Transactional
	public List consultarTodos() throws Exception {
		List listado = new ArrayList ();
		
		try
		{
			listado = (List) session
					.createCriteria(clase)
					.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();
		}
		catch (Exception e)
		{
			throw e;
		}
		
		return listado;
	}

}
