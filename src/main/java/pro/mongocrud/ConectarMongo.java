package pro.mongocrud; 

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Updates;
import org.bson.Document;

public class ConectarMongo {

    String database = "inkvibes";
    String collection = "usuarios";

    public void getValues() {
        System.out.println("getValues");
        String uri = "mongodb://localhost";
        MongoClient mongo = MongoClients.create(uri);
        MongoDatabase db = mongo.getDatabase(database);

 

        MongoCollection<Document> docs = db.getCollection(collection);
        for (Document doc : docs.find()) {
            System.out.println("item: " + doc);
        }
        System.out.println("getValues ok");
    }

 

    public String selectValues(String email) {
        String y = "";
        System.out.println("Select Values");
        String uri = "mongodb://localhost";
        MongoClient mongo = MongoClients.create(uri);
        MongoDatabase db = mongo.getDatabase(database);
        MongoCollection<Document> docs = db.getCollection(collection);
        Document doc = docs.find(Filters.eq("email", email)).first();
        y = doc.getString("nome");
        for (Document doc1 : docs.find()) {
            System.out.println("item2: " + doc1);
        }
        System.out.println("Select id ok");
        return y;
    }

 

    public void insertValues(String Nome, int telefone, String email,int cpf, String end,String senha) {
        System.out.println("insertValues");
        String uri = "mongodb://localhost";
        MongoClient mongo = MongoClients.create(uri);
        MongoDatabase db = mongo.getDatabase(database);
        MongoCollection<Document> docs = db.getCollection(collection);
        Document docBuilder = new Document();
        docBuilder.append("nome", Nome);
        docBuilder.append("telefone", telefone);
        docBuilder.append("email", email);
        docBuilder.append("cpf", cpf);
        docBuilder.append("endereco", end);
        docBuilder.append("senha", senha);
        docs.insertOne(docBuilder);  
        System.out.println("insertValues ok");
    }

 

    public boolean findValuesemail(String email) {
        boolean y =false;
        System.out.println("findValues");
        String uri = "mongodb://localhost";
        MongoClient mongo = MongoClients.create(uri);
        MongoDatabase db = mongo.getDatabase(database);
        MongoCollection<Document> docs = db.getCollection(collection);
        for (Document doc : docs.find(Filters.eq("email", email))) {
            System.out.println("achou pelo id: " + doc);
            y=true;
        }
        System.out.println("findid() - finalizou");
        return y;
    }

 

    public String findValuesName(String nome) {
        String nome2 = "n�o achou";
        System.out.println("findName");
        String uri = "mongodb://localhost";
        MongoClient mongo = MongoClients.create(uri);
        MongoDatabase db = mongo.getDatabase(database);
        MongoCollection<Document> docs = db.getCollection(collection);
        for (Document doc : docs.find(Filters.eq("nome", nome))) {
            System.out.println("achou pelo nome: " + doc);
            nome2=nome;
            System.out.println(nome2);
        }
        System.out.println("findName() - finalizou");
        return nome2;
    }

 

    public void updateValues() {
        System.out.println("updateValues");
        String uri = "mongodb://localhost";
        MongoClient mongo = MongoClients.create(uri);
        MongoDatabase db = mongo.getDatabase(database);
        MongoCollection<Document> docs = db.getCollection(collection);
        docs.updateOne(Filters.eq("_id", 2),
                Updates.set("cidadenasc", "Santa Maria - RS"));
        System.out.println("Documento teve sucesso no update...");
        for (Document doc : docs.find()) {
            System.out.println("item update: " + doc);
        }
    }

 

    public void deleteValues() {
        System.out.println("deleteValues");
        String uri = "mongodb://localhost";
        MongoClient mongo = MongoClients.create(uri);
        MongoDatabase db = mongo.getDatabase(database);
        MongoCollection<Document> docs = db.getCollection(collection);
        docs.deleteOne(Filters.eq("_id", 4));
        System.out.println("Documento teve sucesso no delete...");
        for (Document doc : docs.find()) {
            System.out.println("item deleted: " + doc);
        }
    }
}
