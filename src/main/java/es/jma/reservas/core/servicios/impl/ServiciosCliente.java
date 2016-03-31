/**
 * 
 */
package es.jma.reservas.core.servicios.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import es.jma.reservas.core.daos.IClienteDAO;
import es.jma.reservas.core.daos.ICrudDAO;
import es.jma.reservas.core.daos.impl.ClienteDAOHibImpl;
import es.jma.reservas.core.dominio.negocio.Negocio;
import es.jma.reservas.core.dominio.usuarios.Cliente;
import es.jma.reservas.core.servicios.IServiciosCliente;

/**
 * @author jmiranda
 *
 */
public class ServiciosCliente extends ServiciosHibernate implements
		IServiciosCliente {
	private IClienteDAO clienteDAO;
	private static ServiciosCliente instance;
	
	//Singleton
	public static ServiciosCliente getInstance(SessionFactory sessionFactory)
	{
		if (instance == null)
		{
			instance = new ServiciosCliente(sessionFactory);
		}
		
		return instance;
	}
	
	private ServiciosCliente (SessionFactory sessionFactory)
	{
		super(sessionFactory);
		this.clienteDAO = ClienteDAOHibImpl.getInstance();
	}
	
	/* (non-Javadoc)
	 * @see es.jma.reservas.core.daos.IHibernateDAO#getSession()
	 */
	@Override
	public Session getSession() {
		return clienteDAO.getSession();
	}

	/* (non-Javadoc)
	 * @see es.jma.reservas.core.daos.IHibernateDAO#setSession(org.hibernate.Session)
	 */
	@Override
	public void setSession(Session session) {
		clienteDAO.setSession(session);

	}

	/* (non-Javadoc)
	 * @see es.jma.reservas.core.servicios.impl.ServiciosHibernate#getCrudDAO()
	 */
	@Override
	protected ICrudDAO getCrudDAO() {
		return clienteDAO;
	}

	@Override
	public List<Cliente> consultarClientesNegocio(Negocio negocio)
			throws Exception {
		Session session = null;
    	List<Cliente> clientes = new ArrayList<Cliente> ();
		
		try
		{
			session = sessionFactory.openSession();				
			clienteDAO.setSession(session);
			
			//Hacer la consulta
			clientes = clienteDAO.consultarClientesNegocio(negocio);
			
		}
		catch (Exception e)
		{
			throw e;
		}
		finally
		{
			cerrarSesion(session);
		}
		
		return clientes;
	}

	@Override
	public void guardarClienteNegocio(Cliente cliente, Negocio negocio)
			throws Exception {
		Session session = null;
    	Transaction tx = null;
    	
		try
		{
			if (cliente != null)
			{
				if (txEnabled)
				{
					session = sessionFactory.openSession();
					tx = session.beginTransaction();
					clienteDAO.setSession(session);
				}
				else
				{
					if (clienteDAO.getSession() == null)
					{
						session = sessionFactory.openSession();
						tx = session.beginTransaction();
						clienteDAO.setSession(session);
					}
				}
				
				//Guardar
				clienteDAO.guardarClienteNegocio(cliente, negocio);
				commit(tx);

			}
		}
		catch (Exception e)
		{
			rollback(tx);
			throw e;
		}
		finally
		{
			cerrarSesion(session);
		}

		
	}

}
