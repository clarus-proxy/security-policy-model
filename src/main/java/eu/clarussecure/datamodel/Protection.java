package eu.clarussecure.datamodel;

import eu.clarussecure.datamodel.types.Module;
import java.io.Serializable;

import java.util.Set;
import java.util.HashSet;
import java.util.stream.Stream;
import org.jdom2.Element;

public class Protection implements Serializable {

    private Module module = null;
    private Set<ProtectionAttributeType> attributeTypes = new HashSet<>();

    public Protection() {
        // Default value here.
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

    public Element getXMLElement() {
        // Create the XML Element of this object
        Element elem = new Element("protection");

        // Add the "module" attribute of this element
        elem.setAttribute("module", this.module.getClarusModuleName());

        // Get the XML Elements of each attribute type
        Stream<Element> subelems = this.attributeTypes.stream().map(attribType -> {
            return attribType.getXMLElement();
        });

        // Attach the attribute types
        Element attribTypes = new Element("attribute_types");
        subelems.forEach(subelem -> attribTypes.addContent(subelem));

        // Attach the attribute types element
        elem.addContent(attribTypes);

        return elem;
    }
}
