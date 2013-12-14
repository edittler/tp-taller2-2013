package fiuba.taller.actividad;

import java.rmi.RemoteException;
import java.util.List;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

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
	public Nota getNota(Object usernameParticipante) throws RemoteException {
		String username = (String) usernameParticipante;
		NotaIndividual nota = NotaIndividual.getNota(id, username);
		return nota;
	}

	@Override
	public List<Nota> getNotas() {
		// TODO Implementar
		return null;
	}

	@Override
	public void actualizar(String xml) throws RemoteException {
		ActividadIndividualEvaluable actividadTemporal = new ActividadIndividualEvaluable();
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
		if (actividad.tipo.startsWith(TIPO_ACTIVIDAD_INDIVIDUAL_EVALUABLE)) {
			return true;
		}
		return false;
	}

	public static ActividadIndividualEvaluable crearActividad(
			String xmlPropiedades) throws RemoteException {
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
			throws RemoteException {
		ActividadIndividualEvaluable actividad = new ActividadIndividualEvaluable();
		actividad.levantarEstado(idActividad);
		return actividad;
	}

	@Override
	protected String serializarInterno() {
		return super.serializarInterno() + "<TipoEscala>" + escala
				+ "</TipoEscala>";
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
		this.escala = getValue("TipoEscala", element);
	}
}
