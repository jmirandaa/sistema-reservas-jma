/**
 * 
 */
package es.jma.reservas.core.daos.impl;

import java.util.List;

import org.hibernate.Query;
import org.springframework.transaction.annotation.Transactional;

import es.jma.reservas.core.daos.INegocioDAO;
import es.jma.reservas.core.dominio.negocio.Negocio;

/**
 * DAO de Negocio
 * @author Jorge Miranda
 *
 */
public class NegocioDAOHibImpl extends CrudDAOHibImpl implements INegocioDAO{
	private static NegocioDAOHibImpl instance;
	
	//Singleton
	private NegocioDAOHibImpl()
	{
		super(Negocio.class);
	}
	
	public static NegocioDAOHibImpl getInstance()
	{
		if (instance == null)
		{
			instance = new NegocioDAOHibImpl();
		}
		
		return instance;
	}

	@Override
	@Transactional
	public Negocio consultarNegocioId(int id) {
		Negocio negocio = new Negocio();
		
		try
		{
			//Consultar bar por nombre
			String hql = "from Negocio where id = :id";
			Query query = session.createQuery(hql);
			query.setInteger("id", id);
			@SuppressWarnings("unchecked")
			List<Negocio> results = query.list();
			
			if ((results != null ) && (results.size() > 0))
			{
				negocio = results.get(0);
			}
		}
		catch (Exception e)
		{
			throw e;
		}
		
		return negocio;
	}


		
}
