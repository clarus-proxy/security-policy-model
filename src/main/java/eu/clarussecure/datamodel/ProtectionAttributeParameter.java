package eu.clarussecure.datamodel;

import eu.clarussecure.datamodel.types.ProtectionParam;
import java.io.Serializable;

public class ProtectionAttributeParameter implements Serializable {

    private ProtectionParam param;
    private double value;

    public ProtectionAttributeParameter() {
        this.param = ProtectionParam.CLOUDSNUMBER;
    }

    public ProtectionAttributeParameter(ProtectionParam param, double value) {
        this.param = param;
        this.value = value;
    }

    public ProtectionParam getParam() {
        return this.param;
    }

    public void setParam(ProtectionParam param) {
        this.param = param;
    }

    public double getValue() {
        return this.value;
    }

    public void setValue(double value) {
        this.value = value;
    }
}
