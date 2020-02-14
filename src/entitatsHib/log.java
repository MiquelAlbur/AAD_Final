package entitatsHib;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "log")
public class log {
	
	@Id
	@Column(name = "log_id")
	private int _id;

	@Column(name = "log_texte")
	private String _text;
	
	@Column(name = "log_data")
	private Date _data;
	
	public Date get_data() {
		return _data;
	}

	public void set_data(Date _data) {
		this._data = _data;
	}

	public log() {
	}
	
	public log(int id, String idioma, String text, Date data) {
		this._id = id;
		this._text = text;
		this._data = data;
	}

	public int get_id() {
		return _id;
	}

	public void set_id(int _id) {
		this._id = _id;
	}

	public String get_text() {
		return _text;
	}

	public void set_text(String _text) {
		this._text = _text;
	}
	
}

