package logs;

import java.util.ArrayList;
import java.util.Date;

import org.bson.Document;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

public class logMongo {
	private ArrayList<Document> llista = new ArrayList<Document>();
	private Document _d;
	private MongoCollection<Document> _mc;

	public logMongo(MongoDatabase md) {
		this._mc = md.getCollection("log");
	}

	public void log(String s, boolean c) {
		_d = new Document().append("data", new Date()).append("text", s);
		if (c) {
			llista.add(_d);
		} else {
			_mc.insertOne(_d);
		}
	}

	public void savelog() {
		_mc.insertMany(llista);
	}

	public void clean() {
		_mc.drop();
	}
}
