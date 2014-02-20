package fiuba.taller.actividad;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.ws.services.ActualizarDatos;
import com.ws.services.ActualizarDatosResponse;
import com.ws.services.EliminarDatos;
import com.ws.services.EliminarDatosResponse;
import com.ws.services.GuardarDatos;
import com.ws.services.GuardarDatosResponse;
import com.ws.services.IntegracionStub;
import com.ws.services.SeleccionarDatos;
import com.ws.services.SeleccionarDatosResponse;

/* FORMATO DEL XML GRUPO
 * 
 * FIXME ACLARACION: Revisar el formato XML que necesitan los de Integracion 
 * para poder persistir este objeto (incluyendo la lista de strings).
 * 
	<Grupo>
	   <id> </id>
	   <actividadId> </actividadId>
	   <usuarios>
	      <Usuario>
	         <id> </id>
	         <username> </username>
	      </Usuario>
	         ... 
	      <Usuario>
	         <id> </id>
	         <username> </username>
	      </Usuario>
	   </usuarios>
	</Grupo>
 * 
 * ws->seleccionarDatos(xml)
 * 
 * "todos los usuarios tales que el id del grupo es 5"
 * <Usuario>
 * 	<Grupo>
 * 		<id>5</id>
 * 	</Grupo>
 * </Usuario>
 */
public class Grupo implements Serializable {

	private long idActividad;
	private long id;
	private List<Usuario> participantes;

	private final static String className = Grupo.class.getSimpleName();

	public Grupo() {
		id = -1;
		idActividad = -1;
		participantes = new ArrayList<>();
	}

	public long getId() {
		return id;
	}

	protected void setId(long id) {
		this.id = id;
	}

	public long getIdActividad() {
		return idActividad;
	}

	public void setIdActividad(long idActividad) {
		this.idActividad = idActividad;
	}

	/**
	 * Agrega un participante al grupo.
	 * 
	 * @param username
	 *            Identificador del participante a agregar.
	 * @throws ParticipanteExistenteExcepcion
	 *             Si el participante ya se encuentra en el grupo
	 */
	public void agregarParticipante(String username) throws RemoteException {
		if (contieneParticipante(username)) {
			String mensaje = "El username " + username
					+ "ya se encuentra en el grupo.";
			throw new RemoteException(mensaje);
		}
		Usuario usuario = Usuario.getUsuario(username);
		participantes.add(usuario);
	}

	/**
	 * Elimina un participante del grupo.
	 * 
	 * @param username
	 *            Identificador del participante a eliminar.
	 * @throws ParticipanteInexistenteExcepcion
	 *             Si el participante a eliminar no existe.
	 */
	public void eliminarParticipante(String username) throws RemoteException {
		if (!contieneParticipante(username)) {
			String mensaje = "El username " + username
					+ "no se encuentra en el grupo.";
			throw new RemoteException(mensaje);
		}
		boolean encontrado = false;
		Iterator<Usuario> it = participantes.iterator();
		while (!encontrado) {
			Usuario usuario = it.next();
			if (usuario.getUsername().equals(username)) {
				participantes.remove(usuario);
				encontrado = true;
			}
		}
	}

	public List<String> getUsernameParticipantes() {
		List<String> usernames = new ArrayList<>();
		for (Usuario usuario : participantes) {
			usernames.add(usuario.getUsername());
		}
		return usernames;
	}

	public int tamanio() {
		return participantes.size();
	}

