package fiuba.taller.actividad;

public class ActividadIndividual extends Actividad {

	protected static final String TIPO_ACTIVIDAD_INDIVIDUAL = "Individual";

	public ActividadIndividual() {
		super();
		tipo = TIPO_ACTIVIDAD_INDIVIDUAL;
	}

	public boolean agregarParticipante(long idParticipante) {
		// TODO Implementar
		return true;
	}

	public static boolean esTipoValido(String xml) {
		/*
		 * TODO Implementar Se debe verificar que el tipo almacenado en el xml
		 * contiene a TIPO_ACTIVIDAD_INDIVIDUAL. No precisamente debe ser igual,
		 * ya que pueden haber clases hijas, como ActividadIndividualEvaluable.
		 */
		return true;
	}

	public static ActividadIndividual getActividad(long idActividad) {
		ActividadIndividual actividad = new ActividadIndividual();
		actividad.setId(idActividad);
		actividad.levantarEstado();
		return actividad;
	}
}
