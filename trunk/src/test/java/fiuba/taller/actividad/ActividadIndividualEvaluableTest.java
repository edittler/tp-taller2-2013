package fiuba.taller.actividad;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import fiuba.taller.actividad.excepcion.NotaInexistenteExcepcion;

public class ActividadIndividualEvaluableTest {
	
	ActividadIndividualEvaluable actIndividual;

	@Before
	public void setUp() throws Exception {
		actIndividual = new ActividadIndividualEvaluable();
	}

	@After
	public void tearDown() throws Exception {
	}
/*
	@Test
	public void EvaluarNotaExistente() throws NotaInexistenteExcepcion {
		actIndividual.evaluar("juancito", "10");
		Nota nota = actIndividual.getNota("juancito");
		assertEquals(nota.getValor(), "10");
	}

	@Test(expected=NotaInexistenteExcepcion.class)
	public void EvaluarNotaInexistente() throws NotaInexistenteExcepcion {
		actIndividual.getNota("pedrito");
	}
*/
	@Test
	public void esTipoValidoConTipoCorrecto() {
		String tipo = ActividadIndividualEvaluable.TIPO_ACTIVIDAD_INDIVIDUAL_EVALUABLE;

		String xml = AuxiliarPruebas.auxGenerarXml(tipo,"","");

		assertTrue("Tipo que deberia ser aceptado fue rechazado",
				ActividadIndividualEvaluable.esTipoValido(xml));
	}

	@Test
	public void esTipoValidoConTipoExtendidoCorrecto() {
		String tipo = ActividadIndividualEvaluable.TIPO_ACTIVIDAD_INDIVIDUAL_EVALUABLE
				+ " Dificil";

		String xml = AuxiliarPruebas.auxGenerarXml(tipo,"","");

		assertTrue("Tipo que deberia ser aceptado fue rechazado",
				ActividadIndividualEvaluable.esTipoValido(xml));
	}

	@Test
	public void esTipoValidoConTipoExtendidoErroneo() {
		String tipo = "Corta "
				+ ActividadIndividualEvaluable.TIPO_ACTIVIDAD_INDIVIDUAL_EVALUABLE
				+ " Dificil";

		String xml = AuxiliarPruebas.auxGenerarXml(tipo,"","");

		assertFalse("Tipo que deberia ser rechazado fue aceptado",
				ActividadIndividualEvaluable.esTipoValido(xml));
	}

	@Test
	public void esTipoValidoConTipoErroneo() {
		String tipo = "Individual";

		String xml = AuxiliarPruebas.auxGenerarXml(tipo,"","");

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
