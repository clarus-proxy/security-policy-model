package eu.clarussecure.datamodel.types;

import com.google.gson.annotations.SerializedName;

public enum AttrType {

    // FIXME - Add new attribute types here to extend the support
    // Format: ENUM-NAME(AttrName)
    // attrName is required for obtaining the value from a string given by the user
    @SerializedName("non_confidential") NON_CONFIDENTIAL("non_confidential"),
    @SerializedName("confidential") CONFIDENTIAL("confidential"),
    @SerializedName("identifier") IDENTIFIER("identifier"),
    @SerializedName("quasi_identifier") QUASI_IDENTIFIER("quasi_identifier");

    private String name;

    private AttrType(String n) {
        this.name = n;
    }

    public String getAttrName() {
        return this.name;
    }

    public static AttrType fromString(String n) throws IllegalArgumentException {
        for (AttrType t : AttrType.values()) {
            if (t.getAttrName().equals(n.toLowerCase())) {
                return t;
            }
        }
        throw new IllegalArgumentException();
    }
}
