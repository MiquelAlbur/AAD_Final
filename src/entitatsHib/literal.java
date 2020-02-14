package entitatsHib;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "literal")
public class literal {
	
	@Id
	@Column(name = "lit_id")
	private int _id;

	@Column(name = "idi_cod")
	private String _idi_cod;
	
	@Column(name = "lit_clau")
	private String _clau;
	
	@Column(name = "lit_text")
	private String _text;
	
	public literal() {
	}
	
	public literal(int id, String idioma, String clau, String text) {
		this._clau = clau;
		this._id = id;
		this._idi_cod = idioma;
		this._text = text;
	}

	public int get_id() {
		return _id;
	}

	public void set_id(int _id) {
		this._id = _id;
	}

	public String get_idi_cod() {
		return _idi_cod;
	}

	public void set_idi_cod(String _idi_cod) {
		this._idi_cod = _idi_cod;
	}

	public String get_clau() {
		return _clau;
	}

	public void set_clau(String _clau) {
		this._clau = _clau;
	}

	public String get_text() {
		return _text;
	}

	public void set_text(String _text) {
		this._text = _text;
	}
	
}

