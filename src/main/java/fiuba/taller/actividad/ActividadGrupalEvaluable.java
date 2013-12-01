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
		Actividad actividad = new Actividad();
		actividad.descerializar(xml);
		if (actividad.tipo.contains((CharSequence) TIPO_ACTIVIDAD_GRUPAL_EVALUABLE))
			return true;
		return false;
	}

	public static ActividadGrupalEvaluable crearInstancia(String xmlPropiedades) {
		ActividadGrupalEvaluable actividad = new ActividadGrupalEvaluable();
		actividad.descerializar(xmlPropiedades);
		// TODO(Pampa) Obtener un ID nuevo
		// actividad.id = nuevoId;
		actividad.tipo = TIPO_ACTIVIDAD_GRUPAL_EVALUABLE;
		// TODO(Pampa) Validar fecha y lanzar excepcion
		actividad.guardarEstado();
		return actividad;
	}

	public static ActividadGrupalEvaluable getActividad(long idActividad) {
		ActividadGrupalEvaluable actividad = new ActividadGrupalEvaluable();
		String xml = actividad.getXml(idActividad);
		actividad.descerializar(xml);
		return actividad;
	}
}
