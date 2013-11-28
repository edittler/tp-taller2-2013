package fiuba.taller.actividad;

public class ActividadIndividualEvaluable extends ActividadIndividual implements IEvaluable {

	public ActividadIndividualEvaluable() {
		
	}
	
	public void evaluar(long idEvaluado, String nota);
	
	public String getNota(long idParticipante);
	
	public String getNotas();
}
