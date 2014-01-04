package fiuba.taller.actividad;

import java.rmi.RemoteException;

import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

public abstract class Nota implements Serializable {

	protected long idActividad;
	protected String valor;
	protected String observaciones;
	
	protected static final String NODO_NOTA = "Nota";
	protected static final String NODO_ID_ACTIVIDAD = "IdActividad";
	protected static final String NODO_VALOR = "Valor";
	protected static final String NODO_OBSERVACIONES = "Observaciones";

	protected Nota(long idActividad) {
		this.idActividad = idActividad;
		valor = "";
		observaciones = "";
	}

	public long getIdActividad() {
		return idActividad;
	}

	public String getValor() {
		return valor;
	}

	public void setValor(String valor) {
		this.valor = valor;
	}

	public String getObservaciones() {
		return observaciones;
	}

	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}

	protected static String getValue(String tag, Element element)
			throws RemoteException {
		NodeList nodes = element.getElementsByTagName(tag);
		if (nodes.getLength() != 1) {
			String mensaje = "Debe existir un nodo " + tag + ".";
			throw new RemoteException(mensaje);
		}
		Element elemento = (Element) nodes.item(0);
		String text = elemento.getTextContent();
		return text;
	}
	
	@Override
	public void guardarEstado() {
		/* 
		 * TODO Implementar. Se debe persistir el objeto en la base de
		 * datos.
		 */
	}

	@Override
	public String realizarConsulta() {
		/*
		 * TODO Implementar
		 */
		return "";
	}

	@Override
	public void guardarNuevoEstado() throws RemoteException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void eliminarEstado() throws RemoteException {
		// TODO Auto-generated method stub
		
	}
}
