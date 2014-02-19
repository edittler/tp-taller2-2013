package fiuba.taller.actividad;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

public class ActividadIndividual extends Actividad {

	protected static final String TIPO_ACTIVIDAD_INDIVIDUAL = "Individual";

	protected List<Usuario> participantes;

	public ActividadIndividual() {
		super();
		tipo = TIPO_ACTIVIDAD_INDIVIDUAL;
		participantes = new ArrayList<>();
	}

	public List<String> getParticipantes() {
		List<String> usernames = new ArrayList<>();
		for(Usuario participante:participantes){
			usernames.add(participante.getUsername());
		}
		return usernames;
	}

	/**
	 * Agrega un participante a la actividad. Los cambios no se persisten en la
	 * base de datos, para ello debe ejecutar el método "actualizarEstado".
	 * 
	 * @param username
	 *            String con el username del usuario que se desea agregar a la
	 *            actividad individual.
	 * @throws RemoteException
	 *             Si el usuario no existe o ya se encuentra en la actividad.
	 */
	public void agregarParticipante(String username) throws RemoteException {
		if (contieneParticipante(username)) {
			String mensaje = "El usuario " + username
					+ " ya se encuentra en la actividad";
			throw new RemoteException(mensaje);
		}
		Usuario usuario = Usuario.getUsuario(username);
		participantes.add(usuario);
	}

	/**
	 * Elimina un participante a la actividad. Los cambios no se persisten en la
	 * base de datos, para ello debe ejecutar el método "actualizarEstado".
	 * 
	 * @param username
	 *            String con el username del usuario que se desea eliminar de la
	 *            actividad individual.
	 * @throws RemoteException
	 *             Si el usuario no se encuentra en la actividad.
	 */
	public void eliminarParticipante(String username) throws RemoteException {
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

	/* METODOS PROTEGIDOS AUXILIARES */

	@Override
	protected String serializarInterno() {
		String xml = super.serializarInterno();
		xml += "<usuarios>";
		for (Usuario usuario : participantes) {
			// TODO : devolver excepcion si es -1 ?
			// FIXME : ver si hace falta algo mas para poder pedir el id
			xml += usuario.serializar();
		}
		xml += "</usuarios>";
		return xml;
	}

	@Override
	protected void descerializar(Document doc) throws RemoteException {
		super.descerializar(doc);
		NodeList nodes = doc.getElementsByTagName("usuarios");
		if (nodes.getLength() > 1) {
			throw new RemoteException(
					"Debe haber solamente un nodo usuarios");
		}
		if (nodes.getLength() == 1) {
			Element element = (Element) nodes.item(0);
			cargarParticipantes(element);
		}
	}

	/* METODOS PRIVADOS AUXILIARES */

	private void cargarParticipantes(Element nodoUsuarios)
			throws RemoteException {
		participantes = new ArrayList<>();
		NodeList nodos = nodoUsuarios.getElementsByTagName("Usuario");
		for (int i = 0; i < nodos.getLength(); i++) {
			Element nodoUsuario = (Element) nodos.item(i);
			String nodoStr = ParserXml.toString(nodoUsuario);
			Usuario usuario = Usuario.deserializar(nodoStr);
			participantes.add(usuario);
		}
	}

	private boolean contieneParticipante(String username) {
		for (Usuario usuario : participantes) {
			if (usuario.getUsername().equals(username)) {
				return true;
			}
		}
		return false;
	}
}
