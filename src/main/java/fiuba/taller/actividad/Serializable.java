package fiuba.taller.actividad;

import fiuba.taller.actividad.excepcion.XmlErroneoExcepcion;

public interface Serializable {

	/**
	 * Serializa el objeto en su estado actual.
	 * 
	 * @return String XML con el objeto serializado.
	 */
	public String serializar();

	/**
	 * Carga el estado del objeto a partir del XML.
	 * 
	 * @param xml
	 *            XML en un String que contiene el estado del objeto.
	 * @throws XmlErroneoExcepcion
	 *             cuando el XML es erroneo.
	 */
	public void descerializar(String xml) throws XmlErroneoExcepcion;
}
