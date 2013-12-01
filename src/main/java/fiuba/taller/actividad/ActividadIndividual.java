package fiuba.taller.actividad;


import fiuba.taller.actividad.excepcion.ParticipanteInexistenteExcepcion;
import fiuba.taller.actividad.excepcion.XmlErroneoExcepcion;

public class ActividadIndividual extends Actividad {

	protected static final String TIPO_ACTIVIDAD_INDIVIDUAL = "Individual";

	public ActividadIndividual() {
		super();
		tipo = TIPO_ACTIVIDAD_INDIVIDUAL;
	}

	public void agregarParticipante(long idParticipante) {
		/* TODO Implementar.
		 * Si ya existe el participante, no se realiza nada?
		 */
	}

	public void eliminarParticipante(long idParticipante)
			throws ParticipanteInexistenteExcepcion {
		/*
		 * TODO Implementar. Si no existe el participante, se debería lanzar una
		 * excepcion.
		 */
	}

	/* METODOS DE CLASE (ESTATICOS) */

	public static long crearInstancia(String xmlPropiedades)
			throws XmlErroneoExcepcion {
		ActividadIndividual actividad = new ActividadIndividual();
		actividad.descerializar(xmlPropiedades);
		// TODO Obtener un ID nuevo
		// actividad.id = nuevoId;
		return actividad.id;
	}

	public static ActividadIndividual getActividad(long idActividad) {
		ActividadIndividual actividad = new ActividadIndividual();
		actividad.setId(idActividad);
		actividad.levantarEstado();
		return actividad;
	}
}