	/**
	 * Verifica si el grupo ya tiene un participante.
	 * 
	 * @param username
	 *            Identificador del participante.
	 * @return true si el participante ya está en el grupo.
	 */
	public boolean contieneParticipante(String username) {
		for (Usuario usuario : participantes) {
			if (usuario.getUsername().equals(username)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Verifica si el grupo contiene participantes de otro grupo.
	 * 
	 * @param grupo
	 *            Grupo donde se va a verificar la existencia de participantes
	 *            del propio grupo.
	 * @return true si el grupo no posee participantes de otro grupo.
	 */
	public boolean contieneParticipantesDe(Grupo grupo) {
		for (Usuario usuario : participantes) {
			if (grupo.contieneParticipante(usuario.getUsername())) {
				return true;
			}
		}
		return false;
	}

	public List<String> getParticipantesDuplicados(Grupo grupo) {
		// TODO Implementar
		return new ArrayList<>();
	}

	@Override
	public String serializar() {
		String xml = "<WS><Grupo>";
		if (id > 0) {
			xml += "<id>" + Long.toString(id) + "</id>";
		}
		if (idActividad > 0) {
			xml += "<actividadId>" + Long.toString(idActividad) + "</actividadId>";
		}
		if (tamanio() > 0) {
			xml += "<usuarios>";
			for (Usuario usuario : participantes) {
				xml += usuario.serializar();
			}
			xml += "</usuarios>";
		}
		xml += "</Grupo></WS>";
		return xml;
	}

	@Override
	public void descerializar(String xml) throws RemoteException {
		Document doc = ParserXml.getDocumentElement(xml);

		NodeList nodes = doc.getElementsByTagName("Grupo");
		if (nodes.getLength() != 1) {
			throw new RemoteException("Debe haber solamente un nodo Grupo");
		}

		Element grupoElement = (Element) nodes.item(0);
		idActividad = Long.valueOf(ParserXml.getRequiredValue("idActividad",
				grupoElement));
		id = Long.valueOf(ParserXml.getRequiredValue("id", grupoElement));

		NodeList lista = ((Element) grupoElement).getElementsByTagName("usuarios");
		Element listaE = (Element) lista.item(0);

		NodeList participantes = listaE.getElementsByTagName("Usuario");
		for (int j = 0; j < participantes.getLength(); j++) {
			Node node = participantes.item(j);
			if (node.getNodeType() == Node.ELEMENT_NODE) {
				String nodeStr = node.toString();
				Usuario usuario = Usuario.deserializar(nodeStr);
				this.participantes.add(usuario);
			}
		}
	}

	@Override
	public void guardarNuevoEstado() throws RemoteException {
		setId(-1);
		IntegracionStub servicio = new IntegracionStub();
		GuardarDatos envio = new GuardarDatos();
		String xml = serializar();
		envio.setXml(xml);
		GuardarDatosResponse response = servicio.guardarDatos(envio);
		String retorno = response.get_return();
		String idStr = ParserXml.procesarNotificacionIntegracion(className,
				retorno);
		id = Long.valueOf(idStr);
	}

	@Override
	public void actualizarEstado() throws RemoteException {
		IntegracionStub servicio = new IntegracionStub();
		ActualizarDatos envio = new ActualizarDatos();
		String xml = serializar();
		envio.setXml(xml);
		ActualizarDatosResponse respuesta = servicio.actualizarDatos(envio);
		String retorno = respuesta.get_return();
		ParserXml.procesarNotificacionIntegracion(className, retorno);
	}

	@Override
	public void eliminarEstado() throws RemoteException {
		IntegracionStub servicio = new IntegracionStub();
		EliminarDatos envio = new EliminarDatos();
		String xml = "<WS><Grupo><id>" + Long.toString(id)
				+ "</id></Grupo></WS>";
		envio.setXml(xml);
		EliminarDatosResponse respuesta = servicio.eliminarDatos(envio);
		String retorno = respuesta.get_return();
		ParserXml.procesarNotificacionIntegracion(className, retorno);
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

	/* METODOS DE CLASE (ESTATICOS) */

	public static Grupo getGrupo(long idGrupo) {
		/* TODO(Jorge) Implementar. Se debe cargar desde la base de datos el 
		 * grupo correspondiente a la actividad e idGrupo pasados por parámetro.
		 */
		Grupo grupo = new Grupo();
		return grupo;
	}
}
