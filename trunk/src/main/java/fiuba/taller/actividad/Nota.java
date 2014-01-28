package fiuba.taller.actividad;

import java.io.IOException;
import java.io.StringReader;
import java.rmi.RemoteException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

public abstract class Nota implements Serializable {

	protected long idActividad;
	protected String valor;
	protected String observaciones;
	
	protected static final String NODO_NOTA = "Nota";
	protected static final String NODO_ID_ACTIVIDAD = "idActividad";
	protected static final String NODO_VALOR = "valor";
	protected static final String NODO_OBSERVACIONES = "observaciones";

	protected Nota(long idActividad) {
		this.idActividad = idActividad;
		valor = "";
		observaciones = "";
	}

	public long getIdActividad() {
		return idActividad;
	}

	public String getValor() {
		return valor;
	}

	public void setValor(String valor) {
		this.valor = valor;
	}

	public String getObservaciones() {
		return observaciones;
	}

	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}

	protected static String procesarNotificacionIntegracion(String xmlMensaje)
			throws RemoteException {
		Document doc = getDocumentElement(xmlMensaje);
		NodeList nodes = doc.getElementsByTagName("notificacion");
		if (nodes.getLength() != 1) {
			throw new RemoteException(
					"Integracion no devolvio un unico nodo notificacion");
		}
		Element element = (Element) nodes.item(0);
		String numeroStr = getValue("numero", element);
		int numero = Integer.valueOf(numeroStr);
		String mensaje = getValue("mensaje", element);
		String datos = "";
		try {
			datos = getValue("datos", element);
		} catch (RemoteException e) {
		}
		switch (numero) {
		// Codigo 0: Operación no permitida
		case 0:
			throw new RemoteException("Integracion: " + mensaje);
		// Codigo 1: Error al realizar la operación
		case 1:
			throw new RemoteException("Integracion: " + mensaje);
		// Codigo 2: Operación correcta
		case 2:
			return datos;
		default:
			throw new RemoteException("Integracion: Codigo desconocido.");
		}
	}

	protected static String procesarConsultaIndividualIntegracion(
			String xmlMensaje) throws RemoteException {
		Document doc = getDocumentElement(xmlMensaje);
		NodeList nodes = doc.getElementsByTagName("Nota");
		int cantidadNotas = nodes.getLength();
		switch (cantidadNotas) {
		case 0:
			procesarNotificacionIntegracion(xmlMensaje);
			break;
		case 1:
			return xmlMensaje;
		default:
			throw new RemoteException(
					"Integracion no devolvio un unico nodo Nota");
		}
		return xmlMensaje;
	}

	protected static Document getDocumentElement(String xml)
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

	protected static String getValue(String tag, Element element)
			throws RemoteException {
		NodeList nodes = element.getElementsByTagName(tag);
		if (nodes.getLength() != 1) {
			String mensaje = "Debe existir un nodo " + tag + ".";
			throw new RemoteException(mensaje);
		}
		Element elemento = (Element) nodes.item(0);
		String text = elemento.getTextContent();
		return text;
	}
}
