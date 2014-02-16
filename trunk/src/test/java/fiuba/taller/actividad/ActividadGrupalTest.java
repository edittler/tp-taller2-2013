package fiuba.taller.actividad;

import static org.junit.Assert.*;

import java.rmi.RemoteException;

import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

@Ignore
public class ActividadGrupalTest {
	
	ActividadGrupal actGrupal;

	@Before
	public void setUp() throws Exception {
		actGrupal = new ActividadGrupal();
	}

	@After
	public void tearDown() throws Exception {
	}
	
	@Test
	public void PrimerIdentificador() throws RemoteException {
		Grupo grupo = new Grupo();
		actGrupal.agregarGrupo(grupo);
		assertEquals("El id del primer grupo es 1", 1, grupo.getId());
	}
	
	@Test
	public void SegundoIdentificador() throws RemoteException {
		Grupo grupo1 = new Grupo();
		actGrupal.agregarGrupo(grupo1);
		Grupo grupo2 = new Grupo();
		actGrupal.agregarGrupo(grupo2);
		assertEquals("El id del segundo grupo es 2", 2, grupo2.getId());
	}
	
	@Test
	public void IdentificadorEliminandoGrupo() throws RemoteException {
		Grupo grupo1 = new Grupo();
		actGrupal.agregarGrupo(grupo1);
		Grupo grupo2 = new Grupo();
		actGrupal.agregarGrupo(grupo2);
		long id = grupo1.getId();
		actGrupal.eliminarGrupo(id);
		Grupo grupo3 = new Grupo();
		actGrupal.agregarGrupo(grupo3);
		assertEquals("El id del tercer grupo es 3", 3, grupo3.getId());
	}

	@Test
	public void AgregarUnGrupo() throws RemoteException {
		Grupo grupo = new Grupo();
		actGrupal.agregarGrupo(grupo);
		assertEquals("Se esperaba tener un grupo creado", 1, actGrupal.getGrupos().size());
	}
	
	@Test
	public void AgregarDosGrupos() throws RemoteException {
		Grupo grupo1 = new Grupo();
		actGrupal.agregarGrupo(grupo1);
		Grupo grupo2 = new Grupo();
		actGrupal.agregarGrupo(grupo2);
		assertEquals("Se esperaba tener dos grupos creado", 2, actGrupal.getGrupos().size());
	}

	@Test
	public void EliminarGrupoExistente() throws RemoteException {
		Grupo grupo = new Grupo();
		actGrupal.agregarGrupo(grupo);
		long id = grupo.getId();
		actGrupal.eliminarGrupo(id);
		assertEquals("No deberia haber ningun grupo creado", 0, actGrupal.getGrupos().size());
	}
	
	@Test(expected=RemoteException.class)
	public void EliminarGrupoInexistente() throws RemoteException {
		Grupo grupo = new Grupo();
		actGrupal.agregarGrupo(grupo);
		long id = grupo.getId() + 1;
		actGrupal.eliminarGrupo(id);
	}
	
	@Test
	public void AgregarParticipanteAGrupo() throws RemoteException {
		Grupo grupo = new Grupo();
		actGrupal.agregarGrupo(grupo);
		actGrupal.agregarParticipanteAGrupo(grupo.getId(),"pepito");
		assertTrue("Deberia existir el particiapante",
				actGrupal.getGrupo(grupo.getId()).contieneParticipante("pepito"));
	}
	
	@Test(expected=RemoteException.class)
	public void AgregarMismoParticipanteAGruposExclusivos() throws RemoteException {
		Grupo grupo1 = new Grupo();
		actGrupal.agregarGrupo(grupo1);
		Grupo grupo2 = new Grupo();
		actGrupal.agregarGrupo(grupo2);
		actGrupal.setGruposExclusivos();
		actGrupal.agregarParticipanteAGrupo(grupo1.getId(),"pepito");
		actGrupal.agregarParticipanteAGrupo(grupo2.getId(),"pepito");
	}
	
	@Test
	public void EliminarParticipanteDeGrupo() throws RemoteException {
		Grupo grupo = new Grupo();
		actGrupal.agregarGrupo(grupo);
		actGrupal.agregarParticipanteAGrupo(grupo.getId(),"pepito");
		actGrupal.agregarParticipanteAGrupo(grupo.getId(),"juancito");
		actGrupal.eliminarParticipanteDeGrupo(grupo.getId(),"pepito");
		assertFalse("No deberia existir el particiapante", 
				actGrupal.getGrupo(grupo.getId()).contieneParticipante("pepito"));
	}
	
	@Test(expected=RemoteException.class)
	public void EliminarParticipanteInexistenteDeGrupo() throws RemoteException {
		Grupo grupo = new Grupo();
		actGrupal.agregarGrupo(grupo);
		actGrupal.agregarParticipanteAGrupo(grupo.getId(),"pepito");
		actGrupal.eliminarParticipanteDeGrupo(grupo.getId(),"pepe");
	}
	
	@Test(expected=RemoteException.class)
	public void EliminarUltimoParticipanteDeGrupo() throws RemoteException {
		Grupo grupo = new Grupo();
		actGrupal.agregarGrupo(grupo);
		actGrupal.agregarParticipanteAGrupo(grupo.getId(),"pepito");
		actGrupal.eliminarParticipanteDeGrupo(grupo.getId(),"pepito");
		actGrupal.getGrupo(grupo.getId());
	}
	
	@Test
	public void esTipoValidoConTipoCorrecto() {
		String tipo = ActividadGrupal.TIPO_ACTIVIDAD_GRUPAL;

		String xml = AuxiliarPruebas.auxGenerarXml(tipo,"","");

		assertTrue("Tipo que deberia ser aceptado fue rechazado",
				ActividadGrupal.esTipoValido(xml));
	}

	@Test
	public void esTipoValidoConTipoExtendidoCorrecto() {
		String tipo = ActividadGrupal.TIPO_ACTIVIDAD_GRUPAL + " Facil";

		String xml = AuxiliarPruebas.auxGenerarXml(tipo,"","");

		assertTrue("Tipo que deberia ser aceptado fue rechazado",
				ActividadGrupal.esTipoValido(xml));
	}

	@Test
	public void esTipoValidoConTipoExtendidoErroneo() {
		String tipo = "Larga " + ActividadGrupal.TIPO_ACTIVIDAD_GRUPAL
				+ " Facil";

		String xml = AuxiliarPruebas.auxGenerarXml(tipo,"","");

		assertFalse("Tipo que deberia ser rechazado fue aceptado",
				ActividadGrupal.esTipoValido(xml));
	}

	@Test
	public void esTipoValidoConTipoErroneo() {
		String tipo = "Facil";

		String xml = AuxiliarPruebas.auxGenerarXml(tipo,"","");

		assertFalse("Tipo que deberia ser rechazado fue aceptado",
				ActividadGrupal.esTipoValido(xml));
	}

}
