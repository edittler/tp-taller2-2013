package fiuba.taller.actividad;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import fiuba.taller.actividad.Grupo;

public class GrupoTest {
	Grupo grupo;
	@Before
	public void setUp() throws Exception {
		grupo = new Grupo();
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testGetXml() {
		fail("Not yet implemented");
	}

	@Test
	public void serializar() {
		long idGrupo = 10;
		long idParticipante1 = 20;
		long idParticipante2 = 88;
		String xmlADescerializar =  "<?xml version=\"1.0\"?><WS><Grupo>"
				+"<IdGrupo>" + idGrupo + "</IdGrupo>"
				+"<IdParticipante>"+ idParticipante1+ "</IdParticipante>"
				+"<IdParticipante>"+ idParticipante2+ "</IdParticipante>"
				+"</Grupo></WS>";
		
		grupo.descerializar(xmlADescerializar);
		
		String xml = grupo.serializar();
		
		if(!xml.equals(xmlADescerializar)){
			fail("xml DISTINTOS:");
		}
	}
	@Test
	public void testDescerializar() {
		long idGrupo = 10;
		long idParticipante1 = 20;
		long idParticipante2 = 88;
		String xmlADescerializar =  "<?xml version=\"1.0\"?><WS><Grupo>"
				+"<IdGrupo>" + idGrupo + "</IdGrupo>"
				+"<IdParticipante>"+ idParticipante1+ "</IdParticipante>"
				+"<IdParticipante>"+ idParticipante2+ "</IdParticipante>"
				+"</Grupo></WS>";
		
		grupo.descerializar(xmlADescerializar);
		
		if(grupo.getId() != idGrupo){
			fail("IdGrupo esperado: "+idGrupo+" IdGrupo encontrado: "+grupo.getId());
		}
		if(grupo.getIdParticipantes().get(0) != idParticipante1){
			fail("IdParticipante esperado: "+idParticipante1+" IdParticipante encontrado: "+grupo.getIdParticipantes().get(0));
		}
		if(grupo.getIdParticipantes().get(1) != idParticipante2){
			fail("IdParticipante esperado: "+idParticipante2+" IdParticipante encontrado: "+grupo.getIdParticipantes().get(1));
		}
	}

	@Test
	public void testGuardarEstado() {
		fail("Not yet implemented");
	}

}
