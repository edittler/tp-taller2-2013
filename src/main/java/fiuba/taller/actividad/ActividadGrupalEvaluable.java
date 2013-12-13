package fiuba.taller.actividad;

import java.util.List;

import fiuba.taller.actividad.excepcion.NotaInexistenteExcepcion;
import fiuba.taller.actividad.excepcion.XmlErroneoExcepcion;

public class ActividadGrupalEvaluable extends ActividadGrupal implements
		Evaluable {

	protected static final String TIPO_ACTIVIDAD_GRUPAL_EVALUABLE = TIPO_ACTIVIDAD_GRUPAL
			+ " Evaluable";
	protected String escala;

	public ActividadGrupalEvaluable() {
		super();
		tipo = TIPO_ACTIVIDAD_GRUPAL_EVALUABLE;
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
	public void evaluar(Object idGrupo, String nota) {
		long longGrupo = (long) idGrupo;
		NotaGrupal notaGrupal = NotaGrupal.crearNota(id, longGrupo);
		notaGrupal.setValor(nota);
	}

	@Override
	public void evaluar(Object idGrupo, String nota, String observaciones) {
		long longGrupo = (long) idGrupo;
		NotaGrupal notaGrupal = NotaGrupal.crearNota(id, longGrupo);
		notaGrupal.setValor(nota);
		notaGrupal.setObservaciones(observaciones);
	}

	@Override
	public Nota getNota(Object idGrupo) throws NotaInexistenteExcepcion {
		long longGrupo = (long) idGrupo;
		NotaGrupal nota = NotaGrupal.getNota(id, longGrupo);
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
		if (actividad.tipo.startsWith(TIPO_ACTIVIDAD_GRUPAL_EVALUABLE)) {
			return true;
		}
		return false;
	}

	public static ActividadGrupalEvaluable crearActividad(String xmlPropiedades)
			throws XmlErroneoExcepcion {
		ActividadGrupalEvaluable actividad = new ActividadGrupalEvaluable();
		actividad.descerializar(xmlPropiedades);
		// TODO(Pampa) Obtener un ID nuevo
		// actividad.id = nuevoId;
		actividad.tipo = TIPO_ACTIVIDAD_GRUPAL_EVALUABLE;
		// TODO(Pampa) Validar fecha y lanzar excepcion
		actividad.guardarEstado();
		return actividad;
	}

	public static ActividadGrupalEvaluable getActividad(long idActividad)
			throws XmlErroneoExcepcion {
		/*
		 * FIXME Si no existe la actividad con el ID especificado, se debe
		 * lanzar la excepcion ActividadInexistenteExcepcion
		 */
		ActividadGrupalEvaluable actividad = new ActividadGrupalEvaluable();
		actividad.levantarEstado(idActividad);
		return actividad;
	}
	
	protected String serializarInterno() {
		return super.serializarInterno() + "<TipoEscala>" + escala
				+ "</TipoEscala>";
	}
}
