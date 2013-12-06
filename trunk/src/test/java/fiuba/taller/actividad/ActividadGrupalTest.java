package fiuba.taller.actividad;

import static org.junit.Assert.*;

import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;

import fiuba.taller.actividad.AuxiliarPruebas;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.xml.sax.SAXException;

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
	public void testEsTipoValido() throws ParserConfigurationException,
			SAXException, IOException {
		String tipo1 = "mangosta" + ActividadGrupal.TIPO_ACTIVIDAD_GRUPAL;
		String tipo2 = ActividadGrupal.TIPO_ACTIVIDAD_GRUPAL;

		String xml1 = AuxiliarPruebas.auxGenerarXmlConTipo(tipo1);
		String xml2 = AuxiliarPruebas.auxGenerarXmlConTipo(tipo2);

		assertFalse("Tipo que no corresponde fue aceptado", ActividadGrupal.esTipoValido(xml1));
		assertTrue("Tipo que deberia ser aceptado fue rechazado", ActividadGrupal.esTipoValido(xml2));
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
