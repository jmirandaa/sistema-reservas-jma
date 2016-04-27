/**
 * 
 */
package es.jma.reservas.core.daos.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import es.jma.reservas.core.daos.IUsuarioDAO;
import es.jma.reservas.core.dominio.usuarios.Usuario;
import es.jma.reservas.core.utils.UtilCifrado;

/**
 * DAO de Usuario (Hibernate)
 * @author Jorge Miranda
 *
 */
@Repository
public class UsuarioDAOHibImpl implements IUsuarioDAO {
	private Session session;
	private static IUsuarioDAO instance;
	
	//Singleton
	private UsuarioDAOHibImpl()
	{
		
	}
	
	public static IUsuarioDAO getInstance()
	{
		if (instance == null)
		{
			instance = new UsuarioDAOHibImpl();
		}
		
		return instance;
	}
	
	/* (non-Javadoc)
	 * @see es.jma.reservas.core.daos.IHibernateDAO#getSession()
	 */
	@Override
	public Session getSession() {
		return this.session;
	}

	/* (non-Javadoc)
	 * @see es.jma.reservas.core.daos.IHibernateDAO#setSession(org.hibernate.Session)
	 */
	@Override
	public void setSession(Session session) {
		this.session = session;

	}

	/* (non-Javadoc)
	 * @see es.jma.reservas.core.daos.IUsuarioDAO#nuevo(es.jma.reservas.core.dominio.usuarios.Usuario)
	 */
	@Override
	@Transactional
	public void nuevo(Usuario usuario) throws Exception {
		try
		{
			session.saveOrUpdate(usuario);
		}
		catch (Exception e)
		{
			throw e;
		}
	}

	/* (non-Javadoc)
	 * @see es.jma.reservas.core.daos.IUsuarioDAO#actualizar(es.jma.reservas.core.dominio.usuarios.Usuario)
	 */
	@Override
	@Transactional
	public void actualizar(Usuario usuario) throws Exception {
		try
		{
			session.update(usuario);
		}
		catch (Exception e)
		{
			throw e;
		}
	}

	/* (non-Javadoc)
	 * @see es.jma.reservas.core.daos.IUsuarioDAO#borrar(es.jma.reservas.core.dominio.usuarios.Usuario)
	 */
	@Override
	@Transactional
	public void borrar(Usuario usuario) throws Exception {
		try
		{
			session.delete(usuario);
		}
		catch (Exception e)
		{
			throw e;
		}

	}

	/* (non-Javadoc)
	 * @see es.jma.reservas.core.daos.IUsuarioDAO#consultarTodosUsuarios()
	 */
	@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public List<Usuario> consultarTodosUsuarios() throws Exception {
		List<Usuario> usuarios = new ArrayList<Usuario> ();
		
		try
		{
			usuarios = (List<Usuario>) session
					.createCriteria(Usuario.class)
					.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();
		}
		catch (Exception e)
		{
			throw e;
		}
		
		return usuarios;
	}

	@Override
	@Transactional
	public Usuario consultarUsuarioNombreUsuario(String nombreUsuario) throws Exception {
		Usuario usuario = new Usuario();
		
		try
		{
			//Consultar bar por nombre
			String hql = "from Usuario where email = :email";
			Query query = session.createQuery(hql);
			query.setString("email", nombreUsuario);
			@SuppressWarnings("unchecked")
			List<Usuario> results = query.list();
			
			if ((results != null ) && (results.size() > 0))
			{
				usuario = results.get(0);
			}
		}
		catch (Exception e)
		{
			throw e;
		}
		
		return usuario;
	}

	@Override
	@Transactional
	public boolean comprobarDatosUsuario(String nombreUsuario, String password)
			throws Exception {
		boolean resultado = false;
		
		try
		{
			//Aplicar cifrado al password
			String passwordCifrado=UtilCifrado.cifrarTexto(password);
			
			//Consultar bar por nombre
			String hql = "from Usuario where email = :email and password = :password";
			Query query = session.createQuery(hql);
			query.setString("email", nombreUsuario);
			query.setString("password",passwordCifrado);
			@SuppressWarnings("unchecked")
			List<Usuario> results = query.list();
			
			if ((results != null ) && (results.size() > 0))
			{
				resultado = true;
			}
		}
		catch (Exception e)
		{
			throw e;
		}
		
		return resultado;
	}

}
