/**
 * 
 */
package es.jma.reservas.core.dominio.citas;

import java.util.Date;

/**
 * @author jmiranda
 *
 */
public class Franja {
	//Atributos
	private Date horaIni;
	private Date horaFin;
	
	//Constructores
	public Franja()
	{
		
	}

	/**
	 * @return the horaIni
	 */
	public Date getHoraIni() {
		return horaIni;
	}

	/**
	 * @param horaIni the horaIni to set
	 */
	public void setHoraIni(Date horaIni) {
		this.horaIni = horaIni;
	}

	/**
	 * @return the horaFin
	 */
	public Date getHoraFin() {
		return horaFin;
	}

	/**
	 * @param horaFin the horaFin to set
	 */
	public void setHoraFin(Date horaFin) {
		this.horaFin = horaFin;
	}
	
	
}
