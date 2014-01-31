package fiuba.taller.actividad;

import static org.junit.Assert.*;

import java.rmi.RemoteException;

import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

public class NotaGrupalTest {


	NotaGrupal nota;
	long idActividad;
	long  idGrupo;
	String valorNota;
	String observaciones;
	String xmlADescerializar;

	@Before
	public void setUp() throws Exception {
		idActividad = 100;
		idGrupo = 2;
		valorNota = "4";
		observaciones = "pibes metanle pata que asi van a terminar laburando de panadero";

		nota = new NotaGrupal(idActividad, idGrupo, valorNota, observaciones);

		xmlADescerializar = "<WS><Nota>"
				+ "<idActividad>" + idActividad + "</idActividad>"
				+ "<idGrupo>" + idGrupo + "</idGrupo>"
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
				+ "<idGrupo>" + idGrupo + "</idGrupo>"
				+ "<valor>" + valorNota + "</valor>"
				+ "<Observaciones>" + observaciones + "</Observaciones>"
				+ "</Notita></WS>";

		nota.descerializar(xmlADescerializar);

		fail("Se esperaba una excepcion por no existir el nodo Nota.");
	}

	@Test(expected = RemoteException.class)
	public void descerializarConXMLConDosNodosNota() throws RemoteException {
		String xmlADescerializar = "<WS><Notas><Nota>"
				+ "<IdActividad>" + idActividad + "</IdActividad>"
				+ "<idGrupo>" + idGrupo + "</idGrupo>"
				+ "<Valor>" + valorNota + "</Valor>"
				+ "<Observaciones>" + observaciones + "</Observaciones>"
				+ "</Nota><Nota>"
				+ "<IdActividad>" + idActividad + "</IdActividad>"
				+ "<idGrupo>" + idGrupo + "</idGrupo>"
				+ "<Valor>" + valorNota + "</Valor>"
				+ "<Observaciones>" + observaciones + "</Observaciones>"
				+ "</Nota></Notas></WS>";

		nota.descerializar(xmlADescerializar);

		fail("Se esperaba una excepcion por existir más de 2 nodos Nota.");
	}

	@Test(expected = RemoteException.class)
	public void descerializarConXMLConDosNodosValor() throws RemoteException {
		String xmlADescerializar = "<WS><Nota>"
				+ "<idActividad>" + idActividad + "</idActividad>"
				+ "<idGrupo>" + idGrupo + "</idGrupo>"
				+ "<valor>" + valorNota + "</valor>"
				+ "<observaciones>" + observaciones + "</observaciones>"
				+ "<valor>" + valorNota + "</valor>"
				+ "</Nota></WS>";

		nota.descerializar(xmlADescerializar);

		fail("Se esperaba una excepcion por existir más de 2 nodos Valor.");
	}

	@Test
	public void descerializarConXMLConComentarioDentroDeNodoValor()
			throws RemoteException {
		String xmlADescerializar = "<WS><Nota>"
				+ "<idActividad>" + idActividad + "</idActividad>"
				+ "<idGrupo>" + idGrupo + "</idGrupo>"
				+ "<valor><!-- Comentario -->" + valorNota + "</valor>"
				+ "<observaciones>" + observaciones + "</observaciones>"
				+ "</Nota></WS>";

		nota.descerializar(xmlADescerializar);

		assertEquals(idActividad, nota.getIdActividad());
		assertEquals(idGrupo, nota.getIdGrupo());
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
		NotaGrupal nota = new NotaGrupal(14, 2, "8");
		nota.actualizarEstado();
	}

}
