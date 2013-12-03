package fiuba.taller.actividad;

import java.io.IOException;
import java.io.StringReader;
import java.rmi.RemoteException;
import java.util.Vector;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.apache.axis2.AxisFault;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import fiuba.taller.actividad.excepcion.XmlErroneoExcepcion;

//import com.ws.services.*;

@SuppressWarnings("unused")
enum AmbitoSuperior {
	AMBITO,
	ACTIVIDAD
}

@SuppressWarnings("unused")
public class Actividad implements Serializable{

	protected long id;
	protected String nombre;
	protected String tipo;
	protected String descripcion;
	protected long idAmbitoSuperior;
	protected long idActividadSuperior;
	// - coordinadores : ArrayList<Miembro>
	// - cartelera : Cartelera
	// - foro : Foro
	// - actividadesInternas : ArrayList<Actividad>
	// - chat : Chat (necesario ??)
	// usar desp tipo de dato correcto en fechas
	protected String fechaInicio;
	protected String fechaFin;

	// - muroSuperior : Informable
	public Actividad() {
		id = -1;
		idAmbitoSuperior = -1;
		idActividadSuperior = -1;
		nombre = "";
		fechaFin = "";
		fechaInicio = "";
		descripcion = "";
		tipo = "";
	}

	@Deprecated
	public String pruebaIntegracion() {
		String xml = "<?xml version=\"1.0\"?><WS><Usuario><username>usuario_prueba1</username><password>1234</password><activado>true</activado><habilitado>true</habilitado></Usuario></WS>";
		/*GuardarDatosResponse response = new GuardarDatosResponse();
		 GuardarDatos envio = new GuardarDatos();
		 envio.setXml(xml);
		 IntegracionStub servicio;
		 try {
		 servicio = new IntegracionStub();
		 response = servicio.guardarDatos(envio);
		 } catch (RemoteException e) {
		 System.out.print("Ocurrio un Error en el metodo pruebaIntegracion\n");
		 e.printStackTrace();
		 return "ERROR";
		 }
		*/
		return "Integracion Contesto";// +response.get_return()+"\n";

	}

	// Recibe el xml obtenido de integracion, cullo contenido es los datos de la
	// clase actividad
	// es privado pero por motivo de testing lo pongo publico
	public void descerializar(String xml) throws XmlErroneoExcepcion {
		Document doc = getDocumentElement(xml);
		descerializar(doc);
	}

	// serializa a la actividad
	// devuelve xml
	// este metodo intente ser util tanto para cuando:
	// -> se envian las propiedades de la actividad a presentacion
	// -> se guardan los datos a integracion y se envian los datos de consulta a
	// integracion
	public String serializar() {
		String identif = "";
		String idAmbSupStr = "";
		String idActSupStr = "";
		if (id >= 0) {
			identif = String.valueOf(id);
		}
		if (idAmbitoSuperior >= 0) {
			idAmbSupStr = String.valueOf(idAmbitoSuperior);
		}
		if (idActividadSuperior >= 0) {
			idActSupStr = String.valueOf(idActividadSuperior);
		}
		String xml = "<?xml version=\"1.0\"?><WS><Actividad>"
				+"<Id>" + identif + "</Id>"
				+"<IdAmbitoSuperior>" + idAmbSupStr + "</IdAmbitoSuperior>"
				+"<IdActividadSuperior>" + idActSupStr + "</IdActividadSuperior>"
				+"<Nombre>" + nombre + "</Nombre>"
				+"<Tipo>" + tipo + "</Tipo>"
				+"<Descripcion>" + descripcion + "</Descripcion>"
				+"<FechaInicio>" + fechaInicio + "</FechaInicio>"
				+"<FechaFin>" + fechaFin + "</FechaFin>" 
				+"</Actividad></WS>";
		return xml;
	}

	public void guardarEstado() {
		/* GuardarDatosResponse response = new GuardarDatosResponse();
		 GuardarDatos envio = new GuardarDatos();
		 envio.setXml(serializar());
		 IntegracionStub servicio;
		 try {
		 servicio = new IntegracionStub();
		 response = servicio.guardarDatos(envio);
		 } catch (RemoteException e) {
		 System.out.print("Ocurrio un Error en el metodo setNombre\n");
		 e.printStackTrace();
		 }
		 System.out.print(response.get_return());
		 */
	}

	/*
	 * Con el estado interno actual que tiene la instancia , la serializa y
	 * manda su xml a integracion, integracion devuelve un xml con los datos
	 * completos de los campos vacios enviados
	 */
	public String realizarConsulta() {
		// "No implementado todavia :)";
		// devuelve siempre lo mismo
		String xml = this.serializar();
		//TODO llamar a integrar y conseguir el xml completo
		String xmlDevuelto = "<?xml version=\"1.0\"?><WS><Actividad>"
				+ "<Id>" + 45 + "</Id>" 
				+ "<IdSuperior>" + 88 + "</IdSuperior>"
				+ "<Nombre>" + "pepe" + "</Nombre>"
				+ "<Tipo>"+ "ActividadIndividual" + "</Tipo>" 
				+ "<Descripcion>"+ "esto es una descripcion" + "</Descripcion>" 
				+ "<FechaInicio>"+ fechaInicio + "</FechaInicio>" 
				+ "<FechaFin>"+ fechaFin + "</FechaFin>" 
				+ "</Actividad></WS>";
		return xmlDevuelto;
	}

