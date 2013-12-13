package fiuba.taller.actividad;

import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;

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
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import fiuba.taller.actividad.excepcion.GrupoNoExclusivoExcepcion;
import fiuba.taller.actividad.excepcion.GrupoInexistenteExcepcion;
import fiuba.taller.actividad.excepcion.NotaInexistenteExcepcion;
import fiuba.taller.actividad.excepcion.ParticipanteExistenteExcepcion;
import fiuba.taller.actividad.excepcion.ParticipanteInexistenteExcepcion;
import fiuba.taller.actividad.excepcion.XmlErroneoExcepcion;

public class ActividadControlador {

	/* METODOS COMUNES A TODAS LAS ACTIVIDADES */

	public String getPropiedades(String username ,long idActividad) throws XmlErroneoExcepcion {
		
		Actividad actividad = null;
		try {
			actividad = Actividad.getActividad(idActividad);
		} catch (XmlErroneoExcepcion e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return actividad.serializar();
	}
	
	public void setPropiedades(String username, long idActividad, String propiedades) {
		// TODO
	}

	public String getActividadesDeAmbito(String username, long idAmbito) {
		Actividad act = new Actividad();
		act.setIdAmbitoSuperior(idAmbito);
		String xml = act.realizarConsulta();
		return xml;
	}

	public String getActividadesDeActividad(String username, long idActividad) {
		Actividad act = new Actividad();
		act.setIdActividadSuperior(idActividad);
		String xml = act.realizarConsulta();
		// por ahora devolvemos los xml directamente
		return xml;
	}

	public void destruirActividad(String username, long idActividad){
		// TODO Implementar
	}

	/* CREADORES DE ACTIVIDAD */

	/**
	 * Crea una actividad individual con las propiedades especificadas.
	 * 
	 * @param xmlPropiedades
	 *            XML que contiene las propiedades de la actividad a crear
	 * @return Identificador de la actividad creada
	 * @throws XmlErroneoExcepcion
	 */
	public long crearActividadIndividual(String username, String xmlPropiedades)
			throws XmlErroneoExcepcion {
		ActividadIndividual actividad = ActividadIndividual
				.crearActividad(xmlPropiedades);
		return actividad.getId();
	}

	public long crearActividadGrupal(String username, String xmlPropiedades)
			throws XmlErroneoExcepcion {
		ActividadGrupal actividad = ActividadGrupal
				.crearActividad(xmlPropiedades);
		return actividad.getId();
	}

	public long crearActividadIndividualEvaluable(String username,
			String xmlPropiedades) throws XmlErroneoExcepcion {
		ActividadIndividualEvaluable actividad = ActividadIndividualEvaluable
				.crearActividad(xmlPropiedades);
		return actividad.getId();
	}

	public long crearActividadGrupalEvaluable(String username,
			String xmlPropiedades) throws XmlErroneoExcepcion {
		ActividadGrupalEvaluable actividad = ActividadGrupalEvaluable
				.crearActividad(xmlPropiedades);
		return actividad.getId();
	}

	/* METODOS COMUNES A LAS ACTIVIDADES INDIVIDUALES */

	public void agregarParticipante(String username, long idActividad,
			String usernameNuevoParticipante)
			throws XmlErroneoExcepcion, ParticipanteExistenteExcepcion {
		ActividadIndividual actividad = ActividadIndividual
				.getActividad(idActividad);
		try {
			actividad.agregarParticipante(usernameNuevoParticipante);
		} catch (ParticipanteExistenteExcepcion e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void eliminarParticipante(String username, long idActividad,
			String usernameParticipanteAEliminar) 
			throws XmlErroneoExcepcion, ParticipanteInexistenteExcepcion {
		ActividadIndividual actividad = ActividadIndividual
				.getActividad(idActividad);
		actividad.eliminarParticipante(usernameParticipanteAEliminar);
	}
	
	// TODO: Refactorizar! Mover este metodo al lugar adecuado
	protected String getStringFromDocument(Document doc) 
			throws TransformerException {
	    DOMSource domSource = new DOMSource(doc);
	    StringWriter writer = new StringWriter();
	    StreamResult result = new StreamResult(writer);
	    TransformerFactory tf = TransformerFactory.newInstance();
	    Transformer transformer = tf.newTransformer();
	    transformer.transform(domSource, result);
	    return writer.toString();
	}

	public String getParticipantes(String username, long idActividad)
			throws XmlErroneoExcepcion {
		ActividadIndividual actInd = 
				ActividadIndividual.getActividad(idActividad);
		actInd.getParticipantes();
		return "blabla"; // FIXME Fer, volvemos para atras  --Pampa
		
		/* Se obtiene la lista de participantes inscriptos a la 
		* actividad. TODO: Hay que ver que informacion nos dan!
		* Por lo pronto manejaré solamente los IDs
		*/
		
		/*
		List<Long> participantes = actInd.getParticipantes();
		
		// Paso a un XML genérico la lista de participantes obtenida
		Document docParticipantes = null;
		try {
			docParticipantes =  DocumentBuilderFactory.newInstance().
					newDocumentBuilder().newDocument();
		} catch(ParserConfigurationException e) {
			// TODO: Do something Ferno!
		}
		
		Element root = docParticipantes.createElement("Participantes");
		for (Long idParticipante : participantes) {
			
			// Agrego un nodo al XML por cada participante
			Element nodo = docParticipantes.createElement("Participante");
			
			// Por ahora solo devuelvo el ID como atributo del nodo
			nodo.setAttribute("ID", idParticipante.toString());
			
			root.appendChild(nodo);
		}
		
		// Asigno el nodo raiz con todos sus hijos al documento XML
		docParticipantes.appendChild(root);
		
		// Convierto el documento a string XML para retornar
		String xmlParticipantes = null;
		try {
			xmlParticipantes = this.getStringFromDocument(docParticipantes);
		} catch (TransformerException e) {
			// TODO Do something Ferno!
		}
		
		return xmlParticipantes;*/
	}

	/* METODOS COMUNES A LAS ACTIVIDADES GRUPALES */

	public void agregarGrupo(String username, long idActividad, String grupo) 
			throws XmlErroneoExcepcion {
		ActividadGrupal actividad = ActividadGrupal
				.getActividad(idActividad);
		// TODO Descerializar el grupo
		Grupo nuevoGrupo = new Grupo();
		try {
			actividad.agregarGrupo(nuevoGrupo);
		} catch (GrupoNoExclusivoExcepcion e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void eliminarGrupo(String username ,long idActividad, long idGrupo) 
			throws XmlErroneoExcepcion {
		ActividadGrupal actividad = ActividadGrupal
				.getActividad(idActividad);
		try {
			actividad.eliminarGrupo(idGrupo);
		} catch (GrupoInexistenteExcepcion e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void agregarParticipanteAGrupo(String username, long idActividad,
			long idGrupo, String usernameNuevoParticipante) {
		// TODO Implementar cuando esté el resto terminado
	}

	public void eliminarParticipanteAGrupo(String username, long idActividad,
			long idGrupo, String usernameNuevoParticipante) {
		// TODO Implementar cuando esté el resto terminado
	}

	public String getGrupos(String username, long idActividad)
			throws XmlErroneoExcepcion {
		ActividadGrupal actividad = ActividadGrupal.getActividad(idActividad);
		actividad.getGrupos();
		return ""; // FIXME Fer, volvemos para atrás --Pampa
		/* Se obtiene la lista de grupos inscriptos a la 
		* actividad. TODO: Hay que ver que informacion nos dan!
		* Por lo pronto se maneja solo IDs
		*/
		/*List<Long> grupos = actividad.getGrupos();

		// Paso a un XML genérico la lista de participantes obtenida
		Document docGrupos = null;
		try {
			docGrupos =  DocumentBuilderFactory.newInstance().
					newDocumentBuilder().newDocument();
		} catch(ParserConfigurationException e) {
			// TODO: Do something Ferno!
		}
				
		Element root = docGrupos.createElement("Grupos");
		for (Long idGrupo : grupos) {
					
			// Agrego un nodo al XML por cada participante
			Element nodo = docGrupos.createElement("Grupo");
					
			// Por ahora solo devuelvo el ID como atributo del nodo
			nodo.setAttribute("ID", idGrupo.toString());
					
			root.appendChild(nodo);
		}
				
		// Asigno el nodo raiz con todos sus hijos al documento XML
		docGrupos.appendChild(root);
		
		String xmlGrupos = null;
		
		try {
			xmlGrupos = this.getStringFromDocument(docGrupos);
		} catch (TransformerException e) {
			// TODO do something Ferno!
		}
				
		return xmlGrupos;*/
	}

	/* METODOS COMUNES A LAS ACTIVIDADES EVALUABLES */

	// Evaluado puede ser un participante o un grupo, dependiendo si la
	// actividad es ind o grupal
	public void evaluar(String username, long idActividad, String notas) throws XmlErroneoExcepcion {
		Actividad actividad = Actividad.getActividad(idActividad);
		String xml = actividad.serializar();
		Evaluable evaluable = null;
		if (ActividadIndividualEvaluable.esTipoValido(xml)) {
			evaluable = ActividadIndividualEvaluable.getActividad(idActividad);
		} else if (ActividadGrupalEvaluable.esTipoValido(xml)) {
			evaluable = ActividadGrupalEvaluable.getActividad(idActividad);
		} else {
			// LEVANTAR EXCEPCION
		}
		// TODO Terminar de implementar
	}
	
	// TODO: Refactorizar
	private Evaluable encontrarActividadEvaluable(long idActividad) throws XmlErroneoExcepcion {
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

	public String getNota(String username ,long idActividad, long idEvaluado) throws XmlErroneoExcepcion, NotaInexistenteExcepcion {
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
	public String getNotas(String username ,long idActividad) throws XmlErroneoExcepcion {
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
