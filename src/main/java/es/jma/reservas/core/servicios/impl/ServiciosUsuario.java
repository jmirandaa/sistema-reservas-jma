/**
 * 
 */
package es.jma.reservas.core.servicios.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import es.jma.reservas.core.daos.ICrudDAO;
import es.jma.reservas.core.daos.IUsuarioDAO;
import es.jma.reservas.core.daos.impl.UsuarioDAOHibImpl;
import es.jma.reservas.core.dominio.usuarios.Usuario;
import es.jma.reservas.core.servicios.IServiciosUsuario;

/**
 * Servicios para la clase Usuario
 * @author Jorge Miranda
 *
 */
public class ServiciosUsuario extends ServiciosHibernate implements
		IServiciosUsuario {
	private IUsuarioDAO usuarioDAO;
	private static ServiciosUsuario instance;

	//Singleton
	public static ServiciosUsuario getInstance(SessionFactory sessionFactory)
	{
		if (instance == null)
		{
			instance = new ServiciosUsuario(sessionFactory);
		}
		
		return instance;
	}
	
	private ServiciosUsuario (SessionFactory sessionFactory)
	{
		super(sessionFactory);
		this.usuarioDAO = UsuarioDAOHibImpl.getInstance();
	}
	/* (non-Javadoc)
	 * @see es.jma.reservas.core.daos.IUsuarioDAO#nuevo(es.jma.reservas.core.dominio.usuarios.Usuario)
	 */
	@Override
	public void nuevo(Usuario usuario) throws Exception {
		Session session = null;
    	Transaction tx = null;
    	
		try
		{
			if (usuario != null)
			{
				if (txEnabled)
				{
					session = sessionFactory.openSession();
					tx = session.beginTransaction();
					usuarioDAO.setSession(session);
				}
				else
				{
					if (usuarioDAO.getSession() == null)
					{
						session = sessionFactory.openSession();
						tx = session.beginTransaction();
						usuarioDAO.setSession(session);
					}
				}
				
				//Buscar si el usuario ya existe
				Usuario usuarioExistente = usuarioDAO.consultarUsuarioNombreUsuario(usuario.getEmail());
				
				//En caso de que no, crear
				if (usuarioExistente.getEmail() == null)
				{
					usuarioDAO.nuevo(usuario);
					commit(tx);
				}
				else
				{
					rollback(tx);
				}
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

	/* (non-Javadoc)
	 * @see es.jma.reservas.core.daos.IUsuarioDAO#actualizar(es.jma.reservas.core.dominio.usuarios.Usuario)
	 */
	@Override
	public void actualizar(Usuario usuario) throws Exception {
		Session session = null;
    	Transaction tx = null;
    	
		try
		{
			if (usuario != null)
			{
				session = sessionFactory.openSession();
				tx = session.beginTransaction();				
				usuarioDAO.setSession(session);
			
				//Actualizar
				usuarioDAO.actualizar(usuario);
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

	/* (non-Javadoc)
	 * @see es.jma.reservas.core.daos.IUsuarioDAO#borrar(es.jma.reservas.core.dominio.usuarios.Usuario)
	 */
	@Override
	public void borrar(Usuario usuario) throws Exception {
		Session session = null;
    	Transaction tx = null;
    	
		try
		{
			if (usuario != null)
			{
				session = sessionFactory.openSession();
				tx = session.beginTransaction();				
				usuarioDAO.setSession(session);
			
				//Borrar
				usuarioDAO.borrar(usuario);
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

	/* (non-Javadoc)
	 * @see es.jma.reservas.core.daos.IUsuarioDAO#consultarTodosUsuarios()
	 */
	@Override
	public List<Usuario> consultarTodosUsuarios() throws Exception {
		Session session = null;
    	List<Usuario> usuarios = new ArrayList<Usuario> ();
		
		try
		{
			session = sessionFactory.openSession();				
			usuarioDAO.setSession(session);
			
			//Hacer la consulta
			usuarios = usuarioDAO.consultarTodosUsuarios();
			
		}
		catch (Exception e)
		{
			throw e;
		}
		finally
		{
			cerrarSesion(session);
		}
		
		return usuarios;
	}

	/* (non-Javadoc)
	 * @see es.jma.reservas.core.daos.IHibernateDAO#getSession()
	 */
	@Override
	public Session getSession() {
		return usuarioDAO.getSession();
	}

	/* (non-Javadoc)
	 * @see es.jma.reservas.core.daos.IHibernateDAO#setSession(org.hibernate.Session)
	 */
	@Override
	public void setSession(Session session) {
		usuarioDAO.setSession(session);

	}

	@Override
	public Usuario consultarUsuarioNombreUsuario(String nombreUsuario)
			throws Exception {
		Session session = null;
    	Usuario usuario = new Usuario ();
		
		try
		{
			if (txEnabled)
			{
				session = sessionFactory.openSession();
				usuarioDAO.setSession(session);
			}
			else
			{
				if (usuarioDAO.getSession() == null)
				{
					session = sessionFactory.openSession();
				}
			}
			
			//Hacer la consulta
			usuario = usuarioDAO.consultarUsuarioNombreUsuario(nombreUsuario);
			
		}
		catch (Exception e)
		{
			throw e;
		}
		finally
		{
			cerrarSesion(session);
		}
		
		return usuario;
	}

	@Override
	protected ICrudDAO getCrudDAO() {
		return null;
	}

	@Override
	public boolean comprobarDatosUsuario(String nombreUsuario, String password)
			throws Exception {
		Session session = null;
    	boolean resultado = false;
		
		try
		{
			if (txEnabled)
			{
				session = sessionFactory.openSession();
				usuarioDAO.setSession(session);
			}
			else
			{
				if (usuarioDAO.getSession() == null)
				{
					session = sessionFactory.openSession();
				}
			}
			
			//Hacer la consulta
			resultado = usuarioDAO.comprobarDatosUsuario(nombreUsuario, password);
			
		}
		catch (Exception e)
		{
			throw e;
		}
		finally
		{
			cerrarSesion(session);
		}
		
		return resultado;
	}

}
