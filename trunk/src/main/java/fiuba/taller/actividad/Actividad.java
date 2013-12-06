package fiuba.taller.actividad;

import java.io.IOException;
import java.io.StringReader;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

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
public class Actividad implements Serializable {

	protected long id;
	protected long idAmbitoSuperior;
	protected long idActividadSuperior;
	protected String nombre;
	protected String tipo;
	protected String descripcion;
	protected String fechaInicio;
	protected String fechaFin;
	protected List<Long> coordinadores;
	protected long idMuro;
	protected long idCartelera;
	protected long idForo;
	protected List<Long> actividadesInternas;
	protected long idChat;

	public Actividad() {
		id = -1;
		idAmbitoSuperior = -1;
		idActividadSuperior = -1;
		nombre = "";
		tipo = "";
		descripcion = "";
		fechaFin = "";
		fechaInicio = "";
	}

	public long getId() {
		return id;
	}
	
	@Deprecated
	public long getIdAmbitoSuperior() {
		return idAmbitoSuperior;
	}

	@Deprecated
	public void setIdAmbitoSuperior(long idAmbitoSuperior) {
		this.idAmbitoSuperior = idAmbitoSuperior;
	}

	@Deprecated
	public long getIdActividadSuperior() {
		return idActividadSuperior;
	}

	@Deprecated
	public void setIdActividadSuperior(long idActividadSuperior) {
		this.idActividadSuperior = idActividadSuperior;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public String getTipo() {
		return tipo;
	}

	public String getDescripcion() {
		return descripcion;
	}
	
	public String getFechaInicio() {
		return fechaInicio;
	}

	public String getFechaFin() {
		return fechaFin;
	}

	public void setFecha(String fechaInicio) {
		/*
		 * TODO(Pampa) Implementar Hay que validad si las fecha se encuentra en 
		 * el formato correcto. Si no, lanzar una excepcion.
		 * La fecha de fin se setea igual a la fecha de inicio.
		 */
	}

	public void setFecha(String fechaInicio, String fechaFin) {
		/*
		 * TODO(Pampa) Implementar Hay que validad si las fechas se encuentran
		 * en el formato correcto y si la fecha de inicio es menor a la fecha de
		 * fin. Si no, lanzar una excepcion
		 */
	}

	public List<Miembro> getCoordinadores() {
		// TODO(Pampa) Implementar
		return new ArrayList<>();
	}

	public void agregarCoordinador(long idCoordinador) {
		/*
		 * TODO(Pampa) Implementar. Si el id ya existe, debe lanzar una
		 * excepcion.
		 */
	}

	public void eliminarCoordinador(long idCoordinador) {
		/*
		 * TODO(Pampa) Implementar. Si el id no existe, debe lanzar una
		 * excepcion. Si es el único coordinador, no se puede borrar. Lanzar una
		 * excepcion.
		 */
	}

	public long getIdMuro() {
		return idMuro;
	}

	public long getIdCartelera() {
		return idCartelera;
	}

	public long getIdForo() {
		return idForo;
	}

	public List<Actividad> getActividades() {
		// TODO(Pampa) Implementar
		return new ArrayList<>();
	}

	public long getIdChat() {
		return idChat;
	}

	@Deprecated
	public String pruebaIntegracion() {
		String xml = "<?xml version=\"1.0\"?><WS><Usuario><username>usuario_prueba1</username><password>1234</password><activado>true</activado><habilitado>true</habilitado></Usuario></WS>";
		/*
		 * GuardarDatosResponse response = new GuardarDatosResponse();
		 * GuardarDatos envio = new GuardarDatos(); envio.setXml(xml);
		 * IntegracionStub servicio; try { servicio = new IntegracionStub();
		 * response = servicio.guardarDatos(envio); } catch (RemoteException e)
		 * {
		 * System.out.print("Ocurrio un Error en el metodo pruebaIntegracion\n"
		 * ); e.printStackTrace(); return "ERROR"; }
		 */
		return "Integracion Contesto";// +response.get_return()+"\n";

	}

	// serializa a la actividad
	// devuelve xml
	// este metodo intenta ser util tanto para cuando:
	// -> se envian las propiedades de la actividad a presentacion
	// -> se guardan los datos a integracion y se envian los datos de consulta a
	// integracion
	@Override
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
				+ "<Id>" + identif + "</Id>" 
				+ "<IdAmbitoSuperior>" + idAmbSupStr + "</IdAmbitoSuperior>" 
				+ "<IdActividadSuperior>" + idActSupStr + "</IdActividadSuperior>"
				+ "<Nombre>" + nombre + "</Nombre>"
				+ "<Tipo>" + tipo + "</Tipo>" 
				+ "<Descripcion>" + descripcion + "</Descripcion>" 
				+ "<FechaInicio>" + fechaInicio + "</FechaInicio>"
				+ "<FechaFin>" + fechaFin + "</FechaFin>"
				+ "</Actividad></WS>";
		return xml;
	}

