package fiuba.taller.actividad;

import java.util.List;

import fiuba.taller.actividad.excepcion.NotaInexistenteExcepcion;

/**
 * Interfaz que determina el comportamiento de las actividades evaluables.
 */
public interface Evaluable {

	/**
	 * Retorna el tipo de escala utilizada en la actividad evaluable.
	 */
	public String getEscala();

	/**
	 * Modifica el tipo de escala de la actividad evaluable.
	 * 
	 * @param escala
	 *            String con la nueva escala.
	 */
	public void setEscala(String escala);

	/**
	 * Asigna una nota al evaluado correspondiente.
	 * 
	 * @param idEvaluado
	 *            Identificador del evaluado.
	 * @param nota
	 *            String con la nota asignada al evaluado.
	 */
	public void evaluar(Object idEvaluado, String nota);

	/**
	 * Asigna una nota al evaluado correspondiente.
	 * 
	 * @param idEvaluado
	 *            Identificador del evaluado.
	 * @param nota
	 *            String con la nota asignada al evaluado.
	 * @param observaciones
	 *            String con las observaciones realizadas al evaluado.
	 */
	public void evaluar(Object idEvaluado, String nota, String observaciones);

	/**
	 * Devuelve la nota del evaluado.
	 * 
	 * @param idEvaluado
	 *            Identificador del evaluado que se desea obtener la nota.
	 * @throws NotaInexistenteExcepcion Si la nota asociada al evaluado no existe.
	 */
	public Nota getNota(Object idEvaluado) throws NotaInexistenteExcepcion;

	/**
	 * Retorna una lista de las notas asignadas a los evaluados de la actividad.
	 */
	public List<Nota> getNotas();
}
