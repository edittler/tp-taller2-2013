package fiuba.taller.actividad;

import static org.junit.Assert.*;

import java.rmi.RemoteException;

import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

@SuppressWarnings("deprecation")
public class NotaIndividualTest {

	NotaIndividual nota;
	long idActividad;
	String username;
	String valorNota;
	String observaciones;
	String xmlADescerializar;

	@Before
	public void setUp() throws Exception {
		nota = new NotaIndividual();

		idActividad = 100;
		username = "pepito";
		valorNota = "4";
		observaciones = "pibe metele pata que asi vas a terminar laburando de panadero";

		xmlADescerializar = "<WS><Nota>"
				+ "<idActividad>" + idActividad + "</idActividad>"
				+ "<username>" + username + "</username>"
				+ "<valor>" + valorNota + "</valor>"
				+ "<observaciones>" + observaciones + "</observaciones>"
				+ "</Nota></WS>";
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test(expected = RemoteException.class)
	public void descerializarConXMLSinNodoNota() throws RemoteException {
		String xmlADescerializar = "<WS><Notita>"
				+ "<IdActividad>" + idActividad + "</IdActividad>"
				+ "<Username>" + username + "</Username>"
				+ "<Valor>" + valorNota + "</Valor>"
				+ "<Observaciones>" + observaciones + "</Observaciones>"
				+ "</Notita></WS>";

		nota.descerializar(xmlADescerializar);

		fail("Se esperaba una excepcion por no existir el nodo Nota.");
	}

	@Test(expected = RemoteException.class)
	public void descerializarConXMLConDosNodosNota() throws RemoteException {
		String xmlADescerializar = "<WS><Notas><Nota>"
				+ "<IdActividad>" + idActividad + "</IdActividad>"
				+ "<UsernameParticipante>" + username + "</UsernameParticipante>"
				+ "<Valor>" + valorNota + "</Valor>"
				+ "<Observaciones>" + observaciones + "</Observaciones>"
				+ "</Nota><Nota>"
				+ "<IdActividad>" + idActividad + "</IdActividad>"
				+ "<Username>" + username + "</Username>"
				+ "<Valor>" + valorNota + "</Valor>"
				+ "<Observaciones>" + observaciones + "</Observaciones>"
				+ "</Nota></Notas></WS>";

		nota.descerializar(xmlADescerializar);

		fail("Se esperaba una excepcion por existir más de 2 nodos Nota.");
	}

	@Test(expected = RemoteException.class)
	public void descerializarConXMLConDosNodosValor() throws RemoteException {
		String xmlADescerializar = "<WS><Nota>"
				+ "<IdActividad>" + idActividad + "</IdActividad>"
				+ "<Username>" + username + "</Username>"
				+ "<Valor>" + valorNota + "</Valor>"
				+ "<Observaciones>" + observaciones + "</Observaciones>"
				+ "<Valor>" + valorNota + "</Valor>"
				+ "</Nota></WS>";

		nota.descerializar(xmlADescerializar);

		fail("Se esperaba una excepcion por existir más de 2 nodos Valor.");
	}

	@Test
	public void descerializarConXMLConComentarioDentroDeNodoValor()
			throws RemoteException {
		String xmlADescerializar = "<WS><Nota>"
				+ "<idActividad>" + idActividad + "</idActividad>"
				+ "<username>" + username + "</username>"
				+ "<valor><!-- Comentario -->" + valorNota + "</valor>"
				+ "<observaciones>" + observaciones + "</observaciones>"
				+ "</Nota></WS>";

		nota.descerializar(xmlADescerializar);

		assertEquals(idActividad, nota.getIdActividad());
		assertEquals(username, nota.getUsername());
		assertEquals(valorNota, nota.getValor());
		assertEquals(observaciones, nota.getObservaciones());
	}

	@Test
	public void serializarCorrecto() throws RemoteException {
		nota.descerializar(xmlADescerializar);

		String xmlFinal = nota.serializar();

		assertEquals(xmlADescerializar, xmlFinal);
	}

	@Ignore
	@Test
	public void almacenarNota() throws RemoteException {
		NotaIndividual nota = new NotaIndividual(12, "pepito", "8");
		nota.actualizarEstado();
	}
}