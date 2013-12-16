package fiuba.taller.actividad;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class ActividadGrupal extends Actividad {

	protected static final String TIPO_ACTIVIDAD_GRUPAL = "Grupal";
	protected boolean gruposExclusivos;
	protected List<Grupo> grupos;

	public ActividadGrupal() {
		super();
		tipo = TIPO_ACTIVIDAD_GRUPAL;
		gruposExclusivos = false;
		grupos = null;
	}

	public boolean esDeGruposExclusivos() {
		return gruposExclusivos;
	}

	public Grupo getGrupo(long idGrupo) throws RemoteException {
		if (!gruposCargados()) {
			cargarGrupos();
		}
		for(Grupo grupo : grupos) {
			if (grupo.getId() == idGrupo) {
				return grupo;
			}
		}
		String mensaje = "No existe el grupo con el identificador " + idGrupo;
		throw new RemoteException(mensaje);
	}

	public List<Grupo> getGrupos() {
		if (!gruposCargados()) {
			cargarGrupos();
		}
		return grupos;
	}

	public void agregarGrupo(Grupo grupo) throws RemoteException {
		if (!gruposCargados()) {
			cargarGrupos();
		}
		if (gruposExclusivos) {
			verificarParticipantes(grupo);
		}
		grupo.setId(nuevoIdentificador());
		grupo.setIdActividad(id);
		grupos.add(grupo);
		/*
		 * FIXME(Jorge) Hará falta llamar a Integración para agregar el
		 * grupo o con el método "GuardarEstado" alcanza?
		 */
	}

	public void eliminarGrupo(long idGrupo) throws RemoteException {
		if (!gruposCargados()) {
			cargarGrupos();
		}
		Grupo grupoAEliminar = getGrupo(idGrupo);
		grupos.remove(grupoAEliminar);
		/*
		 * FIXME(Jorge) Hará falta llamar a Integración para eliminar el
		 * grupo o con el método "GuardarEstado" alcanza?
		 */
	}

	@Override
	public void guardarEstado() {
		super.guardarEstado();
		/*
		 * TODO(Jorge) Se debe guardar además el atributo "gruposExclusivos".
		 * Como "guardarEstado" llama al método descerializar que está 
		 * redefinido en la clase "ActividadGrupal", no haría falta este método.
		 * Testear y probar si es cierto lo que digo. --Pampa
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
		 * Si el campo "Tipo" comienza con la palabra "Grupal", se considerara
		 * de tipo valido. Es decir, ActividadGrupalEvaluable tambien se
		 * considera como ActividadGrupal (por ser clase derivada)
		 */
		if (actividad.tipo.startsWith(TIPO_ACTIVIDAD_GRUPAL)) {
			return true;
		}
		return false;
	}

	public static ActividadGrupal crearActividad(String xmlPropiedades)
			throws RemoteException {
		ActividadGrupal actividad = new ActividadGrupal();
		actividad.descerializar(xmlPropiedades);
		actividad.setId(-1);
		actividad.tipo = TIPO_ACTIVIDAD_GRUPAL;
		// TODO(Pampa) Validar fecha y lanzar excepcion
		actividad.guardarNuevoElemento();
		return actividad;
	}

	public static ActividadGrupal getActividad(long idActividad)
			throws RemoteException {
		/*
		 * FIXME Si no existe la actividad con el ID especificado, se debe
		 * lanzar la excepcion ActividadInexistenteExcepcion
		 */
		ActividadGrupal actividad = new ActividadGrupal();
		actividad.levantarEstado(idActividad);
		return actividad;
	}

	/* METODOS PROTEGIDOS AUXILIARES */

	@Override
	protected String serializarInterno() {
		return super.serializarInterno() + "<gruposExclusivos>"
				+ gruposExclusivos + "</gruposExclusivos>";
	}

	@Override
	protected void descerializar(Document doc) throws RemoteException {
		super.descerializar(doc);
		NodeList nodes = doc.getElementsByTagName("Actividad");
		if (nodes.getLength() != 1) {
			throw new RemoteException("Debe haber solamente un nodo Actividad");
		}
		Node node = nodes.item(0);
		if (node.getNodeType() == Node.ELEMENT_NODE) {
			Element element = (Element) node;
			this.gruposExclusivos = Boolean.valueOf(getValue(
					"gruposExclusivos", element));
		}
	}

	/* METODOS PRIVADOS AUXILIARES */

	private boolean gruposCargados() {
		return grupos != null;
	}

	private void cargarGrupos() {
		grupos = new ArrayList<>();
		/*
		 * TODO(Jorge o Pampa?) Implementar. Se debe cargar desde la base de
		 * datos la lista de grupos.
		 */
	}

	private void verificarParticipantes(Grupo grupoNuevo)
			throws RemoteException {
		for (Grupo grupo : grupos) {
			if (grupo.contieneParticipantesDe(grupo)) {
				List<String> participantesRepetidos = grupo
						.getParticipantesDuplicados(grupoNuevo);
				String mensaje = "El grupo a agregar tiene los siguientes "
						+ "participantes duplicados: ";
				for (String participante : participantesRepetidos) {
					mensaje += participante;
				}
				throw new RemoteException(mensaje);
			}
		}
	}

	private long nuevoIdentificador() {
		if (grupos.size() == 0) {
			return 1;
		}	
		List<Long> ids = new ArrayList<>();
		for(Grupo grupo : grupos) {
			ids.add(grupo.getId());
		}
		long maximo = Collections.max(ids);
		return maximo + 1;
	}
}
