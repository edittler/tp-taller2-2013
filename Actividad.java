package wtp;

public class Actividad {
	private int id;
	private String nombre;
	private String descripcion;
	// - coordinadores : ArrayList<Miembro>
	// - cartelera : Cartelera
	// - foro : Foro
	// - actividadesInternas : ArrayList<Actividad>
	// - chat : Chat
	// - fechaInicio : Fecha
	// - fechaFin : Fecha
	// - muroSuperior : Informable
	public Actividad(){
		//claramente no podemos utilizar construcores para los atributos, pero para parametros que todas las instancias compartitian podrian estar aca
		
		// esto es solo para demostracion
		id=3583; // aca vamos a llamar a integracion para levantar los datos
		nombre="pepe";
	}

	public int getId() {
		return id;
	}

	public String setId(int id) {
		this.id = id;
		return "OK id set to"+id;
		// quiero hacer especial enfasis aca que si no se persiste el id en un archivo (o en integracion)
		// el getId va a devolver siempre 3583 pruebenlo!!
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre; // idem que setId
	}
	
	//FALTA HACER
	// devuelve xml 
	public String getActividades(int idAmbito ,String tipoAmbito){
		return "none";
	}
	public int crearActividadIndividual(String propiedades_xml){
		return 0;
	}
	public int crearActividadGrupal(String propiedades_xml){
		return 0;
	}
	public int crearActividadIndividualEvaluable(String propiedades_xml){
		return 0;
	}
	public int crearActividadGrupalEvaluable(String propiedades_xml){
		return 0;
	}
	public int crearActividadGrupalEvaluableGruposExlusivos(String propiedades_xml) {
		return 0;
	}
	// esta devuelve un xml
	public String getPropiedades(int IdActividad){
		return "none";
	}
	
}
