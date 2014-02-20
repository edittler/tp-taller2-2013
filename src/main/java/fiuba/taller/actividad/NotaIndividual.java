package fiuba.taller.actividad;

import java.rmi.RemoteException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.ws.services.ActualizarDatos;
import com.ws.services.ActualizarDatosResponse;
import com.ws.services.GuardarDatos;
import com.ws.services.GuardarDatosResponse;
import com.ws.services.IntegracionStub;
import com.ws.services.SeleccionarDatos;
import com.ws.services.SeleccionarDatosResponse;

public class NotaIndividual extends Nota {

	private String username;
	
	private final static String NODO_USERNAME = "username";

	private final static String className = NotaIndividual.class.getSimpleName();

	private NotaIndividual(long idActividad, String username) {
		super(idActividad);
		this.username = username;
	}

	public NotaIndividual(long idActividad, String username, String valorNota) {
		super(idActividad);
		this.username = username;
		this.valor = valorNota;
	}

	public NotaIndividual(long idActividad, String username, String valorNota,
			String observaciones) {
		super(idActividad);
		this.username = username;
		this.valor = valorNota;
		this.observaciones = observaciones;
	}

	public String getUsername() {
		return username;
	}

	@Override
	public String serializar() {
		String xml = "<WS><Nota>";
		if (idActividad > 0) {
			xml += "<idActividad>" + idActividad + "</idActividad>";
		}
		if (username.length() > 0) {
			xml += "<username>" + username + "</username>";
		}
		if (valor.length() > 0) {
			xml += "<valor>" + valor + "</valor>";
		}
		if (observaciones.length() > 0) {
			xml += "<observaciones>" + observaciones + "</observaciones>";
		}
		xml += "</Nota></WS>";
		return xml;
	}

	@Override
	public void descerializar(String xml) throws RemoteException {

		Document doc = ParserXml.getDocumentElement(xml);

		NodeList nodes = doc.getElementsByTagName(NODO_NOTA);
		if (nodes.getLength() != 1) {
			String message = "La cantidad de nodos Nota no es unica.";
			throw new RemoteException(message);
		}

		Node node = nodes.item(0);
		if (node.getNodeType() != Node.ELEMENT_NODE) {
			String message = "El nodo Nota no es de tipo Element";
			throw new RemoteException(message);
		}

		Element element = (Element) node;
		idActividad = Long.valueOf(ParserXml.getValue(NODO_ID_ACTIVIDAD,
				element));
		username = ParserXml.getValue(NODO_USERNAME, element);
		valor = ParserXml.getValue(NODO_VALOR, element);
		observaciones = ParserXml.getValue(NODO_OBSERVACIONES, element);
	}

	@Override
	public void guardarNuevoEstado() throws RemoteException {
		IntegracionStub servicio = new IntegracionStub();
		GuardarDatos envio = new GuardarDatos();
		String xml = serializar();
		envio.setXml(xml);
		GuardarDatosResponse response = servicio.guardarDatos(envio);
		String retorno = response.get_return();
		ParserXml.procesarNotificacionIntegracion(className, retorno);
	}

	@Override
	public void actualizarEstado() throws RemoteException {
		if (!notaCreada(idActividad, username)) {
			guardarNuevoEstado();
		} else {
			IntegracionStub servicio = new IntegracionStub();
			ActualizarDatos envio = new ActualizarDatos();
			String xml = serializar();
			envio.setXml(xml);
			ActualizarDatosResponse respuesta = servicio.actualizarDatos(envio);
			String retorno = respuesta.get_return();
			ParserXml.procesarNotificacionIntegracion(className, retorno);
		}
	}

	@Override
	public void eliminarEstado() throws RemoteException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String realizarConsulta() throws RemoteException {
		IntegracionStub servicio = new IntegracionStub();
		SeleccionarDatos envio = new SeleccionarDatos();
		String xml = serializar();
		envio.setXml(xml);
		SeleccionarDatosResponse respuesta = servicio.seleccionarDatos(envio);
		String retorno = respuesta.get_return();
		return retorno;
	}

	/**
	 * Elimina la NotaIndividual de la base de datos.
	 * 
	 * @param idActividad
	 *            Identificador de la actividad individual evaluable.
	 * @param username
	 *            Identificador del participante.
	 */
	public static void eliminarNota(long idActividad, String username) {
		// TODO
	}

	/**
	 * Carga desde la base de datos la NotaIndividual.
	 * 
	 * @param idActividad
	 *            Identificador de la actividad individual evaluable.
	 * @param username
	 *            Identificador del participante.
	 * @return NotaIndividual cargada e instanciada.
	 * @throws NotaInexistenteExcepcion
	 *             Si no hay cargada una nota individual asociada a la actividad
	 *             y participante.
	 */
	public static NotaIndividual getNota(long idActividad, String username)
			throws RemoteException {
		NotaIndividual nota = new NotaIndividual(idActividad, username);
		String xml = nota.realizarConsulta();
		procesarConsultaIndividualIntegracion(xml);
		nota.descerializar(xml);
		return nota;
	}

	private static boolean notaCreada(long idActividad, String username) {
		try {
			getNota(idActividad, username);
		} catch (RemoteException e) {
			return false;
		}
		return true;
	}
}
