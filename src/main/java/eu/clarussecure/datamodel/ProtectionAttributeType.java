package eu.clarussecure.datamodel;

import eu.clarussecure.datamodel.types.ProtectionName;
import eu.clarussecure.datamodel.types.AttrType;
import java.io.Serializable;

public class ProtectionAttributeType implements Serializable {

    private ProtectionName protection;
    private AttrType type;
    private ProtectionAttributeParameter parameters; // Name changed as suggested by AKKA

    private boolean canEdit;

    public ProtectionAttributeType() {
        this.protection = ProtectionName.CLOUDS;
        this.type = AttrType.CONFIDENTIAL;
        this.parameters = new ProtectionAttributeParameter();
    }

    public ProtectionAttributeType(ProtectionName name, AttrType type, ProtectionAttributeParameter param) {
        this.protection = name;
        this.type = type;
        this.parameters = param;
    }

    public ProtectionName getProtection() {
        return protection;
    }

    public void setProtection(ProtectionName protection) {
        this.protection = protection;
    }

    public AttrType getType() {
        return type;
    }

    public void setType(AttrType type) {
        this.type = type;
    }

    public ProtectionAttributeParameter getParameters() {
        return parameters;
    }

    public void setParameters(ProtectionAttributeParameter parameters) {
        this.parameters = parameters;
    }

    public boolean isCanEdit() {
        return canEdit;
    }

    public void setCanEdit(boolean canEdit) {
        this.canEdit = canEdit;
    }
}
