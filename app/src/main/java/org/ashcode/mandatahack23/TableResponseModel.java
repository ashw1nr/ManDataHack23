package org.ashcode.mandatahack23;

import com.google.gson.annotations.SerializedName;

public class TableResponseModel {
    @SerializedName("Status")
    private String Status;

    public String getStatus() {
        return Status;
    }

    public void setStatus(String status) {
        Status = status;
    }

    public TableResponseModel(String status) {
        Status = status;
    }
}
