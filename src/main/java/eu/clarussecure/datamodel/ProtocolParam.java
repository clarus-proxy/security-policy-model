package eu.clarussecure.datamodel;

import java.io.Serializable;

public class ProtocolParam implements Serializable {
    private String param;
    private String value;

    public ProtocolParam() {
        this.param = "";
        this.value = "";
    }

    public ProtocolParam(String param, String value) {
        this.param = param;
        this.value = value;
    }

    public String getParam() {
        return this.param;
    }

    public void setParam(String param) {
        this.param = param;
    }

    public String getValue() {
        return this.value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        // TODO
        throw new UnsupportedOperationException();
    }

    public boolean isValid() {
        // TODO - Change this behavior when defining correctly how a protocol Parameter must look like
        return true;
    }
}
