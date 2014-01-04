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
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.TransformerFactoryConfigurationError;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.Text;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

public class NotaIndividual extends Nota {

	private String username;
	
	private final static String NODO_USERNAME = "Username";

	/**
	 * Constructor utilizado para realizar testing.
	 */
	@Deprecated
	public NotaIndividual() {
		super(-1);
	}

	private NotaIndividual(long idActividad, String username) {
		super(idActividad);
		this.username = username;
	}

	public String getUsername() {
		return username;
	}

	/**
	 * Crea e inicializa en la base de datos la nota para la actividad
	 * individual y participante dado. Si la nota ya fue inicializada, retorna
	 * la nota almacenada.
	 * 
	 * @param idActividad
	 *            Identificador de la actividad individual evaluable.
	 * @param username
	 *            Identificador del participante.
	 * @return NotaIndividual inicializada e instanciada.
	 * @throws RemoteExcepcion
	 *             Si no existe la actividad, si no es individual evaluable, si
	 *             no existe el participante.
	 */
	public static NotaIndividual crearNota(long idActividad, String username) {
		/*
		 * TODO Verificar si la nota ya fue inicializada. En caso afirmativo, se
		 * ejecuta el método "getNota". Si no está cargada la nota, verificar si
		 * la actividad existe y es individual evaluable. Luego, verificar si
		 * existe el usuario y si es participante de la actividad mencionada.
		 */
		NotaIndividual nota = new NotaIndividual(idActividad, username);
		return nota;
	}

	/**
	 * Elimina la NotaIndividual de la base de datos.
	 * 
	 * @param idActividad
	 *            Identificador de la actividad individual evaluable.
	 * @param username
	 *            Identificador del participante.
	 */
	public static void eliminarNota(long idActividad, String username) {
		// TODO
	}

	/**
	 * Carga desde la base de datos la NotaIndividual.
	 * 
	 * @param idActividad
	 *            Identificador de la actividad individual evaluable.
	 * @param username
	 *            Identificador del participante.
	 * @return NotaIndividual cargada e instanciada.
	 * @throws NotaInexistenteExcepcion
	 *             Si no hay cargada una nota individual asociada a la actividad
	 *             y participante.
	 */
	public static NotaIndividual getNota(long idActividad, String username)
			throws RemoteException {
		// TODO Corregir
		NotaIndividual nota = new NotaIndividual(idActividad, username);
		return nota;
	}

	@Override
	public String serializar() {
		Document doc = null;
		try {
			doc = DocumentBuilderFactory.newInstance()
					.newDocumentBuilder().newDocument();
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Element root = doc.createElement("WS");
		Element notaNode = doc.createElement(NODO_NOTA);
		Element idActividadNode = doc.createElement(NODO_ID_ACTIVIDAD);
		Text idActividadText = doc.createTextNode((new Long(idActividad)).toString());
		idActividadNode.appendChild(idActividadText);
		Element usernameNode = doc.createElement(NODO_USERNAME);
		Text usernameText = doc.createTextNode(username);
		usernameNode.appendChild(usernameText);
		Element valorNode = doc.createElement(NODO_VALOR);
		Text valorText = doc.createTextNode(valor);
		valorNode.appendChild(valorText);
		Element observacionesNode = doc.createElement(NODO_OBSERVACIONES);
		Text observacionesText = doc.createTextNode(observaciones);
		observacionesNode.appendChild(observacionesText);
		notaNode.appendChild(idActividadNode);
		notaNode.appendChild(usernameNode);
		notaNode.appendChild(valorNode);
		notaNode.appendChild(observacionesNode);
		root.appendChild(notaNode);
		doc.appendChild(root);
		Transformer transformer = null;
		try {
			transformer = TransformerFactory.newInstance().newTransformer();
		} catch (TransformerConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (TransformerFactoryConfigurationError e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "yes");
		StringWriter writer = new StringWriter();
		try {
			transformer.transform(new DOMSource(doc), new StreamResult(writer));
		} catch (TransformerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String output = writer.toString();//.replaceAll("\n|\r", "");
		return output;
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
			String message = "Error al cargar el string como XML.";
			throw new RemoteException(message);
		}
		doc.getDocumentElement().normalize();

		NodeList nodes = doc.getElementsByTagName(NODO_NOTA);
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
		idActividad = Long.valueOf(getValue(NODO_ID_ACTIVIDAD, element));
		username = getValue(NODO_USERNAME, element);
		valor = getValue(NODO_VALOR, element);
		observaciones = getValue(NODO_OBSERVACIONES, element);
	}
}
