package fiuba.taller.actividad;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import com.ws.services.ActualizarDatos;
import com.ws.services.ActualizarDatosResponse;
import com.ws.services.IntegracionStub;
import com.ws.services.SeleccionarDatos;
import com.ws.services.SeleccionarDatosResponse;

public class ActividadIndividual extends Actividad {

	protected static final String TIPO_ACTIVIDAD_INDIVIDUAL = "Individual";

	protected List<String> usernameParticipantes;

	public ActividadIndividual() {
		super();
		tipo = TIPO_ACTIVIDAD_INDIVIDUAL;
		usernameParticipantes = null;
	}

	public List<String> getParticipantes() {
		if (!participantesCargados()) {
			cargarParticipantes();
		}
		return new ArrayList<>(usernameParticipantes);
	}

	public void agregarParticipante(String username) throws RemoteException {
		if (!participantesCargados()) {
			cargarParticipantes();
		}
		if (usernameParticipantes.contains(username)) {
			String mensaje = "El usuario " + username
					+ " ya se encuentra en la actividad";
			throw new RemoteException(mensaje);
		}
		long idUsuario = getIdUsuario(username);
		String xml = "<WS><Actividad><id>" + id + "</id>"
				+ "<participantes><Usuario><id>" + idUsuario
				+ "</id></Usuario></participantes></Actividad></WS>";
		IntegracionStub servicio = new IntegracionStub();
		ActualizarDatos envio = new ActualizarDatos();
		envio.setXml(xml);
		ActualizarDatosResponse respuesta = servicio.actualizarDatos(envio);
		String retorno = respuesta.get_return();
		procesarNotificacionIntegracion(retorno);
		usernameParticipantes.add(username);
	}

	public void eliminarParticipante(String username) throws RemoteException {
		if (!participantesCargados()) {
			cargarParticipantes();
		}
		if (!usernameParticipantes.contains(username)) {
			String mensaje = "El usuario " + username
					+ " no se encuentra en la actividad";
			throw new RemoteException(mensaje);
		}
		usernameParticipantes.remove(username);
		/*
		 * FIXME(Jorge) Hará falta llamar a Integración para eliminar el
		 * participante o con el método "GuardarEstado" alcanza?
		 */
	}

	@Override
	public void actualizarEstado() throws RemoteException {
		super.actualizarEstado();
		/*
		 * TODO(Jorge) Se debe guardar la lista de usernames.
		 */
	}

	/* METODOS DE CLASE (ESTATICOS) */

	public static boolean esTipoValido(String xml) {
		Actividad actividad = new Actividad();
		try {
			actividad.descerializar(xml);
		} catch (RemoteException e) {
			return false;
		}
		/*
		 * Si el campo "Tipo" comienza con la palabra "Individual", se
		 * considerara de tipo valido. Es decir, ActividadIndividualEvaluable
		 * tambien se considera como ActividadIndividual (por ser clase
		 * derivada)
		 */
		if (actividad.tipo.startsWith(TIPO_ACTIVIDAD_INDIVIDUAL)) {
			return true;
		}
		return false;
	}

	public static ActividadIndividual crearActividad(String xmlPropiedades)
			throws RemoteException {
		ActividadIndividual actividad = new ActividadIndividual();
		actividad.descerializar(xmlPropiedades);
		actividad.setId(-1);
		actividad.tipo = TIPO_ACTIVIDAD_INDIVIDUAL;
		// TODO(Pampa) Validar fecha y lanzar excepcion
		actividad.guardarNuevoEstado();
		return actividad;
	}

	public static void eliminarActividad(long idActividad)
			throws RemoteException {
		
		ActividadIndividual actividad = ActividadIndividual.getActividad(idActividad);
		actividad.eliminarEstado();
	}

	public static ActividadIndividual getActividad(long idActividad)
			throws RemoteException {
		/*
		 * FIXME Si no existe la actividad con el ID especificado, se debe
		 * lanzar la excepcion.
		 */
		String propiedades = getPropiedades(idActividad);
		if(!esTipoValido(propiedades)) {
			String mensaje = "La actividad a cargar no es Individual";
			throw new RemoteException(mensaje);
		}
		ActividadIndividual actividad = new ActividadIndividual();
		actividad.descerializar(propiedades);
		return actividad;
	}

	/* METODOS PRIVADOS AUXILIARES */

	private boolean participantesCargados() {
		return usernameParticipantes != null;
	}

	private void cargarParticipantes() {
		usernameParticipantes = new ArrayList<>();
		/*
		 * TODO(Jorge o Pampa?) Implementar. Se debe cargar desde la base de
		 * datos la lista de participantes.
		 */
	}

	private static long getIdUsuario(String username) throws RemoteException {
		IntegracionStub servicio = new IntegracionStub();
		SeleccionarDatos envio = new SeleccionarDatos();
		String xml = "<WS><Usuario><username>" + username
				+ "</username></Usuario></WS>";
		envio.setXml(xml);
		SeleccionarDatosResponse respuesta = servicio.seleccionarDatos(envio);
		String retorno = respuesta.get_return();
		long id = procesarConsultaDeUsuario(retorno);
		return id;
	}

	private static long procesarConsultaDeUsuario(String xml) throws RemoteException {
		Document doc = getDocumentElement(xml);
		NodeList nodes = doc.getElementsByTagName("Usuario");
		if (nodes.getLength() == 0) {
			throw new RemoteException("Integracion no devolvio un unico nodo Usuario");
		}
		// Solo tomo el primer usuario. No importa que hayan más usuarios.
		Element element = (Element) nodes.item(0);
		String idStr = getValue("id", element);
		return Long.valueOf(idStr);
	}
}
