/**
 * 
 */
package es.jma.reservas.pruebasimple.servicios;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.common.collect.Lists;

import es.jma.reservas.core.daos.IClienteDAO;
import es.jma.reservas.core.daos.INegocioDAO;
import es.jma.reservas.core.daos.impl.ClienteDAOHibImpl;
import es.jma.reservas.core.daos.impl.NegocioDAOHibImpl;
import es.jma.reservas.core.dominio.citas.Cita;
import es.jma.reservas.core.dominio.citas.Horario;
import es.jma.reservas.core.dominio.citas.Slot;
import es.jma.reservas.core.dominio.negocio.Negocio;
import es.jma.reservas.core.dominio.servicios.Servicio;
import es.jma.reservas.core.dominio.usuarios.AbstractPersona;
import es.jma.reservas.core.dominio.usuarios.Cliente;
import es.jma.reservas.core.dominio.usuarios.Empleado;
import es.jma.reservas.core.dominio.usuarios.Usuario;
import es.jma.reservas.core.servicios.IServiciosCita;
import es.jma.reservas.core.servicios.IServiciosCliente;
import es.jma.reservas.core.servicios.IServiciosEmpleado;
import es.jma.reservas.core.servicios.IServiciosHorario;
import es.jma.reservas.core.servicios.IServiciosNegocio;
import es.jma.reservas.core.servicios.IServiciosServicios;
import es.jma.reservas.core.servicios.IServiciosSlot;
import es.jma.reservas.core.servicios.IServiciosUsuario;

/**
 * @author jmiranda
 *
 */
@Controller
public class TestUsuariosRest {
	@Autowired
	SessionFactory sessionFactory;
	
	@Autowired
	IServiciosUsuario serviciosUsuario;
	@Autowired
	IServiciosNegocio serviciosNegocio;
	@Autowired
	IServiciosCliente serviciosCliente;
	@Autowired
	IServiciosEmpleado serviciosEmpleado;
	@Autowired
	IServiciosServicios serviciosServicio;
	@Autowired
	IServiciosSlot serviciosSlot;
	@Autowired
	IServiciosHorario serviciosHorario;
	@Autowired
	IServiciosCita serviciosCita;
	
	 @RequestMapping(value = "services/testNuevoUsuario", method = RequestMethod.GET,consumes ="*/*",produces = "application/json")
	 @ResponseBody
	 public void testNuevoUsuario(ModelMap model)
	 {
			Usuario usuario = new Usuario();
			usuario.setNivel(Usuario.NIVEL_EMPLEADO);
			usuario.setNombreUsuario("jameskirk");
			usuario.setPassword("capitan");
			
			try
			{
				//Nuevo usuario
				serviciosUsuario.nuevo(usuario);
				
				//Consultar por nombre
				Usuario usuarioConsulta = serviciosUsuario.consultarUsuarioNombreUsuario("jameskirk");
				
				//Modificar
				usuarioConsulta.setPassword("almirante");
				usuarioConsulta.setNivel(Usuario.NIVEL_ADMIN);
				serviciosUsuario.actualizar(usuarioConsulta);
				
				//Borrar
				serviciosUsuario.borrar(usuarioConsulta);
			}
			catch (Exception e)
			{
				e.printStackTrace();
			}
	 }
	 
