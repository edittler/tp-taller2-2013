package fiuba.taller.actividad;

public class ActividadControlador {
	// para metodos comunes de todas las clases llama a actividad
	public String getNombre(int id) {
		Actividad actividad = new Actividad();
		return actividad.getNombre(id);
	}
	public void setNombre(int id, String nombre) {
		Actividad actividad = new Actividad();
		actividad.setNombre(id, nombre);
	}
	public String getActividades(int idAmbito ,String tipoAmbito){
		Actividad actividad = new Actividad();
		return actividad.getActividades(idAmbito, tipoAmbito);
	}
	public int crearActividadIndividual(String propiedades_xml){
		Actividad actividad = new Actividad();
		return actividad.crearActividadIndividual(propiedades_xml);
	}
	public int crearActividadGrupal(String propiedades_xml){
		Actividad actividad = new Actividad();
		return actividad.crearActividadGrupal(propiedades_xml);
	}
	public int crearActividadIndividualEvaluable(String propiedades_xml){
		Actividad actividad = new Actividad();
		return actividad.crearActividadIndividualEvaluable(propiedades_xml);
	}
	public int crearActividadGrupalEvaluable(String propiedades_xml){
		Actividad actividad = new Actividad();
		return actividad.crearActividadGrupalEvaluable(propiedades_xml);
	}
	public int crearActividadGrupalEvaluableGruposExlusivos(String propiedades_xml) {
		Actividad actividad = new Actividad();
		return actividad.crearActividadGrupalEvaluableGruposExlusivos(propiedades_xml);
	}
	private String id_corresponde(long id){
		// recupero el tipo de clase de Integracion
		// puede devolver ActInd ActGrup ActIndEv ActGrupEv ActGrupEvExcl o none
		return "ActInd";
	}
	// para metodos propios de herederos de actividad llams segun id
	public boolean agregar_participante (long id ,long id_participante){
		if(id_corresponde(id)=="ActInd"){
			ActividadIndividual act_individual = new ActividadIndividual();
			return act_individual.agregar_participante(id, id_participante);
		}else{
			// aca podria levantar una excepcion o algo asi
			return false;
		}
	}
}
