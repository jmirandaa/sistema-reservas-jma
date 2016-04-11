/**
 * 
 */
package es.jma.reservas.core.daos.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.springframework.transaction.annotation.Transactional;

import es.jma.reservas.core.daos.ISlotDAO;
import es.jma.reservas.core.dominio.citas.Slot;
import es.jma.reservas.core.dominio.negocio.Negocio;

/**
 * @author jmiranda
 *
 */
public class SlotDAOHibImpl extends CrudDAOHibImpl implements ISlotDAO {
	private static SlotDAOHibImpl instance;
	
	//Singleton
	private SlotDAOHibImpl()
	{
		super(Slot.class);
	}
	
	public static SlotDAOHibImpl getInstance()
	{
		if (instance == null)
			
		{
			instance = new SlotDAOHibImpl();
		}
		
		return instance;
	}
	/* (non-Javadoc)
	 * @see es.jma.reservas.core.daos.ISlotDAO#nuevoSlotNegocio(es.jma.reservas.core.dominio.citas.Slot, es.jma.reservas.core.dominio.negocio.Negocio)
	 */
	@Override
	@Transactional
	public void nuevoSlotNegocio(Slot slot, Negocio negocio) throws Exception{
		try
		{
			List <Slot> slots = negocio.getSlots();
			if (slots == null)
			{
				slots = new ArrayList<Slot> ();
				negocio.setSlots(slots);
			}
			negocio.getSlots().add(slot);
			this.nuevo(negocio);
		}
		catch (Exception e)
		{
			throw e;
		}

	}

	/* (non-Javadoc)
	 * @see es.jma.reservas.core.daos.ISlotDAO#consultarSlotsNegocio(es.jma.reservas.core.dominio.negocio.Negocio)
	 */
	@Override
	@Transactional
	public List<Slot> consultarSlotsNegocio(Negocio negocio) throws Exception{
		List<Slot> slots = new ArrayList<Slot> ();
		
		try
		{
			//Consultar clientes por el id del Negocio
			String hql = "SELECT neg.slots from Negocio neg where neg.id = :id";
			Query query = session.createQuery(hql);
			query.setLong("id", negocio.getId());
			@SuppressWarnings("unchecked")
			List<Slot> results = query.list();
			
			if ((results != null ) && (results.size() > 0))
			{
				slots = results;
			}
		}
		catch (Exception e)
		{
			throw e;
		}
		return slots;
	}

}
