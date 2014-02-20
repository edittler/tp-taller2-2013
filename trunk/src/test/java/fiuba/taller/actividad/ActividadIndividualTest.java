package fiuba.taller.actividad;

import static org.junit.Assert.*;

import java.rmi.RemoteException;

import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

public class ActividadIndividualTest {

	ActividadIndividual actIndividual;

	@Before
	public void setUp() throws Exception {
		actIndividual = new ActividadIndividual();
	}

	@After
	public void tearDown() throws Exception {
	}

	@Ignore
	@Test
	public void AgregarUnParticipante() throws RemoteException {
		actIndividual.agregarParticipante("juancito");
		assertEquals("Deberia haber un participante", 1, actIndividual
				.getParticipantes().size());
	}

	@Ignore
	@Test
	public void AgregarDosParticipantes() throws RemoteException {
		actIndividual.agregarParticipante("juancito");
		actIndividual.agregarParticipante("tito");
		assertEquals("Deberia haber dos participantes", 2, actIndividual
				.getParticipantes().size());
	}

	@Ignore
	@Test(expected = RemoteException.class)
	public void AgregarParticipantesConMismoUsername() throws RemoteException {
		actIndividual.agregarParticipante("juancito");
		actIndividual.agregarParticipante("juancito");
	}

	@Ignore
	@Test
	public void EliminarParticipanteExistente() throws RemoteException {
		actIndividual.agregarParticipante("juancito");
		actIndividual.eliminarParticipante("juancito");
		assertEquals("No deberia haber ningun participante", 0, actIndividual
				.getParticipantes().size());
	}

	@Ignore
	@Test(expected = RemoteException.class)
	public void EliminarParticipanteInexistente() throws RemoteException {
		actIndividual.agregarParticipante("juan");
		actIndividual.eliminarParticipante("tito");
	}

	@Test
	public void actualizarNombreCorrecto() throws RemoteException {
		String tipo = ActividadIndividual.TIPO_ACTIVIDAD_INDIVIDUAL;
		String xml = AuxiliarPruebas.auxGenerarXml(tipo,"","");
		
		ActividadIndividual actividad = new ActividadIndividual();
		actividad.descerializar(xml);
		
		String nuevoNombre = "Esta actividad tiene un nombre cambiado";
		
		String xmlActualizacion = "<WS><Actividad>"
					+ "<id></id>"
					+ "<nombre>" + nuevoNombre + "</nombre>"
					+ "<tipo></tipo>"
					+ "<ambitoSuperiorId></ambitoSuperiorId>"
					+ "<actividadSuperiorId></actividadSuperiorId>"
					+ "<descripcion></descripcion>"
					+ "<fechaInicio></fechaInicio>"
					+ "<fechaFin></fechaFin>"
					+ "<gruposExclusivos></gruposExclusivos>"
					+ "</Actividad></WS>";
		
		actividad.actualizar(xmlActualizacion);
		assertEquals("No se actualizo el nombre", nuevoNombre, actividad.getNombre());
	}

	@Test
	public void esTipoValidoConTipoCorrecto() {
		String tipo = ActividadIndividual.TIPO_ACTIVIDAD_INDIVIDUAL;

		String xml = AuxiliarPruebas.auxGenerarXml(tipo, "", "");

		assertTrue("Tipo que deberia ser aceptado fue rechazado",
				ActividadIndividual.esTipoValido(xml));
	}

	@Test
	public void esTipoValidoConTipoExtendidoCorrecto() {
		String tipo = ActividadIndividual.TIPO_ACTIVIDAD_INDIVIDUAL + " Facil";

		String xml = AuxiliarPruebas.auxGenerarXml(tipo, "", "");

		assertTrue("Tipo que deberia ser aceptado fue rechazado",
				ActividadIndividual.esTipoValido(xml));
	}

	@Test
	public void esTipoValidoConTipoExtendidoErroneo() {
		String tipo = "Larga " + ActividadIndividual.TIPO_ACTIVIDAD_INDIVIDUAL
				+ " Facil";

		String xml = AuxiliarPruebas.auxGenerarXml(tipo, "", "");

		assertFalse("Tipo que deberia ser rechazado fue aceptado",
				ActividadIndividual.esTipoValido(xml));
	}

	@Test
	public void esTipoValidoConTipoErroneo() {
		String tipo = "Facil";

		String xml = AuxiliarPruebas.auxGenerarXml(tipo, "", "");

		assertFalse("Tipo que deberia ser rechazado fue aceptado",
				ActividadIndividual.esTipoValido(xml));
	}
}
