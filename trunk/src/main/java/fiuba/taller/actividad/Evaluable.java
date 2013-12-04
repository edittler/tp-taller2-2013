package fiuba.taller.actividad;

import java.util.List;

public interface Evaluable {

	/*
	 * idEvaluado: Puede representar tanto a un participante como a un grupo
	 * nota: Numerica o conceptual
	 * 
	 * Asigna nota al evaluado correspondiente
	 * */
	public void evaluar(long idEvaluado, String nota);
	
	/*
	 * 
	 * */
	public String getNota(long idEvaluado);
	
	/*
	 * 
	 * */
	public List<Nota> getNotas();
}
