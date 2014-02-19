package fiuba.taller.actividad;

import java.rmi.RemoteException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import com.ws.services.IntegracionStub;
import com.ws.services.SeleccionarDatos;
import com.ws.services.SeleccionarDatosResponse;

public class Usuario implements Serializable {

	private long id;
	private String username;
	
	private Usuario(String username) {
		this.id = -1;
		this.username = username;
	}

	public long getId() {
		/* TODO: me gustaria que aca se realizara la consulta a integracion
		y asi mantener transparente para los que usen esta clase */
		return id;
	}

	public String getUsername() {
		return username;
	}

	@Override
	public String serializar() {
		String xml = "<Usuario>";
		if (id > 0) {
			String idStr = String.valueOf(id);
			xml += "<id>" + idStr + "</id>";
		}
		if (username.length() > 0) {
			xml += "<username>" + username + "</username>";
		}
		xml += "</Usuario>";
		return xml;
	}

	@Override
	public void descerializar(String xml) throws RemoteException {
		Document doc = ParserXml.getDocumentElement(xml);
		NodeList nodes = doc.getElementsByTagName("Usuario");
		if (nodes.getLength() != 1) {
			throw new RemoteException("Debe haber solamente un nodo Usuario.");
		}
		Element element = (Element) nodes.item(0);
		String idStr = ParserXml.getRequiredValue("id", element);
		id = Long.valueOf(idStr);
		username = ParserXml.getRequiredValue("username", element);
	}

	@Override
	public void guardarNuevoEstado() throws RemoteException {
		throw new RemoteException("El paquete Actividad no debe guardar un "
				+ "nuevo usuario.");
	}

	@Override
	public void actualizarEstado() throws RemoteException {
		throw new RemoteException("El paquete Actividad no debe actualizar un "
				+ "usuario.");
	}

	@Override
	public void eliminarEstado() throws RemoteException {
		throw new RemoteException("El paquete Actividad no debe eliminar un "
				+ "usuario.");
	}

	@Override
	public String realizarConsulta() throws RemoteException {
		IntegracionStub servicio = new IntegracionStub();
		SeleccionarDatos envio = new SeleccionarDatos();
		String xml = "<WS>" + serializar() + "</WS>";
		envio.setXml(xml);
		SeleccionarDatosResponse respuesta = servicio.seleccionarDatos(envio);
		String retorno = respuesta.get_return();
		return retorno;
	}

	/* METODOS DE CLASE (ESTATICOS) */

	public static Usuario getUsuario(String username) throws RemoteException {
		Usuario usuario = new Usuario(username);
		String xml = usuario.realizarConsulta();
		usuario.id = procesarConsultaIndividual(xml);
		return usuario;
	}

	public static Usuario deserializar(String xml) throws RemoteException {
		Usuario usuario = new Usuario("");
		usuario.descerializar(xml);
		return usuario;
	}

	private static long procesarConsultaIndividual(String xml) throws RemoteException {
		Document doc = ParserXml.getDocumentElement(xml);
		NodeList nodes = doc.getElementsByTagName("Usuario");
		if (nodes.getLength() == 0) {
			throw new RemoteException("Integracion no devolvio un unico nodo Usuario");
		}
		// Solo tomo el primer usuario. No importa que hayan más usuarios.
		Element element = (Element) nodes.item(0);
		String idStr = ParserXml.getRequiredValue("id", element);
		return Long.valueOf(idStr);
	}
}
