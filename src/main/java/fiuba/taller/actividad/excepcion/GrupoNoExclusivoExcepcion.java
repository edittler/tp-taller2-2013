package fiuba.taller.actividad.excepcion;

import java.rmi.RemoteException;

public class GrupoNoExclusivoExcepcion extends RemoteException {

	private static final long serialVersionUID = 5164996661911033429L;

	public GrupoNoExclusivoExcepcion(String message) {
		super(message);
	}
}
