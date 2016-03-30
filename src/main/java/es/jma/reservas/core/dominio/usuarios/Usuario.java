/**
 * 
 */
package es.jma.reservas.core.dominio.usuarios;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author tulon
 *
 */
@Entity
@Table(name = "CORE_USUARIOS")
public class Usuario implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 2616763098034434580L;
	//Constantes asociadas
	public static final int NIVEL_ADMIN = 0;
	public static final int NIVEL_EMPLEADO = 1;
	public static final int NIVEL_CLIENTE = 2;
	
	//Atributos
	@Id
	@GeneratedValue
	private long id;
	private String nombreUsuario;
	private String password;
	private int nivel;
	
	//Constructores
	public Usuario()
	{
		
	}
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getNombreUsuario() {
		return nombreUsuario;
	}

	public void setNombreUsuario(String nombreUsuario) {
		this.nombreUsuario = nombreUsuario;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getNivel() {
		return nivel;
	}

	public void setNivel(int nivel) {
		this.nivel = nivel;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (id ^ (id >>> 32));
		result = prime * result + nivel;
		result = prime * result
				+ ((nombreUsuario == null) ? 0 : nombreUsuario.hashCode());
		result = prime * result
				+ ((password == null) ? 0 : password.hashCode());
		return result;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Usuario other = (Usuario) obj;
		if (id != other.id)
			return false;
		if (nivel != other.nivel)
			return false;
		if (nombreUsuario == null) {
			if (other.nombreUsuario != null)
				return false;
		} else if (!nombreUsuario.equals(other.nombreUsuario))
			return false;
		if (password == null) {
			if (other.password != null)
				return false;
		} else if (!password.equals(other.password))
			return false;
		return true;
	}
	
	
}
