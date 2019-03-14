package promongodigg;

import com.mongodb.BasicDBObject;
import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.bson.types.ObjectId;

public class Promongodigg {

    public static MongoClient mogoCliente;
    public static MongoDatabase base;
    public static MongoCollection<Document> coleccion;
    public static MongoCursor<Document> itera;

    public static void main(String[] args) {

        MongoClient mongoCliente = new MongoClient("localhost", 27017);
        base = mongoCliente.getDatabase("digg");
        coleccion = base.getCollection("stories");
        // MongoCollection<Document> collec = (MongoCollection<Document>) base.getCollection("stories");

        filtrarDocumentos();
        //inserirDatos();
        mostrarId();
        filtrarIdXeral();
        incrementaDiggs();
        multiplicaDiggs();
    }

    public static void filtrarDocumentos() {
        BasicDBObject gtQuery = new BasicDBObject();
        gtQuery.put("diggs", new BasicDBObject("$gt", 8000));
        FindIterable<Document> find = coleccion.find(gtQuery);
        itera = find.iterator();

        while (itera.hasNext()) {
            Document next = itera.next();
            String media = next.getString("media");
            String status = next.getString("status");
            System.out.println("media-> " + media + "\nstatus-> " + status + "\n");

        }
        itera.close();
//        Document first = coleccion.find(gtQuery).first();
//        String media = first.getString("media");
//        int diggs = first.getInteger("diggs");
//        System.out.println(media + " " + diggs);
//        System.out.println(gtQuery);
//        while (cursor.hasNext()) {
//
//            System.out.println("media-> " + cursor.one().get("media"));
//            System.out.println("status-> " + cursor.next().get("status"));
//        }
    }

    public static void inserirDatos() {

        Document data = new Document();
        data.append("_id", 1);
        data.append("media", "papel");
        data.append("status", "a");
        data.append("diggs", 1000);
        coleccion.insertOne(data);
        Document data2 = new Document();
        data2.append("_id", 2);
        data2.append("media", "papel");
        data2.append("status", "b");
        data2.append("diggs", 2000);
        coleccion.insertOne(data2);

        Document data3 = new Document();
        data3.append("_id", 3);
        data3.append("media", "carton");
        data3.append("status", "a");
        data3.append("diggs", 3000);
        coleccion.insertOne(data3);
    }

    public static void mostrarId() {
        BasicDBObject gtQuery = new BasicDBObject();
        gtQuery.put("_id", new BasicDBObject("$eq", 2));
        Document first = coleccion.find(gtQuery).first();
        String status = first.getString("status");
        int diggs = first.getInteger("diggs");
        System.out.println("status-> " + status + "\ndiggs-> " + diggs + "\n");
//        DBCursor cursor = collection.find(gtQuery);
//        System.out.println(gtQuery);
//        while (cursor.hasNext()) {
//            System.out.println("status-> " + cursor.one().get("status"));
//            System.out.println("diggs-> " + cursor.next().get("diggs"));
//        }
    }

    public static void filtrarIdXeral() {
        BasicDBObject gtQuery = new BasicDBObject();
        ObjectId id = new ObjectId("4ba267dc238d3ba3ca000014");
        gtQuery.put("_id", id);
        Document first = coleccion.find(gtQuery).first();
        String status = first.getString("status");
        int diggs = first.getInteger("diggs");
        System.out.println("status-> " + status + "\ndiggs-> " + diggs + "\n");

////        DBCursor cursor = collection.find(gtQuery);
////        System.out.println(gtQuery);
////        while (cursor.hasNext()) {
////            System.out.println("status-> " + cursor.one().get("status"));
////            System.out.println("diggs-> " + cursor.next().get("diggs"));
////        }
//
    }

    public static void incrementaDiggs() {
        coleccion.updateOne(new BasicDBObject("_id", 1), new BasicDBObject("$inc", new BasicDBObject("diggs", 3)));

    }
     public static void multiplicaDiggs() {
        coleccion.updateOne(new BasicDBObject("_id", 2), new BasicDBObject("$mul", new BasicDBObject("diggs", 3)));

    }
}
