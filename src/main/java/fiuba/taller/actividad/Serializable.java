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

	/**
	 * Guarda el estado actual del objeto a la base de datos.
	 */
	public void guardarEstado();

	/**
	 * A partir de los valores de los atributos internos de la instancia, genera
	 * un xml que es enviado integracion. Integracion devuelve un xml con todos
	 * los datos y este es devuelto. NOTA: integracion puede devolver mas de 1
	 * xml según los valores de los parametros internos de la instancia
	 */
	public String realizarConsulta();
}
