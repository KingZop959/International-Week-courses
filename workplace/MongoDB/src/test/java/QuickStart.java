import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import static com.mongodb.client.model.Filters.eq;

public class QuickStart {
    public static void main(String[] args) {
        // Replace the placeholder with your MongoDB deployment's connection string
        String uri = "mongodb://zly20010826:zly030508@ac-hap07w1-shard-00-00.g4ijuay.mongodb.net:27017,ac-hap07w1-shard-00-01.g4ijuay.mongodb.net:27017,ac-hap07w1-shard-00-02.g4ijuay.mongodb.net:27017/?ssl=true&replicaSet=atlas-vu3oty-shard-0&authSource=admin&retryWrites=true&w=majority";
        try (MongoClient mongoClient = MongoClients.create(uri)) {
            MongoDatabase database = mongoClient.getDatabase("sample_mflix");
            MongoCollection<Document> collection = database.getCollection("movies");
            Document doc = collection.find(eq("title", "Back to the Future")).first();
            if (doc != null) {
                System.out.println(doc.toJson());
            } else {
                System.out.println("No matching documents found.");
            }
        }
    }
}
