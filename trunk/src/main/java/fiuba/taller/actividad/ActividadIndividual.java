package fiuba.taller.actividad;

import java.util.ArrayList;
import java.util.List;

import fiuba.taller.actividad.excepcion.ActividadInexistenteExcepcion;
import fiuba.taller.actividad.excepcion.ParticipanteExistenteExcepcion;
import fiuba.taller.actividad.excepcion.ParticipanteInexistenteExcepcion;
import fiuba.taller.actividad.excepcion.XmlErroneoExcepcion;

public class ActividadIndividual extends Actividad {

	protected static final String TIPO_ACTIVIDAD_INDIVIDUAL = "Individual";

	protected List<String> usernameParticipantes;

	public ActividadIndividual() {
		super();
		tipo = TIPO_ACTIVIDAD_INDIVIDUAL;
		usernameParticipantes = null;
	}

	public List<String> getParticipantes() {
		if (!participantesCargados()) {
			cargarParticipantes();
		}
		return new ArrayList<>(usernameParticipantes);
	}

	public void agregarParticipante(String username)
			throws ParticipanteExistenteExcepcion {
		if (!participantesCargados()) {
			cargarParticipantes();
		}
		if (usernameParticipantes.contains(username)) {
			String mensaje = "El usuario " + username
					+ " ya se encuentra en la actividad";
			throw new ParticipanteExistenteExcepcion(mensaje);
		}
		usernameParticipantes.add(username);
		/*
		 * FIXME(Jorge) Hará falta llamar a Integración para agregar el
		 * participante o con el método "GuardarEstado" alcanza?
		 */
	}

	public void eliminarParticipante(String username)
			throws ParticipanteInexistenteExcepcion {
		if (!participantesCargados()) {
			cargarParticipantes();
		}
		if (!usernameParticipantes.contains(username)) {
			String mensaje = "El usuario " + username
					+ " no se encuentra en la actividad";
			throw new ParticipanteInexistenteExcepcion(mensaje);
		}
		usernameParticipantes.remove(username);
		/*
		 * FIXME(Jorge) Hará falta llamar a Integración para eliminar el
		 * participante o con el método "GuardarEstado" alcanza?
		 */
	}

	@Override
	public void guardarEstado() {
		super.guardarEstado();
		/*
		 * TODO(Jorge) Se debe guardar la lista de usernames.
		 */
	}

	/* METODOS DE CLASE (ESTATICOS) */

	public static boolean esTipoValido(String xml) {
		Actividad actividad = new Actividad();
		try {
			actividad.descerializar(xml);
		} catch (XmlErroneoExcepcion e) {
			return false;
		}
		/*
		 * Si el campo "Tipo" comienza con la palabra "Individual", se
		 * considerara de tipo valido. Es decir, ActividadIndividualEvaluable
		 * tambien se considera como ActividadIndividual (por ser clase
		 * derivada)
		 */
		if (actividad.tipo.startsWith(TIPO_ACTIVIDAD_INDIVIDUAL)) {
			return true;
		}
		return false;
	}

	public static ActividadIndividual crearActividad(String xmlPropiedades)
			throws XmlErroneoExcepcion {
		ActividadIndividual actividad = new ActividadIndividual();
		actividad.descerializar(xmlPropiedades);
		// TODO(Pampa) Obtener un ID nuevo
		// actividad.id = nuevoId;
		actividad.tipo = TIPO_ACTIVIDAD_INDIVIDUAL;
		// TODO(Pampa) Validar fecha y lanzar excepcion
		actividad.guardarEstado();
		return actividad;
	}

	public static void eliminarActividad(long idActividad)
			throws ActividadInexistenteExcepcion {
		/*
		 * TODO(Jorge?) Implementar.
		 */
	}

	public static ActividadIndividual getActividad(long idActividad)
			throws XmlErroneoExcepcion {
		/*
		 * FIXME Si no existe la actividad con el ID especificado, se debe
		 * lanzar la excepcion ActividadInexistenteExcepcion
		 */
		ActividadIndividual actividad = new ActividadIndividual();
		actividad.levantarEstado(idActividad);
		return actividad;
	}

	/* METODOS PRIVADOS AUXILIARES */

	private boolean participantesCargados() {
		return usernameParticipantes != null;
	}

	private void cargarParticipantes() {
		usernameParticipantes = new ArrayList<>();
		/*
		 * TODO(Jorge o Pampa?) Implementar. Se debe cargar desde la base de
		 * datos la lista de participantes.
		 */
	}
}
