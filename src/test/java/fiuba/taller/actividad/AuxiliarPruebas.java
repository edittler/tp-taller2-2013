package fiuba.taller.actividad;

public class AuxiliarPruebas {
	public static String auxGenerarXml(String tipo,String tipoEscala, String gruposExclusivos) {
		
		long idPrueba = 22;
		long idAmbSupStr = 1;
		long idActSupStr = 14;
		String nombrePrueba = "Una gran actividad";
		String descripcion = "La mejor actividad del mundo";
		String fechaIni = "111111";
		String fechaFin = "121212";
		
		String xml = "<?xml version=\"1.0\"?><WS><Actividad>"
				+ "<id>" + idPrueba + "</id>"
				+ "<nombre>" + nombrePrueba + "</nombre>"
				+ "<tipo>" + tipo + "</tipo>"
				+ "<ambitoSuperiorId>" + idAmbSupStr + "</ambitoSuperiorId>"
				+ "<actividadSuperiorId>" + idActSupStr + "</actividadSuperiorId>"
				+ "<descripcion>" + descripcion + "</descripcion>"
				+ "<fechaInicio>" + fechaIni + "</fechaInicio>"
				+ "<fechaFin>" + fechaFin + "</fechaFin>";
					if (gruposExclusivos != "") {
						xml += "<gruposExclusivos>" + gruposExclusivos
								+ "</gruposExclusivos>";
					}
					if (tipoEscala != "") {
						xml += "<tipoEscala>" + tipoEscala + "</tipoEscala>";
					}
				  xml +="</Actividad></WS>";
		
		return xml;
	}
	
}
