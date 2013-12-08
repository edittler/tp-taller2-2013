package fiuba.taller.actividad;

import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import fiuba.taller.actividad.excepcion.XmlErroneoExcepcion;

public abstract class Nota implements Serializable {

	protected long idActividad;
	protected String valor;
	protected String observaciones;
	
	protected static final String NODO_NOTA = "Nota";
	protected static final String NODO_ID_ACTIVIDAD = "IdActividad";
	protected static final String NODO_VALOR = "Valor";
	protected static final String NODO_OBSERVACIONES = "Observaciones";

	protected Nota(long idActividad) {
		this.idActividad = idActividad;
		valor = "";
		observaciones = "";
	}

	public long getIdActividad() {
		return idActividad;
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

	/**
	 * Guarda el estado del objeto en la base de datos.
	 */
	public abstract void guardarEstado();

	protected static String getValue(String tag, Element element)
			throws XmlErroneoExcepcion {
		NodeList nodes = element.getElementsByTagName(tag);
		if (nodes.getLength() != 1) {
			String mensaje = "Debe existir un nodo " + tag + ".";
			throw new XmlErroneoExcepcion(mensaje);
		}
		Element elemento = (Element) nodes.item(0);
		String text = elemento.getTextContent();
		return text;
	}
}
