package fiuba.taller.actividad;

import java.util.List;

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

	public Nota getNota(long idParticipante) {
		// TODO Implementar
		return null;
	}

	public List<Nota> getNotas() {
		// TODO Implementar
		return null;
	}

	public static boolean esTipoValido(String xml) {
		Actividad actividad = new Actividad();
		actividad.descerializar(xml);
		if (actividad.tipo.equals(TIPO_ACTIVIDAD_INDIVIDUAL_EVALUABLE)){
			return true;
		}
		return false;
	}

	public static ActividadIndividualEvaluable crearInstancia(String xmlPropiedades) {
		ActividadIndividualEvaluable actividad = new ActividadIndividualEvaluable();
		actividad.descerializar(xmlPropiedades);
		// TODO(Pampa) Obtener un ID nuevo
		// actividad.id = nuevoId;
		actividad.tipo = TIPO_ACTIVIDAD_INDIVIDUAL_EVALUABLE;
		// TODO(Pampa) Validar fecha y lanzar excepcion
		actividad.guardarEstado();
		return actividad;
	}

	public static ActividadIndividualEvaluable getActividad(long idActividad) {
		ActividadIndividualEvaluable actividad = new ActividadIndividualEvaluable();
		String xml = actividad.realizarConsulta(idActividad);
		actividad.descerializar(xml);
		return actividad;
	}
}