package fiuba.taller.actividad;

public interface Serializable {
	/*
	 * A partir de los valores de los parametros internos de la instancia, genera un xml
	 * este xml es enviado a integracion, integracion devuelve un xml con TODOS los datos
	 * y este es devuelto.
	 * NOTA: integracion puede devolver mas de 1 xml según los valores de los parametros 
	 * internos de la instancia
	 */
	public String realizarConsulta();
	/*
	 * devuelve el xml de su estado actual
	 */
	public String serializar();
	/* 
	 * absorbe los valores del xml y los asigna a sus atributos internos
	 */
	public void descerializar(String xml);
	/*
	 * guarda el estado de la instacia en integracion
	 */
	public void guardarEstado();

}
