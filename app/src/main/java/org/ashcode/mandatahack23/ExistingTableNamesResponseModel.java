package org.ashcode.mandatahack23;

import java.util.ArrayList;

public class ExistingTableNamesResponseModel {
    ArrayList<String> table_names;

    public ExistingTableNamesResponseModel(ArrayList<String> table_names) {
        this.table_names = table_names;
    }

    public ArrayList<String> getTable_names() {
        return table_names;
    }

    public void setTable_names(ArrayList<String> table_names) {
        this.table_names = table_names;
    }
}
