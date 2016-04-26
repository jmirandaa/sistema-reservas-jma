/**
 * 
 */
package es.jma.reservas.core.controladores;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import es.jma.reservas.core.dominio.citas.Slot;
import es.jma.reservas.core.dominio.negocio.Negocio;
import es.jma.reservas.core.dominio.negocio.NegocioAjax;
import es.jma.reservas.core.dominio.respuestas.RespuestaAjax;
import es.jma.reservas.core.dominio.usuarios.AbstractPersona;
import es.jma.reservas.core.dominio.usuarios.Empleado;
import es.jma.reservas.core.dominio.usuarios.Usuario;
import es.jma.reservas.core.servicios.IServiciosEmpleado;
import es.jma.reservas.core.servicios.IServiciosNegocio;
import es.jma.reservas.core.utils.UtilCifrado;

/**
 * Controlador de peticiones AJAX
 * @author Jorge Miranda
 *
 */
@Controller
public class AjaxController {
	@Autowired
	IServiciosNegocio serviciosNegocio;
	@Autowired
	IServiciosEmpleado serviciosEmpleado;

	/**
	 * Petición de nuevo negocio
	 * @param negocio
	 * @return
	 */
	@RequestMapping(value = "controladores/ajax/negocio/nuevoNegocio", method = RequestMethod.POST)
	@ResponseBody
	public RespuestaAjax nuevoNegocioAjax(@RequestBody NegocioAjax negocio) {
		RespuestaAjax respuesta = new RespuestaAjax();
		
		try
		{
			//Comprobar que tiene usuario y empleado
			List <Empleado> empleados = negocio.getPersonas();
			if ((empleados == null) || (empleados.isEmpty()))
			{
				respuesta.setCodRespuesta(1);
				respuesta.setRespuesta("No hay administrador");
			}
			else
			{
				Empleado empleado = empleados.get(0);
				Usuario usuario = empleado.getUsuario();
				if ((usuario == null) || (usuario.getEmail() == null) || (usuario.getPassword() == null))
				{
					respuesta.setCodRespuesta(2);
					respuesta.setRespuesta("No hay datos de usuario");
				}
				else
				{
					//Cifrar password
					
					
					//Completar estructura
					usuario.setNivel(Usuario.NIVEL_ADMIN);
					String password = UtilCifrado.cifrarTexto(usuario.getPassword());
					usuario.setPassword(password);
					
					Negocio neg = new Negocio();
					neg.setDireccion(negocio.getDireccion());
					neg.setNombre(negocio.getNombre());
					neg.setSlots(new ArrayList<Slot> ());
					neg.setSlotsFijos(false);
					neg.setTelefono(negocio.getTelefono());
					neg.setTipo(negocio.getTipo());
					neg.setUrl(negocio.getUrl());			
					
					List <AbstractPersona> personas = new ArrayList <AbstractPersona> ();
					personas.add(empleado);
					neg.setPersonas(personas);
					//Añadir negocio
					serviciosNegocio.nuevo(neg);
					
					//Indicar OK
					respuesta.setCodRespuesta(0);
					respuesta.setRespuesta("OK");
					
				}
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
			respuesta.setCodRespuesta(9);
			respuesta.setRespuesta(e.getMessage());
		}
		
		return respuesta;

	}
}
