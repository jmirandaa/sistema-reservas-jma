/**
 * 
 */
package es.jma.reservas.pruebasimple.servicios;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import es.jma.reservas.core.dominio.usuarios.Usuario;
import es.jma.reservas.core.servicios.IServiciosUsuario;

/**
 * @author jmiranda
 *
 */
@Controller
public class TestUsuariosRest {
	@Autowired
	IServiciosUsuario serviciosUsuario;
	
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
}
