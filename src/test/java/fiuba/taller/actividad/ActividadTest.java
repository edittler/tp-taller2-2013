package fiuba.taller.actividad;

import static org.junit.Assert.*;

import java.rmi.RemoteException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

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
	public void testDescerializar() throws RemoteException {
		long idPrueba = 22;
		long idAmbSupStr = 99;
		long idActSupStr = 77;
		String nombrePrueba = "langosta";
		String tipo = "bay guy";
		String descripcion = "nada q ver nada q oler";
		long fechaIni = 111111;
		long fechaFin = 121212;
		
		String xmlADescerializar = "<?xml version=\"1.0\"?><WS><Actividad>"
				+ "<Id>" + idPrueba + "</Id>" 
				+ "<IdAmbitoSuperior>" + idAmbSupStr + "</IdAmbitoSuperior>"
				+ "<IdActividadSuperior>" + idActSupStr + "</IdActividadSuperior>"
				+ "<Nombre>" + nombrePrueba + "</Nombre>"
				+ "<Tipo>" + tipo + "</Tipo>" 
				+ "<Descripcion>" + descripcion + "</Descripcion>" 
				+ "<FechaInicio>" + fechaIni + "</FechaInicio>" 
				+ "<FechaFin>" + fechaFin + "</FechaFin>" 
				+ "</Actividad></WS>";
		
		act.descerializar(xmlADescerializar);
		
		assertEquals(idPrueba, act.getId());
		assertEquals(idAmbSupStr,act.getIdAmbitoSuperior());
		assertEquals(idActSupStr,act.getIdActividadSuperior());
		assertEquals(nombrePrueba,act.getNombre());
		assertEquals(tipo,act.getTipo());
		assertEquals(descripcion,act.getDescripcion());
		assertEquals(fechaIni,act.getFechaInicio());
		assertEquals(fechaFin,act.getFechaFin());
	}

	@Test
	public void testDescerializarXMLSinEncabezadoNiTagWS() throws RemoteException {
		long idPrueba = 22;
		long idAmbSupStr = 99;
		long idActSupStr = 77;
		String nombrePrueba = "langosta";
		String tipo = "bay guy";
		String descripcion = "nada q ver nada q oler";
		long fechaIni = 484651;
		long fechaFin = 121212;
		
		String xmlADescerializar = "<Actividad>"
				+ "<Id>" + idPrueba + "</Id>" 
				+ "<IdAmbitoSuperior>" + idAmbSupStr + "</IdAmbitoSuperior>"
				+ "<IdActividadSuperior>" + idActSupStr + "</IdActividadSuperior>"
				+ "<Nombre>" + nombrePrueba + "</Nombre>"
				+ "<Tipo>"+ tipo + "</Tipo>" 
				+ "<Descripcion>"+ descripcion + "</Descripcion>" 
				+ "<FechaInicio>"+ fechaIni + "</FechaInicio>" 
				+ "<FechaFin>"+ fechaFin + "</FechaFin>" 
				+ "</Actividad>";
		
		act.descerializar(xmlADescerializar);
		
		assertEquals(idPrueba, act.getId());
		assertEquals(idAmbSupStr,act.getIdAmbitoSuperior());
		assertEquals(idActSupStr,act.getIdActividadSuperior());
		assertEquals(nombrePrueba,act.getNombre());
		assertEquals(tipo,act.getTipo());
		assertEquals(descripcion,act.getDescripcion());
		assertEquals(fechaIni,act.getFechaInicio());
		assertEquals(fechaFin,act.getFechaFin());
	}

	@Test(expected = RemoteException.class)
	public void testDescerializarConXmlSinActividadCorrecta() throws RemoteException {
		long idPrueba = 22;
		long idAmbSupStr = 99;
		long idActSupStr = 77;
		String nombrePrueba = "langosta";
		String tipo = "bay guy";
		String descripcion = "nada q ver nada q oler";
		String fechaIni = "11/11/11";
		String fechaFin = "12/12/12";
		
		// Cambiamos el tag <Actividad> por <TAGACTIVIDAD> para que falle
		String xmlADescerializar = "<?xml version=\"1.0\"?><WS><TAGACTIVIDAD>"
				+ "<Id>" + idPrueba + "</Id>" 
				+ "<IdAmbitoSuperior>" + idAmbSupStr + "</IdAmbitoSuperior>"
				+ "<IdActividadSuperior>" + idActSupStr + "</IdActividadSuperior>"
				+ "<Nombre>" + nombrePrueba + "</Nombre>"
				+ "<Tipo>" + tipo + "</Tipo>" 
				+ "<Descripcion>" + descripcion + "</Descripcion>" 
				+ "<FechaInicio>" + fechaIni + "</FechaInicio>" 
				+ "<FechaFin>" + fechaFin + "</FechaFin>" 
				+ "</TAGACTIVIDAD></WS>";
		
		act.descerializar(xmlADescerializar);
	}

	@Test(expected = RemoteException.class)
	public void testDescerializarConXmlConDosActividades() throws RemoteException {
	long idPrueba = 22;
		long idAmbSupStr = 99;
		long idActSupStr = 77;
		String nombrePrueba = "langosta";
		String tipo = "bay guy";
		String descripcion = "nada q ver nada q oler";
		String fechaIni = "11/11/11";
		String fechaFin = "12/12/12";
		
		// Agregamos dos nodos <Actividad> para que falle
		String xmlADescerializar = "<?xml version=\"1.0\"?><WS><Actividad>"
				+ "<Id>" + idPrueba + "</Id>" 
				+ "<IdAmbitoSuperior>" + idAmbSupStr + "</IdAmbitoSuperior>"
				+ "<IdActividadSuperior>" + idActSupStr + "</IdActividadSuperior>"
				+ "<Nombre>" + nombrePrueba + "</Nombre>"
				+ "<Tipo>" + tipo + "</Tipo>"
				+ "<Descripcion>" + descripcion + "</Descripcion>"
				+ "<FechaInicio>" + fechaIni + "</FechaInicio>"
				+ "<FechaFin>" + fechaFin + "</FechaFin>" 
				+ "</Actividad>"
				+ "<Actividad>"
				+ "<Id>" + idPrueba + "</Id>"
				+ "<IdAmbitoSuperior>" + idAmbSupStr + "</IdAmbitoSuperior>"
				+ "<IdActividadSuperior>" + idActSupStr + "</IdActividadSuperior>"
				+ "<Nombre>" + nombrePrueba + "</Nombre>"
				+ "<Tipo>" + tipo + "</Tipo>"
				+ "<Descripcion>" + descripcion + "</Descripcion>"
				+ "<FechaInicio>" + fechaIni + "</FechaInicio>"
				+ "<FechaFin>" + fechaFin + "</FechaFin>"
				+ "</Actividad>"
				+ "</WS>";
		
		act.descerializar(xmlADescerializar);
	}

	@Test
	public void testSerializar() throws RemoteException {
		String xmlADescerializar = AuxiliarPruebas.auxGenerarXml("no me importa el tipo","","");
		
		act.descerializar(xmlADescerializar);
		
		String xmlFinal = act.serializar();
		
		assertEquals(xmlADescerializar, xmlFinal);
	}
/*
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
*/
}
