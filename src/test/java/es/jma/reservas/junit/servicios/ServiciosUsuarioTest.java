/**
 * 
 */
package es.jma.reservas.junit.servicios;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.web.WebAppConfiguration;

import es.jma.reservas.core.config.ApplicationContextConfig;
import es.jma.reservas.core.dominio.usuarios.Usuario;
import es.jma.reservas.core.servicios.IServiciosUsuario;

/**
 * Test jUnit del servicio de Usuario
 * @author Jorge Miranda
 *
 */
@WebAppConfiguration
@ContextConfiguration(classes = ApplicationContextConfig.class)
public class ServiciosUsuarioTest extends AbstractJUnit4SpringContextTests{

	@Autowired
	IServiciosUsuario serviciosUsuario;
	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		
	}

	/**
	 * Usuario de prueba
	 * @return
	 */
	private Usuario nuevoUsuario()
	{
		Usuario usuario = new Usuario();
		usuario.setNivel(Usuario.NIVEL_EMPLEADO);
		usuario.setEmail("jameskirk");
		usuario.setPassword("capitan");
		return usuario;
	}
	
	@Test
	/**
	 * Probar nuevo usuario
	 */
	public void testCRUDUsuario() {
		//Crear usuario
		Usuario usuario = nuevoUsuario();
		
		try
		{
			//Añadir usuario
			serviciosUsuario.nuevo(usuario);
			
			//Consultar y comprobar coincidencia
			Usuario consultado = serviciosUsuario.consultarUsuarioNombreUsuario(usuario.getEmail());
			assertEquals(usuario, consultado);
			
			//Modificar
			consultado.setEmail("spock");
			consultado.setNivel(Usuario.NIVEL_EMPLEADO);
			consultado.setPassword("vulcaniano");
			
			serviciosUsuario.actualizar(consultado);
			//Consultar
			Usuario consultadoAct = serviciosUsuario.consultarUsuarioNombreUsuario(consultado.getEmail());
			assertEquals(consultadoAct, consultado);
			
			//Borrar
			serviciosUsuario.borrar(consultado);
			Usuario consultadoBorrado = serviciosUsuario.consultarUsuarioNombreUsuario(consultado.getEmail());
			assertNotEquals(consultadoBorrado, consultado);
		}
		catch (Exception e)
		{
			fail("Error en el servicio");
		}

	}

}
