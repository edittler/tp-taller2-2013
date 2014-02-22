package fiuba.taller.actividad;

import java.rmi.RemoteException;

import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

@Ignore
@SuppressWarnings("unused")
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
	public void obtenerActividadesDeAmbitoSuperiorCorrecto()
			throws RemoteException {
		// Elegir el id de un ambito que exista.
		controlador.getActividadesDeAmbito("juan", 2);
	}

	@Test(expected = RemoteException.class)
	public void obtenerActividadesDeAmbitoSuperiorInexistente()
			throws RemoteException {
		// Elegir el id de un ambito que NO exista.
		controlador.getActividadesDeAmbito("juan", 200);
	}

	@Test
	public void obtenerActividadesDeAmbitoSuperiorSinActividadesInternas()
			throws RemoteException {
		// Elegir el id de un ambito que NO tenga actividades internas.
		controlador.getActividadesDeAmbito("juan", 20);
	}

	@Test
	public void obtenerActividadesDeActividadSuperiorCorrecto()
			throws RemoteException {
		// Elegir el id de una actividad que tenga actividades internas.
		controlador.getActividadesDeActividad("juan", 14);
	}

	@Test(expected = RemoteException.class)
	public void obtenerActividadesDeActividadSuperiorInexistente()
			throws RemoteException {
		// Elegir el id de una actividad que NO exista.
		controlador.getActividadesDeActividad("juan", 400);
	}

	@Test
	public void obtenerActividadesDeActividadSuperiorSinActividadesInternas()
			throws RemoteException {
		// Elegir el id de una actividad que NO tenga actividades internas.
		controlador.getActividadesDeActividad("juan", 32);
	}

	@Test
	public void obtenerActividadesDeUsuarioCorrecto()
			throws RemoteException {
		// Elegir el username de un usuario que tenga actividades internas.
		controlador.getActividadesDeMiembro("juan");
	}

	@Test(expected = RemoteException.class)
	public void obtenerActividadesDeUsuarioInexistente()
			throws RemoteException {
		// Elegir el username de un usuario INEXISTENTE.
		controlador.getActividadesDeMiembro("pirulo");
	}

	@Test
	public void obtenerActividadesDeUsuarioSinActividades()
			throws RemoteException {
		// Elegir el username de un usuario que NO tenga actividades internas.
		controlador.getActividadesDeMiembro("usuario_prueba1");
	}

	@Test
	public void destruirActividadExistente() throws RemoteException {
		controlador.destruirActividad("juan", 58);
	}

	@Test(expected = RemoteException.class)
	public void destruirActividadInexistente() throws RemoteException {
		controlador.destruirActividad("juan", 57);
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
		String propiedades = controlador.getPropiedades("pepe", 13);
//		assertEquals("no son iguales:",xml,xmlProp);
	}

	@Test
	public void setPropiedadesConNombreNuevo() throws RemoteException {
		String xml = "<WS><Actividad>"
				+ "<nombre>Nuevo Nombre</nombre>"
				+ "</Actividad></WS>";
		controlador.setPropiedades("pampa", 14, xml);
//		assertEquals("no son iguales:",xml,xmlProp);
	}

	@Test
	public void agregarParticipante() throws RemoteException {
		controlador.agregarParticipante("Seba", 14, "usuario_prueba1");
//		controlador.agregarParticipante("Seba", 14, "juan");
	}

	@Test
	public void eliminarParticipante() throws RemoteException {
		controlador.eliminarParticipante("Seba", 14, "usuario_prueba1");
//		controlador.agregarParticipante("Seba", 14, "juan");
	}
}
