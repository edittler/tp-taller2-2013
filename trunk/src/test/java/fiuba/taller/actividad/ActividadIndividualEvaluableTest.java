package fiuba.taller.actividad;

import static org.junit.Assert.*;

import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.xml.sax.SAXException;

public class ActividadIndividualEvaluableTest {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testEsTipoValido() throws ParserConfigurationException, SAXException, IOException {
		String tipo1="mangosta"+ActividadIndividual.TIPO_ACTIVIDAD_INDIVIDUAL;
		String tipo2=ActividadIndividual.TIPO_ACTIVIDAD_INDIVIDUAL;
		
		String xml1=AuxiliarPruebas.auxGenerarXmlConTipo(tipo1);
		String xml2=AuxiliarPruebas.auxGenerarXmlConTipo(tipo2);
		
		if(ActividadIndividual.esTipoValido(xml1)){
			fail("tipo que no corresponde fue aceptado !!");
		}
		if(!ActividadIndividual.esTipoValido(xml2)){
			fail("tipo que deveria ser aceptado fue rechazado");
		}
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
