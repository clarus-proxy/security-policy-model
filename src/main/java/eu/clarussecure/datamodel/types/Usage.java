package eu.clarussecure.datamodel.types;

import com.google.gson.annotations.SerializedName;

public enum Usage {
    // The annotation "SerializedName" is required by Gson to correctly map
    // the name on the Json file with the enum value
    @SerializedName("storage") STORAGE("storage"),
    @SerializedName("search") SEARCH("search"),
    @SerializedName("update") UPDATE("update"),
    @SerializedName("compute") COMPUTE("compute");

    private final String usageName;

    private Usage(String name) {
        this.usageName = name;
    }

    public String getUsageName() {
        return this.usageName;
    }

    public static Usage fromString(String name) throws IllegalArgumentException {
        for (Usage u : Usage.values()) {
            if (u.getUsageName().equals(name)) {
                return u;
            }
        }

        throw new IllegalArgumentException();
    }
}
