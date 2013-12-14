package fiuba.taller.actividad;

import java.io.IOException;
import java.io.StringReader;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

/* FORMATO DEL XML GRUPO
 * 
 * FIXME ACLARACION: Revisar el formato XML que necesitan los de Integracion 
 * para poder persistir este objeto (incluyendo la lista de strings).
 * 
 * <Grupo>
 * 		<IdGrupo></IdGrupo>
 * 		<list>
 * 			<UsernameParticipante></UsernameParticipante>
 * 				.
 * 				.
 * 			<UsernameParticipante></UsernameParticipante>
 * 		</list>
 * </Grupo>
 * 
 * ws->seleccionarDatos(xml)
 * 
 * "todos los usuarios tales que el id del grupo es 5"
 * <Usuario>
 * <join>
 * 		<Grupo>
 * 			<idGrupo>5</idGrupo>
 * 		</Grupo>
 * </join>
 * </Usuario>
 * 
 */

public class Grupo implements Serializable {

	private long idActividad;
	private long id;
	private List<String> usernameParticipantes;

	public Grupo() {
		id = -1;
		idActividad = -1;
		usernameParticipantes = new ArrayList<>();
	}

	public long getIdActividad() {
		return idActividad;
	}

	public void setIdActividad(long idActividad) {
		this.idActividad = idActividad;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
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
		usernameParticipantes.add(username);
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
		usernameParticipantes.remove(username);
	}

	public List<String> getUsernameParticipantes() {
		return usernameParticipantes;
	}

	public int tamanio() {
		return usernameParticipantes.size();
	}

	/**
	 * Verifica si el grupo ya tiene un participante.
	 * 
	 * @param username
	 *            Identificador del participante.
	 * @return true si el participante ya está en el grupo.
	 */
	public boolean contieneParticipante(String username) {
		return usernameParticipantes.contains(username);
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
		for (String usernameParticipante : usernameParticipantes) {
			if (grupo.contieneParticipante(usernameParticipante)) {
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
		String xml = "<?xml version=\"1.0\"?><WS><Grupo>"
				+ "<IdActividad>" + idActividad + "</IdActividad>"
				+ "<IdGrupo>" + id + "</IdGrupo>";
		for (String participante : usernameParticipantes) {
			xml += "<usernameParticipante>" + participante
					+ "</usernameParticipante>";
		}
		xml += "</Grupo></WS>";
		return xml;
	}

	@Override
	public void descerializar(String xml) throws RemoteException {
		Document doc = null;
		try {
			DocumentBuilder builder = DocumentBuilderFactory.newInstance()
					.newDocumentBuilder();
			InputSource is = new InputSource(new StringReader(xml));
			doc = builder.parse(is);
		} catch (ParserConfigurationException | SAXException | IOException e) {
			String mensaje = "Error de parseo del string recibido";
			throw new RemoteException(mensaje);
		}
		doc.getDocumentElement().normalize();

		NodeList nodes = doc.getElementsByTagName("Grupo");
		if (nodes.getLength() != 1) {
			throw new RemoteException("Debe haber solamente un nodo Grupo");
		}

		Element grupoElement = (Element) nodes.item(0);
		idActividad = Long.valueOf(getValue("IdActividad", grupoElement));
		id = Long.valueOf(getValue("IdGrupo", grupoElement));

		NodeList participantes = ((Element) grupoElement)
				.getElementsByTagName("usernameParticipante");
		for (int j = 0; j < participantes.getLength(); j++) {
			// System.out.print("LARGO: "+participantes.getLength()+"\n");
			Node nodde = participantes.item(j);
			if (nodde.getNodeType() == Node.ELEMENT_NODE) {
				String valor = nodde.getChildNodes().item(0).getNodeValue();
				// System.out.print("NODO: "+Long.valueOf(valor)+"\n");
				this.usernameParticipantes.add(valor);
			}
		}
		// System.out.print("ID: "+this.id+" PARTICIPANTES: "+this.idParticipantes);
	}

	@Override
	public void guardarEstado() {
		/* 
		 * TODO(Jorge) Implementar. Se debe persistir el objeto en la base de
		 * datos.
		 */
	}

	@Override
	public String realizarConsulta() {
		/*
		 * TODO(Jorge) Implementar
		 */
		return "";
	}

	/* METODOS DE CLASE (ESTATICOS) */

	public static boolean eliminarGrupo(long idActividad, long idGrupo) {
		/* TODO(Jorge) Implementar. Se debe eliminar de la base de datos el
		 * grupo que corresponde a la actividad e idGrupo pasados por parámetro.
		 */
		return false;
	}

	public static Grupo getGrupo(long idActividad, long idGrupo) {
		/* TODO(Jorge) Implementar. Se debe cargar desde la base de datos el 
		 * grupo correspondiente a la actividad e idGrupo pasados por parámetro.
		 */
		Grupo grupo = new Grupo();
		return grupo;
	}

	/* METODOS PRIVADOS AUXILIARES */

	private static String getValue(String tag, Element element)
			throws RemoteException {
		NodeList nodes = element.getElementsByTagName(tag);
		if (nodes.getLength() != 1) {
			String mensaje = "Debe existir un nodo " + tag + ".";
			throw new RemoteException(mensaje);
		}
		Element elemento = (Element) nodes.item(0);
		return elemento.getTextContent();
	}
}
