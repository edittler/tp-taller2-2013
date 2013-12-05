package fiuba.taller.actividad;

import java.io.StringReader;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

public class Nota implements Serializable {
	private long idActividad;
	private long idEvaluado;
	private String nota;
	private String observaciones;

	public Nota() {
		idActividad = -1;
		idEvaluado = -1;
		nota = "";
		observaciones = "";
	}

	public Nota(long idActividad, long idEvaluado, String nota) {
		this.idActividad = idActividad;
		this.idEvaluado = idEvaluado;
		this.nota = nota;
		this.observaciones = "";
	}

	public Nota(long idActividad, long idEvaluado, String nota,
			String observaciones) {
		this.idActividad = idActividad;
		this.idEvaluado = idEvaluado;
		this.nota = nota;
		this.observaciones = observaciones;
	}

	public long getIdActividad() {
		return idActividad;
	}

	public long getIdEvaluado() {
		return idEvaluado;
	}

	public String getNota() {
		return nota;
	}

	public void setNota(String nota) {
		this.nota = nota;
	}
	public String getObservaciones() {
		return observaciones;
	}

	@Override
	public void descerializar(String xml) {
		try {
			DocumentBuilderFactory factory = DocumentBuilderFactory
					.newInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();
			InputSource is = new InputSource(new StringReader(xml));
			Document doc = builder.parse(is);
			doc.getDocumentElement().normalize();

			NodeList nodes = doc.getElementsByTagName("Nota");
			for (int i = 0; i < nodes.getLength(); i++) {
				Node node = nodes.item(i);
				if (node.getNodeType() == Node.ELEMENT_NODE) {
					Element element = (Element) node;
					idActividad = Long
							.valueOf(getValue("IdActividad", element));
					idEvaluado = Long.valueOf(getValue("IdEvaluado", element));
					nota = getValue("ValorNota", element);
					observaciones = getValue("Observaciones", element);
				}
			}

		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	@Override
	public String serializar() {
		String idActividadString = "";
		String idElementoEvaluadoString = "";
		if (idActividad >= 0) {
			idActividadString = String.valueOf(idActividad);
		}
		if (idEvaluado >= 0) {
			idElementoEvaluadoString = String.valueOf(idEvaluado);
		}
		return "<?xml version=\"1.0\"?><WS><Nota>" + "<IdActividad>"
				+ idActividadString + "</IdActividad>" + "<IdEvaluado>"
				+ idElementoEvaluadoString + "</IdEvaluado>" + "<ValorNota>"
				+ nota + "</ValorNota>" + "<Observaciones>" + observaciones
				+ "</Observaciones>" + "</Nota></WS>";
	}

	protected static String getValue(String tag, Element element) {
		NodeList nodes = element.getElementsByTagName(tag).item(0)
				.getChildNodes();
		Node node = (Node) nodes.item(0);
		return node.getNodeValue();
	}
}
