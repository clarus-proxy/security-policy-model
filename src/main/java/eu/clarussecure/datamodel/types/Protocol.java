package eu.clarussecure.datamodel.types;

import com.google.gson.annotations.SerializedName;

public enum Protocol{
	// FIXME - Add new protocol schemes here to extend the support
	// Format: ENUM-NAME(protocolName, protocolScheme)
	// protocolScheme is used to form the final URL later
	// protocolName is required for obtaining the value from a string given by the user
	@SerializedName("postgresql")
	POSTGRESQL("postgresql", "postgresql"),
	@SerializedName("mysql")
	MYSQL("mysql", "mysql"),
	@SerializedName("http")
	HTTP("http", "http"),
	@SerializedName("https")
	HTTPS("https", "https"),
	@SerializedName("wfs")
	WFS("wfs", "http");

	private final String protocolScheme;
	private final String protocolName;

	private Protocol(String name, String scheme){
		this.protocolName = name;
		this.protocolScheme = scheme;
	}

	public String getProtocolScheme(){
		return this.protocolScheme;
	}
	
	public String getProtocolName(){
		return this.protocolName;
	}

	public static Protocol fromString(String proto) throws IllegalArgumentException{
		for (Protocol p : Protocol.values()){
			if (p.getProtocolName().toLowerCase().equals(proto)){
				return p;
			}
		}

		throw new IllegalArgumentException();
	}
}
