package fiuba.taller.actividad;

import java.rmi.RemoteException;
import java.util.List;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

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
	public Nota getNota(Object idGrupo) throws RemoteException {
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
	public void actualizar(String xml) throws RemoteException {
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
		} catch (RemoteException e) {
			return false;
		}
		if (actividad.tipo.startsWith(TIPO_ACTIVIDAD_GRUPAL_EVALUABLE)) {
			return true;
		}
		return false;
	}

	public static ActividadGrupalEvaluable crearActividad(String xmlPropiedades)
			throws RemoteException {
		ActividadGrupalEvaluable actividad = new ActividadGrupalEvaluable();
		actividad.descerializar(xmlPropiedades);
		actividad.setId(-1);
		actividad.tipo = TIPO_ACTIVIDAD_GRUPAL_EVALUABLE;
		// TODO(Pampa) Validar fecha y lanzar excepcion
		actividad.guardarNuevoElemento();
		return actividad;
	}

	public static ActividadGrupalEvaluable getActividad(long idActividad)
			throws RemoteException {
		/*
		 * FIXME Si no existe la actividad con el ID especificado, se debe
		 * lanzar la excepcion ActividadInexistenteExcepcion
		 */
		ActividadGrupalEvaluable actividad = new ActividadGrupalEvaluable();
		actividad.levantarEstado(idActividad);
		return actividad;
	}
	
	protected String serializarInterno() {
		return super.serializarInterno() + "<tipoEscala>" + escala
				+ "</tipoEscala>";
	}

	
	// Para el caso de las evaluables, se agrega el Tag "TipoEscala"
	@Override
	protected void descerializar(Document doc) throws RemoteException {
		super.descerializar(doc);
		NodeList nodes = doc.getElementsByTagName("Actividad");
		if (nodes.getLength() != 1) {
			throw new 
			RemoteException("Debe haber solamente un nodo Actividad");
		}
		Element element = (Element) nodes.item(0);
		this.escala = getValue("tipoEscala", element);
	}
	
}
