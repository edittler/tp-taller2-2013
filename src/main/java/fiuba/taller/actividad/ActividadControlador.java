package fiuba.taller.actividad;

public class ActividadControlador {

	/* METODOS COMUNES A TODAS LAS ACTIVIDADES */

	/**
	 * @param idActividad
	 *            Identificador de la actividad
	 * @return String con el nombre de la actividad
	 */
	public String getNombre(long idActividad) {
		Actividad actividad = Actividad.getActividad(idActividad);
		return actividad.getNombre();
	}

	/**
	 * @param idActividad
	 *            Identificador de la actividad
	 * @param nombre
	 *            Nuevo nombre a asignar a la actividad
	 */
	public void setNombre(long idActividad, String nombre) {
		Actividad actividad = Actividad.getActividad(idActividad);
		actividad.setNombre(nombre);
		actividad.guardarEstado();
	}

	public String getActividades(int idAmbito, String tipoAmbito) {
		/*
		 * FIXME Para que no haya mucho margen de errores por lo que se pasa
		 * como parámetro en "String tipoAmbito", se podria crear un enumerado
		 * publico que defina el tipo "AMBITO" y "ACTIVIDAD" o colocar un
		 * booleano llamado "superiorEsAmbito" pero genera ambiguedades ya que
		 * una actividad tambien es un ambito. --Pampa
		 */
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

	/* CREADORES DE ACTIVIDAD */

	/**
	 * Crea una actividad individual con las propiedades especificadas.
	 * 
	 * @param propiedadesXml
	 *            XML que contiene las propiedades de la actividad a crear
	 * @return Identificador de la actividad creada
	 */
	public long crearActividadIndividual(String propiedadesXml) {
		ActividadIndividual act = new ActividadIndividual();
		act.descerializar(propiedadesXml);
		// TODO Terminar de implementar
		// [ ] chequeo de logica en propiedades ???
		// [ ] chequeo de previa existencia ????
		// [ ] asignacion de un unico id de Actividad ????
		// [OK] se manda a persistir a integracion
		act.guardarEstado();
		return act.getId();
	}

	public long crearActividadGrupal(String propiedadesXml) {
		// TODO Terminar de implementar
		return 0;
	}

	public long crearActividadIndividualEvaluable(String propiedadesXml) {
		// TODO Terminar de implementar
		return 0;
	}

	public long crearActividadGrupalEvaluable(String propiedades_xml) {
		// TODO Terminar de implementar
		return 0;
	}

	public long crearActividadGrupalEvaluableGruposExlusivos(
			String propiedades_xml) {
		/*
		 * FIXME Este método no se estaría repitiendo con
		 * "crearActividadGrupalEvaluable" ya que solo hay que setear distinto
		 * un booleano?
		 */

		return 0;
	}

	/* METODOS COMUNES A LAS ACTIVIDADES INDIVIDUALES */

	// metodo que levanta una excepcion (FALTA HACER)
	public void agregarParticipante(long idActividad, long idParticipante) {
		ActividadIndividual actividad = ActividadIndividual.getActividad(idActividad);
		actividad.agregarParticipante(idParticipante);
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

	/* METODOS COMUNES A LAS ACTIVIDADES GRUPALES */

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

	/* METODOS COMUNES A LAS ACTIVIDADES EVALUABLES */

	// Evaluado puede ser un participante o un grupo, dependiendo si la
	// actividad es ind o grupal
	public void evaluar(long idActividad, long idEvaluado, String nota) {
		Actividad actividad = new Actividad();
		String xml = actividad.getXml(idActividad);
		IEvaluable evaluable = null;
		if (ActividadIndividualEvaluable.esTipoValido(xml)) {
			evaluable = ActividadIndividualEvaluable.getActividad(idActividad);
		} else if (ActividadGrupalEvaluable.esTipoValido(xml)) {
			evaluable = ActividadGrupalEvaluable.getActividad(idActividad);
		} else {
			// LEVANTAR EXCEPCION
		}
		evaluable.evaluar(idEvaluado, nota);
		// TODO Terminar de implementar
	}

	public void getNota(long idActividad, long idEvaluado) {
		// TODO Implementar
	}

	// Verificar que la actividad sea evaluable
	public void getNotas(long idActividad) {
		// TODO Implementar
	}
}
