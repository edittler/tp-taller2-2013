package tests.actividad;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import fiuba.taller.actividad.Actividad;

public class PruebasActividad {
	Actividad act;
	@Before
	public void setUp() throws Exception {
		act = new Actividad();
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testDescerializar() {
		long idPrueba = 22;
		long idAmbSupStr = 99;
		long idActSupStr = 77;
		String nombrePrueba = "langosta";
		String tipo = "bay guy";
		String descripcion = "nada q ver nada q oler";
		String fechaIni = "11/11/11";
		String fechaFin = "12/12/12";
		
		String xmlADescerializar = "<?xml version=\"1.0\"?><WS><Actividad>"
				+ "<id>" + idPrueba + "</id>" 
				+"<idAmbitoSuperior>" + idAmbSupStr + "</idAmbitoSuperior>"
				+ "<idActividadSuperior>" + idActSupStr + "</idActividadSuperior>"
				+ "<nombre>" + nombrePrueba + "</nombre>"
				+ "<Tipo>"+ tipo + "</Tipo>" 
				+ "<Descripcion>"+ descripcion + "</Descripcion>" 
				+ "<fechaini>"+ fechaIni + "</fechaini>" 
				+ "<fechafin>"+ fechaFin + "</fechafin>" 
				+ "</Actividad></WS>";
		
		act.descerializar(xmlADescerializar);
		
		if (act.getId() != idPrueba){
			fail("id esperado: "+idPrueba+" id encontrado: "+act.getId());
		}
		if(act.getIdActiSup() != idActSupStr){
			fail("idActividadSuperior esperado: "+idActSupStr+" idActividadSuperior encontrado: "+act.getIdActiSup());
		}
		if(!act.getNombre().equals(nombrePrueba)){
			fail("nombre esperado: "+nombrePrueba+" nombre encontrado: "+act.getNombre());
		}
		if(act.getIdAmbSup() != idAmbSupStr){
			fail("idAmbitoSuperior esperado: "+idAmbSupStr+" idAmbitoSuperior encontrado: "+act.getIdAmbSup());
		}
		if(!act.getFechaIni().equals(fechaIni)) {
			fail("fecha inicio distinta");
		}
		if(!act.getFechaFin().equals(fechaFin)){
			fail("fecha fin distinta");
		}
		if(!act.getDescripcion().equals(descripcion)){
			fail("descripcion esperada: "+descripcion+" descripcion encontrada: "+act.getDescripcion());
		}
	}

	@Test
	public void testSerializar() {
		fail("Not yet implemented");
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
