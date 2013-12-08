package fiuba.taller.actividad.excepcion;

import java.rmi.RemoteException;

public class ActividadErroneaExcepcion extends RemoteException {

	private static final long serialVersionUID = -8958094031673012605L;

	public ActividadErroneaExcepcion(String mensaje) {
		super(mensaje);
	}
}
