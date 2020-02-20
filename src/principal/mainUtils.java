package principal;

import java.io.File;
import java.sql.Date;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.DOMImplementation;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.Text;

public class mainUtils {

	public void DOMecho(String nomFitxer) {//Métode que s'encarrega d'utilitzar DOM per a llegir l'xml i guardarlo dins classes. Pot ser es podria fer estàtic?
		
		File fitxerXml = new File(nomFitxer + ".xml"); 
		File dirActual = new File(""); 
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = factory.newDocumentBuilder();
		DOMImplementation implementation = builder.getDOMImplementation();
		Document document = implementation.createDocument(null, "dir", null);
		document.setXmlVersion("1.0");
		Element arrel = document.getDocumentElement();
		arrel.setAttribute("name", dirActual.getAbsoluteFile().getName()); 
		
		// Generate XML 
		//Element node;
		Source source = new DOMSource(document); //Indicam on el volem guardar. 
		Result result = new StreamResult(new java.io.File(fitxerXml.getAbsoluteFile().getName())); // nom de l'arxiu 
		
		//Métode separat al codi del profe
		//implementar recorregut
		File element;
		File[] elementsDir = dirActual.getAbsoluteFile().listFiles(); 
		Element fitxersNode = document.createElement("files");
		node.appendChild(fitxersNode);
		Element dirsNode = document.createElement("dirs");
		node.appendChild(dirsNode); 
		Element fitxerNode = document.createElement("file"); 
		fitxerNode.setAttribute("name", element.getAbsoluteFile().getName());
		Element fitxerData = document.createElement("date");
		Date dateModificacio = new Date(element.getAbsoluteFile().lastModified());
		Text nodeDateValue = document.createTextNode(formatDiaHora.format(dateModificacio));
		fitxerData.appendChild(nodeDateValue); 
		fitxerData.setNodeValue(formatDiaHora.format(dateModificacio));
		fitxerNode.appendChild(fitxerData);
		Element fitxerHidden = document.createElement("hidden");
		Text nodeHiddenValue = document.createTextNode(String.valueOf(element.getAbsoluteFile().isHidden()));
		fitxerHidden.appendChild(nodeHiddenValue);
		fitxerNode.appendChild(fitxerHidden);
		fitxersNode.appendChild(fitxerNode); 
	}
}
