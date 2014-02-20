package fiuba.taller.actividad;

import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.rmi.RemoteException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

public class ParserXml {

	public static Document getDocumentElement(String xml)
			throws RemoteException {
		Document doc = null;
		try {
			DocumentBuilder builder = DocumentBuilderFactory.newInstance()
					.newDocumentBuilder();
			InputSource is = new InputSource(new StringReader(xml));
			doc = builder.parse(is);
			doc.getDocumentElement().normalize();
		} catch (ParserConfigurationException | SAXException | IOException e) {
			e.printStackTrace();
			throw new RemoteException("Error al parsear el XML.");
			
		}
		return doc;
	}

	public static String toString(Node node) throws RemoteException {
		Transformer transformer;
		StringWriter buffer = new StringWriter();
		try {
			transformer = TransformerFactory.newInstance().newTransformer();
			transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "yes");
			transformer.transform(new DOMSource(node), new StreamResult(buffer));
		} catch (TransformerException e) {
			throw new RemoteException("Error al parsear el Elemento XML.");
		}
		String str = buffer.toString();
		return str;
	}

	public static String getValue(String tag, Element element)
			throws RemoteException {
		NodeList nodes = element.getElementsByTagName(tag);
		int numeroNodos = nodes.getLength();
		switch (numeroNodos) {
		case 0:
			return "";
		case 1:
			Element elemento = (Element) nodes.item(0);
			return elemento.getTextContent();
		default:
			String mensaje = "No debe existir mas de un nodo " + tag + ".";
			throw new RemoteException(mensaje);
		}
	}

	public static String getRequiredValue(String tag, Element element)
			throws RemoteException {
		String value = getValue(tag, element);
		if (value.length() == 0) {
			String message = "El valor de " + tag + "es obligatorio.";
			throw new RemoteException(message);
		}
		return value;
	}

	public static String procesarNotificacionIntegracion(String clase,
			String xmlMensaje) throws RemoteException {
		String errorMsj = "[Paquete Actividad: Clase " + clase + "] ";
		Document doc = ParserXml.getDocumentElement(xmlMensaje);
		NodeList nodes = doc.getElementsByTagName("notificacion");
		if (nodes.getLength() != 1) {
			errorMsj += "Integracion no devolvió un único nodo <notificacion>";
			throw new RemoteException(errorMsj);
		}
		Element element = (Element) nodes.item(0);
		String numeroStr = ParserXml.getValue("numero", element);
		int numero = Integer.valueOf(numeroStr);
		String mensaje = ParserXml.getValue("mensaje", element);
		String datos = "";
		try {
			datos = ParserXml.getValue("datos", element);
		} catch (RemoteException e) {
		}
		switch (numero) {
		// Codigo 0: Operación no permitida
		case 0:
			throw new RemoteException(errorMsj + "Integracion: " + mensaje);
		// Codigo 1: Error al realizar la operación
		case 1:
			throw new RemoteException(errorMsj + "Integracion: " + mensaje);
		// Codigo 2: Operación correcta
		case 2:
			return datos;
		// Codigo 3: Consulta sin resultados
		case 3:
			throw new RemoteException(errorMsj + "Integracion: " + mensaje);
		default:
			throw new RemoteException(errorMsj + "Integracion: Codigo desconocido.");
		}
	}
}
