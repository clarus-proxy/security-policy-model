package eu.clarussecure.datamodel;

import eu.clarussecure.datamodel.types.AttrType;
import eu.clarussecure.datamodel.types.DataType;
import java.io.Serializable;

public class PolicyAttribute implements Serializable{
	
	private String path;
	private AttrType attributeType;
	private DataType dataType;
	
    private boolean canEdit; // This field could be renamed as "registered"

    public PolicyAttribute() { // This constructor can pose problems
        this.path = "";
        this.attributeType = AttrType.CONFIDENTIAL;
        this.dataType = DataType.CATEGORIC;
        this.canEdit = false;
    }

	public PolicyAttribute(String path, AttrType type, DataType dtype){
		this.path = path;
		this.attributeType = type;
		this.dataType = dtype;
        this.canEdit = false;
	}

	public String getPath(){
		return this.path;
	}

    public void setPath(String path) {
        this.path = path;
    }

	public AttrType getAttributeType(){
		return this.attributeType;
	}

	public void setAttributeType(AttrType type){
		this.attributeType = type;
	}

    public DataType getDataType() {
        return this.dataType;
    }

    public void setDataType(DataType type) {
        this.dataType = type;
    }

    public boolean isCanEdit() {
        return canEdit;
    }

    public void setCanEdit(boolean canEdit) {
        this.canEdit = canEdit;
    }
}
