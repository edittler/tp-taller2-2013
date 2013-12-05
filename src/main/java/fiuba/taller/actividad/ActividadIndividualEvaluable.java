package fiuba.taller.actividad;

import java.util.List;

import fiuba.taller.actividad.excepcion.XmlErroneoExcepcion;

public class ActividadIndividualEvaluable extends ActividadIndividual implements
		Evaluable {

	protected static final String TIPO_ACTIVIDAD_INDIVIDUAL_EVALUABLE = TIPO_ACTIVIDAD_INDIVIDUAL
			+ " Evaluable";

	public ActividadIndividualEvaluable() {
		super();
		tipo = TIPO_ACTIVIDAD_INDIVIDUAL_EVALUABLE;
	}

	@Override
	public void evaluar(long idEvaluado, String nota) {
		// TODO Auto-generated method stub

	}

	@Override
	public String getNota(long idParticipante) {
		// TODO Implementar
		return "";
	}

	@Override
	public List<Nota> getNotas() {
		// TODO Implementar
		return null;
	}

	public static boolean esTipoValido(String xml) {
		Actividad actividad = new Actividad();
		try {
			actividad.descerializar(xml);
		} catch (XmlErroneoExcepcion e) {
			return false;
		}
		if (actividad.tipo.equals(TIPO_ACTIVIDAD_INDIVIDUAL_EVALUABLE)) {
			return true;
		}
		return false;
	}

	public static ActividadIndividualEvaluable crearInstancia(
			String xmlPropiedades) throws XmlErroneoExcepcion {
		ActividadIndividualEvaluable actividad = new ActividadIndividualEvaluable();
		actividad.descerializar(xmlPropiedades);
		// TODO(Pampa) Obtener un ID nuevo
		// actividad.id = nuevoId;
		actividad.tipo = TIPO_ACTIVIDAD_INDIVIDUAL_EVALUABLE;
		// TODO(Pampa) Validar fecha y lanzar excepcion
		actividad.guardarEstado();
		return actividad;
	}

	public static ActividadIndividualEvaluable getActividad(long idActividad)
			throws XmlErroneoExcepcion {
		ActividadIndividualEvaluable actividad = new ActividadIndividualEvaluable();
		actividad.levantarEstado(idActividad);
		return actividad;
	}
}
