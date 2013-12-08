package fiuba.taller.actividad.excepcion;

import java.rmi.RemoteException;

public class NotaInexistenteExcepcion extends RemoteException {

	private static final long serialVersionUID = -8449522842002237521L;

	public NotaInexistenteExcepcion(String mensaje) {
		super(mensaje);
	}
}
