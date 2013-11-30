package fiuba.taller.actividad;

import java.io.StringReader;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

public class NotaGrupal extends Nota {
	long idGrupo;
	public NotaGrupal(){
		idGrupo=-1;
		idActividad=-1;
		nota="";
		observaciones="";
	}
	public void descerializar(String xml) {
		try {
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		    DocumentBuilder builder = factory.newDocumentBuilder();
		    InputSource is = new InputSource(new StringReader(xml));
		    Document doc = builder.parse(is);
			doc.getDocumentElement().normalize();
			
			NodeList nodes = doc.getElementsByTagName("notaGrupal");
			for (int i = 0; i < nodes.getLength(); i++) {
				Node node = nodes.item(i);
				if (node.getNodeType() == Node.ELEMENT_NODE) {
					Element element = (Element) node;
					idActividad=Integer.valueOf(getValue("idActividad",element));
					idGrupo = Integer.valueOf(getValue("idGrupo",element));
					nota = getValue("nota",element);
					observaciones= getValue("observaciones",element);
				}
			}
			
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	protected String serializar (){
		String idActividadString="";
		String idGrupoString="";
		if(idActividad>=0){
			idActividadString=String.valueOf(idActividad);
		}
		if(idGrupo>=0){
			idGrupoString=String.valueOf(idGrupo);
		}
		return "<idActividad>"+idActividadString+"</idActividad>"
				+"<idGrupo>"+idGrupoString+"</idGrupo>"
				+"<nota>"+nota+"</nota>"
				+"<observaciones>"+observaciones+"</observaciones>";
	}
	@Override
	public String getXml() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public void guardarEstado() {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void setNota(String nota) {
		// TODO Auto-generated method stub
		
	}
}
