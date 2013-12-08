package fiuba.taller.actividad;

import java.util.List;

import fiuba.taller.actividad.excepcion.ParticipanteExistenteExcepcion;
import fiuba.taller.actividad.excepcion.ParticipanteInexistenteExcepcion;
import fiuba.taller.actividad.excepcion.XmlErroneoExcepcion;

public class ActividadIndividual extends Actividad {

	protected static final String TIPO_ACTIVIDAD_INDIVIDUAL = "Individual";

	protected List<Long> idParticipantes;

	public ActividadIndividual() {
		super();
		tipo = TIPO_ACTIVIDAD_INDIVIDUAL;
		idParticipantes = null;
	}

	public List<Miembro> getParticipantes() {
		/*
		 * TODO: Implementar
		 */
		return null;
	}

	public void agregarParticipante(long idParticipante)
			throws ParticipanteExistenteExcepcion {
		/*
		 * TODO Implementar. Si ya existe el participante, lanzar excepcion.
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

	public static boolean esTipoValido(String xml) {
		Actividad actividad = new Actividad();
		try {
			actividad.descerializar(xml);
		} catch (XmlErroneoExcepcion e) {
			return false;
		}
		/*
		 * Si el campo "Tipo" comienza con la palabra "Individual", se 
		 * considerara de tipo valido. 
		 * Es decir, ActividadIndividualEvaluable tambien se considera como 
		 * ActividadIndividual (por ser clase derivada)
		 */
		if (actividad.tipo.startsWith(TIPO_ACTIVIDAD_INDIVIDUAL)) {
			return true;
		}
		return false;
	}

	public static ActividadIndividual crearActividad(String xmlPropiedades)
			throws XmlErroneoExcepcion {
		ActividadIndividual actividad = new ActividadIndividual();
		actividad.descerializar(xmlPropiedades);
		// TODO(Pampa) Obtener un ID nuevo
		// actividad.id = nuevoId;
		actividad.tipo = TIPO_ACTIVIDAD_INDIVIDUAL;
		// TODO(Pampa) Validar fecha y lanzar excepcion
		actividad.guardarEstado();
		return actividad;
	}

	public static ActividadIndividual getActividad(long idActividad)
			throws XmlErroneoExcepcion {
		/*
		 * FIXME Si no existe la actividad con el ID especificado, se debe
		 * lanzar la excepcion ActividadInexistenteExcepcion
		 */
		ActividadIndividual actividad = new ActividadIndividual();
		actividad.levantarEstado(idActividad);
		return actividad;
	}
}
