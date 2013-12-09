package fiuba.taller.actividad;

import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import fiuba.taller.actividad.excepcion.ParticipanteExistenteExcepcion;
import fiuba.taller.actividad.excepcion.ParticipanteInexistenteExcepcion;
import fiuba.taller.actividad.excepcion.XmlErroneoExcepcion;

/* FORMATO DEL XML GRUPO
 * <Grupo>
 *   <IdActividad></IdActividad>
 *   <IdGrupo></IdGrupo>
 *   <Participantes>
 *     <UsernameParticipante></UsernameParticipante>
 *       .
 *       .
 *     <UsernameParticipante></UsernameParticipante>
 *   <Participantes>
 * </Grupo>
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
	public void agregarParticipante(String username)
			throws ParticipanteExistenteExcepcion {
		if (contieneParticipante(username)) {
			String mensaje = "El username " + username
					+ "ya se encuentra en el grupo.";
			throw new ParticipanteExistenteExcepcion(mensaje);
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
	public void eliminarParticipante(String username)
			throws ParticipanteInexistenteExcepcion {
		if (!contieneParticipante(username)) {
			String mensaje = "El username " + username
					+ "no se encuentra en el grupo.";
			throw new ParticipanteInexistenteExcepcion(mensaje);
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

	public void descerializar(String xml) {
		try {
			DocumentBuilder builder = DocumentBuilderFactory.newInstance()
					.newDocumentBuilder();
			InputSource is = new InputSource(new StringReader(xml));
			Document doc = builder.parse(is);
			doc.getDocumentElement().normalize();

			NodeList nodes = doc.getElementsByTagName("Grupo");
			for (int i = 0; i < nodes.getLength(); i++) {
				Node node = nodes.item(i);

				if (node.getNodeType() == Node.ELEMENT_NODE) {
					Element element = (Element) node;
					idActividad = Long.valueOf(getValue("IdActividad", element));
					id = Long.valueOf(getValue("IdGrupo", element));

					NodeList participantes = ((Element) node)
							.getElementsByTagName("usernameParticipante");
					for (int j = 0; j < participantes.getLength(); j++) {
						// System.out.print("LARGO: "+participantes.getLength()+"\n");
						Node nodde = participantes.item(j);
						if (nodde.getNodeType() == Node.ELEMENT_NODE) {
							String valor = nodde.getChildNodes().item(0)
									.getNodeValue();

							// System.out.print("NODO: "+Long.valueOf(valor)+"\n");
							this.usernameParticipantes.add(valor);
						}
					}
				}
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		// System.out.print("ID: "+this.id+" PARTICIPANTES: "+this.idParticipantes);
	}

	/* METODOS DE CLASE (ESTATICOS) */

	public static boolean eliminarGrupo(long idActividad, long idGrupo) {
		// TODO
		return false;
	}

	public static Grupo getGrupo(long idActividad, long idGrupo) {
		// TODO
		Grupo grupo = new Grupo();
		return grupo;
	}

	/* METODOS PRIVADOS AUXILIARES */

	private static String getValue(String tag, Element element)
			throws XmlErroneoExcepcion {
		NodeList nodes = element.getElementsByTagName(tag);
		if (nodes.getLength() != 1) {
			String mensaje = "Debe existir un nodo " + tag + ".";
			throw new XmlErroneoExcepcion(mensaje);
		}
		Element elemento = (Element) nodes.item(0);
		return elemento.getTextContent();
	}
}