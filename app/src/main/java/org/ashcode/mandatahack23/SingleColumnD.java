package org.ashcode.mandatahack23;

import java.util.ArrayList;

public class SingleColumnD {
    String clmnName;
    String dataType;
    String constraint;

    public SingleColumnD(String clmnName, String dataType, String constraint) {
        this.clmnName = clmnName;
        this.dataType = dataType;
        this.constraint = constraint;
    }

    public String getClmnName() {
        return clmnName;
    }

    public void setClmnName(String clmnName) {
        this.clmnName = clmnName;
    }

    public String getDataType() {
        return dataType;
    }

    public void setDataType(String dataType) {
        this.dataType = dataType;
    }

    public String getConstraint() {
        return constraint;
    }

    public void setConstraint(String constraint) {
        this.constraint = constraint;
    }
}
