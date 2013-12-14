package fiuba.taller.actividad;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class ActividadGrupalEvaluableTest {
	
	public ActividadGrupalEvaluable actGrupal;

	@Before
	public void setUp() throws Exception {
		actGrupal = new ActividadGrupalEvaluable();
	}

	@After
	public void tearDown() throws Exception {
	}
/*	
	@Test
	public void EvaluarNotaExistente() throws NotaInexistenteExcepcion {
		Grupo grupo = new Grupo();
		actGrupal.evaluar(grupo.getId(), "10");
		Nota nota = actGrupal.getNota(grupo.getId());
		assertEquals(nota.getValor(), "10");
	}

	@Test(expected=NotaInexistenteExcepcion.class)
	public void EvaluarNotaInexistente() throws NotaInexistenteExcepcion {
		Grupo grupo = new Grupo();
		actGrupal.evaluar(grupo.getId(), "10");
		actGrupal.getNota(grupo.getId()+1);
	}
*/	
	@Test
	public void esTipoValidoConTipoCorrecto() {
		String tipo = ActividadGrupalEvaluable.TIPO_ACTIVIDAD_GRUPAL_EVALUABLE;

		String xml = AuxiliarPruebas.auxGenerarXml(tipo,"","");

		assertTrue("Tipo que deberia ser aceptado fue rechazado",
				ActividadGrupalEvaluable.esTipoValido(xml));
	}

	@Test
	public void esTipoValidoConTipoExtendidoCorrecto() {
		String tipo = ActividadGrupalEvaluable.TIPO_ACTIVIDAD_GRUPAL_EVALUABLE
				+ " Dificil";

		String xml = AuxiliarPruebas.auxGenerarXml(tipo,"","");

		assertTrue("Tipo que deberia ser aceptado fue rechazado",
				ActividadGrupalEvaluable.esTipoValido(xml));
	}

	@Test
	public void esTipoValidoConTipoExtendidoErroneo() {
		String tipo = "Corta "
				+ ActividadGrupalEvaluable.TIPO_ACTIVIDAD_GRUPAL_EVALUABLE
				+ " Dificil";

		String xml = AuxiliarPruebas.auxGenerarXml(tipo,"","");

		assertFalse("Tipo que deberia ser rechazado fue aceptado",
				ActividadGrupalEvaluable.esTipoValido(xml));
	}

	@Test
	public void esTipoValidoConTipoErroneo() {
		String tipo = "Grupal Dificil Evaluable";

		String xml = AuxiliarPruebas.auxGenerarXml(tipo,"","");

		assertFalse("Tipo que deberia ser rechazado fue aceptado",
				ActividadGrupalEvaluable.esTipoValido(xml));
	}
/*
	@Test
	public void testCrearInstancia() {
		fail("Not yet implemented");
	}

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
	public void testGetActividadLong() {
		fail("Not yet implemented");
	}
*/
}
