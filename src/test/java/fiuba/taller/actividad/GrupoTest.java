package fiuba.taller.actividad;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import fiuba.taller.actividad.Grupo;

public class GrupoTest {
	
	Grupo grupo;
	String xmlADescerializar;
	long idGrupo;
	long idParticipante1;
	long idParticipante2;
	
	@Before
	public void setUp() throws Exception {
		grupo = new Grupo();
		idGrupo = 10;
		idParticipante1 = 20;
		idParticipante2 = 88;
		xmlADescerializar =  "<?xml version=\"1.0\"?><WS><Grupo>"
				+ "<IdGrupo>" + idGrupo + "</IdGrupo>"
				+ "<IdParticipante>" + idParticipante1 + "</IdParticipante>"
				+ "<IdParticipante>" + idParticipante2 + "</IdParticipante>"
				+ "</Grupo></WS>";
	}

	@After
	public void tearDown() throws Exception {
	}
/*
	@Test
	public void testGetXml() {
		fail("Not yet implemented");
	}
*/
	@Test
	public void serializar() {		
		grupo.descerializar(xmlADescerializar);
		
		String xmlFinal = grupo.serializar();
		
		assertEquals(xmlADescerializar, xmlFinal);
	}
	
	@Test
	public void testDescerializar() {
		
		grupo.descerializar(xmlADescerializar);
		
		assertEquals(idGrupo, grupo.getId());

		if(grupo.getIdParticipantes().get(0) != idParticipante1){
			fail("IdParticipante esperado: "+idParticipante1+" IdParticipante encontrado: "+grupo.getIdParticipantes().get(0));
		}
		if(grupo.getIdParticipantes().get(1) != idParticipante2){
			fail("IdParticipante esperado: "+idParticipante2+" IdParticipante encontrado: "+grupo.getIdParticipantes().get(1));
		}
	}
/*
	@Test
	public void testGuardarEstado() {
		fail("Not yet implemented");
	}
*/
}
