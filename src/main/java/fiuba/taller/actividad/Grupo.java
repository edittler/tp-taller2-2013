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

/* FORMATO DEL XML GRUPO
 * <Grupo>
 * 		<IdGrupo></IdGrupo>
 * 		<IdParticipante></IdParticipante>
 * 			.
 * 			.
 * 		<IdParticipante></IdParticipante>
 * </Grupo>
 */

public class Grupo implements Serializable {
	
	long id;
	List <Long> idParticipantes;

	public Grupo() {
		id=-1;
		idParticipantes = new ArrayList<>();
	}

	public long getId() {
		return id;
	}

	public List<Long> getIdParticipantes() {
		return idParticipantes;
	}
	
	public void setIdParticipantes(ArrayList<Long> idParticipantes) {
		this.idParticipantes = idParticipantes;
	}

	public String realizarConsulta() {
		return "no implementado";
	}
	
	public void guardarEstado() {
		// manda a guardar la informacion a integracion
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

					NodeList participantes = ((Element) node).getElementsByTagName("IdParticipante");
					for (int j = 0; j < participantes.getLength(); j++) {
						//System.out.print("LARGO: "+participantes.getLength()+"\n");
						Node nodde = participantes.item(j);
						if(nodde .getNodeType() == Node.ELEMENT_NODE){
							String valor = nodde.getChildNodes().item(0).getNodeValue();
							
							//System.out.print("NODO: "+Long.valueOf(valor)+"\n");
							this.idParticipantes.add(Long.valueOf(valor));
						}
					}
				}
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		//System.out.print("ID: "+this.id+" PARTICIPANTES: "+this.idParticipantes);
	}

	public String serializar (){
		String xml = "<?xml version=\"1.0\"?><WS><Grupo>" 	
				+ "<IdGrupo>" + id + "</IdGrupo>";
		for (int i = 0; i < idParticipantes.size(); ++i) {
			xml += "<IdParticipante>" + idParticipantes.get(i) + "</IdParticipante>";
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
