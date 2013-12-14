package fiuba.taller.actividad;

import static org.junit.Assert.*;

import java.rmi.RemoteException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;


public class ActividadControladorTest {
	ActividadControlador controlador;
	@Before
	public void setUp() throws Exception {
		controlador=new ActividadControlador();
	}

	@After
	public void tearDown() throws Exception {
	}
	/*
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
	*/
	@Test
	public void testCrearActividadIndividual() throws RemoteException {
		String xml = AuxiliarPruebas.auxGenerarXml(
				ActividadIndividual.TIPO_ACTIVIDAD_INDIVIDUAL, "", "");
		long actHandler = controlador.crearActividadIndividual("pancho", xml);
		String xmlProp = controlador.getPropiedades("pancho", actHandler);
		assertEquals("no son iguales:",xml,xmlProp);
	}

	@Test
	public void testCrearActividadGrupal() throws RemoteException {
		String xml = AuxiliarPruebas.auxGenerarXml(
				ActividadGrupal.TIPO_ACTIVIDAD_GRUPAL, "", "true");
		long actHandler = controlador.crearActividadGrupal("pancho", xml);
		String xmlProp = controlador.getPropiedades("pancho", actHandler);
		assertEquals("no son iguales:",xml,xmlProp);
	}

	@Test
	public void testCrearActividadIndividualEvaluable() throws RemoteException {
		String xml = AuxiliarPruebas.auxGenerarXml(
				ActividadIndividualEvaluable.TIPO_ACTIVIDAD_INDIVIDUAL_EVALUABLE, "gausiana", "");
		long actHandler = controlador.crearActividadIndividualEvaluable("pancho", xml);
		String xmlProp = controlador.getPropiedades("pancho", actHandler);
		assertEquals("no son iguales:",xml,xmlProp);
	}

	@Test
	public void testCrearActividadGrupalEvaluable() throws RemoteException {
		String xml = AuxiliarPruebas.auxGenerarXml(
				ActividadGrupalEvaluable.TIPO_ACTIVIDAD_GRUPAL_EVALUABLE, "gausiana", "false");
		long actHandler = controlador.crearActividadGrupalEvaluable("pancho", xml);
		String xmlProp = controlador.getPropiedades("pancho", actHandler);
		assertEquals("no son iguales:",xml,xmlProp);
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
	*/
}
