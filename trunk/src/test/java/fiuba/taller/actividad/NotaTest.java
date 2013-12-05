package fiuba.taller.actividad;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class NotaTest {
	Nota nota;
	
	@Before
	public void setUp() throws Exception {
		nota = new Nota();
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testDescerializar() {
		long idActividad = 100;
		long idElemento = 878;
		String valorNota = "4";
		String observaciones = "pibe metele pata que asi vas a terminar laburando de panadero";

		String xmlADescerializar = "<?xml version=\"1.0\"?><WS><Nota>"
				+"<IdActividad>"+idActividad+"</IdActividad>"
				+"<IdEvaluado>"+idElemento+"</IdEvaluado>"
				+"<ValorNota>"+valorNota+"</ValorNota>"
				+"<Observaciones>"+observaciones+"</Observaciones>"
				+"</Nota></WS>";
		
		nota.descerializar(xmlADescerializar);

		if (nota.getIdActividad() != idActividad) {
			fail("IdActividad esperado: " + idActividad
					+ " idActividad encontrado: " + nota.getIdActividad());
		}
		if (nota.getIdElementoEvaluado() != idElemento) {
			fail("IdElementoEvaluado  esperado: " + idElemento
					+ "IdElementoEvaluado encontrado: "
					+ nota.getIdElementoEvaluado());
		}
		if (!nota.getNota().equals(valorNota)) {
			fail("ValorNota  esperado: " + valorNota + "ValorNota encontrado: "
					+ nota.getNota());
		}
		if (!nota.getObservaciones().equals(observaciones)) {
			fail("Observaciones esperado: " + observaciones
					+ "Observaciones encontrado: " + nota.getObservaciones());
		}
	}

	@Test
	public void testSerializar() {
		long idActividad = 100;
		long idElemento = 878;
		String valorNota = "4";
		String observaciones = "pibe metele pata que asi vas a terminar laburando de panadero";

		String xmlADescerializar = "<?xml version=\"1.0\"?><WS><Nota>"
				+"<IdActividad>"+idActividad+"</IdActividad>"
				+"<IdEvaluado>"+idElemento+"</IdEvaluado>"
				+"<ValorNota>"+valorNota+"</ValorNota>"
				+"<Observaciones>"+observaciones+"</Observaciones>"
				+"</Nota></WS>";
		
		nota.descerializar(xmlADescerializar);
		
		String xml = nota.serializar();
		
		if(!xml.equals(xmlADescerializar)){
			fail("xml NO COINCIDEN \n esperado: \n "+xmlADescerializar+" \n xml devuelto: \n "+xml);
		}
	}

	@Test
	public void testGetXml() {
		fail("Not yet implemented");
	}

	@Test
	public void testGuardarEstado() {
		fail("Not yet implemented");
	}
}
