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
	
	/*	METODOS DE CREACION DE ACTIVIDADES	*/
	
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
	
	/*	METODOS DE CADA ACTIVIDAD PARTICULAR	*/
	
	/*	ACTIVIDAD INDIVIDUAL E HIJAS	*/
	
	public boolean agregarParticipante(long idActividad ,long idParticipante){
		if(id_corresponde(idActividad)=="ActInd"){
			ActividadIndividual actividadIndividual = new ActividadIndividual();
			return actividadIndividual.agregarParticipante(idActividad, idParticipante);
		}else{
			// aca podria levantar una excepcion o algo asi
			return false;
		}
	}
	
	public void eliminarParticipante(long idActividad, long idParticipante) {
		
	}
	
	public void getParticipantes(long idActividad) {
		
	}
	
	public void getParticipante(long idActividad, long idParticipante) {
		
	}
	
	private String id_corresponde(long id){
		// recupero el tipo de clase de Integracion
		// puede devolver ActInd ActGrup ActIndEv ActGrupEv ActGrupEvExcl o none
		return "ActInd";
	}
	
	/*	ACTIVIDAD GRUPAL E HIJAS	*/
	
	public void agregarGrupo(long idActividad, long idGrupo) {
		
	}
	
	public void eliminarGrupo(long idActividad, long idGrupo) {
		
	}
	
	public void getGrupos(long idActividad) {
		
	}
	
	public void getGrupo(long idActividad, long idGrupo) {
		
	}
	
	/*	ACTIVIDADES EVALUABLES	*/
	
	// Evaluado puede ser un participante o un grupo, dependiendo si la actividad es ind o grupal
	public void evaluar(long idActividad, long idEvaluado) {
		
	}
	
	public void getNota(long idActividad, long idEvaluado) {
		
	}
	
	// Verificar que la actividad sea evaluable
	public void getNotas(long idActividad) {
		
	}
}
