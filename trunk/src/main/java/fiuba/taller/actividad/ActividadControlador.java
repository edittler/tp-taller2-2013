package fiuba.taller.actividad;

import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.rmi.RemoteException;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

public class ActividadControlador {

	/* METODOS COMUNES A TODAS LAS ACTIVIDADES */

	/**
	 * Se obtienen las propiedades basicas de las actividades: nombre,
	 * descripcion, tipo, fecha de inicio, fecha de fin, tipo de escala (en
	 * actividades evaluables) y si es de grupos exlusivos (en actividades
	 * grupales).
	 * 
	 * @param username
	 *            Identificador del usuario que ejecuta el metodo.
	 * @param idActividad
	 *            Identificador de la actividad de la cual se desea obtener las
	 *            propiedades.
	 * @return XML con las propiedades de la actividad.
	 * @throws RemoteException
	 *             Si el usuario no tiene permiso para consultar las propiedades
	 *             de la actividad o si la actividad no existe.
	 */
	public String getPropiedades(String username, long idActividad)
			throws RemoteException {
		String propiedades = Actividad.getPropiedades(idActividad);
		return propiedades;
	}

	/**
	 * Se modifican las propiedades basicas de las actividades: nombre,
	 * descripcion, tipo, fecha de inicio, fecha de fin, tipo de escala (en
	 * actividades evaluables) y si es de grupos exlusivos (en actividades
	 * grupales).
	 * 
	 * @param username
	 *            Identificador del usuario que ejecuta el metodo.
	 * @param idActividad
	 *            Identificador de la actividad de la cual se desea modificar
	 *            las propiedades.
	 * @param xmlPropiedades
	 *            String que contiene el XML con las propiedades a modificar.
	 * @throws RemoteException
	 *             Si el usuario no tiene permiso para consultar las propiedades
	 *             de la actividad o si la actividad no existe.
	 */
	public void setPropiedades(String username, long idActividad,
			String xmlPropiedades) throws RemoteException {
		String xml = Actividad.getPropiedades(idActividad);
		if (ActividadIndividualEvaluable.esTipoValido(xml)) {
			ActividadIndividualEvaluable actividadIndividual = new ActividadIndividualEvaluable();
			actividadIndividual.descerializar(xml);
			actividadIndividual.actualizar(xmlPropiedades);
			actividadIndividual.actualizarEstado();
			return;
		}
		if (ActividadGrupalEvaluable.esTipoValido(xml)) {
			ActividadGrupalEvaluable actividadGrupal = new ActividadGrupalEvaluable();
			actividadGrupal.descerializar(xml);
			actividadGrupal.actualizar(xmlPropiedades);
			actividadGrupal.actualizarEstado();
			return;
		}
		/*
		 * Si la actividad es Grupal o Individual solamente, se ejecuta el
		 * "actualizar" de Actividad, ya que no tiene atributos adicionales
		 * que actualizar. (En ActividadGrupal no se puede cambiar si es de 
		 * grupos exclusivos)
		 */
		Actividad actividad = new Actividad();
		actividad.descerializar(xml);
		actividad.actualizar(xmlPropiedades);
		actividad.actualizarEstado();
	}

	/**
	 * Se obtienen las propiedades de las actividades internas de un ambito (que
	 * no es una actividad).
	 * 
	 * @param username
	 *            Identificador del usuario que ejecuta el metodo.
	 * @param idAmbito
	 *            Identificador del ambito del cual se desea obtener las
	 *            propiedades de las actividades internas.
	 * @return XML con las propiedades de las actividades internas del ambito.
	 * @throws RemoteException
	 *             Si el usuario no tiene permiso para consultar las propiedades
	 *             del ambito o si el ambito no existe.
	 */
	public String getActividadesDeAmbito(String username, long idAmbito)
			throws RemoteException {
		Actividad act = new Actividad();
		act.setIdAmbitoSuperior(idAmbito);
		String xml = act.realizarConsulta();
		return xml;
	}

	/**
	 * Se obtienen las propiedades de las actividades internas de una actividad.
	 * 
	 * @param username
	 *            Identificador del usuario que ejecuta el metodo.
	 * @param idAmbito
	 *            Identificador de la actividad de la cual se desea obtener las
	 *            propiedades de las actividades internas.
	 * @return XML con las propiedades de las actividades internas de la actividad.
	 * @throws RemoteException
	 *             Si el usuario no tiene permiso para consultar las propiedades
	 *             de la actividad o si la actividad no existe.
	 */
	public String getActividadesDeActividad(String username, long idActividad)
			throws RemoteException {
		Actividad act = new Actividad();
		act.setIdActividadSuperior(idActividad);
		String xml = act.realizarConsulta();
		// por ahora devolvemos los xml directamente
		return xml;
	}

