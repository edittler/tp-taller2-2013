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

public class NotaGrupal extends Nota {

	private long idGrupo;

	public NotaGrupal(long idActividad, long idGrupo, String valorNota) {
		super(idActividad);
		this.idGrupo = idGrupo;
		this.valor = valorNota;
	}

	public NotaGrupal(long idActividad, long idGrupo, String valorNota,
			String observaciones) {
		super(idActividad);
		this.idGrupo = idGrupo;
		this.valor = valorNota;
		this.observaciones = observaciones;
	}

	private NotaGrupal(long idActividad, long idGrupo) {
		super(idActividad);
		this.idGrupo = idGrupo;
	}

	public long getIdGrupo() {
		return idGrupo;
	}

	@Override
	public String serializar() {
		String xml = "<WS><Nota>";
		if (idActividad > 0) {
			String idActividadString = String.valueOf(idActividad);
			xml += "<idActividad>" + idActividadString + "</idActividad>";
		}
		if (idGrupo > 0) {
			String idGrupoString = String.valueOf(idGrupo);
			xml += "<idGrupo>" + idGrupoString + "</idGrupo>";
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
		Document doc = getDocumentElement(xml);

		NodeList nodes = doc.getElementsByTagName("Nota");
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
		idActividad = Long.valueOf(getValue("idActividad", element));
		idGrupo = Long.valueOf(getValue("idGrupo", element));
		valor = getValue("valor", element);
		observaciones = getValue("observaciones", element);
	}

	@Override
	public void guardarNuevoEstado() throws RemoteException {
		IntegracionStub servicio = new IntegracionStub();
		GuardarDatos envio = new GuardarDatos();
		String xml = serializar();
		envio.setXml(xml);
//		System.out.println(xml);
		GuardarDatosResponse response = servicio.guardarDatos(envio);
		String retorno = response.get_return();
		System.out.println(retorno);
		procesarNotificacionIntegracion(retorno);
	}

	@Override
	public void actualizarEstado() throws RemoteException {
		if (!notaCreada(idActividad, idGrupo)) {
			guardarNuevoEstado();
		} else {
			IntegracionStub servicio = new IntegracionStub();
			ActualizarDatos envio = new ActualizarDatos();
			String xml = serializar();
			envio.setXml(xml);
			ActualizarDatosResponse respuesta = servicio.actualizarDatos(envio);
			String retorno = respuesta.get_return();
			System.out.println(retorno);
			procesarNotificacionIntegracion(retorno);
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
	 * Elimina la NotaGrupal de la base de datos.
	 * 
	 * @param idActividad
	 *            Identificador de la actividad grupal evaluable.
	 * @param idGrupo
	 *            Identificador del grupo.
	 */
	public static void eliminarNota(long idActividad, long idGrupo) {
		// TODO
	}

	/**
	 * Carga desde la base de datos la NotaGrupal.
	 * 
	 * @param idActividad
	 *            Identificador de la actividad grupal evaluable.
	 * @param idGrupo
	 *            Identificador del grupo.
	 * @return NotaGrupal cargada e instanciada.
	 * @throws NotaInexistenteExcepcion
	 *             Si no hay cargada una nota grupal asociada a la actividad y
	 *             grupo.
	 */
	public static NotaGrupal getNota(long idActividad, long idGrupo)
			throws RemoteException {
		NotaGrupal nota = new NotaGrupal(idActividad, idGrupo);
		String xml = nota.realizarConsulta();
		procesarConsultaIndividualIntegracion(xml);
		nota.descerializar(xml);
		return nota;
	}

	private static boolean notaCreada(long idActividad, long idGrupo) {
		try {
			getNota(idActividad, idGrupo);
		} catch (RemoteException e) {
			return false;
		}
		return true;
	}
}
