package fiuba.taller.actividad;

import java.io.StringWriter;
import java.util.List;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

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
			String xml = act.realizarConsulta();
			// por ahora devolvemos los xml directamente
			return xml;
		} else if (tipoAmbito.equalsIgnoreCase("Actividad")) {
			Actividad act = new Actividad();
			act.setIdActividadSuperior(idAmbito);
			String xml = act.realizarConsulta();
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
	 * @param xmlPropiedades
	 *            XML que contiene las propiedades de la actividad a crear
	 * @return Identificador de la actividad creada
	 */
	public long crearActividadIndividual(String xmlPropiedades) {
		ActividadIndividual actividad = ActividadIndividual
				.crearInstancia(xmlPropiedades);
		return actividad.getId();
	}

	public long crearActividadGrupal(String xmlPropiedades) {
		ActividadGrupal actividad = ActividadGrupal
				.crearInstancia(xmlPropiedades);
		return actividad.getId();
	}

	public long crearActividadIndividualEvaluable(String xmlPropiedades) {
		ActividadIndividualEvaluable actividad = ActividadIndividualEvaluable
				.crearInstancia(xmlPropiedades);
		return actividad.getId();
	}

	public long crearActividadGrupalEvaluable(String xmlPropiedades) {
		ActividadGrupalEvaluable actividad = ActividadGrupalEvaluable
				.crearInstancia(xmlPropiedades);
		return actividad.getId();
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
	
	// TODO: Refactorizar! Mover este metodo al lugar adecuado
	public String getStringFromDocument(Document doc) 
			throws TransformerException {
	    DOMSource domSource = new DOMSource(doc);
	    StringWriter writer = new StringWriter();
	    StreamResult result = new StreamResult(writer);
	    TransformerFactory tf = TransformerFactory.newInstance();
	    Transformer transformer = tf.newTransformer();
	    transformer.transform(domSource, result);
	    return writer.toString();
	}

	public String getParticipantes(long idActividad) {
		ActividadIndividual actInd = 
				ActividadIndividual.getActividad(idActividad);
		/* Se obtiene la lista de participantes inscriptos a la 
		* actividad. TODO: Hay que ver que informacion nos dan!
		* Por lo pronto manejaré solamente los IDs
		*/
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
		
		return xmlParticipantes;
	}

	public void getParticipante(long idActividad, long idParticipante) {
		// TODO Implementar getParticipante (¿comunicarse con Participacion?)
	}

	/* METODOS COMUNES A LAS ACTIVIDADES GRUPALES */

	public void agregarGrupo(long idActividad, long idGrupo) {
		// TODO Implementar
	}

	public void eliminarGrupo(long idActividad, long idGrupo) {
		// TODO Implementar
	}

	public String getGrupos(long idActividad) {
		ActividadGrupal actGrup = 
				ActividadGrupal.getActividad(idActividad);
		/* Se obtiene la lista de grupos inscriptos a la 
		* actividad. TODO: Hay que ver que informacion nos dan!
		* Por lo pronto se maneja solo IDs
		*/
		List<Long> grupos = actGrup.getGrupos();

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
				
		return xmlGrupos;
	}

	public void getGrupo(long idActividad, long idGrupo) {
		// TODO Implementar getGrupo (¿comunicarse con Participacion?)
	}

	/* METODOS COMUNES A LAS ACTIVIDADES EVALUABLES */

	// Evaluado puede ser un participante o un grupo, dependiendo si la
	// actividad es ind o grupal
	public void evaluar(long idActividad, long idEvaluado, String nota) {
		Actividad actividad = new Actividad();
		String xml = actividad.realizarConsulta(idActividad);
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
	
	// TODO: Refactorizar
	public IEvaluable encontrarActividadEvaluable(long idActividad) {
		// TODO Refactorizar la busqueda de actividades evaluables
		Actividad actividad = new Actividad();
		String xml = actividad.realizarConsulta(idActividad);
		IEvaluable evaluable = null;
		if (ActividadIndividualEvaluable.esTipoValido(xml)) {
			return ActividadIndividualEvaluable.getActividad(idActividad);
		} else if (ActividadGrupalEvaluable.esTipoValido(xml)) {
			return ActividadGrupalEvaluable.getActividad(idActividad);
		} else {
			return evaluable;
		}
	}

	public String getNota(long idActividad, long idEvaluado) {
		IEvaluable evaluable = encontrarActividadEvaluable(idActividad);
		if (evaluable == null) {
			//TODO: Lanzar alguna excepcion
		}
		Nota nota = evaluable.getNota(idEvaluado);
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
		return null;
	}

	// Verificar que la actividad sea evaluable
	public String getNotas(long idActividad) {
		IEvaluable evaluable = encontrarActividadEvaluable(idActividad);
		if (evaluable == null) {
			//TODO: Lanzar alguna excepcion
		}
		
		List<Nota> notas = evaluable.getNotas();
		
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
		
		return xmlNotas;
	}
}