	/**
	 * Se obtienen las propiedades de las actividades en las cuales participa un
	 * miembro.
	 * 
	 * @param username
	 *            Identificador del miembro del cual se desea obtener las
	 *            propiedades de las actividades en las que participa.
	 * @return XML con las propiedades de las actividades en las cuales
	 *         participa el miembro.
	 * @throws RemoteException
	 */
	public String getActividadesDeMiembro(String username)
			throws RemoteException {
		// TODO: Implementar. Obtener todas las actividades de un participante
		return "";
	}

	/**
	 * Se destruye la actividad, eliminando sus propiedades, grupos asociados
	 * (en actividades grupales) y notas asociadas (en actividades evaluables).
	 * 
	 * @param username
	 *            Identificador del miembro que desea destruir la actividad.
	 * @throws RemoteException
	 *             Si la actividad no existe.
	 */
	public void destruirActividad(String username, long idActividad)
			throws RemoteException {
		// TODO Implementar
	}

	/* CREADORES DE ACTIVIDAD */

	/**
	 * Crea una actividad individual con las propiedades especificadas.
	 * 
	 * @param username
	 *            Identificador del miembro que desea crear la actividad.
	 * @param xmlPropiedades
	 *            XML que contiene las propiedades de la actividad a crear.
	 * @return Identificador de la actividad creada.
	 * @throws RemoteException
	 *             Si el usuario no tiene permiso para crear la actividad.
	 */
	public long crearActividadIndividual(String username, String xmlPropiedades)
			throws RemoteException {
		/*
		 * FIXME Corroborar con Participacion si "username" puede crear
		 * actividad. Obtener el identificador del ambito o actividad del xml de
		 * propiedades. Si no existe algún identificador, lanzar una excepcion.
		 */
		ActividadIndividual actividad = ActividadIndividual
				.crearActividad(xmlPropiedades);
		return actividad.getId();
	}

	/**
	 * Crea una actividad grupal con las propiedades especificadas.
	 * 
	 * @param username
	 *            Identificador del miembro que desea crear la actividad.
	 * @param xmlPropiedades
	 *            XML que contiene las propiedades de la actividad a crear.
	 * @return Identificador de la actividad creada.
	 * @throws RemoteException
	 *             Si el usuario no tiene permiso para crear la actividad.
	 */
	public long crearActividadGrupal(String username, String xmlPropiedades)
			throws RemoteException {
		/*
		 * FIXME Corroborar con Participacion si "username" puede crear
		 * actividad. Obtener el identificador del ambito o actividad del xml de
		 * propiedades. Si no existe algún identificador, lanzar una excepcion.
		 */
		ActividadGrupal actividad = ActividadGrupal
				.crearActividad(xmlPropiedades);
		return actividad.getId();
	}

	/**
	 * Crea una actividad individual evaluable con las propiedades
	 * especificadas.
	 * 
	 * @param username
	 *            Identificador del miembro que desea crear la actividad.
	 * @param xmlPropiedades
	 *            XML que contiene las propiedades de la actividad a crear.
	 * @return Identificador de la actividad creada.
	 * @throws RemoteException
	 *             Si el usuario no tiene permiso para crear la actividad.
	 */
	public long crearActividadIndividualEvaluable(String username,
			String xmlPropiedades) throws RemoteException {
		/*
		 * FIXME Corroborar con Participacion si "username" puede crear
		 * actividad. Obtener el identificador del ambito o actividad del xml de
		 * propiedades. Si no existe algún identificador, lanzar una excepcion.
		 */
		ActividadIndividualEvaluable actividad = ActividadIndividualEvaluable
				.crearActividad(xmlPropiedades);
		return actividad.getId();
	}

	/**
	 * Crea una actividad grupal evaluable con las propiedades especificadas.
	 * 
	 * @param username
	 *            Identificador del miembro que desea crear la actividad.
	 * @param xmlPropiedades
	 *            XML que contiene las propiedades de la actividad a crear.
	 * @return Identificador de la actividad creada.
	 * @throws RemoteException
	 *             Si el usuario no tiene permiso para crear la actividad.
	 */
	public long crearActividadGrupalEvaluable(String username,
			String xmlPropiedades) throws RemoteException {
		/*
		 * FIXME Corroborar con Participacion si "username" puede crear
		 * actividad. Obtener el identificador del ambito o actividad del xml de
		 * propiedades. Si no existe algún identificador, lanzar una excepcion.
		 */
		ActividadGrupalEvaluable actividad = ActividadGrupalEvaluable
				.crearActividad(xmlPropiedades);
		return actividad.getId();
	}

