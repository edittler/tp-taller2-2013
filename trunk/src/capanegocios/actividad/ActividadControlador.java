package fiuba.taller.actividad;

public class ActividadControlador {
	// para metodos comunes de todas las clases llama a actividad
	public String getNombre(int id) {
		Actividad actividad = Actividad.getActividad(id);
		return actividad.getNombre();
	}

	public void setNombre(int id, String nombre) {
		Actividad actividad = Actividad.getActividad(id);
		actividad.setNombre(nombre);
		actividad.guardarEstado();
	}

	public String getActividades(int idAmbito, String tipoAmbito) {
		if (tipoAmbito.equalsIgnoreCase("Ambito")) {
			Actividad act = new Actividad();
			act.setIdAmbitoSuperior(idAmbito);
			String xml = act.getXml();
			// por ahora devolvemos los xml directamente
			return xml;
		} else if (tipoAmbito.equalsIgnoreCase("Actividad")) {
			Actividad act = new Actividad();
			act.setIdActividadSuperior(idAmbito);
			String xml = act.getXml();
			// por ahora devolvemos los xml directamente
			return xml;
		}
		// ver de devolver una excepcion o similar
		return "tipo de ambito no valido : " + tipoAmbito;
	}

	public long crearActividadIndividual(String propiedades_xml) {
		ActividadIndividual act = new ActividadIndividual();
		act.descerializar(propiedades_xml);
		// [ ] chequeo de logica en propiedades ???
		// [ ] chequeo de previa existencia ????
		// [ ] asignacion de un unico id de Actividad ????
		// [OK] se manda a persistir a integracion
		act.guardarEstado();
		return act.getId();
	}

	// IDEM PARA EL RESTO (FALTA HACER)
	// ====================================================
	public int crearActividadGrupal(String propiedades_xml) {
		Actividad actividad = new Actividad();
		return actividad.crearActividadGrupal(propiedades_xml);
	}

	public int crearActividadIndividualEvaluable(String propiedades_xml) {
		Actividad actividad = new Actividad();
		return actividad.crearActividadIndividualEvaluable(propiedades_xml);
	}

	public int crearActividadGrupalEvaluable(String propiedades_xml) {
		Actividad actividad = new Actividad();
		return actividad.crearActividadGrupalEvaluable(propiedades_xml);
	}

	public int crearActividadGrupalEvaluableGruposExlusivos(
			String propiedades_xml) {
		Actividad actividad = new Actividad();
		return actividad
				.crearActividadGrupalEvaluableGruposExlusivos(propiedades_xml);
	}

	// ========================================================================

	// metodo que levanta una excepcion (FALTA HACER)
	public void agregarParticipante(long id, long id_participante) {
		ActividadIndividual actInd = ActividadIndividual.getActividad(id);
		actInd.agregar_participante(id_participante);
	}

	public void eliminarParticipante(long idActividad, long idParticipante) {
		// TODO Implementar
	}

	public void getParticipantes(long idActividad) {
		// TODO Implementar
	}

	public void getParticipante(long idActividad, long idParticipante) {
		// TODO Implementar
	}

	/* ACTIVIDAD GRUPAL E HIJAS */

	public void agregarGrupo(long idActividad, long idGrupo) {
		// TODO Implementar
	}

	public void eliminarGrupo(long idActividad, long idGrupo) {
		// TODO Implementar
	}

	public void getGrupos(long idActividad) {
		// TODO Implementar
	}

	public void getGrupo(long idActividad, long idGrupo) {
		// TODO Implementar
	}

	/* ACTIVIDADES EVALUABLES */

	// Evaluado puede ser un participante o un grupo, dependiendo si la
	// actividad es ind o grupal
	public void evaluar(long idActividad, long idEvaluado) {
		// TODO Implementar
	}

	public void getNota(long idActividad, long idEvaluado) {
		// TODO Implementar
	}

	// Verificar que la actividad sea evaluable
	public void getNotas(long idActividad) {
		// TODO Implementar
	}
}
