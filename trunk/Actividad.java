package wtp;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;


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
		//claramente no podemos utilizar construcores para los atributos, pero para parametros que todas las instancias compartitian podrian estar aca
		
		// esto es solo para demostracion
		id=-1; // aca vamos a llamar a integracion para levantar los datos
		nombre="";
		fechaFin="";
		fechaInicio="";
	}

	private static String getValue(String tag, Element element) {
		NodeList nodes = element.getElementsByTagName(tag).item(0).getChildNodes();
		Node node = (Node) nodes.item(0);
		return node.getNodeValue();
	}

	// Recibe el xml obtenido de integracion, cullo contenido es los datos de la clase actividad
	private Boolean descerializar(String xml) {
		// procesar xml y asignar sus datos a los atributos internos de
		// Actividad
		try {
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(xml);
			doc.getDocumentElement().normalize();
			
			NodeList nodes = doc.getElementsByTagName("Actividad");
			for (int i = 0; i < nodes.getLength(); i++) {
				Node node = nodes.item(i);

				if (node.getNodeType() == Node.ELEMENT_NODE) {
					Element element = (Element) node;
					System.out.println("Actividad id: "+ getValue("id", element));
					System.out.println("Actividad nombre: "+ getValue("nombre", element));
					this.nombre=getValue("nombre", element);
					System.out.println("Actividad fechaini: "+ getValue("fechaini", element));
					this.fechaInicio=getValue("fechaini", element);
					System.out.println("Actividad fechafin: "+ getValue("fechafin", element));
					this.fechaFin=getValue("fechafin", element);
				}
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return true;
	}

	// serializa a la actividad
	// devuelve xml
	// este metodo intente ser util tanto para cuando:
	// 		-> se envian las propiedades de la actividad a presentacion
	// 		-> se guardan los datos a integracion y se envian los datos de consulta a integracion
	private String serializar () {
		String identif="";
		if(id>=0){
			identif=String.valueOf(id);
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
	public int crearActividadIndividual(String propiedades_xml){
		return 0;
	}
	public int crearActividadGrupal(String propiedades_xml){
		return 0;
	}
	public int crearActividadIndividualEvaluable(String propiedades_xml){
		return 0;
	}
	public int crearActividadGrupalEvaluable(String propiedades_xml){
		return 0;
	}
	public int crearActividadGrupalEvaluableGruposExlusivos(String propiedades_xml) {
		return 0;
	}
	// esta devuelve un xml
	public String getPropiedades(int IdActividad){
		// cargo datos de integracion
		// devuelvo la clase serializada(necesario, no combiene directamente enviar xml que devuelve integracion??)
		return serializar();
	}
	
}