	/* METODOS COMUNES A LAS ACTIVIDADES INDIVIDUALES */

	/**
	 * Agrega un participante a la actividad individual.
	 * 
	 * @param username
	 *            Identificador del miembro que desea agregar un participante en
	 *            la actividad.
	 * @param idActividad
	 *            Identificador de la actividad en la cual se desea agregar el
	 *            participante.
	 * @param usernameNuevoParticipante
	 *            Identificador del miembro a agregar como participante en la
	 *            actividad.
	 * @throws RemoteException
	 *             Si el miembro no tiene permisos para agregar el participante
	 *             o si la actividad no es individual.
	 */
	public void agregarParticipante(String username, long idActividad,
			String usernameNuevoParticipante) throws RemoteException {
		ActividadIndividual actividad = ActividadIndividual
				.getActividad(idActividad);
		actividad.agregarParticipante(usernameNuevoParticipante);
	}

	/**
	 * Elimina un participante de la actividad individual.
	 * 
	 * @param username
	 *            Identificador del miembro que desea eliminar un participante
	 *            de la actividad.
	 * @param idActividad
	 *            Identificador de la actividad en la cual se desea eliminar el
	 *            participante.
	 * @param usernameParticipanteAEliminar
	 *            Identificador del miembro a eliminar como participante de la
	 *            actividad.
	 * @throws RemoteException
	 *             Si el miembro no tiene permisos para eliminar el
	 *             participante, si la actividad no es individual o si el
	 *             miembro a eliminar no es participante de la actividad.
	 */
	public void eliminarParticipante(String username, long idActividad,
			String usernameParticipanteAEliminar) throws RemoteException {
		ActividadIndividual actividad = ActividadIndividual
				.getActividad(idActividad);
		actividad.eliminarParticipante(usernameParticipanteAEliminar);
	}

	/**
	 * Se obtienen todos los participantes de una actividad individual.
	 * 
	 * @param username
	 *            Identificador del usuario que ejecuta el metodo.
	 * @param idActividad
	 *            Identificador de la actividad de la cual se desea obtener los
	 *            participantes.
	 * @return String con XML que contiene la lista de los participantes de la
	 *         actividad.
	 * @throws RemoteException
	 *             Si el miembro no tiene permisos para realizar la consulta o
	 *             si la actividad no es individual.
	 */
	public String getParticipantes(String username, long idActividad)
			throws RemoteException {
		ActividadIndividual actInd = 
				ActividadIndividual.getActividad(idActividad);		
		/* Se obtiene la lista de participantes inscriptos a la 
		* actividad y se retornan los usernames
		*/
		
		List<String> participantes = actInd.getParticipantes();
		
		// Paso a un XML genérico la lista de participantes obtenida
		Document docParticipantes = null;
		try {
			docParticipantes =  DocumentBuilderFactory.newInstance().
					newDocumentBuilder().newDocument();
		} catch(ParserConfigurationException e) {
			// TODO: Do something Ferno!
		}
		
		Element root = docParticipantes.createElement("Participantes");
		for (String participanteUsername : participantes) {
			
			// Agrego un nodo al XML por cada participante
			Element nodo = docParticipantes.createElement("Participante");
			
			// Por ahora solo devuelvo el ID como atributo del nodo
			nodo.setAttribute("Username", participanteUsername);
			
			root.appendChild(nodo);
		}
		
		// Asigno el nodo raiz con todos sus hijos al documento XML
		docParticipantes.appendChild(root);
		
		// Convierto el documento a string XML para retornar
		String xmlParticipantes = null;
		xmlParticipantes = this.getStringFromDocument(docParticipantes);
		
		return xmlParticipantes;
	}

	/* METODOS COMUNES A LAS ACTIVIDADES GRUPALES */

	/**
	 * Agrega un grupo a la actividad grupal.
	 * 
	 * @param username
	 *            Identificador del usuario que ejecuta el metodo.
	 * @param idActividad
	 *            Identificador de la actividad en la cual se desea agregar el
	 *            grupo.
	 * @param grupo
	 *            Identificadores de los usernames que conforman el grupo a
	 *            agregar, separadas con el caracter ":".
	 * @throws RemoteException
	 *             Si el miembro no tiene permisos para agregar el grupo o si la
	 *             actividad no es individual.
	 */
	public void agregarGrupo(String username, long idActividad, String grupo) 
			throws RemoteException {
		ActividadGrupal actividad = ActividadGrupal
				.getActividad(idActividad);
		// TODO Descerializar el grupo
		Grupo nuevoGrupo = new Grupo();
		actividad.agregarGrupo(nuevoGrupo);
	}