	 @SuppressWarnings("unchecked")
	@RequestMapping(value = "services/testNuevoNegocio", method = RequestMethod.GET,consumes ="*/*",produces = "application/json")
	 @ResponseBody
	 public void testNuevoNegocio(ModelMap model)
	 {
		 try
		 {
			 Negocio negocio = new Negocio();
			 negocio.setDireccion("calle pepipo palotes");
			 negocio.setNombre("Perruqueria Pepe");
			 negocio.setTelefono("666666666");
			 negocio.setTipo("A saber");
			 negocio.setUrl("notengo");
			 
			 Slot slot1 = new Slot();
			 slot1.setCapacidadMax(4);
			 slot1.setDescripcion("sala de la muelte");
			 
			 List<Slot> slots = new ArrayList<Slot> ();
			 slots.add(slot1);
			 negocio.setSlots(slots);
			 
				Usuario usuario = new Usuario();
				usuario.setNivel(Usuario.NIVEL_EMPLEADO);
				usuario.setNombreUsuario("jameskirk");
				usuario.setPassword("capitan");
				
				Cliente cliente = new Cliente();
				cliente.setUsuario(usuario);
				cliente.setApellidos("Kirk");
				cliente.setDireccion("USS Enterprise A");
				cliente.setDni("12345689N");
				cliente.setFechaNacimiento(new Date());
				cliente.setNombre("James T.");
				
				Usuario usuario2 = new Usuario();
				usuario2.setNivel(Usuario.NIVEL_EMPLEADO);
				usuario2.setNombreUsuario("billAdama");
				usuario2.setPassword("almirante");
				
				Empleado empleado = new Empleado();
				empleado.setUsuario(usuario2);
				empleado.setApellidos("Adama");
				empleado.setDni("33333333L");
				empleado.setFechaNacimiento(new Date());
				empleado.setNombre("William");
				
				List <Servicio> servicios = new ArrayList<Servicio> ();
				
				Servicio servicio = new Servicio();
				servicio.setNombre("Matar");
				servicio.setDuracionMin(40);
				servicio.setCoste(50);
				servicios.add(servicio);
				
				empleado.setServicios(servicios);
				
				 Slot slot2 = new Slot();
				 slot2.setCapacidadMax(3);
				 slot2.setDescripcion("sala de la risa");
				 
				 Horario horario1 = new Horario();
				 horario1.setDescripcion("Lunes mañana semana");
				 horario1.setDiaSemana(0);
				 horario1.setHoraInicio(new Date());
				 horario1.setHoraFin(new Date());
				 
				 Cita cita1 = new Cita();
				 cita1.setCliente(cliente);
				 cita1.setDescripcion("Cita de prueba");
				 cita1.setEmpleado(empleado);
				 cita1.setFechaAlta(new Date());
				 cita1.setFechaCita(new Date());
				 cita1.setNombre("Cita de prueba");
				 cita1.setPagada(false);
				 cita1.setServicio(servicio);
				 cita1.setSlot(slot1);

			 serviciosNegocio.nuevo(negocio);
			 serviciosCliente.guardarClienteNegocio(cliente, negocio);
			 serviciosEmpleado.guardarEmpleadoNegocio(empleado, negocio);
			 serviciosSlot.nuevoSlotNegocio(slot2, negocio);
			 serviciosHorario.guardarHorarioEmpleado(horario1, empleado);
			 serviciosCita.nuevo(cita1);
			 
			 List<Negocio> negocios = serviciosNegocio.consultarTodos();
			 System.out.println("lala");
			 
			 List<Cliente> clientesNeg = serviciosCliente.consultarClientesNegocio(negocio);
			 List<Cita> citasCliente = clientesNeg.get(0).getCitas();
			 System.out.println("ff");
			 
			 List<Empleado> empleadosNeg = serviciosEmpleado.consultarEmpleadosNegocio(negocio);
			 List<Cita> citasEmpleado = empleadosNeg.get(0).getCitas();
			 System.out.println("ffk");
			 
			 List<Servicio> serviciosEmp = serviciosServicio.consultarServiciosEmpleado(empleado);
			 System.out.println("kk");
		 }
		 catch (Exception e)
		 {
			 e.printStackTrace();
		 }
	 }
	 
	 @RequestMapping(value = "services/testNuevoCliente", method = RequestMethod.GET,consumes ="*/*",produces = "application/json")
	 @ResponseBody
	 public void testNuevoCliente(ModelMap model)
	 {
			Usuario usuario = new Usuario();
			usuario.setNivel(Usuario.NIVEL_EMPLEADO);
			usuario.setNombreUsuario("jameskirk");
			usuario.setPassword("capitan");
			
			Cliente cliente = new Cliente();
			cliente.setUsuario(usuario);
			cliente.setApellidos("Kirk");
			cliente.setDireccion("USS Enterprise A");
			cliente.setDni("12345689N");
			cliente.setFechaNacimiento(new Date());
			cliente.setNombre("James T.");
			
			try
			{
				 Session session = sessionFactory.openSession();
				 Transaction tx = session.beginTransaction();
				 
				 IClienteDAO clienteDAO = ClienteDAOHibImpl.getInstance();
				 clienteDAO.setSession(session);
				 clienteDAO.nuevo(cliente);
				 
				 tx.commit();
			}
			catch (Exception e)
			{
				e.printStackTrace();
			}
	 }
}
