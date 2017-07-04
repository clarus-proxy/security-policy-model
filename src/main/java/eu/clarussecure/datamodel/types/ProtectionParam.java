package eu.clarussecure.datamodel.types;

import java.util.List;
import java.util.LinkedList;

import com.google.gson.annotations.SerializedName;

public enum ProtectionParam {
    // The annotation "SerializedName" is required by Gson to correctly map
    // the name on the Json file with the enum value

    // TODO - Complete this enum to support more ProtectionNames
    // Format: ENUM-NAME(ProtectionName.<ProtectionName>, paramName)
    // <protectionName> is the ProtectionName value to associate the given param
    // paramName is required for obtaining the value from a string given by the user
    @SerializedName("k") KVALUE(ProtectionName.KANONIMITY, "k"),
    @SerializedName("t") TCLOSENESSVALUE(ProtectionName.TCLOSENESS, "t"),
    @SerializedName("radius") RADIUSVALUE(ProtectionName.COARSENING, "radius"),
    @SerializedName("clouds") CLOUDSNUMBER(ProtectionName.CLOUDS, "clouds");

    private final ProtectionName module;
    private final String name;

    private ProtectionParam(ProtectionName m, String n) {
        this.module = m;
        this.name = n;
    }

    public ProtectionName getProtectionName() {
        return this.module;
    }

    public String getName() {
        return this.name;
    }

    public static ProtectionParam fromString(String n, ProtectionName m) throws IllegalArgumentException {
        // IMPORTANT: For this enum it is important to search the right attribute
        // considering the CLARUS Module implemented
        for (ProtectionParam p : ProtectionParam.values()) {
            if (p.getProtectionName() == m && p.getName().equals(n.toLowerCase())) {
                return p;
            }
        }

        throw new IllegalArgumentException();
    }

    public static ProtectionParam[] values(ProtectionName n) {
        List<ProtectionParam> res = new LinkedList<>();
        for (ProtectionParam p : ProtectionParam.values()) {
            if (p.getProtectionName() == n) {
                res.add(p);
            }
        }

        return res.toArray(new ProtectionParam[0]);
    }
}
