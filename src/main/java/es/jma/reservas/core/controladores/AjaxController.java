/**
 * 
 */
package es.jma.reservas.core.controladores;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import es.jma.reservas.core.dominio.negocio.Negocio;
import es.jma.reservas.core.dominio.respuestas.RespuestaAjax;

/**
 * Controlador de peticiones AJAX
 * @author Jorge Miranda
 *
 */
@Controller
public class AjaxController {

	@RequestMapping(value = "controladores/ajax/negocio/nuevoNegocio", method = RequestMethod.POST)
	@ResponseBody
	public RespuestaAjax nuevoNegocioAjax(@RequestBody Negocio negocio) {
		RespuestaAjax respuesta = new RespuestaAjax();
		respuesta.setCodRespuesta(1);
		respuesta.setRespuesta("Me apetece fallar");
		
		return respuesta;

	}
}