	// Recibe el xml obtenido de integracion, cuyo contenido es los datos de la
	// clase actividad
	// es privado pero por motivo de testing lo pongo publico
	@Override
	public void descerializar(String xml) throws XmlErroneoExcepcion {
		Document doc = getDocumentElement(xml);
		descerializar(doc);
	}
	
	/**
	 * Guarda el estado actual del objeto a la base de datos.
	 */
	public void guardarEstado() {
		/*
		 * GuardarDatosResponse response = new GuardarDatosResponse();
		 * GuardarDatos envio = new GuardarDatos(); envio.setXml(serializar());
		 * IntegracionStub servicio; try { servicio = new IntegracionStub();
		 * response = servicio.guardarDatos(envio); } catch (RemoteException e)
		 * { System.out.print("Ocurrio un Error en el metodo setNombre\n");
		 * e.printStackTrace(); } System.out.print(response.get_return());
		 */
	}

	/**
	 * A partir de los valores de los atributos internos de la instancia, genera
	 * un XML que es enviado a Integracion. Integracion devuelve un XML con
	 * todos los datos y este es devuelto. NOTA: integracion puede devolver mas
	 * de 1 XML según los valores de los parametros internos de la instancia.
	 */
	public String realizarConsulta() {
		// "No implementado todavia :)";
		// devuelve siempre lo mismo
		String xml = this.serializar();
		// TODO llamar a integrar y conseguir el xml completo
		String xmlDevuelto = "<?xml version=\"1.0\"?><WS><Actividad>" 
				+ "<Id>" + 45 + "</Id>" 
				+ "<IdSuperior>" + 88 + "</IdSuperior>"
				+ "<Nombre>" + "pepe" + "</Nombre>" 
				+ "<Tipo>" + "ActividadIndividual" + "</Tipo>" 
				+ "<Descripcion>" + "esto es una descripcion" + "</Descripcion>"
				+ "<FechaInicio>" + fechaInicio + "</FechaInicio>"
				+ "<FechaFin>" + fechaFin + "</FechaFin>" 
				+ "</Actividad></WS>";
		return xmlDevuelto;
	}

	/* METODOS DE CLASE (ESTATICOS) */

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

	/* METODOS PROTEGIDOS AUXILIARES */

	protected void setId(long id) {
		this.id = id;
	}

	// metodo interno de ayuda para el parseo
	protected static String getValue(String tag, Element element) {
		NodeList nodes = element.getElementsByTagName(tag).item(0)
				.getChildNodes();
		Node node = (Node) nodes.item(0);
		return node.getNodeValue();
	}

	protected void levantarEstado(long idActividad) throws XmlErroneoExcepcion {
		id = idActividad;
		descerializar(realizarConsulta());
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

	/* METODOS PUBLICOS DE TESTING */

	public String toString() {
		return "ID: " + this.id + "\n" 
				+ "ID AMBITO SUP: " + this.idAmbitoSuperior + "\n" 
				+ "NOMBRE: " + this.nombre + "\n" 
				+ "FECHA INI: " + this.fechaInicio + "\n"
				+ "FECHA FIN: " + this.fechaFin + "\n" 
				+ "DESCRIPCION: " + this.descripcion + "\n";
	}
	
}
