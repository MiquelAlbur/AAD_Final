package entitatsHib;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "usuari")
public class usuari {
	
	@Id
	@Column(name = "user_id")
	private int _id;

	@Column(name = "user_name")
	private String _name;
	
	@Column(name = "user_pass")
	private String _pass;
	
	public usuari() {
	}
	
	public usuari(int id, String idioma, String name, String pass) {
		this._id = id;
		this._name = name;
		this._pass = pass;
	}

	public int get_id() {
		return _id;
	}

	public void set_id(int _id) {
		this._id = _id;
	}

	public String get_name() {
		return _name;
	}

	public void set_name(String _name) {
		this._name = _name;
	}

	public String get_pass() {
		return _pass;
	}

	public void set_pass(String _pass) {
		this._pass = _pass;
	}
	
}