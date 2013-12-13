package fiuba.taller.actividad.excepcion;

import java.rmi.RemoteException;

public class FechaErroneaExcepcion extends RemoteException {

	private static final long serialVersionUID = 8819918478039821046L;

	public FechaErroneaExcepcion(String mensaje) {
		super(mensaje);
	}
}
