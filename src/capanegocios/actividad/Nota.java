package capanegocios.actividad;

public abstract class Nota {
	String nota;
	long idActividad;
	String observaciones;
	public String getXml() {
		return "no implementado";
	}
	public void descerializa(String xml) {

	}
	protected abstract String serializar ();
	
	public void guardarEstado() {
		// manda a guardar la informacion a integracion
	}
	public abstract void setNota (long idActividad);

	
}
