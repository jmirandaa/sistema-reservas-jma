/**
 * 
 */
package es.jma.reservas.core.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

/**
 * Clase de configuración para Spring MVC
 * @author Jorge Miranda
 *
 */
@Configuration
@EnableWebMvc
@ComponentScan(basePackages = "es.jma.reservas")
public class SpringConfiguration {

}
