package fiuba.taller.actividad;

import java.util.List;

import fiuba.taller.actividad.excepcion.XmlErroneoExcepcion;

public class ActividadGrupal extends Actividad {

	protected static final String TIPO_ACTIVIDAD_GRUPAL = "Grupal";

	protected boolean gruposExclusivos;

	public ActividadGrupal() {
		super();
		tipo = TIPO_ACTIVIDAD_GRUPAL;
		gruposExclusivos = false;
	}

	public void agregarGrupo(long idGrupo) {
		/*
		 * TODO Implementar Previamente se debe verificar si el grupo ya existe.
		 * En ese caso, se debe lanzar una excepcion.
		 */
	}

	public void eliminarGrupo(long idGrupo) {
		/*
		 * TODO Implementar Previamente se debe verificar si el grupo existe. En
		 * ese caso de que no exista, se debe lanzar una excepcion.
		 */
	}

	// FIXME Se devuelve, por lo pronto, una lista de IDs de grupo
	public List<Long> getGrupos() {
		// TODO: Implementar
		return null;
	}

	public static boolean esTipoValido(String xml) {
		Actividad actividad = new Actividad();
		try {
			actividad.descerializar(xml);
		} catch (XmlErroneoExcepcion e) {
			return false;
		}
		if (actividad.tipo.equals(TIPO_ACTIVIDAD_GRUPAL)) {
			return true;
		}
		return false;
	}

	public static ActividadGrupal crearInstancia(String xmlPropiedades)
			throws XmlErroneoExcepcion {
		ActividadGrupal actividad = new ActividadGrupal();
		actividad.descerializar(xmlPropiedades);
		// TODO(Pampa) Obtener un ID nuevo
		// actividad.id = nuevoId;
		actividad.tipo = TIPO_ACTIVIDAD_GRUPAL;
		// TODO(Pampa) Validar fecha y lanzar excepcion
		actividad.guardarEstado();
		return actividad;
	}

	public static ActividadGrupal getActividad(long idActividad)
			throws XmlErroneoExcepcion {
		/*
		 * FIXME Si no existe la actividad con el ID especificado, se debe
		 * lanzar la excepcion ActividadInexistenteExcepcion
		 */
		ActividadGrupal actividad = new ActividadGrupal();
		actividad.levantarEstado(idActividad);
		return actividad;
	}
}
