package fiuba.taller.actividad;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

import com.ws.services.ActualizarDatos;
import com.ws.services.ActualizarDatosResponse;
import com.ws.services.IntegracionStub;

public class ActividadIndividual extends Actividad {

	protected static final String TIPO_ACTIVIDAD_INDIVIDUAL = "Individual";

	protected List<Usuario> participantes;

	public ActividadIndividual() {
		super();
		tipo = TIPO_ACTIVIDAD_INDIVIDUAL;
		participantes = null;
	}

	public List<String> getParticipantes() {
		if (!participantesCargados()) {
			cargarParticipantes();
		}
		// FIXME Terminar de implementar
		return new ArrayList<>();
	}

	public void agregarParticipante(String username) throws RemoteException {
		if (!participantesCargados()) {
			cargarParticipantes();
		}
		if (contieneParticipante(username)) {
			String mensaje = "El usuario " + username
					+ " ya se encuentra en la actividad";
			throw new RemoteException(mensaje);
		}
		Usuario usuario = Usuario.getUsuario(username);
		String usuarioIdStr = Long.toString(usuario.getId());
		String xml = "<WS><Actividad><id>" + id + "</id>"
				+ "<participantes><Usuario><id>" + usuarioIdStr
				+ "</id></Usuario></participantes></Actividad></WS>";
		IntegracionStub servicio = new IntegracionStub();
		ActualizarDatos envio = new ActualizarDatos();
		envio.setXml(xml);
		ActualizarDatosResponse respuesta = servicio.actualizarDatos(envio);
		String retorno = respuesta.get_return();
		procesarNotificacionIntegracion(retorno);
		participantes.add(usuario);
	}

	public void eliminarParticipante(String username) throws RemoteException {
		if (!participantesCargados()) {
			cargarParticipantes();
		}
		if (!contieneParticipante(username)) {
			String mensaje = "El usuario " + username
					+ " no se encuentra en la actividad";
			throw new RemoteException(mensaje);
		}
		participantes.remove(username);
		/*
		 * FIXME(Jorge) Hará falta llamar a Integración para eliminar el
		 * participante o con el método "GuardarEstado" alcanza?
		 */
	}

	@Override
	public void actualizarEstado() throws RemoteException {
		super.actualizarEstado();
		/*
		 * TODO(Jorge) Se debe guardar la lista de usernames.
		 */
	}

	/* METODOS DE CLASE (ESTATICOS) */

	public static boolean esTipoValido(String xml) {
		Actividad actividad = new Actividad();
		try {
			actividad.descerializar(xml);
		} catch (RemoteException e) {
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
			throws RemoteException {
		ActividadIndividual actividad = new ActividadIndividual();
		actividad.descerializar(xmlPropiedades);
		actividad.setId(-1);
		actividad.tipo = TIPO_ACTIVIDAD_INDIVIDUAL;
		// TODO(Pampa) Validar fecha y lanzar excepcion
		actividad.guardarNuevoEstado();
		return actividad;
	}

	public static void eliminarActividad(long idActividad)
			throws RemoteException {
		
		ActividadIndividual actividad = ActividadIndividual.getActividad(idActividad);
		actividad.eliminarEstado();
	}

	public static ActividadIndividual getActividad(long idActividad)
			throws RemoteException {
		/*
		 * FIXME Si no existe la actividad con el ID especificado, se debe
		 * lanzar la excepcion.
		 */
		String propiedades = getPropiedades(idActividad);
		if(!esTipoValido(propiedades)) {
			String mensaje = "La actividad a cargar no es Individual";
			throw new RemoteException(mensaje);
		}
		ActividadIndividual actividad = new ActividadIndividual();
		actividad.descerializar(propiedades);
		return actividad;
	}

	/* METODOS PRIVADOS AUXILIARES */

	private boolean participantesCargados() {
		return participantes != null;
	}

	private void cargarParticipantes() {
		participantes = new ArrayList<>();
		/*
		 * TODO(Jorge o Pampa?) Implementar. Se debe cargar desde la base de
		 * datos la lista de participantes.
		 */
	}

	private boolean contieneParticipante(String username) {
		// TODO Implementar
		return false;
	}
}
