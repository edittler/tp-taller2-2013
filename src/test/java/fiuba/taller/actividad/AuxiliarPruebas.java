package fiuba.taller.actividad;

public class AuxiliarPruebas {
	public static String auxGenerarXmlConTipo(String tipo){
		long idPrueba = 22;
		long idAmbSupStr = 99;
		long idActSupStr = 77;
		String nombrePrueba = "langosta";
		String descripcion = "nada q ver nada q oler";
		String fechaIni = "11/11/11";
		String fechaFin = "12/12/12";
		
		String xml = "<?xml version=\"1.0\"?><WS><Actividad>"
				+ "<id>" + idPrueba + "</id>" 
				+ "<idAmbitoSuperior>" + idAmbSupStr + "</idAmbitoSuperior>"
				+ "<idActividadSuperior>" + idActSupStr + "</idActividadSuperior>"
				+ "<nombre>" + nombrePrueba + "</nombre>"
				+ "<Tipo>"+ tipo + "</Tipo>" 
				+ "<Descripcion>"+ descripcion + "</Descripcion>" 
				+ "<fechainicio>"+ fechaIni + "</fechainicio>" 
				+ "<fechafin>"+ fechaFin + "</fechafin>" 
				+ "</Actividad></WS>";
		return xml;
	}
}
