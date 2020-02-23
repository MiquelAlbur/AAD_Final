package literals;

import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;

import org.bson.Document;
import org.bson.conversions.Bson;

import logs.logMongo;

public class literalHib {
	private logMongo _l;
	private MongoClient _mc;
	private MongoDatabase _md;
	private MongoCollection<Document> _mco;

	/**
	 * En crear-se, crea una sessió i crida a LogUtil.
	 */
	public literalHib() {
		new MongoTransform();
		_mc = new MongoClient();
		_md = _mc.getDatabase("test");
		_mco = _md.getCollection("literal");
		_l = new LogMongo(_md);
	}

	/**
	 * Mètode que s'executa cada vegada que s'ha de obtenir un missatge de la colecció
	 * literal.
	 * 
	 * @param id  Idioma del literal
	 * @param err Clau del literal
	 */
	public void handle(String id, String err) {
		Bson b = Filters.and(Filters.eq("idi_cod", id), Filters.eq("clau", err));
		FindIterable<Document> fi = _mco.find(b);
		MongoCursor<Document> mcu = fi.iterator();
		do {
			System.out.println(mcu.next().get("text"));
		} while (mcu.hasNext());
	}

	public LogMongo get_L() {
		return _l;
	}
}
