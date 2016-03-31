/**
 * 
 */
package es.jma.reservas.core.daos.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;

import es.jma.reservas.core.daos.IClienteDAO;
import es.jma.reservas.core.dominio.negocio.Negocio;
import es.jma.reservas.core.dominio.usuarios.AbstractPersona;
import es.jma.reservas.core.dominio.usuarios.Cliente;

/**
 * @author jmiranda
 *
 */
public class ClienteDAOHibImpl extends CrudDAOHibImpl implements IClienteDAO{
	private static ClienteDAOHibImpl instance;
	
	//Singleton
	private ClienteDAOHibImpl()
	{
		super(Cliente.class);
	}
	
	public static ClienteDAOHibImpl getInstance()
	{
		if (instance == null)
		{
			instance = new ClienteDAOHibImpl();
		}
		
		return instance;
	}

	@Override
	public List<Cliente> consultarClientesNegocio(Negocio negocio) throws Exception{
		List<Cliente> clientes = new ArrayList<Cliente> ();
		
		try
		{
			//Consultar clientes por el id del Negocio
			String hql = "SELECT neg.personas from Negocio neg where neg.id = :id";
			Query query = session.createQuery(hql);
			query.setLong("id", negocio.getId());
			@SuppressWarnings("unchecked")
			List<AbstractPersona> results = query.list();
			
			if ((results != null ) && (results.size() > 0))
			{
				for (AbstractPersona persona : results)
				{
					if (persona instanceof Cliente)
					{
						Cliente cliente = (Cliente) persona;
						clientes.add(cliente);
					}
				}
			}
		}
		catch (Exception e)
		{
			throw e;
		}
		return clientes;
	}

	@Override
	public void guardarClienteNegocio(Cliente cliente, Negocio negocio) throws Exception {
		try
		{
			List <AbstractPersona> personas = negocio.getPersonas();
			if (personas == null)
			{
				personas = new ArrayList<AbstractPersona> ();
				negocio.setPersonas(personas);
			}
			negocio.getPersonas().add(cliente);
			this.nuevo(negocio);
		}
		catch (Exception e)
		{
			throw e;
		}
		
	}
}
