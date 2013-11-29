package fiuba.taller.actividad;

public class NotaIndividual extends Nota {
	long idParticipante;
	public NotaIndividual(){
		idParticipante = -1;
		nota="";
		idActividad=-1;
		observaciones="";
	}
	public void descerializa(String xml) {

	}
	public void setNota (long idActividad){
		
	}
	protected String serializar (){
		String idActividadString="";
		String idParticipanteString="";
		if(idActividad>=0){
			idActividadString=String.valueOf(idActividad);
		}
		if(idParticipante>=0){
			idParticipanteString=String.valueOf(idParticipante);
		}
		return "<idActividad>"+idActividadString+"</idActividad>"
				+"<idParticipante>"+idParticipanteString+"</idParticipante>"
				+"<nota>"+nota+"</nota>"
				+"<observaciones>"+observaciones+"</observaciones>";
	}
}
