package fiuba.taller.actividad;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import fiuba.taller.actividad.excepcion.XmlErroneoExcepcion;

public class ActividadTest {
	Actividad act;
	@Before
	public void setUp() throws Exception {
		act = new Actividad();
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testDescerializar() throws XmlErroneoExcepcion {
		long idPrueba = 22;
		long idAmbSupStr = 99;
		long idActSupStr = 77;
		String nombrePrueba = "langosta";
		String tipo = "bay guy";
		String descripcion = "nada q ver nada q oler";
		String fechaIni = "11/11/11";
		String fechaFin = "12/12/12";
		
		String xmlADescerializar = "<?xml version=\"1.0\"?><WS><Actividad>"
				+ "<Id>" + idPrueba + "</Id>" 
				+ "<IdAmbitoSuperior>" + idAmbSupStr + "</IdAmbitoSuperior>"
				+ "<IdActividadSuperior>" + idActSupStr + "</IdActividadSuperior>"
				+ "<Nombre>" + nombrePrueba + "</Nombre>"
				+ "<Tipo>"+ tipo + "</Tipo>" 
				+ "<Descripcion>"+ descripcion + "</Descripcion>" 
				+ "<FechaInicio>"+ fechaIni + "</FechaInicio>" 
				+ "<FechaFin>"+ fechaFin + "</FechaFin>" 
				+ "</Actividad></WS>";
		
		act.descerializar(xmlADescerializar);
		
		assertEquals("id esperado: "+idPrueba+" id encontrado: "+act.getId(), act.getId(), idPrueba);
		if (act.getId() != idPrueba){
			fail("id esperado: "+idPrueba+" id encontrado: "+act.getId());
		}
		if(act.getIdAmbitoSuperior() != idAmbSupStr){
			fail("idAmbitoSuperior esperado: "+idAmbSupStr+" idAmbitoSuperior encontrado: "+act.getIdAmbitoSuperior());
		}
		if(act.getIdActividadSuperior() != idActSupStr){
			fail("idActividadSuperior esperado: "+idActSupStr+" idActividadSuperior encontrado: "+act.getIdActividadSuperior());
		}
		if(!act.getNombre().equals(nombrePrueba)){
			fail("nombre esperado: "+nombrePrueba+" nombre encontrado: "+act.getNombre());
		}
		if(!act.getTipo().equals(tipo)){
			fail("tipo esperado: "+tipo+"tipo encontrado: "+act.getTipo());
		}
		if(!act.getDescripcion().equals(descripcion)){
			fail("descripcion esperada: "+descripcion+" descripcion encontrada: "+act.getDescripcion());
		}
		if(!act.getFechaInicio().equals(fechaIni)) {
			fail("fecha inicio distinta");
		}
		if(!act.getFechaFin().equals(fechaFin)){
			fail("fecha fin distinta");
		}
		
	}

	@Test(expected=XmlErroneoExcepcion.class)
	public void testDescerializarConXmlSinActividadCorrecta() throws XmlErroneoExcepcion {
		long idPrueba = 22;
		long idAmbSupStr = 99;
		long idActSupStr = 77;
		String nombrePrueba = "langosta";
		String tipo = "bay guy";
		String descripcion = "nada q ver nada q oler";
		String fechaIni = "11/11/11";
		String fechaFin = "12/12/12";
		
		String xmlADescerializar = "<?xml version=\"1.0\"?><WS><Actividadd>"
				+ "<Id>" + idPrueba + "</Id>" 
				+ "<IdAmbitoSuperior>" + idAmbSupStr + "</IdAmbitoSuperior>"
				+ "<IdActividadSuperior>" + idActSupStr + "</IdActividadSuperior>"
				+ "<Nombre>" + nombrePrueba + "</Nombre>"
				+ "<Tipo>"+ tipo + "</Tipo>" 
				+ "<Descripcion>"+ descripcion + "</Descripcion>" 
				+ "<FechaInicio>"+ fechaIni + "</FechaInicio>" 
				+ "<FechaFin>"+ fechaFin + "</FechaFin>" 
				+ "</Actividadd></WS>";
		act.descerializar(xmlADescerializar);
	}

	@Test(expected=XmlErroneoExcepcion.class)
	public void testDescerializarConXmlConDosActividades() throws XmlErroneoExcepcion {
		long idPrueba = 22;
		long idAmbSupStr = 99;
		long idActSupStr = 77;
		String nombrePrueba = "langosta";
		String tipo = "bay guy";
		String descripcion = "nada q ver nada q oler";
		String fechaIni = "11/11/11";
		String fechaFin = "12/12/12";
		
		String xmlADescerializar = "<?xml version=\"1.0\"?><WS><Actividad>"
				+ "<Id>" + idPrueba + "</Id>" 
				+ "<IdAmbitoSuperior>" + idAmbSupStr + "</IdAmbitoSuperior>"
				+ "<IdActividadSuperior>" + idActSupStr + "</IdActividadSuperior>"
				+ "<Nombre>" + nombrePrueba + "</Nombre>"
				+ "<Tipo>"+ tipo + "</Tipo>"
				+ "<Descripcion>"+ descripcion + "</Descripcion>"
				+ "<FechaInicio>"+ fechaIni + "</FechaInicio>"
				+ "<FechaFin>"+ fechaFin + "</FechaFin>" 
				+ "</Actividad>"
				+ "<Actividad>"
				+ "<Id>" + idPrueba + "</Id>"
				+ "<IdAmbitoSuperior>" + idAmbSupStr + "</IdAmbitoSuperior>"
				+ "<IdActividadSuperior>" + idActSupStr + "</IdActividadSuperior>"
				+ "<Nombre>" + nombrePrueba + "</Nombre>"
				+ "<Tipo>"+ tipo + "</Tipo>"
				+ "<Descripcion>"+ descripcion + "</Descripcion>"
				+ "<FechaInicio>"+ fechaIni + "</FechaInicio>"
				+ "<FechaFin>"+ fechaFin + "</FechaFin>"
				+ "</Actividad>"
				+ "</WS>";
		act.descerializar(xmlADescerializar);
	}

	@Test
	public void testSerializar() throws XmlErroneoExcepcion {
		long idPrueba = 22;
		long idAmbSupStr = 99;
		long idActSupStr = 77;
		String nombrePrueba = "langosta";
		String tipo = "bay guy";
		String descripcion = "nada q ver nada q oler";
		String fechaIni = "11/11/11";
		String fechaFin = "12/12/12";
		
		String xmlADescerializar = "<?xml version=\"1.0\"?><WS><Actividad>"
				+ "<Id>" + idPrueba + "</Id>" 
				+ "<IdAmbitoSuperior>" + idAmbSupStr + "</IdAmbitoSuperior>"
				+ "<IdActividadSuperior>" + idActSupStr + "</IdActividadSuperior>"
				+ "<Nombre>" + nombrePrueba + "</Nombre>"
				+ "<Tipo>"+ tipo + "</Tipo>" 
				+ "<Descripcion>"+ descripcion + "</Descripcion>" 
				+ "<FechaInicio>"+ fechaIni + "</FechaInicio>" 
				+ "<FechaFin>"+ fechaFin + "</FechaFin>" 
				+ "</Actividad></WS>";
		
		act.descerializar(xmlADescerializar);
		
		String xmlFinal = act.serializar();
		
		if(!xmlADescerializar.equals(xmlFinal)){
			fail("los xml son DISTINTOS\nxml original:\n"+xmlADescerializar+"\nxml de actividad:\n"+xmlFinal);
		}
	}

	@Test
	public void testGuardarEstado() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetXmlLong() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetXml() {
		fail("Not yet implemented");
	}

}
