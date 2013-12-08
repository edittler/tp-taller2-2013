package fiuba.taller.actividad;

import fiuba.taller.actividad.excepcion.XmlErroneoExcepcion;

public class Miembro implements Serializable {

	private String username;
	private String nombre;
	
	private Miembro() {
		username = "";
		nombre = "";
	}
	
	public String getUsername() {
		return username;
	}
	
	public String getNombre() {
		return nombre;
	}
	
	@Override
	public String serializar() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void descerializar(String xml) throws XmlErroneoExcepcion {
		// TODO Auto-generated method stub
	}
	
	public static Miembro getMiembro(String username) {
		Miembro miembro = new Miembro();
		miembro.username = username;
		/*
		 * TODO Realizar la consulta de datos de miembro a Participacion y 
		 * completar los datos restantes (nombre)
		 */
		return miembro;
	}
}
