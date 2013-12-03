package fiuba.taller.actividad;

import java.util.List;

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
	
	public List<Long> getParticipantes() {
		/* TODO: Implementar
		* Agregar IDs de participantes (ver si hay que crear clase y
		* agregar más información)
		*/
		return null;
	}

	/* METODOS DE CLASE (ESTATICOS) */

	public static boolean esTipoValido(String xml) {
		Actividad actividad = new Actividad();
		try {
			actividad.descerializar(xml);
		} catch (XmlErroneoExcepcion e) {
			return false;
		}
		if (actividad.tipo.equals(TIPO_ACTIVIDAD_INDIVIDUAL)){
			return true;
		}
		return false;
	}

	public static ActividadIndividual crearInstancia(String xmlPropiedades)
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
