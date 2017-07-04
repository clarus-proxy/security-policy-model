package eu.clarussecure.datamodel.types.utils;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.MongoIterable;
import static com.mongodb.client.model.Filters.eq;

import org.bson.Document;

import java.util.Set;
import java.util.HashSet;
import java.util.Map;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

public class TypesDAO {

    private final MongoDatabase db;
    private final MongoClient mongoClient;
    private static int instances = 0;

    private static TypesDAO instance = null;

    private TypesDAO() {
        // Correctly configure the log level
        Logger mongoLogger = Logger.getLogger("org.mongodb.driver");
        mongoLogger.setLevel(Level.SEVERE);
        // Create a new client connecting to "localhost" on port 
        this.mongoClient = new MongoClient("localhost", 27017);

        // Get the database (will be created if not present)
        this.db = mongoClient.getDatabase("CLARUS");
    }

    public synchronized static TypesDAO getInstance() {
        if (TypesDAO.instances > 0) {
            TypesDAO.instances++;
            return instance;
        }
        TypesDAO.instance = new TypesDAO();
        return TypesDAO.instance;
    }

    public synchronized void deleteInstance() {
        TypesDAO.instances--;
        if (TypesDAO.instances <= 0) {
            // Close the connection
            this.mongoClient.close();
        }
    }

    public Set<String> loadModulesList() {
        MongoIterable<Document> modules = this.db.getCollection("config").find(eq("key", "clarus-module"));

        Set<String> results = new HashSet<>();

        // Iterate over the results
        try (MongoCursor<Document> cursor = modules.iterator()) {
            while (cursor.hasNext()) {
                // Get the document and the name of the registered module;
                Document d = cursor.next();
                results.add(d.getString("name").toLowerCase());
            }
        }
        return results;
    }

    public Map<String, String> loadProtocolsList() {
        MongoIterable<Document> protocols = db.getCollection("protocols").find();

        Map<String, String> results = new HashMap<>();

        // Iterate over the results of the database
        try (MongoCursor<Document> cursor = protocols.iterator()) {
            while (cursor.hasNext()) {
                Document d = cursor.next();
                results.put(d.getString("protocolName"), d.getString("protocolSchema"));
            }
        }
        return results;
    }
}