	/*
	 * metodo para realizar una consulta a integracion que dependa no solamente
	 * del id de la actividad sino del estado interno de la actividad
	 */
	public String realizarConsulta(long id) {
		this.id = id;
		return realizarConsulta();
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public void setIdAmbitoSuperior(long idAmbitoSuperior) {
		this.idAmbitoSuperior = idAmbitoSuperior;
	}

	public void setIdActividadSuperior(long idActividadSuperior) {
		this.idActividadSuperior = idActividadSuperior;
	}

	/*  METODOS DE CLASE (ESTATICOS)  */

	public static Actividad getActividad(long idActividad)
			throws XmlErroneoExcepcion {
		/*
		 * FIXME Si no existe la actividad con el ID especificado, se debe
		 * lanzar la excepcion ActividadInexistenteExcepcion
		 */
		Actividad actividad = new Actividad();
		actividad.levantarEstado(idActividad);
		return actividad;
	}

	public static String getActividades(long idAmbito, AmbitoSuperior tipo) {
		String xml = "";
		switch (tipo) {
		case AMBITO:
			xml = getActividadesDeAmbito(idAmbito);
			break;
		case ACTIVIDAD:
			xml = getActividadesDeActividad(idAmbito);
			break;
		default:
			xml = "";
			break;
		}
		return xml;
	}

	/* METODOS PROTEGIDOS AUXILIARES */

	// metodo interno de ayuda para el parseo
	protected static String getValue(String tag, Element element) {
		NodeList nodes = element.getElementsByTagName(tag).item(0)
				.getChildNodes();
		Node node = (Node) nodes.item(0);
		return node.getNodeValue();
	}

	protected void levantarEstado(long idActividad) throws XmlErroneoExcepcion {
		descerializar(realizarConsulta(idActividad));
	}

	protected static Document getDocumentElement(String xml)
			throws XmlErroneoExcepcion {
		Document doc = null;
		try {
			DocumentBuilder builder = DocumentBuilderFactory.newInstance()
					.newDocumentBuilder();
			InputSource is = new InputSource(new StringReader(xml));
			doc = builder.parse(is);
			doc.getDocumentElement().normalize();
		} catch (ParserConfigurationException | SAXException | IOException e) {
			throw new XmlErroneoExcepcion("Error al parsear el XML.");
		}
		return doc;
	}

	protected void descerializar(Document doc) throws XmlErroneoExcepcion {
		NodeList nodes = doc.getElementsByTagName("Actividad");
		if (nodes.getLength() != 1) {
			throw new XmlErroneoExcepcion(
					"Debe haber solamente un nodo Actividad");
		}
		Node node = nodes.item(0);
		if (node.getNodeType() == Node.ELEMENT_NODE) {
			Element element = (Element) node;
			this.id = Long.valueOf(getValue("Id", element));
			this.idAmbitoSuperior = Long.valueOf(getValue("IdAmbitoSuperior",
					element));
			this.idActividadSuperior = Long.valueOf(getValue(
					"IdActividadSuperior", element));
			this.nombre = getValue("Nombre", element);
			this.tipo = getValue("Tipo", element);
			this.fechaInicio = getValue("FechaInicio", element);
			this.fechaFin = getValue("FechaFin", element);
			this.descripcion = getValue("Descripcion", element);
		}
	}

	/* METODOS PRIVADOS AUXILIARES */

	private static String getActividadesDeAmbito(long idAmbito) {
		// TODO implementar
		return "";
	}
	
	private static String getActividadesDeActividad(long idActividad) {
		// TODO implementar
		return "";
	}

	/*  METODOS PUBLICOS DE TESTING  */

	public String toString() {
		return "ID: "+ this.id+ "\n"
				+"ID AMBITO SUP: " +this.idAmbitoSuperior+"\n"
				+"NOMBRE: "+ this.nombre + "\n"
				+"FECHA INI: "+ this.fechaInicio + "\n"
				+"FECHA FIN: "+ this.fechaFin + "\n"
				+"DESCRIPCION: "+ this.descripcion + "\n";
		
	}

	public long getIdAmbitoSuperior(){
		return idAmbitoSuperior;
	}

	public long getIdActividadSuperior(){
		return idActividadSuperior;
	}

	public String getTipo(){
		return tipo;
	}

	public String getDescripcion(){
		return descripcion;
	}

	public String getFechaInicio(){
		return fechaInicio;
	}

	public String getFechaFin(){
		return fechaFin;
	}
}
