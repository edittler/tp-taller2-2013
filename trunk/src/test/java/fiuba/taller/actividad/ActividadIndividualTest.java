package fiuba.taller.actividad;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import fiuba.taller.actividad.excepcion.ParticipanteExistenteExcepcion;
import fiuba.taller.actividad.excepcion.ParticipanteInexistenteExcepcion;

public class ActividadIndividualTest {
	
	ActividadIndividual actIndividual;

	@Before
	public void setUp() throws Exception {
		actIndividual = new ActividadIndividual();
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void AgregarUnParticipante() throws ParticipanteExistenteExcepcion {
		actIndividual.agregarParticipante("juancito");
		assertEquals("Deberia haber un participante",
				1, actIndividual.getParticipantes().size());
	}
	
	@Test
	public void AgregarDosParticipantes() throws ParticipanteExistenteExcepcion {
		actIndividual.agregarParticipante("juancito");
		actIndividual.agregarParticipante("tito");
		assertEquals("Deberia haber dos participantes",
				2, actIndividual.getParticipantes().size());
	}
	
	@Test(expected=ParticipanteExistenteExcepcion.class)
	public void AgregarParticipantesConMismoUsername() throws ParticipanteExistenteExcepcion {
		actIndividual.agregarParticipante("juancito");
		actIndividual.agregarParticipante("juancito");
	}

	@Test
	public void EliminarParticipanteExistente() throws ParticipanteExistenteExcepcion, ParticipanteInexistenteExcepcion {
		actIndividual.agregarParticipante("juancito");
		actIndividual.eliminarParticipante("juancito");
		assertEquals("No deberia haber ningun participante",
				0, actIndividual.getParticipantes().size());
	}
	
	@Test(expected=ParticipanteInexistenteExcepcion.class)
	public void EliminarParticipanteInexistente() throws ParticipanteExistenteExcepcion, ParticipanteInexistenteExcepcion {
		actIndividual.agregarParticipante("juancito");
		actIndividual.eliminarParticipante("tito");
	}

	@Test
	public void esTipoValidoConTipoCorrecto() {
		String tipo = ActividadIndividualEvaluable.TIPO_ACTIVIDAD_INDIVIDUAL_EVALUABLE;

		String xml = AuxiliarPruebas.auxGenerarXmlConTipo(tipo);

		assertTrue("Tipo que deberia ser aceptado fue rechazado",
				ActividadIndividual.esTipoValido(xml));
	}

	@Test
	public void esTipoValidoConTipoExtendidoCorrecto() {
		String tipo = ActividadIndividualEvaluable.TIPO_ACTIVIDAD_INDIVIDUAL_EVALUABLE
				+ " Facil";

		String xml = AuxiliarPruebas.auxGenerarXmlConTipo(tipo);

		assertTrue("Tipo que deberia ser aceptado fue rechazado",
				ActividadIndividual.esTipoValido(xml));
	}

	@Test
	public void esTipoValidoConTipoExtendidoErroneo() {
		String tipo = "Larga "
				+ ActividadIndividualEvaluable.TIPO_ACTIVIDAD_INDIVIDUAL_EVALUABLE
				+ " Facil";

		String xml = AuxiliarPruebas.auxGenerarXmlConTipo(tipo);

		assertFalse("Tipo que deberia ser rechazado fue aceptado",
				ActividadIndividual.esTipoValido(xml));
	}

	@Test
	public void esTipoValidoConTipoErroneo() {
		String tipo = "Facil";

		String xml = AuxiliarPruebas.auxGenerarXmlConTipo(tipo);

		assertFalse("Tipo que deberia ser rechazado fue aceptado",
				ActividadIndividual.esTipoValido(xml));
	}

}
