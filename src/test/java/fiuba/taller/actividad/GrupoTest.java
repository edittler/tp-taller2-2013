package fiuba.taller.actividad;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import fiuba.taller.actividad.Grupo;
import fiuba.taller.actividad.excepcion.ParticipanteExistenteExcepcion;
import fiuba.taller.actividad.excepcion.ParticipanteInexistenteExcepcion;
import fiuba.taller.actividad.excepcion.XmlErroneoExcepcion;

public class GrupoTest {

	private Grupo grupo;
	private long idActividad;
	private long idGrupo;
	private String xmlADescerializar;
	String usernameParticipante1;
	String usernameParticipante2;

	@Before
	public void setUp() throws Exception {
		grupo = new Grupo();
		idActividad = 2832;
		idGrupo = 10;
		usernameParticipante1 = "pepe";
		usernameParticipante2 = "tito";
		xmlADescerializar = "<?xml version=\"1.0\"?><WS><Grupo>"
				+ "<IdActividad>" + idActividad + "</IdActividad>"
				+ "<IdGrupo>" + idGrupo + "</IdGrupo>"
				+ "<usernameParticipante>" + usernameParticipante1
				+ "</usernameParticipante>" + "<usernameParticipante>"
				+ usernameParticipante2 + "</usernameParticipante>"
				+ "</Grupo></WS>";
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void agregarParticipanteCorrecto()
			throws ParticipanteExistenteExcepcion {
		grupo.agregarParticipante(usernameParticipante1);
		grupo.agregarParticipante(usernameParticipante2);
		assertTrue(grupo.contieneParticipante(usernameParticipante1));
		assertTrue(grupo.contieneParticipante(usernameParticipante2));
	}

	@Test(expected = ParticipanteExistenteExcepcion.class)
	public void agregarParticipanteExistente()
			throws ParticipanteExistenteExcepcion {
		grupo.agregarParticipante(usernameParticipante1);
		grupo.agregarParticipante(usernameParticipante1);
	}

	@Test
	public void eliminarParticipanteCorrecto()
			throws ParticipanteExistenteExcepcion,
			ParticipanteInexistenteExcepcion {
		grupo.agregarParticipante(usernameParticipante1);

		assertTrue(grupo.contieneParticipante(usernameParticipante1));

		grupo.eliminarParticipante(usernameParticipante1);

		assertFalse(grupo.contieneParticipante(usernameParticipante1));
	}

	@Test(expected = ParticipanteInexistenteExcepcion.class)
	public void eliminarParticipanteInexistente()
			throws ParticipanteInexistenteExcepcion {
		grupo.eliminarParticipante(usernameParticipante1);
	}

	@Test
	public void contieneParticipantesDeDosGruposDistintos()
			throws ParticipanteExistenteExcepcion {
		Grupo grupoUno = new Grupo();
		idGrupo = 1;
		String usernameParticipante1 = "pepe";
		String usernameParticipante2 = "raul";
		grupoUno.agregarParticipante(usernameParticipante1);
		grupoUno.agregarParticipante(usernameParticipante2);

		Grupo grupoDos = new Grupo();
		idGrupo = 2;
		String usernameParticipante3 = "tincho";
		String usernameParticipante4 = "juancho";
		grupoDos.agregarParticipante(usernameParticipante3);
		grupoDos.agregarParticipante(usernameParticipante4);

		assertFalse("Los grupos no contienen a un mismo participante",
				grupoDos.contieneParticipantesDe(grupoUno));
	}

	@Test
	public void contieneParticipantesDeDosGruposConUnParticipanteRepetido()
			throws ParticipanteExistenteExcepcion {
		Grupo grupoUno = new Grupo();
		idGrupo = 1;
		String usernameParticipante1 = "pepe";
		String usernameParticipante2 = "raul";
		grupoUno.agregarParticipante(usernameParticipante1);
		grupoUno.agregarParticipante(usernameParticipante2);

		Grupo grupoDos = new Grupo();
		idGrupo = 2;
		String usernameParticipante3 = "pepe";
		String usernameParticipante4 = "juancho";
		grupoDos.agregarParticipante(usernameParticipante3);
		grupoDos.agregarParticipante(usernameParticipante4);

		assertTrue("Los grupos contienen a un mismo participante",
				grupoDos.contieneParticipantesDe(grupoUno));
	}

	@Test
	public void contieneParticipantesConGrupoBaseVacio()
			throws ParticipanteExistenteExcepcion {
		Grupo grupoUno = new Grupo();

		Grupo grupoDos = new Grupo();
		idGrupo = 2;
		String usernameParticipante3 = "pepe";
		String usernameParticipante4 = "juancho";
		grupoDos.agregarParticipante(usernameParticipante3);
		grupoDos.agregarParticipante(usernameParticipante4);

		assertFalse("Los grupos son distintos",
				grupoUno.contieneParticipantesDe(grupoDos));
	}

	@Test
	public void contieneParticipantesConGrupoExternoVacio()
			throws ParticipanteExistenteExcepcion {
		Grupo grupoUno = new Grupo();

		Grupo grupoDos = new Grupo();
		idGrupo = 2;
		String usernameParticipante3 = "pepe";
		String usernameParticipante4 = "juancho";
		grupoDos.agregarParticipante(usernameParticipante3);
		grupoDos.agregarParticipante(usernameParticipante4);

		assertFalse("Los grupos son distintos",
				grupoDos.contieneParticipantesDe(grupoUno));
	}

	@Test
	public void serializarCorrecto() throws XmlErroneoExcepcion {
		grupo.descerializar(xmlADescerializar);

		String xmlFinal = grupo.serializar();

		assertEquals(xmlADescerializar, xmlFinal);
	}

	@Test
	public void descerializarConXMLCorrecto() throws XmlErroneoExcepcion {
		grupo.descerializar(xmlADescerializar);
		assertEquals(idActividad, grupo.getIdActividad());
		assertEquals(idGrupo, grupo.getId());
		assertTrue(grupo.contieneParticipante(usernameParticipante1));
		assertTrue(grupo.contieneParticipante(usernameParticipante2));
	}

	@Test(expected = XmlErroneoExcepcion.class)
	public void descerializarConXMLSinNodoGrupo() throws XmlErroneoExcepcion {
		xmlADescerializar = "<?xml version=\"1.0\"?><WS>" 
				+ "<IdGrupo>" + idGrupo + "</IdGrupo>"
				+ "<usernameParticipante>" + usernameParticipante1
				+ "</usernameParticipante>"
				+ "<usernameParticipante>" + usernameParticipante2
				+ "</usernameParticipante>"
				+ "</WS>";
		grupo.descerializar(xmlADescerializar);
	}

	@Test(expected = XmlErroneoExcepcion.class)
	public void descerializarConXMLConDosNodosGrupo()
			throws XmlErroneoExcepcion {
		xmlADescerializar = "<?xml version=\"1.0\"?><WS><Grupo>" + "<IdGrupo>"
				+ idGrupo + "</IdGrupo>" + "<usernameParticipante>"
				+ usernameParticipante1 + "</usernameParticipante>"
				+ "<usernameParticipante>" + usernameParticipante2
				+ "</usernameParticipante>" + "</Grupo><Grupo></Grupo></WS>";
		grupo.descerializar(xmlADescerializar);
	}

	@Test(expected = XmlErroneoExcepcion.class)
	public void descerializarConXMLSinNodoIdActividad()
			throws XmlErroneoExcepcion {
		xmlADescerializar = "<?xml version=\"1.0\"?><WS><Grupo>" + "<IdGrupo>"
				+ idGrupo + "</IdGrupo>" + "<usernameParticipante>"
				+ usernameParticipante1 + "</usernameParticipante>"
				+ "<usernameParticipante>" + usernameParticipante2
				+ "</usernameParticipante>" + "</Grupo></WS>";
		grupo.descerializar(xmlADescerializar);
	}

	@Test(expected = XmlErroneoExcepcion.class)
	public void descerializarConXMLConDosNodosIdActividad()
			throws XmlErroneoExcepcion {
		xmlADescerializar = "<?xml version=\"1.0\"?><WS><Grupo>"
				+ "<IdActividad>" + idActividad + "</IdActividad>"
				+ "<IdActividad>" + idActividad + "</IdActividad>"
				+ "<IdGrupo>" + idGrupo + "</IdGrupo>"
				+ "<usernameParticipante>" + usernameParticipante1
				+ "</usernameParticipante>" + "<usernameParticipante>"
				+ usernameParticipante2 + "</usernameParticipante>"
				+ "</Grupo></WS>";
		grupo.descerializar(xmlADescerializar);
	}

	@Test(expected = XmlErroneoExcepcion.class)
	public void descerializarConXMLSinNodoIdGrupo() throws XmlErroneoExcepcion {
		xmlADescerializar = "<?xml version=\"1.0\"?><WS><Grupo>"
				+ "<IdActividad>" + idActividad + "</IdActividad>"
				+ "<usernameParticipante>" + usernameParticipante1
				+ "</usernameParticipante>" + "<usernameParticipante>"
				+ usernameParticipante2 + "</usernameParticipante>"
				+ "</Grupo></WS>";
		grupo.descerializar(xmlADescerializar);
	}

	@Test(expected = XmlErroneoExcepcion.class)
	public void descerializarConXMLConDosNodosIdGrupo() throws XmlErroneoExcepcion {
		xmlADescerializar = "<?xml version=\"1.0\"?><WS><Grupo>"
				+ "<IdActividad>" + idActividad + "</IdActividad>"
				+ "<IdGrupo>" + idGrupo + "</IdGrupo>"
				+ "<IdGrupo>" + idGrupo + "</IdGrupo>"
				+ "<usernameParticipante>" + usernameParticipante1
				+ "</usernameParticipante>" + "<usernameParticipante>"
				+ usernameParticipante2 + "</usernameParticipante>"
				+ "</Grupo></WS>";
		grupo.descerializar(xmlADescerializar);
	}
}
