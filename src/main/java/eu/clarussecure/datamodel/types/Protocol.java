package eu.clarussecure.datamodel.types;

import eu.clarussecure.datamodel.types.utils.TypesDAO;
import java.util.Set;
import java.util.Arrays;
import java.util.Map;

public class Protocol {

    private final String protocolScheme;
    private final String protocolName;
    // These variables are marked transient to avoid being serialized by gson
    private static transient Map<String, String> protocols = null;
    private static transient boolean initialized = false;

    public Protocol(String name, String scheme) {
        this.protocolName = name;
        this.protocolScheme = scheme;
    }

    public Protocol(String name) {
        this.protocolName = name;
        this.protocolScheme = Protocol.protocols.get(name);
    }

    public String getProtocolScheme() {
        return this.protocolScheme;
    }

    public String getProtocolName() {
        return this.protocolName;
    }

    public static void initialize() {
        if (Protocol.initialized) {
            // If the class is already initialized, there's nothing to do
            return;
        }

        // Retrieve the list of registered protocols
        TypesDAO dao = TypesDAO.getInstance();
        Protocol.protocols = dao.loadProtocolsList();
        dao.deleteInstance();

        Protocol.initialized = true;
    }

    public static boolean isValidProtocol(String protocolName) {
        // This method looks check if the given protocol is valid or not
        Set<String> protocolNames = Protocol.getProtocolNames();

        for (String p : protocolNames) {
            if (p.equals(protocolName.toLowerCase())) {
                return true;
            }
        }
        return false;
    }

    public static Set<String> getProtocolNames() {
        // Sanity Check
        if (!Protocol.initialized) {
            return null;
        }
        // Simply return the Key Set of the loaded protocols
        return Protocol.protocols.keySet();
    }

    public static String getProtocolNamesAsList() {
        // Sanity Check
        if (!Protocol.initialized) {
            return null;
        }
        Set<String> protocols = Protocol.getProtocolNames();
        return Arrays.toString(protocols.toArray());
    }
}
