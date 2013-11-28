package fiuba.taller.actividad;

import java.io.StringReader;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import fiuba.taller.xmlParser;

public class Actividad {
	private long id;
	private String nombre;
	//private String descripcion;
	// - coordinadores : ArrayList<Miembro>
	// - cartelera : Cartelera
	// - foro : Foro
	// - actividadesInternas : ArrayList<Actividad>
	// - chat : Chat (necesario ??)
	// usar desp tipo de dato correcto en fechas
	String fechaInicio;
	String fechaFin;
	// - muroSuperior : Informable
	
	public Actividad(){
		id=10; // aca vamos a llamar a integracion para levantar los datos
		nombre="pepe";
		fechaFin="mamam";
		fechaInicio="hola";
	}

	private static String getValue(String tag, Element element) {
		NodeList nodes = element.getElementsByTagName(tag).item(0).getChildNodes();
		Node node = (Node) nodes.item(0);
		return node.getNodeValue();
	}
	
	// ------------------------metodos de webbservice ----------------
	public String getNombre(int id) {
		// cargar datos desde integracion usando id
		// ej: string xml = Integracion (blabla id blabla);
		//     obtener_datos(xml);
		
		// devolver nombre
		return nombre;
	}

	public void setNombre(int id, String nombre) {
		// cargar datos desde integracion usando id
		// ej: string xml = Integracion (blabla id blabla);
		//     obtener_datos(xml);
		this.nombre = nombre;
		// guardar datos a integracion
	}
	//FALTA HACER
	// devuelve xml 
	public String getActividades(int idAmbito ,String tipoAmbito){
		if (tipoAmbito == "Ambito"){
			return "usted pidio las actividades que contiene un Ambito";
		}else{
			return "usted pidio las actividades que contiene una actividad";
		}
	}
	
	// esta devuelve un xml
	public String getPropiedades(int IdActividad){
		// cargo datos de integracion
		// devuelvo la clase serializada(necesario, no combiene directamente enviar xml que devuelve integracion??)
		XMLParser xmlParser = new XMLParser();
		return xmlParser.serializarActividad();
	}
	
}
