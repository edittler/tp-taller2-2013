package fiuba.taller.actividad;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class ActividadGrupalTest {
	
	ActividadGrupal actGrupal;

	@Before
	public void setUp() throws Exception {
		actGrupal = new ActividadGrupal();
	}

	@After
	public void tearDown() throws Exception {
	}
/*
	@Test
	public void testAgregarGrupo() {
		fail("Not yet implemented");
	}

	@Test
	public void testEliminarGrupo() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetGrupos() {
		fail("Not yet implemented");
	}
*/
	@Test
	public void esTipoValidoConTipoCorrecto() {
		String tipo = ActividadGrupal.TIPO_ACTIVIDAD_GRUPAL;

		String xml = AuxiliarPruebas.auxGenerarXmlConTipo(tipo);

		assertTrue("Tipo que deberia ser aceptado fue rechazado",
				ActividadGrupal.esTipoValido(xml));
	}

	@Test
	public void esTipoValidoConTipoExtendidoCorrecto() {
		String tipo = ActividadGrupal.TIPO_ACTIVIDAD_GRUPAL + " Facil";

		String xml = AuxiliarPruebas.auxGenerarXmlConTipo(tipo);

		assertTrue("Tipo que deberia ser aceptado fue rechazado",
				ActividadGrupal.esTipoValido(xml));
	}

	@Test
	public void esTipoValidoConTipoExtendidoErroneo() {
		String tipo = "Larga " + ActividadGrupal.TIPO_ACTIVIDAD_GRUPAL
				+ " Facil";

		String xml = AuxiliarPruebas.auxGenerarXmlConTipo(tipo);

		assertFalse("Tipo que deberia ser rechazado fue aceptado",
				ActividadGrupal.esTipoValido(xml));
	}

	@Test
	public void esTipoValidoConTipoErroneo() {
		String tipo = "Facil";

		String xml = AuxiliarPruebas.auxGenerarXmlConTipo(tipo);

		assertFalse("Tipo que deberia ser rechazado fue aceptado",
				ActividadGrupal.esTipoValido(xml));
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
