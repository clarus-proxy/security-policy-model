package eu.clarussecure.datamodel;

import java.io.Serializable;

public class ProtectionAttributeParameter implements Serializable {

    private String param;
    private double value;

    public ProtectionAttributeParameter() {
        // Dummy constructor with dummy initial values
        this.param = null;
        this.value = -1.0;
    }

    public ProtectionAttributeParameter(String param, double value) {
        this.param = param;
        this.value = value;
    }

    public String getParam() {
        return this.param;
    }

    public void setParam(String param) {
        this.param = param;
    }

    public double getValue() {
        return this.value;
    }

    public void setValue(double value) {
        this.value = value;
    }
}
