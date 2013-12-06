package fiuba.taller.actividad;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class ActividadIndividualTest {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}
/*
	@Test
	public void testAgregarParticipante() {
		fail("Not yet implemented");
	}

	@Test
	public void testEliminarParticipante() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetParticipantes() {
		fail("Not yet implemented");
	}
*/
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
/*
	@Test
	public void testCrearInstancia() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetActividadLong() {
		fail("Not yet implemented");
	}
*/
}
