package fiuba.taller.actividad.excepcion;

import java.rmi.RemoteException;

public class ActividadInexistenteExcepcion extends RemoteException {

	private static final long serialVersionUID = 889168557243007501L;

	public ActividadInexistenteExcepcion(String message) {
		super(message);
	}
}
