package fiuba.taller.actividad;

import java.io.StringReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

/* FORMATO DEL XML GRUPO
 * <Grupo>
 * 		<IdGrupo></IdGrupo>
 * 		<UsernameParticipante></UsernameParticipante>
 * 			.
 * 			.
 * 		<UsernameParticipante></UsernameParticipante>
 * </Grupo>
 */

public class Grupo implements Serializable {
	
	long id;
	long idActividad;
	List <String> usernameParticipantes;

	public Grupo() {
		id=-1;
		idActividad = -1;
		usernameParticipantes = new ArrayList<>();
	}

	public long getId() {
		return id;
	}
	
	public long getIdActividad() {
		return idActividad;
	}
	
	public void setId(long id) {
		this.id = id;
	}
	
	public void setIdActividad(long idActividad) {
		this.idActividad = idActividad;
	}
	
	public void agregarParticipante(String username) {
		usernameParticipantes.add(username);
	}
	
	public void eliminarParticipante(String username) {
		usernameParticipantes.remove(username);
	}

	public List<String> getUsernameParticipantes() {
		return usernameParticipantes;
	}
	
	public boolean contieneParticipantesDe(Grupo grupo) {
		Iterator<String> iterador = usernameParticipantes.listIterator();
		while( iterador.hasNext() ) {
	          String usernameParticipante = (String) iterador.next();
	          if (grupo.usernameParticipantes.contains(usernameParticipante)) {
	        	  return true;
	          }
		}
		return false;
	}
	
	public static Grupo crearGrupo(long idActividad) {
		// TODO
		Grupo grupo = new Grupo();
		return grupo;
	}
	
	public static boolean eliminarGrupo(long idActividad, long idGrupo) {
		// TODO
		return false;
	}
	
	public static Grupo getGrupo(long idActividad, long idGrupo) {
		// TODO
		Grupo grupo = new Grupo();
		return grupo;
	}
	
	public void descerializar(String xml) {
		try {
			DocumentBuilderFactory factory = DocumentBuilderFactory
					.newInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();
			InputSource is = new InputSource(new StringReader(xml));
			Document doc = builder.parse(is);
			doc.getDocumentElement().normalize();

			NodeList nodes = doc.getElementsByTagName("Grupo");
			for (int i = 0; i < nodes.getLength(); i++) {
				Node node = nodes.item(i);

				if (node.getNodeType() == Node.ELEMENT_NODE) {
					Element element = (Element) node;
					this.id = Long.valueOf(getValue("IdGrupo", element));

					NodeList participantes = ((Element) node).getElementsByTagName("usernameParticipante");
					for (int j = 0; j < participantes.getLength(); j++) {
						//System.out.print("LARGO: "+participantes.getLength()+"\n");
						Node nodde = participantes.item(j);
						if(nodde .getNodeType() == Node.ELEMENT_NODE){
							String valor = nodde.getChildNodes().item(0).getNodeValue();
							
							//System.out.print("NODO: "+Long.valueOf(valor)+"\n");
							this.usernameParticipantes.add(valor);
						}
					}
				}
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		//System.out.print("ID: "+this.id+" PARTICIPANTES: "+this.idParticipantes);
	}

	public String serializar() {
		String xml = "<?xml version=\"1.0\"?><WS><Grupo>" 	
				+ "<IdGrupo>" + id + "</IdGrupo>";
		for (int i = 0; i < usernameParticipantes.size(); ++i) {
			xml += "<usernameParticipante>" + usernameParticipantes.get(i) + "</usernameParticipante>";
		}
		xml += "</Grupo></WS>";
		return xml;
	}
	
	/* METODOS PRIVADOS AUXILIARES */
	
	private static String getValue(String tag, Element element) {
		NodeList nodes = element.getElementsByTagName(tag).item(0).getChildNodes();
		Node node = (Node) nodes.item(0);
		return node.getNodeValue();
	}
	
}
