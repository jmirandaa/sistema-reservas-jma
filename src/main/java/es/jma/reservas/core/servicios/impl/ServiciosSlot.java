package es.jma.reservas.core.servicios.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import es.jma.reservas.core.daos.ICrudDAO;
import es.jma.reservas.core.daos.ISlotDAO;
import es.jma.reservas.core.daos.impl.SlotDAOHibImpl;
import es.jma.reservas.core.dominio.citas.Slot;
import es.jma.reservas.core.dominio.negocio.Negocio;
import es.jma.reservas.core.servicios.IServiciosSlot;

public class ServiciosSlot extends ServiciosHibernate implements IServiciosSlot {
	private static ServiciosSlot instance;
	private ISlotDAO slotDAO;
	
	//Slot
	private ServiciosSlot(SessionFactory sessionFactory)
	{
		super(sessionFactory);
		slotDAO = SlotDAOHibImpl.getInstance();
	}
	
	public static ServiciosSlot getInstance(SessionFactory sessionFactory)
	{
		if (instance == null)
		{
			instance = new ServiciosSlot(sessionFactory);
		}
		
		return instance;
	}
	
	@Override
	public void nuevoSlotNegocio(Slot slot, Negocio negocio) throws Exception {
		Session session = null;
    	Transaction tx = null;
    	
		try
		{
			if (slot != null)
			{
				if (txEnabled)
				{
					session = sessionFactory.openSession();
					tx = session.beginTransaction();
					slotDAO.setSession(session);
				}
				else
				{
					if (slotDAO.getSession() == null)
					{
						session = sessionFactory.openSession();
						tx = session.beginTransaction();
						slotDAO.setSession(session);
					}
				}
				
				//Guardar
				slotDAO.nuevoSlotNegocio(slot, negocio);
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

	@Override
	public List<Slot> consultarSlotsNegocio(Negocio negocio) throws Exception {
		List <Slot> resultado = new ArrayList <Slot> ();
		Session session = null;
		
		try
		{
			session = sessionFactory.openSession();				
			getCrudDAO().setSession(session);
			
			//Hacer la consulta
			resultado = slotDAO.consultarSlotsNegocio(negocio);		
		}
		catch(Exception e)
		{
			throw e;
		}
		finally
		{
			cerrarSesion(session);
		}
		
		return resultado;
	}

	@Override
	public Session getSession() {
		return slotDAO.getSession();
	}

	@Override
	public void setSession(Session session) {
		slotDAO.setSession(session);

	}

	@Override
	protected ICrudDAO getCrudDAO() {
		return slotDAO;
	}

}
