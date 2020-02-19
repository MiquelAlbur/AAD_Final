package entitatsXml;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "usuari")
public class usuari {
	public int _id;
	public String _name;
	public String _pass;

	public usuari() {
		super();
	}

	@XmlAttribute(name = "id")
	public int getId() {
		return _id;
	}

	public void setId(int id) {
		this._id = id;
	}

	@XmlElement(name = "name")
	public String getName() {
		return _name;
	}

	public void setName(String name) {
		this._name = name;
	}

	@XmlElement(name = "pass")
	public String getPass() {
		return _pass;
	}

	public void setPass(String pass) {
		this._pass = pass;
	}

}