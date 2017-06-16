package eu.clarussecure.datamodel.types;

import com.google.gson.annotations.SerializedName;

public enum DataType{
	// FIXME - Add new data types here to extend the support
	// Format: ENUM-NAME(DataName)
	// DataName is required for obtaining the value from a string given by the user
	@SerializedName("categoric")
	CATEGORIC("categoric"),
	@SerializedName("categoric_ordinal")
	CATEGORIC_ORDINAL("categoric_ordinal"),
	@SerializedName("numeric_discrete")
	NUMERIC_DISCRETE("numeric_discrete"),
	@SerializedName("numeric_continuous")
	NUMERIC_CONTINOUS("numeric_continuous"),
	@SerializedName("geometric_object")
	GEOMETRIC_OBJECT("geometric_object");

	private final String name;

	private DataType(String n){
		this.name = n;
	}

	public String getName(){
		return this.name;
	}

	public static DataType fromString(String n) throws IllegalArgumentException{
		for (DataType d : DataType.values()){
			if(d.getName().equals(n.toLowerCase())){
				return d;
			}
		}

		throw new IllegalArgumentException();
	}
}
