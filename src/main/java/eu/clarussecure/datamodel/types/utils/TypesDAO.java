package eu.clarussecure.datamodel.types.utils;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.MongoIterable;
import static com.mongodb.client.model.Filters.eq;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.bson.Document;

import java.util.Set;
import java.util.HashSet;
import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class TypesDAO {

    private final MongoDatabase db;
    private final MongoClient mongoClient;
    private static int instances = 0;

    private static TypesDAO instance = null;

    private String confFile = "/etc/clarus/clarus-mgmt-tools.conf";
    private String mongoDBHostname = "localhost"; // Default server
    private int mongoDBPort = 27017; // Default port
    private String clarusDBName = "CLARUS"; // Default DB name

    private TypesDAO() {
        // Correctly configure the log level
        Logger mongoLogger = Logger.getLogger("org.mongodb.driver");
        mongoLogger.setLevel(Level.SEVERE);
        // Open the configuraiton file to extract the information from it.
        this.processConfigurationFile();
        // Create a new client connecting to "localhost" on port 
        this.mongoClient = new MongoClient(this.mongoDBHostname, this.mongoDBPort);

        // Get the database (will be created if not present)
        this.db = mongoClient.getDatabase(this.clarusDBName);
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

    private void processConfigurationFile() throws RuntimeException {
        // Open the file in read-only mode. This will avoid any permission problem
        try {
            // Read all the lines and join them in a single string
            List<String> lines = Files.readAllLines(Paths.get(this.confFile));
            String content = lines.stream().reduce("", (a, b) -> a + b);

            // Use the bson document parser to extract the info
            Document doc = Document.parse(content);
            this.mongoDBHostname = doc.getString("CLARUS_metadata_db_hostname");
            this.mongoDBPort = doc.getInteger("CLARUS_metadata_db_port");
            this.clarusDBName = doc.getString("CLARUS_metadata_db_name");
        } catch (IOException e) {
            throw new RuntimeException("CLARUS configuration file could not be processed", e);
        }
    }
}
