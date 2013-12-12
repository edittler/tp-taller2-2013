package fiuba.taller.actividad.excepcion;

import java.rmi.RemoteException;

public class GrupoInexistenteExcepcion extends RemoteException {

	private static final long serialVersionUID = 6164996661911033429L;

	public GrupoInexistenteExcepcion(String message) {
		super(message);
	}
}
