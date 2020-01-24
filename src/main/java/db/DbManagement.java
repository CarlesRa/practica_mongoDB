package db;

import java.util.ArrayList;
import java.util.List;
import org.bson.Document;
import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import model.Moneda;

public class DbManagement extends MongoClient{
	//constants de la base de dades//
	public final String DB_NAME = "MonedasDB";
	public final String COLLECTION_NAME = "monedas";
	public final String KEY_ID = "_id";
	public final String KEY_PAIS = "pais";
	public final String KEY_ANYO = "año";
	public final String KEY_DESCRIPCION = "descripcion";
	public final String KEY_VALORES = "valor";
	//Fi de constants de la base de dades//
	
	private MongoClient client;
	private MongoDatabase db;
	private MongoCollection<Document> collection;
	private ArrayList<Document> consulta;
	private DBCollection dbCollection;
	
	private static DbManagement dbManager = null;
	public static DbManagement getInstance() {
		if(dbManager == null) {
			dbManager = new DbManagement();
		}
		return dbManager;
	}
	
	//Constructor private per al patro singleton
	private DbManagement(){
		client = new MongoClient();
		db = client.getDatabase(DB_NAME);
		collection = db.getCollection(COLLECTION_NAME);
	}
	
	public List<Document> obtenerDatos() {
		consulta = collection.find().into(new ArrayList<Document>());
		return consulta;
	}
	
	//TODO METODE PER INSERTAR EN LA BASE DE DADES MONGO
	public void insertarNouDocument(Moneda moneda){
		List<Document> valores = moneda.getValores();
        Document objectMoneda = new Document();
        		objectMoneda.put(KEY_ID, moneda.get_id());
        		objectMoneda.put(KEY_PAIS, moneda.getPais());
        		objectMoneda.put(KEY_DESCRIPCION, moneda.getDescripcion());
        		objectMoneda.put(KEY_VALORES, valores);
       
        collection.insertOne(objectMoneda);
    }
	
	//Buscar document per id
	public FindIterable<Document> findOneDocument(String nombre) {
		BasicDBObject query =  new BasicDBObject("_id",
                new BasicDBObject("$eq", nombre));
		FindIterable<Document> cursor = collection.find(query);
		return cursor;
	}
	
	public void modificar(Document document) {
		collection.updateOne(Filters.eq(KEY_ID,document.get(KEY_ID)),new Document("$set",document));
	}
	
	public void borrarDocument(Document document) {
		collection.deleteOne(Filters.eq(KEY_ID,document.get(KEY_ID)));
	}
	
}
