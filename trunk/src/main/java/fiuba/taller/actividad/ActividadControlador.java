package fiuba.taller.actividad;

/**
 * Define la interfaz para poder manipular el webservice del paquete Actividad.
 */
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

	/**
	 * 
	 * @param propiedades_xml
	 * @return
	 */
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

	public String agregarParticipante(long idActividad ,long idParticipante){
		if(!esActividadIndividual(idActividad)){
			// FIXME Lanzar mensaje de error o algo por el estilo.
			return "Error";
		}
		/*
		 * FIXME Corregir la forma de carga de actividad desde integracion.
		 * FIXME Corregir el retorno de agregarParticipante de 
		 * ActividadIndividual. Lo ideal es que lance una excepción y en este
		 * método se capture para luego enviar el mensaje correspondiente.
		 */
		ActividadIndividual actividadIndividual = new ActividadIndividual();
		actividadIndividual.agregarParticipante(idActividad, idParticipante);
		return "Ok";
	}

	public void eliminarParticipante(long idActividad, long idParticipante) {

	}

	public void getParticipantes(long idActividad) {

	}

	public void getParticipante(long idActividad, long idParticipante) {

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

	/*  METODOS AUXILIARES PRIVADOS */

	private boolean esActividadIndividual(long idActividad) {
		// FIXME Levantar datos de integración y verificar el tipo de actividad
		return true;
	}
}
