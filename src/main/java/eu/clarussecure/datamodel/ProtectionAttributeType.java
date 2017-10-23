package eu.clarussecure.datamodel;

import eu.clarussecure.datamodel.types.AttrType;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import org.jdom2.Element;

public class ProtectionAttributeType implements Serializable {

    private String protection;
    private AttrType type;
    private List<ProtectionAttributeParameter> parameters; // Name changed as suggested by AKKA

    // Attributes marked as transiet WILL NOT be serialized by gson
    private transient boolean canEdit;

    public ProtectionAttributeType() {
        this.protection = "";
        this.type = AttrType.CONFIDENTIAL;
        this.parameters = new ArrayList<>();
    }

    public ProtectionAttributeType(String name, AttrType type, ProtectionAttributeParameter param) {
        this.protection = name;
        this.type = type;
        this.parameters = new ArrayList<>();
        this.parameters.add(param);
    }

    public ProtectionAttributeType(String name, AttrType type) {
        this.protection = name;
        this.type = type;
    }

    public String getProtection() {
        return protection;
    }

    public void setProtection(String protection) {
        this.protection = protection;
    }

    public AttrType getType() {
        return type;
    }

    public void setType(AttrType type) {
        this.type = type;
    }

    public List<ProtectionAttributeParameter> getParameters() {
        return parameters;
    }

    public void setParameters(List<ProtectionAttributeParameter> parameters) {
        this.parameters = parameters;
    }

    public void addParameter(ProtectionAttributeParameter parameter) {
        this.parameters.add(parameter);
    }

    public boolean isCanEdit() {
        return canEdit;
    }

    public void setCanEdit(boolean canEdit) {
        this.canEdit = canEdit;
    }

    public Element getXMLElement() {
        // Create the Element
        Element elem = new Element("attribute_type");

        // Add the protection attribute
        elem.setAttribute("protection", this.protection);

        // Add the type attribute
        elem.setAttribute("type", this.type.getAttrName());

        // Add any other extra info as attributes
        this.parameters.stream().forEach(param -> {
            elem.setAttribute(param.getParam(), param.getValue() + "");
        });

        return elem;
    }
}
