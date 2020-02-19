package entitatsXml;

import java.util.Date;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "log")
public class log {
	public int _id;
	public String _text;
	public Date _date;

	public log() {
		super();
	}

	@XmlAttribute(name = "id")
	public int getId() {
		return _id;
	}

	public void setId(int id) {
		this._id = id;
	}

	@XmlElement(name = "text")
	public String getText() {
		return _text;
	}

	public void setText(String text) {
		this._text = text;
	}

	@XmlElement(name = "date")
	public Date getDate() {
		return _date;
	}

	public void setDate(Date date) {
		this._date = date;
	}

}