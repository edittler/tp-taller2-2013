package fiuba.taller.actividad;

import java.util.List;

import fiuba.taller.actividad.excepcion.NotaInexistenteExcepcion;
import fiuba.taller.actividad.excepcion.XmlErroneoExcepcion;

public class ActividadIndividualEvaluable extends ActividadIndividual implements
		Evaluable {

	protected static final String TIPO_ACTIVIDAD_INDIVIDUAL_EVALUABLE = TIPO_ACTIVIDAD_INDIVIDUAL
			+ " Evaluable";
	protected String escala;

	public ActividadIndividualEvaluable() {
		super();
		tipo = TIPO_ACTIVIDAD_INDIVIDUAL_EVALUABLE;
		escala = "";
	}

	@Override
	public String getEscala() {
		return escala;
	}

	@Override
	public void setEscala(String escala) {
		this.escala = escala;
	}

	@Override
	public void evaluar(Object usernameParticipante, String nota) {
		String username = (String) usernameParticipante;
		NotaIndividual notaIndividual = NotaIndividual.crearNota(id, username);
		notaIndividual.setValor(nota);
	}

	@Override
	public void evaluar(Object usernameParticipante, String nota,
			String observaciones) {
		String username = (String) usernameParticipante;
		NotaIndividual notaIndividual = NotaIndividual.crearNota(id, username);
		notaIndividual.setValor(nota);
		notaIndividual.setObservaciones(observaciones);
	}

	@Override
	public Nota getNota(Object usernameParticipante)
			throws NotaInexistenteExcepcion {
		String username = (String) usernameParticipante;
		NotaIndividual nota = NotaIndividual.getNota(id, username);
		return nota;
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
		if (actividad.tipo.startsWith(TIPO_ACTIVIDAD_INDIVIDUAL_EVALUABLE)) {
			return true;
		}
		return false;
	}

	public static ActividadIndividualEvaluable crearActividad(
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
	
	protected String serializarInterno() {
		return super.serializarInterno() + "<TipoEscala>" + escala
				+ " </TipoEscala>";
	}
}
