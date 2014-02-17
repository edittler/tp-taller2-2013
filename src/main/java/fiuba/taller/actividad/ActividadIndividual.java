package fiuba.taller.actividad;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

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
		List<String> usernames = new ArrayList<>();
		for(Usuario participante:participantes){
			usernames.add(participante.getUsername());
		}
		return usernames;
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
		participantes.add(usuario);
		actualizarEstado();
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
		boolean encontrado = false;
		Iterator<Usuario> it = participantes.iterator();
		while (!encontrado) {
			Usuario usuario = it.next();
			if (usuario.getUsername().equals(username)) {
				participantes.remove(usuario);
				encontrado = true;
			}
		}
		actualizarEstado();
	}

	@Override
	public void actualizarEstado() throws RemoteException {
		super.actualizarEstado();
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
		// TODO Validar fecha y lanzar excepcion
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
		String xmlConsulta = "<WS><Usuario><join><Actividad>"
				+"<id> "+this.id+" </id>"
				+"</Actividad></join></Usuario></WS>";

// TODO Implementar. Se debe cargar desde la base de datos la lista de participantes.	
// 1) Se lo envia a Integracion la consulta
// 2) Verifica que halla recibido una respuesta valida de parte de integracion
// 	  Si no se levanta excepcion
// 3) pasa el xml de listas (String) a la lista real (Array<Usuario>)
	}

	private boolean contieneParticipante(String username) {
		for (Usuario usuario : participantes) {
			if (usuario.getUsername().equals(username)) {
				return true;
			}
		}
		return false;
	}

	@Override
	protected String serializarInterno() {
		String xml = serializarInterno();
		xml += "<usuarios>";
		for (Usuario usuario : participantes) {
			// TODO : devolver excepcion si es -1 ?
			// FIXME : ver si hace falta algo mas para poder pedir el id
			xml += usuario.serializar();
		}
		xml += "</usuarios>";
		return xml;
	}
}
