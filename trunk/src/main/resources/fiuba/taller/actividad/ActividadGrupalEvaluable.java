package fiuba.taller.actividad;

public class ActividadGrupalEvaluable extends ActividadGrupal implements IEvaluable {

	public ActividadGrupalEvaluable() {
		
	}
	
	public void evaluar(long idEvaluado, String nota);
	
	public String getNota(long idParticipante);
	
	public String getNotas();
}
