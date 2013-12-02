package fiuba.taller.actividad;

import java.io.IOException;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

public class ActividadGrupal extends Actividad {

	protected static final String TIPO_ACTIVIDAD_GRUPAL = "Grupal";
	
	protected boolean gruposExclusivos;

	public ActividadGrupal() {
		super();
		tipo = TIPO_ACTIVIDAD_GRUPAL;
		gruposExclusivos = false;
	}

	public void agregarGrupo(long idGrupo) {
		/*
		 * TODO Implementar Previamente se debe verificar si el grupo ya existe.
		 * En ese caso, se debe lanzar una excepcion.
		 */
	}

	public void eliminarGrupo(long idGrupo) {
		/*
		 * TODO Implementar Previamente se debe verificar si el grupo existe. En
		 * ese caso de que no exista, se debe lanzar una excepcion.
		 */
	}

	// FIXME Se devuelve, por lo pronto, una lista de IDs de grupo
	public List<Long> getGrupos() {
		// TODO: Implementar
		return null;
	}

	public static boolean esTipoValido(String xml) throws ParserConfigurationException, SAXException, IOException {
		Actividad actividad = new Actividad();
		actividad.descerializar(xml);
		if (actividad.tipo.equals(TIPO_ACTIVIDAD_GRUPAL)){
			return true;
		}
		return false;
	}

	public static ActividadGrupalEvaluable crearInstancia(String xmlPropiedades) throws ParserConfigurationException, SAXException, IOException {
		ActividadGrupalEvaluable actividad = new ActividadGrupalEvaluable();
		actividad.descerializar(xmlPropiedades);
		// TODO(Pampa) Obtener un ID nuevo
		// actividad.id = nuevoId;
		actividad.tipo = TIPO_ACTIVIDAD_GRUPAL;
		// TODO(Pampa) Validar fecha y lanzar excepcion
		actividad.guardarEstado();
		return actividad;
	}

	public static ActividadGrupal getActividad(long idActividad) throws ParserConfigurationException, SAXException, IOException {
		ActividadGrupal actividad = new ActividadGrupal();
		String xml = actividad.realizarConsulta(idActividad);
		actividad.descerializar(xml);
		return actividad;
	}
}
