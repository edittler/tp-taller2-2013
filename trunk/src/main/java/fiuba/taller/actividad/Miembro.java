package fiuba.taller.actividad;

import fiuba.taller.actividad.excepcion.XmlErroneoExcepcion;

public class Miembro implements Serializable {

	private long id;
	private String nombre;
	
	private Miembro() {
		id = -1;
		nombre = "";
	}
	
	public long getId() {
		return id;
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
	
	public static Miembro getMiembro(long idMiembro) {
		Miembro miembro = new Miembro();
		miembro.id = idMiembro;
		/*
		 * TODO Realizar la consulta de datos de miembro a Participacion y 
		 * completar los datos restantes (nombre)
		 */
		return miembro;
	}
}
