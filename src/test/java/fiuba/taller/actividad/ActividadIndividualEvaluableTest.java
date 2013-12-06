package fiuba.taller.actividad;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class ActividadIndividualEvaluableTest {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void esTipoValidoConTipoCorrecto() {
		String tipo = ActividadIndividualEvaluable.TIPO_ACTIVIDAD_INDIVIDUAL_EVALUABLE;

		String xml = AuxiliarPruebas.auxGenerarXmlConTipo(tipo);

		assertTrue("Tipo que deberia ser aceptado fue rechazado",
				ActividadIndividualEvaluable.esTipoValido(xml));
	}

	@Test
	public void esTipoValidoConTipoExtendidoCorrecto() {
		String tipo = ActividadIndividualEvaluable.TIPO_ACTIVIDAD_INDIVIDUAL_EVALUABLE
				+ " Dificil";

		String xml = AuxiliarPruebas.auxGenerarXmlConTipo(tipo);

		assertTrue("Tipo que deberia ser aceptado fue rechazado",
				ActividadIndividualEvaluable.esTipoValido(xml));
	}

	@Test
	public void esTipoValidoConTipoExtendidoErroneo() {
		String tipo = "Corta "
				+ ActividadIndividualEvaluable.TIPO_ACTIVIDAD_INDIVIDUAL_EVALUABLE
				+ " Dificil";

		String xml = AuxiliarPruebas.auxGenerarXmlConTipo(tipo);

		assertFalse("Tipo que deberia ser rechazado fue aceptado",
				ActividadIndividualEvaluable.esTipoValido(xml));
	}

	@Test
	public void esTipoValidoConTipoErroneo() {
		String tipo = "Individual";

		String xml = AuxiliarPruebas.auxGenerarXmlConTipo(tipo);

		assertFalse("Tipo que deberia ser rechazado fue aceptado",
				ActividadIndividualEvaluable.esTipoValido(xml));
	}
/*
	@Test
	public void testEvaluar() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetNota() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetNotas() {
		fail("Not yet implemented");
	}

	@Test
	public void testCrearInstanciaString() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetActividadLong() {
		fail("Not yet implemented");
	}
*/
}
