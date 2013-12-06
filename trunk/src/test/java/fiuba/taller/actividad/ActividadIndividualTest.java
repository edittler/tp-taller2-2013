package fiuba.taller.actividad;

import static org.junit.Assert.*;

import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.xml.sax.SAXException;

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
	public void testEsTipoValido() throws ParserConfigurationException, SAXException, IOException {
		String tipo1 = "mangosta"
				+ ActividadIndividualEvaluable.TIPO_ACTIVIDAD_INDIVIDUAL_EVALUABLE;
		String tipo2 = ActividadIndividualEvaluable.TIPO_ACTIVIDAD_INDIVIDUAL_EVALUABLE;

		String xml1 = AuxiliarPruebas.auxGenerarXmlConTipo(tipo1);
		String xml2 = AuxiliarPruebas.auxGenerarXmlConTipo(tipo2);

		assertFalse("Tipo que no corresponde fue aceptado", ActividadIndividualEvaluable.esTipoValido(xml1));
		assertTrue("Tipo que deberia ser aceptado fue rechazado", ActividadIndividualEvaluable.esTipoValido(xml2));
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
