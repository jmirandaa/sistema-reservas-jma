/**
 * 
 */
package es.jma.reservas.core.config;

import java.util.Properties;

import javax.sql.DataSource;

import org.apache.commons.dbcp2.BasicDataSource;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.hibernate4.HibernateTransactionManager;
import org.springframework.orm.hibernate4.LocalSessionFactoryBuilder;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

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
import es.jma.reservas.core.servicios.impl.ServiciosCita;
import es.jma.reservas.core.servicios.impl.ServiciosCliente;
import es.jma.reservas.core.servicios.impl.ServiciosEmpleado;
import es.jma.reservas.core.servicios.impl.ServiciosHorario;
import es.jma.reservas.core.servicios.impl.ServiciosNegocio;
import es.jma.reservas.core.servicios.impl.ServiciosServicio;
import es.jma.reservas.core.servicios.impl.ServiciosSlot;
import es.jma.reservas.core.servicios.impl.ServiciosUsuario;

/**
 * Clase de configuración del contexto de Spring
 * @author Jorge Miranda
 *
 */
@Configuration
@ComponentScan("es.jma.reservas")
@EnableWebMvc
@EnableTransactionManagement
public class ApplicationContextConfig extends WebMvcConfigurerAdapter{
	/**
	 * Ubicación de recursos estáticos
	 */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry
            .addResourceHandler("/resources/**")
            .addResourceLocations("/resources/")
        ;
    }
    
	/**
	 * Ubicación de los jsp
	 * @return
	 */
	@Bean(name = "viewResolver")
    public InternalResourceViewResolver getViewResolver() {
        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
        viewResolver.setPrefix("/WEB-INF/views/");
        viewResolver.setSuffix(".jsp");
        return viewResolver;
    }
     
    /**
     * Configuración de la base de datos
     * @return
     */
    @Bean(name = "dataSource")
    public DataSource getDataSource() {
    	BasicDataSource dataSource = new BasicDataSource();
    	dataSource.setDriverClassName("com.mysql.jdbc.Driver");
    	dataSource.setUrl("jdbc:mysql://dir:3306/SistemaReservas");
    	dataSource.setUsername("username");
    	dataSource.setPassword("password");
    	
    	return dataSource;
    }
    
    /**
     * Propiedades de Hibernate
     * @return
     */
    private Properties getHibernateProperties() {
    	Properties properties = new Properties();
    	properties.put("hibernate.show_sql", "true");
    	properties.put("hibernate.dialect", "org.hibernate.dialect.MySQLDialect");
    	properties.put("hibernate.hbm2ddl.auto", "create");
    	return properties;
    }
    
    /**
     * Clases a buscar por Hibernate
     * @param dataSource
     * @return
     */
    @Autowired
    @Bean(name = "sessionFactory")
    public SessionFactory getSessionFactory(DataSource dataSource) {
    	LocalSessionFactoryBuilder sessionBuilder = new LocalSessionFactoryBuilder(dataSource);
    	sessionBuilder.addProperties(getHibernateProperties());
    	sessionBuilder.addAnnotatedClasses(Cita.class);
    	sessionBuilder.addAnnotatedClasses(Horario.class);
    	sessionBuilder.addAnnotatedClasses(Slot.class);
    	sessionBuilder.addAnnotatedClasses(Negocio.class);
    	sessionBuilder.addAnnotatedClasses(Servicio.class);
    	sessionBuilder.addAnnotatedClasses(AbstractPersona.class);
    	sessionBuilder.addAnnotatedClasses(Cliente.class);
    	sessionBuilder.addAnnotatedClasses(Empleado.class);
    	sessionBuilder.addAnnotatedClasses(Usuario.class);
    	return sessionBuilder.buildSessionFactory();
    }
    
	@Autowired
	@Bean(name = "transactionManager")
	public HibernateTransactionManager getTransactionManager(
			SessionFactory sessionFactory) {
		HibernateTransactionManager transactionManager = new HibernateTransactionManager(
				sessionFactory);

		return transactionManager;
	}
	
    @Autowired
    @Bean(name = "serviciosUsuario")
    public IServiciosUsuario getIServiciosBar(SessionFactory sessionFactory) {
    	ServiciosUsuario serviciosUsuario = ServiciosUsuario.getInstance(sessionFactory);
    	return serviciosUsuario;
    }
    
    @Autowired
    @Bean(name = "serviciosNegocio")
    public IServiciosNegocio getIServiciosNegocio(SessionFactory sessionFactory) {
    	ServiciosNegocio serviciosNegocio = ServiciosNegocio.getInstance(sessionFactory);
    	return serviciosNegocio;
    }
    
    @Autowired
    @Bean(name = "serviciosCliente")
    public IServiciosCliente getIServiciosCliente(SessionFactory sessionFactory) {
    	ServiciosCliente serviciosCliente = ServiciosCliente.getInstance(sessionFactory);
    	return serviciosCliente;
    }
    
    @Autowired
    @Bean(name = "serviciosEmpleado")
    public IServiciosEmpleado getIServiciosEmpleado(SessionFactory sessionFactory) {
    	ServiciosEmpleado serviciosEmpleado = ServiciosEmpleado.getInstance(sessionFactory);
    	return serviciosEmpleado;
    }
    
    @Autowired
    @Bean(name = "serviciosServicios")
    public IServiciosServicios getIServiciosServicio(SessionFactory sessionFactory) {
    	ServiciosServicio serviciosServicios = ServiciosServicio.getInstance(sessionFactory);
    	return serviciosServicios;
    }
    
    @Autowired
    @Bean(name = "serviciosSlot")
    public IServiciosSlot getIServiciosSlot(SessionFactory sessionFactory) {
    	ServiciosSlot serviciosSlot = ServiciosSlot.getInstance(sessionFactory);
    	return serviciosSlot;
    }
    
    @Autowired
    @Bean(name = "serviciosHorario")
    public IServiciosHorario getIServiciosHorario(SessionFactory sessionFactory) {
    	ServiciosHorario serviciosHorario = ServiciosHorario.getInstance(sessionFactory);
    	return serviciosHorario;
    }
    
    @Autowired
    @Bean(name = "serviciosCita")
    public IServiciosCita getIServiciosCita(SessionFactory sessionFactory) {
    	ServiciosCita serviciosCita = ServiciosCita.getInstance(sessionFactory);
    	return serviciosCita;
    }
}