	public void eliminarGrupo(String username ,long idActividad, long idGrupo) 
			throws RemoteException {
		ActividadGrupal actividad = ActividadGrupal.getActividad(idActividad);
		actividad.eliminarGrupo(idGrupo);
	}

	public void agregarParticipanteAGrupo(String username, long idActividad,
			long idGrupo, String usernameNuevoParticipante) 
					throws RemoteException {
		// TODO: Ver si 'username' esta habilitado para esto (Participacion)
		ActividadGrupal actGrupal = ActividadGrupal.getActividad(idActividad);
		actGrupal.agregarParticipanteAGrupo(idGrupo, 
				usernameNuevoParticipante);
	}

	public void eliminarParticipanteAGrupo(String username, long idActividad,
			long idGrupo, String usernameParticipanteAEliminar) 
					throws RemoteException {
		// TODO: Ver si 'username' esta habilitado para esto (Participacion)
		ActividadGrupal actGrupal = ActividadGrupal.getActividad(idActividad);
		actGrupal.eliminarParticipanteDeGrupo(idGrupo, 
				usernameParticipanteAEliminar);
	}

	public String getGrupos(String username, long idActividad)
			throws RemoteException {
		ActividadGrupal actividad = ActividadGrupal.getActividad(idActividad);
		/* Se obtiene la lista de grupos inscriptos a la 
		* actividad. TODO: Hay que ver que informacion nos dan!
		* Por lo pronto se maneja solo IDs
		*/
		List<Grupo> grupos = actividad.getGrupos();

		// Paso a un XML genérico la lista de participantes obtenida
		Document docGrupos = null;
		try {
			docGrupos =  DocumentBuilderFactory.newInstance().
					newDocumentBuilder().newDocument();
		} catch(ParserConfigurationException e) {
			// TODO: Do something Ferno!
		}
				
		Element root = docGrupos.createElement("Grupos");
		for (Grupo grupo : grupos) {
					
			// Agrego un nodo al XML por cada participante
			Element nodo = docGrupos.createElement("Grupo");
					
			// Por ahora solo devuelvo el ID como atributo del nodo
			Element nodoId = docGrupos.createElement("Id");
			nodoId.setTextContent(String.valueOf(grupo.getId()));
			nodo.appendChild(nodoId);
			
			Element nodoParticipantes = docGrupos.createElement("Participantes");
			for (String participanteUsername : grupo.getUsernameParticipantes()) {
				Element nodoParticipante = docGrupos.createElement("Participante");
				
				Element nodoUsername = docGrupos.createElement("Username");
				nodoUsername.setTextContent(participanteUsername);
				nodoParticipante.appendChild(nodoUsername);
				
				nodoParticipantes.appendChild(nodoParticipante);
			}
			nodo.appendChild(nodoParticipantes);
					
			root.appendChild(nodo);
		}
				
		// Asigno el nodo raiz con todos sus hijos al documento XML
		docGrupos.appendChild(root);
		
		String xmlGrupos = null;
		
		xmlGrupos = this.getStringFromDocument(docGrupos);

		return xmlGrupos;
	}

	/* METODOS COMUNES A LAS ACTIVIDADES EVALUABLES */

	// Evaluado puede ser un participante o un grupo, dependiendo si la
	// actividad es ind o grupal
	public void evaluar(String username, long idActividad, String notas)
			throws RemoteException {
		Evaluable evaluable = this.encontrarActividadEvaluable(idActividad);
		if (evaluable == null) {
			// LEVANTAR EXCEPCION
		}
		// FIXME El "username" debe ser un participante o el identificador de un grupo
		// particular. Discutir la relacion entre ambos identificadores
		evaluable.evaluar(username, notas);
		// TODO Terminar de implementar
	}
	
	// TODO: Refactorizar
	private Evaluable encontrarActividadEvaluable(long idActividad)
			throws RemoteException {
		// TODO Refactorizar la busqueda de actividades evaluables
		Actividad actividad = Actividad.getActividad(idActividad);
		String xml = actividad.serializar();
		Evaluable evaluable = null;
		if (ActividadIndividualEvaluable.esTipoValido(xml)) {
			return ActividadIndividualEvaluable.getActividad(idActividad);
		} else if (ActividadGrupalEvaluable.esTipoValido(xml)) {
			return ActividadGrupalEvaluable.getActividad(idActividad);
		} else {
			return evaluable;
		}
	}

