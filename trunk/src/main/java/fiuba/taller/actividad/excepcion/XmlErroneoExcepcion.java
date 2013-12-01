package fiuba.taller.actividad.excepcion;

import java.rmi.RemoteException;

public class XmlErroneoExcepcion extends RemoteException {

	private static final long serialVersionUID = -3394388411326574357L;

	public XmlErroneoExcepcion(String message) {
		super(message);
	}
}
