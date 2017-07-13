package eu.clarussecure.datamodel;

import eu.clarussecure.datamodel.types.Protocol;
import java.io.Serializable;
import java.util.Set;
import java.util.HashSet;

public class Endpoint implements Serializable {

    private Protocol protocol;
    private int port;
    private Set<ProtocolParam> parameters;
    // BaseURL was deleted from this structure
    // The lines related with this attibute were commented (getters and setters included)
    //private String baseUrl;

    public Endpoint() { // This default constructor might pose some problems.
        // Default value here.
        Protocol.initialize();
        this.protocol = new Protocol("http");
        this.port = 0;
        this.parameters = new HashSet<>();
        //this.baseUrl = "";
    }

    public Endpoint(Protocol proto, int p) {
        this.protocol = proto;
        this.port = p;
        this.parameters = new HashSet<>();
        //this.baseUrl = url;
    }

    public Protocol getProtocol() {
        return this.protocol;
    }

    public void setProtocol(Protocol protocol) {
        this.protocol = protocol;
    }

    public int getPort() {
        return this.port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public Set<ProtocolParam> getParameters() {
        return this.parameters;
    }

    public void setParameters(Set<ProtocolParam> params) {
        this.parameters = params;
    }

    public void addParameter(ProtocolParam param) {
        this.parameters.add(param);
    }

    /*
    public String getBaseUrl(){
    	return this.baseUrl;
    }
    
    public void setBaseUrl(String baseUrl) {
        this.baseUrl = baseUrl;
    }
    */

    public boolean isValid() {
        // Validation of the Endpoint structure
        boolean valid;

        // A valid port must be present
        valid = this.port > 0;

        // The Endpoint must have a Valid Protocol
        Protocol.initialize();
        valid = valid && Protocol.isValidProtocol(this.protocol.getProtocolName());

        for (ProtocolParam param : this.parameters)
            valid = valid && param.isValid();

        return valid;
    }

    public String getEndpointURL() {
        return this.protocol.getProtocolScheme() + "://" + "host_name:" + this.port + "/";// + this.baseUrl;
    }

    @Override
    public String toString() {
        String s = "endpoint: protocol: " + this.getProtocol() + ", port: " + this.getPort();// + ", base URL: " + this.getBaseUrl();
        return s;
    }
}