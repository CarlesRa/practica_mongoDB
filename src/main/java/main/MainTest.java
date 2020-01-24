package main;

import java.util.List;
import java.util.ArrayList;


import org.bson.Document;

import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;

import db.DbManagement;
import model.Moneda;

public class MainTest {
	public static void main(String[] args) {
		final Class<? extends List> docClazz = new ArrayList<Document>().getClass();
		ArrayList<Document>documents = new ArrayList<Document>();
		DbManagement manager = DbManagement.getInstance();
		documents.add(new Document("priest",1.23));
		documents.add(new Document("warlock",1));
		Moneda moneda = new Moneda("madrid","esperanto","2000","del paraiso",documents);
		//DbManagement.insertarNouDocument(moneda);
		List <Document>resultados = manager.obtenerDatos();
		for (int i=0; i<resultados.size(); i++) {
			Document d = resultados.get(i);
			System.out.println(d.toJson());
		}
		/*FindIterable<Document> doc = manager.findOneDocument("madrid");
		for (Document document : doc) {
			System.out.println(document.toJson());
		}
		manager.modificar(new Document().append(manager.KEY_ID, "pepe")
				.append(manager.KEY_PAIS, "lkdslkfjdsaj"));
		
		//llegir un unic resultat hi haura que crear un nou document amb la mateixa id
		/*FindIterable<Document> docs = manager.findOneDocument("pepe");
		for (Document document : docs) {
			System.out.println(document.toJson());
		}*/
		
		manager.borrarDocument(new Document().append(manager.KEY_ID, "hola"));
		System.out.println("--------------------------------");
		List <Document>resultados2 = manager.obtenerDatos();
		for (int i=0; i<resultados2.size(); i++) {
			Document d = resultados2.get(i);
			System.out.println(d.toJson());
		}
	}
}
