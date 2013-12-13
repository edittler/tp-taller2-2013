package fiuba.taller.actividad;

public class AuxiliarPruebas {
	
	public static String auxGenerarXmlConTipo(String tipo){
		
		long idPrueba = 22;
		long idAmbSupStr = 99;
		long idActSupStr = 77;
		String nombrePrueba = "langosta";
		String descripcion = "nada q ver nada q oler";
		String fechaIni = "111111";
		String fechaFin = "121212";
		
		String xml = "<?xml version=\"1.0\"?><WS><Actividad>"
				+ "<Id>" + idPrueba + "</Id>" 
				+ "<IdAmbitoSuperior>" + idAmbSupStr + "</IdAmbitoSuperior>"
				+ "<IdActividadSuperior>" + idActSupStr + "</IdActividadSuperior>"
				+ "<Nombre>" + nombrePrueba + "</Nombre>"
				+ "<Tipo>" + tipo + "</Tipo>" 
				+ "<Descripcion>" + descripcion + "</Descripcion>" 
				+ "<FechaInicio>" + fechaIni + "</FechaInicio>" 
				+ "<FechaFin>"+ fechaFin + "</FechaFin>" 
				+ "</Actividad></WS>";
		
		return xml;
	}

	public static String auxGenerarXml(String tipo, String esEvaluable,
			String tipoEscala, String gruposExclusivos) {
		
		long idPrueba = 22;
		long idAmbSupStr = 99;
		long idActSupStr = 77;
		String nombrePrueba = "langosta";
		String descripcion = "nada q ver nada q oler";
		String fechaIni = "111111";
		String fechaFin = "121212";
		
		String xml = "<?xml version=\"1.0\"?><WS><Actividad>"
				  + "<Id>"+idPrueba+ "</Id>"
				  + "<Nombre>"+nombrePrueba+ "</Nombre>"
				  + "<Tipo>"+ tipo +"</Tipo>"
				  + "<IdAmbitoSuperior>"+idAmbSupStr+ "</IdAmbitoSuperior>"
				  + "<IdActividadSuperior>"+idActSupStr+ "</IdActividadSuperior>"
				  + "<TipoEscala>"+tipoEscala+ "</TipoEscala>"
				  + "<GruposExclusivos>"+gruposExclusivos+ "</GruposExclusivos>"
				  + "<Descripcion>"+descripcion+ "</Descripcion>"
				  + "<FechaInicio>"+fechaIni+ "</FechaInicio>"
				  + "<FechaFin>"+fechaFin+ "</FechaFin>"
				  + "</Actividad></WS>";
		
		return xml;
	}
	
}
