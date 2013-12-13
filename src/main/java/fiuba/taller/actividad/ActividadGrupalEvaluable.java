package fiuba.taller.actividad;

import java.util.List;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

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

	@Override
	public void actualizar(String xml) throws XmlErroneoExcepcion {
		ActividadGrupalEvaluable actividadTemporal = new ActividadGrupalEvaluable();
		actividadTemporal.descerializar(xml);
		super.actualizar(actividadTemporal);
		if (actividadTemporal.getEscala().length() > 0) {
			setEscala(actividadTemporal.getEscala());
		}
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

	
	// Para el caso de las evaluables, se agrega el Tag "TipoEscala"
	@Override
	protected void descerializar(Document doc) throws XmlErroneoExcepcion {
		super.descerializar(doc);
		NodeList nodes = doc.getElementsByTagName("Actividad");
		if (nodes.getLength() != 1) {
			throw new 
			XmlErroneoExcepcion("Debe haber solamente un nodo Actividad");
		}
		Element element = (Element) nodes.item(0);
		this.escala = getValue("TipoEscala", element);
	}
	
}
