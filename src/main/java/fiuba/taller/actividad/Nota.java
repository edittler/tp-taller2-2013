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
	String nota;
	String observaciones;
	long idActividad;
	long idElementoEvaluado;
	
	public Nota(){
		idElementoEvaluado = -1;
		nota="";
		idActividad=-1;
		observaciones="";
	}
	
	@Override
	public void descerializar(String xml) {
		try {
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		    DocumentBuilder builder = factory.newDocumentBuilder();
		    InputSource is = new InputSource(new StringReader(xml));
		    Document doc = builder.parse(is);
			doc.getDocumentElement().normalize();
			
			NodeList nodes = doc.getElementsByTagName("Nota");
			for (int i = 0; i < nodes.getLength(); i++) {
				Node node = nodes.item(i);
				if (node.getNodeType() == Node.ELEMENT_NODE) {
					Element element = (Element) node;
					idActividad=Long.valueOf(getValue("IdActividad",element));
					idElementoEvaluado = Long.valueOf(getValue("IdElementoEvaluado",element));
					nota = getValue("ValorNota",element);
					observaciones= getValue("Observaciones",element);
				}
			}
			
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	protected String serializar (){
		String idActividadString="";
		String idElementoEvaluadoString="";
		if(idActividad>=0){
			idActividadString=String.valueOf(idActividad);
		}
		if(idElementoEvaluado>=0){
			idElementoEvaluadoString=String.valueOf(idElementoEvaluado);
		}
		return "<?xml version=\"1.0\"?><WS><Nota>"
				+"<IdActividad>"+idActividadString+"</IdActividad>"
				+"<IdElementoEvaluado>"+idElementoEvaluadoString+"</IdElementoEvaluado>"
				+"<ValorNota>"+nota+"</ValorNota>"
				+"<Observaciones>"+observaciones+"</Observaciones>"
				+"</Nota></WS>";
	}
	
	protected static String getValue(String tag, Element element) {
		NodeList nodes = element.getElementsByTagName(tag).item(0).getChildNodes();
		Node node = (Node) nodes.item(0);
		return node.getNodeValue();
	}
	
	@Override
	public String getXml() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void guardarEstado() {
		// TODO Auto-generated method stub

	}
	
	/* METODOS PARA PRUEBAS */
	public String testSerializar(){
		return this.serializar();
	}

	public String getNota() {
		return nota;
	}

	public String getObservaciones() {
		return observaciones;
	}

	public long getIdActividad() {
		return idActividad;
	}

	public long getIdElementoEvaluado() {
		return idElementoEvaluado;
	}
}
