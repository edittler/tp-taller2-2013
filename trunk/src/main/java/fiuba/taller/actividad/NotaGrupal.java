package fiuba.taller.actividad;

import java.io.StringReader;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

public class NotaGrupal extends Nota {

private long idGrupo;
	
	public NotaGrupal() {
		this.idActividad = -1;
		this.idGrupo = -1;
		this.nota = "";
		this.observaciones = "";
	}
	
	public NotaGrupal(long idActividad, long idGrupo, String nota) {
		this.idActividad = idActividad;
		this.idGrupo = idGrupo;
		this.nota = nota;
		this.observaciones = "";
	}
	
	public NotaGrupal(long idActividad, long idGrupo, String nota,
			String observaciones) {
		this.idActividad = idActividad;
		this.idGrupo = idGrupo;
		this.nota = nota;
		this.observaciones = observaciones;
	}
	
	public long getIdGrupo() {
		return idGrupo;
	}
	
	public NotaGrupal crearNota(long idActividad, long idGrupo) {
		// TODO Corregir
		NotaGrupal nota = new NotaGrupal(idActividad, idGrupo, "");
		return nota;
	}
	
	public void eliminarNota(long idActividad, long idGrupo) {
		// TODO
	}
	
	public NotaGrupal getNota(long idActividad, long idGrupo) {
		// TODO Corregir
		NotaGrupal nota = new NotaGrupal(idActividad, idGrupo, "");
		return nota;
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
					idGrupo = Long.valueOf(getValue("IdEvaluado", element));
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
		if (idGrupo >= 0) {
			idElementoEvaluadoString = String.valueOf(idGrupo);
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
