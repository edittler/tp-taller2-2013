package fiuba.taller.actividad;

public class ActividadIndividual extends Actividad {

	public ActividadIndividual() {
		
	}
	public boolean agregar_participante (long id_participante){
		return true;
	}
	public static ActividadIndividual getActividad(long idActividad){
		ActividadIndividual actividad = new ActividadIndividual();
		String xml = actividad.getXml(idActividad);
		actividad.descerializar(xml);
		return actividad;
	}
	
}
