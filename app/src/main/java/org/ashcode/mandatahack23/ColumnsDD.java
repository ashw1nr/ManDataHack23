package org.ashcode.mandatahack23;

import java.util.ArrayList;

public class ColumnsDD {
    ArrayList<SingleColumnD> columns;

    public ColumnsDD(ArrayList<SingleColumnD> columns) {
        this.columns = columns;
    }

    public ArrayList<SingleColumnD> getColumns() {
        return columns;
    }

    public void setColumns(ArrayList<SingleColumnD> columns) {
        this.columns = columns;
    }

    public void addColumn(SingleColumnD column){
        columns.add(column);
    }

    public void removeColumn(SingleColumnD column) {columns.remove(column);}
}
