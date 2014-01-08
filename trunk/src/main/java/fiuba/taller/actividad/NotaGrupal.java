package fiuba.taller.actividad;

import java.io.StringReader;
import java.rmi.RemoteException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

public class NotaGrupal extends Nota {

	private long idGrupo;

	private NotaGrupal(long idActividad, long idGrupo) {
		super(idActividad);
		this.idGrupo = idGrupo;
	}

	public long getIdGrupo() {
		return idGrupo;
	}

	/**
	 * Crea e inicializa en la base de datos la nota para la actividad grupal y
	 * grupo dado. Si la nota ya fue inicializada, retorna la nota almacenada.
	 * 
	 * @param idActividad
	 *            Identificador de la actividad grupal evaluable.
	 * @param idGrupo
	 *            Identificador del grupo.
	 * @return NotaGrupal inicializada e instanciada.
	 * @throws RemoteExcepcion
	 *             Si no existe la actividad, si no es individual evaluable, si
	 *             no existe el participante.
	 */
	public static NotaGrupal crearNota(long idActividad, long idGrupo) {
		/*
		 * TODO Verificar si la nota ya fue inicializada. En caso afirmativo, se
		 * ejecuta el método "getNota". Si no está cargada la nota, verificar si
		 * la actividad existe y es grupal evaluable. Luego, verificar si existe
		 * el grupo asociado a la mencionada actividad.
		 */
		NotaGrupal nota = new NotaGrupal(idActividad, idGrupo);
		return nota;
	}

	/**
	 * Elimina la NotaGrupal de la base de datos.
	 * 
	 * @param idActividad
	 *            Identificador de la actividad grupal evaluable.
	 * @param idGrupo
	 *            Identificador del grupo.
	 */
	public static void eliminarNota(long idActividad, long idGrupo) {
		// TODO
	}

	/**
	 * Carga desde la base de datos la NotaGrupal.
	 * 
	 * @param idActividad
	 *            Identificador de la actividad grupal evaluable.
	 * @param idGrupo
	 *            Identificador del grupo.
	 * @return NotaGrupal cargada e instanciada.
	 * @throws NotaInexistenteExcepcion
	 *             Si no hay cargada una nota grupal asociada a la actividad y
	 *             grupo.
	 */
	public static NotaGrupal getNota(long idActividad, long idGrupo)
			throws RemoteException {
		// TODO Corregir
		NotaGrupal nota = new NotaGrupal(idActividad, idGrupo);
		return nota;
	}

	@Override
	public String serializar() {
		String idActividadString = "";
		String idGrupoString = "";
		if (idActividad >= 0) {
			idActividadString = String.valueOf(idActividad);
		}
		if (idGrupo >= 0) {
			idGrupoString = String.valueOf(idGrupo);
		}
		return "<WS><Nota>"
				+ "<idActividad>" + idActividadString + "</idActividad>"
				+ "<idGrupo>" + idGrupoString + "</idGrupo>"
				+ "<valor>" + valor + "</valor>"
				+ "<observaciones>" + observaciones + "</Observaciones>"
				+ "</Nota></WS>";
	}

	@Override
	public void descerializar(String xml) {
		try {
			DocumentBuilderFactory factory = DocumentBuilderFactory
					.newInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();
			InputSource is = new InputSource(new StringReader(xml));
			Document doc = builder.parse(is);
			doc.getDocumentElement().normalize();

			NodeList nodes = doc.getElementsByTagName("Nota");
			for (int i = 0; i < nodes.getLength(); i++) {
				Node node = nodes.item(i);
				if (node.getNodeType() == Node.ELEMENT_NODE) {
					Element element = (Element) node;
					idActividad = Long
							.valueOf(getValue("IdActividad", element));
					idGrupo = Long.valueOf(getValue("IdEvaluado", element));
					valor = getValue("ValorNota", element);
					observaciones = getValue("Observaciones", element);
				}
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	@Override
	public void guardarEstado() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void guardarNuevoEstado() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void eliminarEstado() throws RemoteException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String realizarConsulta() {
		// TODO Auto-generated method stub
		return null;
	}
}
