package fiuba.taller.actividad;

import static org.junit.Assert.*;

import java.rmi.RemoteException;

import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

@Ignore
public class ActividadControladorTest {
	ActividadControlador controlador;

	@Before
	public void setUp() throws Exception {
		controlador = new ActividadControlador();
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void crearActividadIndividual() throws RemoteException {
		String xml = AuxiliarPruebas.auxGenerarXml(
				ActividadIndividual.TIPO_ACTIVIDAD_INDIVIDUAL, "", "");
		long actHandler = controlador.crearActividadIndividual("pancho", xml);
		String xmlProp = controlador.getPropiedades("pancho", actHandler);
//		assertEquals("no son iguales:",xml,xmlProp);
	}

	@Test
	public void crearActividadGrupal() throws RemoteException {
		String xml = AuxiliarPruebas.auxGenerarXml(
				ActividadGrupal.TIPO_ACTIVIDAD_GRUPAL, "", "true");
		long actHandler = controlador.crearActividadGrupal("pancho", xml);
		String xmlProp = controlador.getPropiedades("pancho", actHandler);
//		assertEquals("no son iguales:",xml,xmlProp);
	}

	@Test
	public void crearActividadIndividualEvaluable() throws RemoteException {
		String xml = AuxiliarPruebas.auxGenerarXml(
				ActividadIndividualEvaluable.TIPO_ACTIVIDAD_INDIVIDUAL_EVALUABLE, "gausiana", "");
		long actHandler = controlador.crearActividadIndividualEvaluable("pancho", xml);
		String xmlProp = controlador.getPropiedades("pancho", actHandler);
//		assertEquals("no son iguales:",xml,xmlProp);
	}

	@Test
	public void crearActividadGrupalEvaluable() throws RemoteException {
		String xml = AuxiliarPruebas.auxGenerarXml(
				ActividadGrupalEvaluable.TIPO_ACTIVIDAD_GRUPAL_EVALUABLE, "gausiana", "false");
		long actHandler = controlador.crearActividadGrupalEvaluable("pancho", xml);
		String xmlProp = controlador.getPropiedades("pancho", actHandler);
//		assertEquals("no son iguales:",xml,xmlProp);
	}

	@Test
	public void getPropiedades() throws RemoteException {
		String propiedades = Actividad.getPropiedades(28);
//		assertEquals("no son iguales:",xml,xmlProp);
	}
}
