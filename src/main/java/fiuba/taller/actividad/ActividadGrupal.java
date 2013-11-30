package fiuba.taller.actividad;

public class ActividadGrupal extends Actividad {

	protected static final String TIPO_ACTIVIDAD_GRUPAL = "Grupal";

	public ActividadGrupal() {
		super();
		tipo = TIPO_ACTIVIDAD_GRUPAL;
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

	// FIXME Se debe devolver algo. ¿un XML?
	public void getGrupos() {

	}

	public static boolean esTipoValido(String xml) {
		/*
		 * TODO Implementar Se debe verificar que el tipo almacenado en el xml
		 * contiene a TIPO_ACTIVIDAD_GRUPAL. No precisamente debe ser igual, ya
		 * que pueden haber clases hijas, como ActividadGrupalEvaluable.
		 */
		return true;
	}

	public static ActividadGrupal getActividad(long idActividad) {
		ActividadGrupal actividad = new ActividadGrupal();
		String xml = actividad.getXml(idActividad);
		actividad.descerializar(xml);
		return actividad;
	}
}
