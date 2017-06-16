package eu.clarussecure.datamodel.types;

import com.google.gson.annotations.SerializedName;

public enum Module {
	// The annotation "SerializedName" is required by Gson to correctly map
	// the name on the Json file with the enum value

	// TODO - Complete this enum to support more CLARUS Security Modules.
	@SerializedName("anonymization")
	ANONYMIZATION("anonymization"),
	@SerializedName("splitting")
	SPLITTING("splitting"),
	@SerializedName("encryption")
	ENCRYPTION("encryption");

	private final String clarusModuleName;

	private Module(String name){
		this.clarusModuleName = name;
	}

	public String getCLARUSModuleName(){
		return this.clarusModuleName;
	}

	public static Module fromString(String name) throws IllegalArgumentException{
		for (Module m : Module.values()){
			if(m.getCLARUSModuleName().equals(name)){
				return m;
			}
		}

		throw new IllegalArgumentException();
	}
}
