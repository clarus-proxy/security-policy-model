package eu.clarussecure.datamodel;

import eu.clarussecure.datamodel.types.Protocol;
import java.io.Serializable;

public class Endpoint implements Serializable{
    
	private Protocol protocol;
	private int port;
    // BaseURL was deleted from this structure
    // The lines related with this attibute were commented (getters and setters included)
	//private String baseUrl;

    public Endpoint() { // This default constructor might pose some problems.
        // Default value here.
        this.protocol = Protocol.HTTP;
        this.port = 0;
        //this.baseUrl = "";
    }

	public Endpoint(Protocol proto, int p, String url){
		this.protocol = proto;
		this.port = p;
		//this.baseUrl = url;
	}

	public Protocol getProtocol(){
		return this.protocol;
	}

    public void setProtocol(Protocol protocol) {
        this.protocol = protocol;
    }

	public int getPort(){
		return this.port;
	}

    public void setPort(int port) {
        this.port = port;
    }

    /*
	public String getBaseUrl(){
		return this.baseUrl;
	}

    public void setBaseUrl(String baseUrl) {
        this.baseUrl = baseUrl;
    }
    */
	
	public String getEndpointURL(){
		return this.protocol.getProtocolScheme() + "://" + "host_name:" + this.port + "/";// + this.baseUrl;
	}

    @Override
    public String toString() {
        String s = "endpoint: protocol: " + this.getProtocol() + ", port: " + this.getPort();// + ", base URL: " + this.getBaseUrl();
        return s;
    }
}