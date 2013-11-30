package fiuba.taller.actividad;

public class ActividadGrupalEvaluable extends ActividadGrupal implements
		IEvaluable {

	protected static final String TIPO_ACTIVIDAD_GRUPAL_EVALUABLE = TIPO_ACTIVIDAD_GRUPAL
			+ " Evaluable";

	public ActividadGrupalEvaluable() {
		super();
		tipo = TIPO_ACTIVIDAD_GRUPAL_EVALUABLE;
	}

	public void evaluar(long idEvaluado, String nota) {
		// TODO Implementar
	}

	public String getNota(long idParticipante) {
		// TODO Implementar
		return null;
	}

	public String getNotas() {
		// TODO Implementar
		return null;
	}

	public static boolean esTipoValido(String xml) {
		/*
		 * TODO Implementar Se debe verificar que el tipo almacenado en el xml
		 * contiene a TIPO_ACTIVIDAD_GRUPAL_EVALUABLE. No precisamente debe ser
		 * igual, ya que pueden haber clases hijas.
		 */
		return true;
	}

	public static ActividadGrupalEvaluable getActividad(long idActividad) {
		ActividadGrupalEvaluable actividad = new ActividadGrupalEvaluable();
		String xml = actividad.getXml(idActividad);
		actividad.descerializar(xml);
		return actividad;
	}
}
