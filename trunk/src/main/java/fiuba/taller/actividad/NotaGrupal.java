package fiuba.taller.actividad;

public class NotaGrupal extends Nota {
	long idGrupo;
	public NotaGrupal(){
		idGrupo=-1;
		idActividad=-1;
		nota="";
		observaciones="";
	}
	public void descerializa(String xml) {

	}
	public void setNota (long idActividad){
		
	}
	protected String serializar (){
		String idActividadString="";
		String idGrupoString="";
		if(idActividad>=0){
			idActividadString=String.valueOf(idActividad);
		}
		if(idGrupo>=0){
			idGrupoString=String.valueOf(idGrupo);
		}
		return "<idActividad>"+idActividadString+"</idActividad>"
				+"<idGrupo>"+idGrupoString+"</idGrupo>"
				+"<nota>"+nota+"</nota>"
				+"<observaciones>"+observaciones+"</observaciones>";
	}
}
