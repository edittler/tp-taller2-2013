package fiuba.taller.actividad.excepcion;

import java.rmi.RemoteException;

public class ParticipanteInexistenteExcepcion extends RemoteException {

	private static final long serialVersionUID = 3164996661911033429L;

	public ParticipanteInexistenteExcepcion(String mensaje) {
		super(mensaje);
	}
}
