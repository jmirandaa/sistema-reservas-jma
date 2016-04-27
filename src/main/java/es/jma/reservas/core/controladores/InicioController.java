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
import org.springframework.web.bind.annotation.RequestParam;

import es.jma.reservas.core.dominio.negocio.Negocio;
import es.jma.reservas.core.dominio.usuarios.Usuario;
import es.jma.reservas.core.servicios.IServiciosNegocio;
import es.jma.reservas.core.servicios.IServiciosUsuario;

/**
 * Controlador para la pantalla de inicio
 * @author Jorge Miranda
 *
 */
@Controller
public class InicioController {
	
	@Autowired
	private IServiciosNegocio serviciosNegocio;
	@Autowired
	IServiciosUsuario serviciosUsuario;
	
	/**
	 * Cargar página de inicio
	 * @param model
	 * @param req
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String inicio(Model model, HttpServletRequest req, HttpSession session)
	{
		return KPaginas.INICIO;
	}
	
	/**
	 * Comprobar si es la primera vez que se usa la aplicación o no
	 * @param model
	 * @param req
	 * @param session
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "controladores/comprobarLogin.do", method = RequestMethod.POST)
	public String comprobarLogin(@RequestParam("inputEmail") String usuario, @RequestParam("inputPassword") String pass, @RequestParam(required=false, value="rememberme") boolean recordar, 
			Model model, HttpServletRequest req, HttpSession session)
	{
		String pagina = null;
		
		try
		{
			//Si no hay ningún negocio, es la primera carga
			List <Negocio> negocios = serviciosNegocio.consultarTodos();
			if (negocios.size() == 0)
			{
				pagina = KPaginas.PRIMERA_CARGA;
				//pagina = KPaginas.PANEL_PRINCIPAL;
			}
			else
			{	
				
				boolean datosCorrectos = serviciosUsuario.comprobarDatosUsuario(usuario, pass);
				
				if (datosCorrectos)
				{
					//Recuperar los datos del usuario
					Usuario usuarioSesion = serviciosUsuario.consultarUsuarioNombreUsuario(usuario);
					
					//Guardar el negocio y usuario en la sesión
					session.setAttribute(KAtributos.NEGOCIO, negocios.get(0));
					session.setAttribute(KAtributos.USUARIO, usuarioSesion);
					pagina = KPaginas.PANEL_PRINCIPAL;
				}
				else
				{
					req.setAttribute(KPaginas.Mensajes.KEY_ERROR, KPaginas.Mensajes.MSG_LOGIN_INCORRECTO);
					pagina = KPaginas.INICIO;
				}
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
			req.setAttribute(KPaginas.Mensajes.KEY_ERROR, e.getMessage());
		}
		
		return pagina;
	}
	
	@RequestMapping(value = "controladores/principal.do", method = RequestMethod.GET)
	public String paginaPrincipal(Model model, HttpServletRequest req, HttpSession session)
	{
		return KPaginas.PANEL_PRINCIPAL;
	}
}
