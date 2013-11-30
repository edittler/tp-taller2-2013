package fiuba.taller.actividad;

public class ActividadIndividualEvaluable extends ActividadIndividual implements
		IEvaluable {

	protected static final String TIPO_ACTIVIDAD_INDIVIDUAL_EVALUABLE = TIPO_ACTIVIDAD_INDIVIDUAL
			+ " Evaluable";

	public ActividadIndividualEvaluable() {
		super();
		tipo = TIPO_ACTIVIDAD_INDIVIDUAL_EVALUABLE;
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
		 * contiene a TIPO_ACTIVIDAD_INDIVIDUAL_EVALUABLE. No precisamente debe
		 * ser igual, ya que pueden haber clases hijas.
		 */
		return true;
	}

	public static ActividadIndividualEvaluable getActividad(long idActividad) {
		ActividadIndividualEvaluable actividad = new ActividadIndividualEvaluable();
		String xml = actividad.getXml(idActividad);
		actividad.descerializar(xml);
		return actividad;
	}
}
