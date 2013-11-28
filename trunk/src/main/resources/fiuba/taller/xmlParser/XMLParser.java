package fiuba.taller.xmlParser;

import java.io.StringReader;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

/* TODO: Encapsular el agregado de tags de cierre de XML en metodo aparte y el agregado de info de cada clase en 
 * metodos apartes!
*/

public class XMLParser {

	public XMLParser() {
		
	}
	
	// Recibe el xml obtenido de integracion, cuyo contenido es los datos de la clase actividad
	public String descerializar(String xml) {
		// procesar xml y asignar sus datos a los atributos internos de
		// Actividad
		String info="";
		try {
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		    DocumentBuilder builder = factory.newDocumentBuilder();
		    InputSource is = new InputSource(new StringReader(xml));
		    Document doc = builder.parse(is);
			doc.getDocumentElement().normalize();
			
			NodeList nodes = doc.getElementsByTagName("Actividad");
			for (int i = 0; i < nodes.getLength(); i++) {
				Node node = nodes.item(i);
				if (node.getNodeType() == Node.ELEMENT_NODE) {
					Element element = (Element) node;
					info+="Actividad id: "+ getValue("id", element)+"\n";
					info+="Actividad nombre: "+ getValue("nombre", element)+"\n";
					this.nombre=getValue("nombre", element);
					info+="Actividad fechaini: "+ getValue("fechaini", element)+"\n";
					this.fechaInicio=getValue("fechaini", element);
					info+="Actividad fechafin: "+ getValue("fechafin", element)+"\n";
					this.fechaFin=getValue("fechafin", element);
				}
			}
				
		} catch (Exception ex) {
			ex.printStackTrace();
		}
			
		return info;
	}

	// serializa a la actividad
	// devuelve xml
	// este metodo intente ser util tanto para cuando:
	// 		-> se envian las propiedades de la actividad a presentacion
	// 		-> se guardan los datos a integracion y se envian los datos de consulta a integracion
	public String serializarActividad(long idActividad, String nombre, String fechaInicio, String fechaFin) {
		String identif="";
		if(idActividad>=0){
			identif=String.valueOf(idActividad);
		}
		String xml ="<?xml version=\"1.0\"?><WS>"
					+ "<Actividad>"
					+ "<id>"+ identif +"</id>"
					+ "<nombre>"+ nombre + "</nombre>"
					+ "<fechaini>"+ fechaInicio +"</fechaini>"
					+ "<fechafin>"+ fechaFin +"</fechafin>"
					+ "</Actividad></WS>";
		return xml;
	}
	
	// Ademas de los atributos comunes a Actividad, se serializan los correspondientes a una actividad individual
	public String serializarActividadIndividual(long idActividad, String nombre, String fechaInicio, String fechaFin) {
		//String xmlFinal = this->serializarActividad(idActividad, nombre, fechaInicio, fechaFin);
		// xmlFinal += <Todos los atributos del una actividad individual>;
		return xmlFinal;
	}
	
	public String serializarActividadIndividualEvaluable(long idActividad, String nombre, String fechaInicio, String fechaFin) {
		//String xmlFinal = this->serializarActividad(idActividad, nombre, fechaInicio, fechaFin);
		// xmlFinal += <Todos los atributos del una actividad individual evaluable>;
		return xmlFinal;
	}
}