	public String getNota(String username ,long idActividad, long idEvaluado)
			throws RemoteException {
		Evaluable evaluable = encontrarActividadEvaluable(idActividad);
		if (evaluable == null) {
			//TODO: Lanzar alguna excepcion
		}
		
		return evaluable.getNota(idEvaluado).serializar();
		
		/*Nota nota = evaluable.getNota(idEvaluado);
		// TODO: ¿Muy C? Refactorizar, quizas...
		if (nota != null) {
			// Armo el XML correspondiente a la nota
			Document docNota = null;
			try {
				docNota = DocumentBuilderFactory.newInstance().
						newDocumentBuilder().newDocument();
			} catch (ParserConfigurationException e) {
				// TODO do some magic with exceptions, Ferno!
			}
			
			Element root = docNota.createElement("Nota");
			root.setAttribute("ID", ((Long)nota.getIdActividad()).toString());
			
			// Se agrega nodo "Valor" al XML
			Element valor = docNota.createElement("Valor");
			valor.setTextContent(nota.getNota());
			root.appendChild(valor);
			
			// Se agrega nodo "Observaciones" al XML
			Element obs = docNota.createElement("Observaciones");
			obs.setTextContent(nota.getObservaciones());
			root.appendChild(obs);
			
			docNota.appendChild(root);
			
			String xmlNota = null;
			try {
				xmlNota = this.getStringFromDocument(docNota);
			} catch (TransformerException e) {
				// TODO add exceptions magic here, Ferno :)
			}
			
			return xmlNota;
		}
		
		// TODO: Retornar algun tipo de Excepcion!
		return null;*/
	}

	// Verificar que la actividad sea evaluable
	public String getNotas(String username ,long idActividad) throws RemoteException {
		Evaluable evaluable = encontrarActividadEvaluable(idActividad);
		if (evaluable == null) {
			//TODO: Lanzar alguna excepcion
		}
		
		evaluable.getNotas();
		return ""; //FIXME Fer, volvemos para atras  --Pampa
		/*List<Nota> notas = evaluable.getNotas();
		
		// Creo el Documento a crear el XML
		Document docNotas = null;
		try {
			docNotas = DocumentBuilderFactory.newInstance().
					newDocumentBuilder().newDocument();
		} catch (ParserConfigurationException e) {
			// TODO do some magic with exceptions, Ferno!
		}
		
		Element root = docNotas.createElement("Notas");
		
		for( Nota nota : notas) {
			Element nodoNota = docNotas.createElement("Nota");
			nodoNota.setAttribute("ID", ((Long)nota.getIdActividad()).
					toString());
			
			// Se agrega nodo "Valor" al XML
			Element valor = docNotas.createElement("Valor");
			valor.setTextContent(nota.getNota());
			nodoNota.appendChild(valor);
			
			// Se agrega nodo "Observaciones" al XML
			Element obs = docNotas.createElement("Observaciones");
			obs.setTextContent(nota.getObservaciones());
			nodoNota.appendChild(obs);
			
			root.appendChild(nodoNota);
		}
		
		docNotas.appendChild(root);
		
		String xmlNotas = null;
		try {
			xmlNotas = this.getStringFromDocument(docNotas);
		} catch (TransformerException e) {
			// TODO add exceptions magic here, Ferno :)
		}
		
		return xmlNotas;*/
	}

	// TODO: Refactorizar! Mover este metodo al lugar adecuado
		protected String getStringFromDocument(Document doc) throws RemoteException {
			DOMSource domSource = new DOMSource(doc);
			StringWriter writer = new StringWriter();
			StreamResult result = new StreamResult(writer);
			TransformerFactory tf = TransformerFactory.newInstance();
			Transformer transformer;
			try {
				transformer = tf.newTransformer();
				transformer.transform(domSource, result);
			} catch (TransformerException e) {
				throw new RemoteException("Error al parsear el XML.");
			}

			return writer.toString();
		}

	private Document readXml(String xml) {
		DocumentBuilder db = null;
		try {
			db = DocumentBuilderFactory.newInstance().newDocumentBuilder();
		} catch (ParserConfigurationException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	    InputSource is = new InputSource();
	    is.setCharacterStream(new StringReader(xml));
		    
	    Document doc = null;;
		try {
			doc = db.parse(is);
		} catch (SAXException | IOException e) {
			// TODO do something with the exception, Ferno!
		}
		
		return doc;
	}
}
