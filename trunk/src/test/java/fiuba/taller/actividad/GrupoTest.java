package fiuba.taller.actividad;

import static org.junit.Assert.*;

import java.rmi.RemoteException;

import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import fiuba.taller.actividad.Grupo;

public class GrupoTest {

	private Grupo grupo;
	private long idActividad;
	private long idGrupo;
	private String xmlADescerializar;
	String usernameParticipante1;
	String usernameParticipante2;
	String usernameParticipante3;
	String usernameParticipante4;

	@Before
	public void setUp() throws Exception {
		grupo = new Grupo();
		idActividad = 2832;
		idGrupo = 10;
		usernameParticipante1 = "Seba";
		usernameParticipante2 = "testUser";
		usernameParticipante3 = "juan";
		usernameParticipante4 = "javier";
		xmlADescerializar = "<WS><Grupo>"
				+ "<idActividad>" + idActividad + "</idActividad>"
				+ "<id>" + idGrupo + "</id>"
				+"<usuarios>"
				+ "<username>" + usernameParticipante1+ "</username>"
				+ "<username>"+ usernameParticipante2 + "</username>"
				+"</usuarios>"
				+ "</Grupo></WS>";
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void agregarParticipanteCorrecto() throws RemoteException {
		grupo.agregarParticipante(usernameParticipante1);
		grupo.agregarParticipante(usernameParticipante2);
		assertTrue(grupo.contieneParticipante(usernameParticipante1));
		assertTrue(grupo.contieneParticipante(usernameParticipante2));
	}

	@Test(expected = RemoteException.class)
	public void agregarParticipanteExistente() throws RemoteException {
		grupo.agregarParticipante(usernameParticipante1);
		grupo.agregarParticipante(usernameParticipante1);
	}

	@Test
	public void eliminarParticipanteCorrecto() throws RemoteException {
		grupo.agregarParticipante(usernameParticipante1);

		assertTrue(grupo.contieneParticipante(usernameParticipante1));

		grupo.eliminarParticipante(usernameParticipante1);

		assertFalse(grupo.contieneParticipante(usernameParticipante1));
	}

	@Test(expected = RemoteException.class)
	public void eliminarParticipanteInexistente() throws RemoteException {
		grupo.eliminarParticipante(usernameParticipante1);
	}

	@Test
	public void contieneParticipantesDeDosGruposDistintos()
			throws RemoteException {
		Grupo grupoUno = new Grupo();
		idGrupo = 1;
		grupoUno.agregarParticipante(usernameParticipante1);
		grupoUno.agregarParticipante(usernameParticipante2);

		Grupo grupoDos = new Grupo();
		idGrupo = 2;
		grupoDos.agregarParticipante(usernameParticipante3);
		grupoDos.agregarParticipante(usernameParticipante4);

		assertFalse("Los grupos no contienen a un mismo participante",
				grupoDos.contieneParticipantesDe(grupoUno));
	}

	@Test
	public void contieneParticipantesDeDosGruposConUnParticipanteRepetido()
			throws RemoteException {
		Grupo grupoUno = new Grupo();
		idGrupo = 1;
		String usernameParticipante1 = "juan";
		grupoUno.agregarParticipante(usernameParticipante1);
		grupoUno.agregarParticipante(usernameParticipante2);

		Grupo grupoDos = new Grupo();
		idGrupo = 2;
		grupoDos.agregarParticipante(usernameParticipante3);
		grupoDos.agregarParticipante(usernameParticipante4);

		assertTrue("Los grupos contienen a un mismo participante",
				grupoDos.contieneParticipantesDe(grupoUno));
	}

	@Test
	public void contieneParticipantesConGrupoBaseVacio()
			throws RemoteException {
		Grupo grupoUno = new Grupo();

		Grupo grupoDos = new Grupo();
		idGrupo = 2;
		grupoDos.agregarParticipante(usernameParticipante3);
		grupoDos.agregarParticipante(usernameParticipante4);

		assertFalse("Los grupos son distintos",
				grupoUno.contieneParticipantesDe(grupoDos));
	}

	@Test
	public void contieneParticipantesConGrupoExternoVacio()
			throws RemoteException {
		Grupo grupoUno = new Grupo();

		Grupo grupoDos = new Grupo();
		idGrupo = 2;
		grupoDos.agregarParticipante(usernameParticipante3);
		grupoDos.agregarParticipante(usernameParticipante4);

		assertFalse("Los grupos son distintos",
				grupoDos.contieneParticipantesDe(grupoUno));
	}

	@Ignore
	@Test
	public void serializarCorrecto() throws RemoteException {
		grupo.descerializar(xmlADescerializar);

		String xmlFinal = grupo.serializar();

		assertEquals(xmlADescerializar, xmlFinal);
	}

	@Ignore
	@Test
	public void descerializarConXMLCorrecto() throws RemoteException {
		grupo.descerializar(xmlADescerializar);
		assertEquals(xmlADescerializar, grupo.serializar());
		assertEquals(idActividad, grupo.getIdActividad());
		assertEquals(idGrupo, grupo.getId());
		assertTrue(grupo.contieneParticipante(usernameParticipante1));
		assertTrue(grupo.contieneParticipante(usernameParticipante2));
		
	}

	@Test(expected = RemoteException.class)
	public void descerializarConXMLSinNodoGrupo() throws RemoteException {
		xmlADescerializar = "<?xml version=\"1.0\"?><WS>" 
				+ "<IdGrupo>" + idGrupo + "</IdGrupo>"
				+ "<usernameParticipante>" + usernameParticipante1
				+ "</usernameParticipante>"
				+ "<usernameParticipante>" + usernameParticipante2
				+ "</usernameParticipante>"
				+ "</WS>";
		grupo.descerializar(xmlADescerializar);
	}

	@Test(expected = RemoteException.class)
	public void descerializarConXMLConDosNodosGrupo() throws RemoteException {
		xmlADescerializar = "<?xml version=\"1.0\"?><WS><Grupo>" + "<IdGrupo>"
				+ idGrupo + "</IdGrupo>" + "<usernameParticipante>"
				+ usernameParticipante1 + "</usernameParticipante>"
				+ "<usernameParticipante>" + usernameParticipante2
				+ "</usernameParticipante>" + "</Grupo><Grupo></Grupo></WS>";
		grupo.descerializar(xmlADescerializar);
	}

	@Test(expected = RemoteException.class)
	public void descerializarConXMLSinNodoIdActividad() throws RemoteException {
		xmlADescerializar = "<?xml version=\"1.0\"?><WS><Grupo>" + "<IdGrupo>"
				+ idGrupo + "</IdGrupo>" + "<usernameParticipante>"
				+ usernameParticipante1 + "</usernameParticipante>"
				+ "<usernameParticipante>" + usernameParticipante2
				+ "</usernameParticipante>" + "</Grupo></WS>";
		grupo.descerializar(xmlADescerializar);
	}

	@Test(expected = RemoteException.class)
	public void descerializarConXMLConDosNodosIdActividad()
			throws RemoteException {
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

	@Test(expected = RemoteException.class)
	public void descerializarConXMLSinNodoIdGrupo() throws RemoteException {
		xmlADescerializar = "<?xml version=\"1.0\"?><WS><Grupo>"
				+ "<IdActividad>" + idActividad + "</IdActividad>"
				+ "<usernameParticipante>" + usernameParticipante1
				+ "</usernameParticipante>" + "<usernameParticipante>"
				+ usernameParticipante2 + "</usernameParticipante>"
				+ "</Grupo></WS>";
		grupo.descerializar(xmlADescerializar);
	}

	@Test(expected = RemoteException.class)
	public void descerializarConXMLConDosNodosIdGrupo() throws RemoteException {
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

	@Ignore
	@Test
	public void guardarGrupo() throws RemoteException {
		grupo.agregarParticipante(usernameParticipante1);
		grupo.agregarParticipante(usernameParticipante2);
		grupo.setIdActividad(27);
		grupo.guardarNuevoEstado();
	}
}
