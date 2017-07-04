package eu.clarussecure.datamodel;

import eu.clarussecure.datamodel.types.Module;
import java.io.Serializable;

import java.util.Set;
import java.util.HashSet;

public class Protection implements Serializable {

    private Module module = null;
    private Set<ProtectionAttributeType> attributeTypes = new HashSet<>();

    public Protection() {
        // Default value here.
        Module.initialize();
        this.module = new Module();
    }

    public Protection(Module m) {
        this.module = m;
    }

    public Module getModule() {
        return this.module;
    }

    public void setModule(Module module) {
        this.module = module;
    }

    public Set<ProtectionAttributeType> getAttributeTypes() {
        return this.attributeTypes;
    }

    public void setAttributeTypes(Set<ProtectionAttributeType> attributeTypes) {
        this.attributeTypes = attributeTypes;
    }

    public void addProtectionAttribute(ProtectionAttributeType attrib) {
        // Check if there is a Protection Attrbute for this parameter
        for (ProtectionAttributeType pa : this.attributeTypes) {
            if (pa.getProtection() == attrib.getProtection()) {
                this.attributeTypes.remove(pa); // Remove the old value
            }
        }
        // Add the new value
        this.attributeTypes.add(attrib);
    }

    public void deleteProtectionAttributeType(ProtectionAttributeType type) {
        this.attributeTypes.remove(type);
    }

    @Override
    public String toString() {
        String s = "protection: module: " + this.module;
        return s;
    }

    public boolean isValid() {
        // TODO - Reimplement this since different CLARUS modules require different Protection Attributes.
        return true;
    }
}
