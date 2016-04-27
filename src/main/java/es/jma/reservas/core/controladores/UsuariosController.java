/**
 * 
 */
package es.jma.reservas.core.controladores;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import es.jma.reservas.core.dominio.negocio.Negocio;
import es.jma.reservas.core.dominio.usuarios.Empleado;
import es.jma.reservas.core.servicios.IServiciosEmpleado;

/**
 * Controlador de usuarios
 * @author Jorge Miranda
 *
 */
@Controller
public class UsuariosController {
	@Autowired
	IServiciosEmpleado serviciosEmpleado;
	/**
	 * Cargar listado de empleados
	 * @param model
	 * @param req
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "controladores/empleados.do", method = RequestMethod.GET)
	public String paginaPrincipal(Model model, HttpServletRequest req, HttpSession session)
	{
		try
		{
			//Recuperar datos de negocio
			Negocio negocio = (Negocio) session.getAttribute(KAtributos.NEGOCIO);
			
			//Cargar todos los empleados del negocio
			List<Empleado> empleados = serviciosEmpleado.consultarEmpleadosNegocio(negocio);

			//Pasarlo a la solicitud
			req.setAttribute(KAtributos.EMPLEADOS, empleados);
					
		}
		catch (Exception e)
		{
			e.printStackTrace();
			req.setAttribute(KPaginas.Mensajes.KEY_ERROR, e.getMessage());
		}

		//Redireccionar		
		return KPaginas.PANEL_EMPLEADOS;
	}
}
