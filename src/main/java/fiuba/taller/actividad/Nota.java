package fiuba.taller.actividad;

import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public abstract class Nota implements Serializable{
	String nota;
	long idActividad;
	String observaciones;
	
	public abstract void setNota (String nota);
	
	protected static String getValue(String tag, Element element) {
		NodeList nodes = element.getElementsByTagName(tag).item(0).getChildNodes();
		Node node = (Node) nodes.item(0);
		return node.getNodeValue();
	}
}
