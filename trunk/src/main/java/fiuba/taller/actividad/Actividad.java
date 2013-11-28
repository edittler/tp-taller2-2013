package fiuba.taller.actividad;

import fiuba.taller.xml.XMLParser;

public class Actividad {
	private long id;
	private String nombre;
	//private String descripcion;
	// - coordinadores : ArrayList<Miembro>
	// - cartelera : Cartelera
	// - foro : Foro
	// - actividadesInternas : ArrayList<Actividad>
	// - chat : Chat (necesario ??)
	// usar desp tipo de dato correcto en fechas
	String fechaInicio;
	String fechaFin;
	// - muroSuperior : Informable

	public Actividad(){
		id=10; // aca vamos a llamar a integracion para levantar los datos
		nombre="pepe";
		fechaFin="mamam";
		fechaInicio="hola";
	}

	// ------------------------metodos de webbservice ----------------
	public long getID() {
		return id;
	}

	public String getNombre() {
		return nombre;
	}

	public String getNombre(int id) {
		// cargar datos desde integracion usando id
		// ej: string xml = Integracion (blabla id blabla);
		//     obtener_datos(xml);

		// devolver nombre
		return nombre;
	}

	public void setNombre(int id, String nombre) {
		// cargar datos desde integracion usando id
		// ej: string xml = Integracion (blabla id blabla);
		//     obtener_datos(xml);
		this.nombre = nombre;
		// guardar datos a integracion
	}
	//FALTA HACER
	// devuelve xml 
	public String getActividades(int idAmbito ,String tipoAmbito){
		if (tipoAmbito == "Ambito"){
			return "usted pidio las actividades que contiene un Ambito";
		}else{
			return "usted pidio las actividades que contiene una actividad";
		}
	}

	// esta devuelve un xml
	public String getPropiedades(int IdActividad){
		// cargo datos de integracion
		// devuelvo la clase serializada(necesario, no combiene directamente enviar xml que devuelve integracion??)
		XMLParser xmlParser = new XMLParser();
		return xmlParser.serializarActividad(this);
	}

	/*  METODOS AUXILIARES PRIVADOS */
}
