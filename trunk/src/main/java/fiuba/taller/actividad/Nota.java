package fiuba.taller.actividad;

import java.io.StringReader;
import java.rmi.RemoteException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

public class Nota implements Serializable {

	protected long idActividad;
	protected long idEvaluado;
	protected String valor;
	protected String observaciones;
	protected String tipo;

	protected Nota (long idActividad, String tipo) {
		this.idActividad = idActividad;
		this.tipo = tipo;
		valor = "";
		observaciones = "";
	}

	public long getIdActividad() {
		return idActividad;
	}
	public String getTipo(){
		return tipo;
	}
	public long getIdEvaluado(){
		return idEvaluado;
	}
	public void setIdEvaluado(long idEvaluado){
		this.idEvaluado=idEvaluado;
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
				+ idActividadString + "</IdActividad>" + "<Tipo>" + tipo
				+ "</Tipo>" + "<IdEvaluado>" + idElementoEvaluadoString
				+ "</IdEvaluado>" + "<ValorNota>" + valor + "</ValorNota>"
				+ "<Observaciones>" + observaciones + "</Observaciones>"
				+ "</Nota></WS>";
	}

	@Override
	public void descerializar(String xml) throws RemoteException {
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
					idActividad = Long.valueOf(getValue("IdActividad", element));
					tipo = getValue("Tipo", element);
					idEvaluado = Long.valueOf(getValue("IdEvaluado", element));
					valor = getValue("ValorNota", element);
					observaciones = getValue("Observaciones", element);
				}
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public static Nota crearNota(long idActividad, String tipo,
			String valorNota, long idEvaluado, String observaciones)
			throws RemoteException {
		Nota nuevaNota = new Nota(idActividad, tipo);
		String xml = nuevaNota.realizarConsulta();
		// TODO ver como devuelve integracion un xml no encontrado / encontrado ??¿¿
		if (xml == "encontrado??") {
			nuevaNota.descerializar(xml);
			return nuevaNota;
		}
		nuevaNota.setValor(valorNota);
		nuevaNota.setIdEvaluado(idEvaluado);
		nuevaNota.setObservaciones(observaciones);
		nuevaNota.guardarNuevoEstado();
		return nuevaNota;
	}

	public static Nota getNota(long idActividad, String tipo)
			throws RemoteException {
		Nota nota = new Nota(idActividad, tipo);
		String xml = nota.realizarConsulta();
		nota.descerializar(xml);
		return nota;
	}

	public void guardarEstado() {
		/*
		 * TODO(Jorge) Implementar. Se debe persistir el objeto en la base de
		 * datos.
		 */
	}

	@Override
	public String realizarConsulta() throws RemoteException {
		/*
		 * TODO(Jorge) Implementar
		 */
		return "";
	}

	@Override
	public void guardarNuevoEstado() throws RemoteException {
		// TODO Auto-generated method stub

	}

	@Override
	public void eliminarEstado() throws RemoteException {
		// TODO Auto-generated method stub

	}
}
