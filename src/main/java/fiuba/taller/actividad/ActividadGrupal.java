package fiuba.taller.actividad;

import java.util.List;

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
		actividad.descerializar(xml);
		if (actividad.tipo.contains((CharSequence) TIPO_ACTIVIDAD_GRUPAL))
			return true;
		return false;
	}

	public static ActividadGrupalEvaluable crearInstancia(String xmlPropiedades) {
		ActividadGrupalEvaluable actividad = new ActividadGrupalEvaluable();
		actividad.descerializar(xmlPropiedades);
		// TODO(Pampa) Obtener un ID nuevo
		// actividad.id = nuevoId;
		actividad.tipo = TIPO_ACTIVIDAD_GRUPAL;
		// TODO(Pampa) Validar fecha y lanzar excepcion
		actividad.guardarEstado();
		return actividad;
	}

	public static ActividadGrupal getActividad(long idActividad) {
		ActividadGrupal actividad = new ActividadGrupal();
		String xml = actividad.getXml(idActividad);
		actividad.descerializar(xml);
		return actividad;
	}
}
