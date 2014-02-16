package fiuba.taller.actividad;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.ws.services.GuardarDatos;
import com.ws.services.GuardarDatosResponse;
import com.ws.services.IntegracionStub;

/* FORMATO DEL XML GRUPO
 * 
 * FIXME ACLARACION: Revisar el formato XML que necesitan los de Integracion 
 * para poder persistir este objeto (incluyendo la lista de strings).
 * 
	<Grupo>
	   <IdActividad> </IdActividad>
	   <IdGrupo> </IdGrupo>
	   <list>
	      <Username> </Username>
	      .
	      . 
	      <Username> </Username>
	   </list>
	</Grupo>
 * 
 * ws->seleccionarDatos(xml)
 * 
 * "todos los usuarios tales que el id del grupo es 5"
 * <Usuario>
 * <join>
 * 		<Grupo>
 * 			<idGrupo>5</idGrupo>
 * 		</Grupo>
 * </join>
 * </Usuario>
 * 
 */

public class Grupo implements Serializable {

	private long idActividad;
	private long id;
	private List<String> usernameParticipantes;

	public Grupo() {
		id = -1;
		idActividad = -1;
		usernameParticipantes = new ArrayList<>();
	}

	public long getIdActividad() {
		return idActividad;
	}

	public void setIdActividad(long idActividad) {
		this.idActividad = idActividad;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	/**
	 * Agrega un participante al grupo.
	 * 
	 * @param username
	 *            Identificador del participante a agregar.
	 * @throws ParticipanteExistenteExcepcion
	 *             Si el participante ya se encuentra en el grupo
	 */
	public void agregarParticipante(String username) throws RemoteException {
		if (contieneParticipante(username)) {
			String mensaje = "El username " + username
					+ "ya se encuentra en el grupo.";
			throw new RemoteException(mensaje);
		}
		usernameParticipantes.add(username);
	}

	/**
	 * Elimina un participante del grupo.
	 * 
	 * @param username
	 *            Identificador del participante a eliminar.
	 * @throws ParticipanteInexistenteExcepcion
	 *             Si el participante a eliminar no existe.
	 */
	public void eliminarParticipante(String username) throws RemoteException {
		if (!contieneParticipante(username)) {
			String mensaje = "El username " + username
					+ "no se encuentra en el grupo.";
			throw new RemoteException(mensaje);
		}
		usernameParticipantes.remove(username);
	}

	public List<String> getUsernameParticipantes() {
		return usernameParticipantes;
	}

	public int tamanio() {
		return usernameParticipantes.size();
	}

	/**
	 * Verifica si el grupo ya tiene un participante.
	 * 
	 * @param username
	 *            Identificador del participante.
	 * @return true si el participante ya está en el grupo.
	 */
	public boolean contieneParticipante(String username) {
		return usernameParticipantes.contains(username);
	}

	/**
	 * Verifica si el grupo contiene participantes de otro grupo.
	 * 
	 * @param grupo
	 *            Grupo donde se va a verificar la existencia de participantes
	 *            del propio grupo.
	 * @return true si el grupo no posee participantes de otro grupo.
	 */
	public boolean contieneParticipantesDe(Grupo grupo) {
		for (String usernameParticipante : usernameParticipantes) {
			if (grupo.contieneParticipante(usernameParticipante)) {
				return true;
			}
		}
		return false;
	}

	public List<String> getParticipantesDuplicados(Grupo grupo) {
		// TODO Implementar
		return new ArrayList<>();
	}

	@Override
	public String serializar() {
		String xml = "<WS><Grupo>"
				+ "<idActividad>" + idActividad + "</idActividad>"
				+ "<idGrupo>" + id + "</idGrupo>"
				+ "<usuarios>";
		for (String participante : usernameParticipantes) {
			xml += "<Usuario><username>" + participante + "</username></Usuario>";
		}
		// FIXME Linea 166 provisoria
		//xml += "<Usuario><id>3</id><Usuario><Usuario><id>1</id><Usuario>";
		xml += "</usuarios></Grupo></WS>";
		return xml;
	}

	@Override
	public void descerializar(String xml) throws RemoteException {
		Document doc = ParserXml.getDocumentElement(xml);

		NodeList nodes = doc.getElementsByTagName("Grupo");
		if (nodes.getLength() != 1) {
			throw new RemoteException("Debe haber solamente un nodo Grupo");
		}

		Element grupoElement = (Element) nodes.item(0);
		idActividad = Long.valueOf(ParserXml.getRequiredValue("idActividad",
				grupoElement));
		id = Long.valueOf(ParserXml.getValue("id", grupoElement));

		NodeList lista = ((Element) grupoElement).getElementsByTagName("list");
		Element listaE = (Element) lista.item(0);

		NodeList participantes = listaE.getElementsByTagName("username");
		for (int j = 0; j < participantes.getLength(); j++) {
			Node nodde = participantes.item(j);
			if (nodde.getNodeType() == Node.ELEMENT_NODE) {
				String valor = nodde.getChildNodes().item(0).getNodeValue();
				this.usernameParticipantes.add(valor);
			}
		}
	}

	@Override
	public void guardarNuevoEstado() throws RemoteException {
		IntegracionStub servicio = new IntegracionStub();
		GuardarDatos envio = new GuardarDatos();
		String xml = serializar();
		envio.setXml(xml);
		GuardarDatosResponse response = servicio.guardarDatos(envio);
		String retorno = response.get_return();
		System.out.println(retorno);
		String idStr = procesarNotificacionIntegracion(retorno);
		id = Long.valueOf(idStr);
	}

	@Override
	public void actualizarEstado() {
		/* 
		 * TODO(Jorge) Implementar. Se debe persistir el objeto en la base de
		 * datos.
		 */
	}

	@Override
	public void eliminarEstado() throws RemoteException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String realizarConsulta() {
		/*
		 * TODO(Jorge) Implementar
		 */
		return "";
	}

	/* METODOS DE CLASE (ESTATICOS) */

	public static boolean eliminarGrupo(long idActividad, long idGrupo) {
		/* TODO(Jorge) Implementar. Se debe eliminar de la base de datos el
		 * grupo que corresponde a la actividad e idGrupo pasados por parámetro.
		 */
		return false;
	}

	public static Grupo getGrupo(long idActividad, long idGrupo) {
		/* TODO(Jorge) Implementar. Se debe cargar desde la base de datos el 
		 * grupo correspondiente a la actividad e idGrupo pasados por parámetro.
		 */
		Grupo grupo = new Grupo();
		return grupo;
	}

	/* METODOS PRIVADOS AUXILIARES */

	protected static String procesarNotificacionIntegracion(String xmlMensaje)
			throws RemoteException {
		Document doc = ParserXml.getDocumentElement(xmlMensaje);
		NodeList nodes = doc.getElementsByTagName("notificacion");
		if (nodes.getLength() != 1) {
			throw new RemoteException(
					"Integracion no devolvio un unico nodo notificacion");
		}
		Element element = (Element) nodes.item(0);
		String numeroStr = ParserXml.getValue("numero", element);
		int numero = Integer.valueOf(numeroStr);
		String mensaje = ParserXml.getValue("mensaje", element);
		String datos = "";
		try {
			datos = ParserXml.getValue("datos", element);
		} catch (RemoteException e) {
		}
		switch (numero) {
		// Codigo 0: Operación no permitida
		case 0:
			throw new RemoteException("Integracion: " + mensaje);
		// Codigo 1: Error al realizar la operación
		case 1:
			throw new RemoteException("Integracion: " + mensaje);
		// Codigo 2: Operación correcta
		case 2:
			return datos;
		default:
			throw new RemoteException("Integracion: Codigo desconocido.");
		}
	}
}
