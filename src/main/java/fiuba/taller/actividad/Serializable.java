package fiuba.taller.actividad;

public interface Serializable {
	/*
	 * consigue el xml correspondiente a sus parametros asignados
	 */
	public String getXml();
	/* 
	 * absorbe los valores del xml y los asigna a sus atributos internos
	 */
	public void descerializar(String xml);
	/*
	 * guarda el estado de la instacia en integracion
	 */
	public void guardarEstado();

}
