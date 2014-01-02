package fiuba.taller.actividad;

public class Echo {
	protected static int cant;
	public String echo(String echo){
		return "Usted ecribio: "+echo+" => "+cant++;
	}
}
