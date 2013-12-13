package fiuba.taller.actividad;

import static org.junit.Assert.*;
import junit.framework.Assert;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import fiuba.taller.actividad.excepcion.XmlErroneoExcepcion;

public class ActividadControladorTest {
	ActividadControlador controlador;
	@Before
	public void setUp() throws Exception {
		controlador=new ActividadControlador();
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testGetPropiedades() {
		fail("Not yet implemented");
	}

	@Test
	public void testSetPropiedades() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetActividadesDeAmbito() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetActividadesDeActividad() {
		fail("Not yet implemented");
	}

	@Test
	public void testDestruirActividad() {
		fail("Not yet implemented");
	}

	@Test
	public void testCrearActividadIndividual() throws XmlErroneoExcepcion {
		String xml = AuxiliarPruebas.auxGenerarXml(
				ActividadIndividual.TIPO_ACTIVIDAD_INDIVIDUAL, "", "", "");
		long actHandler = controlador.crearActividadIndividual("pancho", xml);
		String xmlProp = controlador.getPropiedades("pancho", actHandler);
		assertEquals("no son iguales:",xml,xmlProp);
	}

	@Test
	public void testCrearActividadGrupal() {
		fail("Not yet implemented");
	}

	@Test
	public void testCrearActividadIndividualEvaluable() {
		fail("Not yet implemented");
	}

	@Test
	public void testCrearActividadGrupalEvaluable() {
		fail("Not yet implemented");
	}

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

	@Test
	public void testAgregarGrupo() {
		fail("Not yet implemented");
	}

	@Test
	public void testEliminarGrupo() {
		fail("Not yet implemented");
	}

	@Test
	public void testAgregarParticipanteAGrupo() {
		fail("Not yet implemented");
	}

	@Test
	public void testEliminarParticipanteAGrupo() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetGrupos() {
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

}
