package pruebasimple.dominio;

import java.util.Calendar;

import es.jma.reservas.core.dominio.usuarios.Cliente;
import es.jma.reservas.core.dominio.usuarios.Usuario;
import es.jma.reservas.core.factorias.FactoriaUsuario;

public class TestUsuarios {

	public static void main(String[] args) {
		//Crear usuario
		Usuario usuario = FactoriaUsuario.crearCliente();
		usuario.setId(1);
		usuario.setEmail("PacoPorras");
		usuario.setPassword("caca");
		
		//Crear cliente
		Cliente cliente = new Cliente (usuario);
		cliente.setNombre("Paco");
		cliente.setApellidos("Porras");
		cliente.setDireccion("Calle pepito de los palotes, 666");
		cliente.setDni("66666666Z");
		cliente.setFechaNacimiento(Calendar.getInstance().getTime());

	}

}
