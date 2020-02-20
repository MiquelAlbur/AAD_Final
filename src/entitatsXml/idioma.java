package entitatsXml;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "idioma")
public class idioma {
	public int _id;
	public String _ides;
	public String _idcat;
	public String _iden;

	public idioma() {
		super();
	}

	@XmlAttribute(name = "id")
	public int getId() {
		return _id;
	}

	public void setId(int id) {
		this._id = id;
	}

	@XmlElement(name = "es")
	public String getlites() {
		return _ides;
	}

	public void setlites(String es) {
		this._ides = es;
	}

	@XmlElement(name = "en")
	public String getliten() {
		return _iden;
	}

	public void setliten(String en) {
		this._iden = en;
	}

	@XmlElement(name = "cat")
	public String getlitcat() {
		return _idcat;
	}

	public void setlitcat(String cat) {
		this._idcat = cat;
	}

}