package eu.clarussecure.datamodel.types;

import java.util.List;
import java.util.LinkedList;

import com.google.gson.annotations.SerializedName;

public enum ProtectionName {
	// The annotation "SerializedName" is required by Gson to correctly map
	// the name on the Json file with the enum value

	// TODO - Complete this enum to support more ProtectionNames
	// Format: ENUM-NAME(CLARUSModule.<Module>, paramRequired, protectionName)
	// module is the CLARUS module to associate this ProtectionName
	// paramRequired is a flag indicating whether the Protection takes a parameter or not
	// protectionName is required for obtaining the value from a string given by the user
	@SerializedName("suppression")
	SUPPRESSION(Module.ANONYMIZATION, false, "suppression"),
	@SerializedName("k-anonimity")
	KANONIMITY(Module.ANONYMIZATION, true, "k-anonimity"),
	@SerializedName("t-closeness")
	TCLOSENESS(Module.ANONYMIZATION, true, "t-closeness"),
	@SerializedName("coarsening")
	COARSENING(Module.ANONYMIZATION, true, "coarsening"),
	@SerializedName("splitting")
	CLOUDS(Module.SPLITTING, true, "splitting"),
	@SerializedName("null")
	NULLANONYMIZATION(Module.ANONYMIZATION, false, "null");
	// @SerializedName("null")
	// NULLENCRYPTION(CLARUSModule.ENCRYPTION, "null");

	private final Module module;
	private final boolean parameter;
	private final String name;

	private ProtectionName(Module m, boolean param, String n){
		this.module = m;
		this.parameter = param;
		this.name = n;
	}

	public Module getCLARUSModule(){
		return this.module;
	}

	public String getName(){
		return this.name;
	}

	public boolean getsParameter(){
		return this.parameter;
	}

	public static ProtectionName fromString(String n, Module m) throws IllegalArgumentException{
		// IMPORTANT: For this enum it is important to search the right attribute
		// considering the CLARUS Module implemented
		for(ProtectionName p : ProtectionName.values()){
			if(p.getCLARUSModule() == m && p.getName().equals(n.toLowerCase())){
				return p;
			}
		}

		throw new IllegalArgumentException();
	}

	public static ProtectionName[] values(Module mod){
		List<ProtectionName> res = new LinkedList<>();
		for (ProtectionName p : ProtectionName.values()){
			if(p.getCLARUSModule() == mod){
				res.add(p);
			}
		}

		return (ProtectionName[]) res.toArray(new ProtectionName[0]);
	}
}
