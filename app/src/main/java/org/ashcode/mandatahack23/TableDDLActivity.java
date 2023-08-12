package org.ashcode.mandatahack23;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TableDDLActivity extends AppCompatActivity implements AdapterRVColumnsTDDL.OnItemClickListener {

    private String tableName;
    private SingleColumnD currColumn;
    private String selectedConstraint;
    private String selectedDataType;
    private RecyclerView rvColumnsTDDL;
    private AdapterRVColumnsTDDL rvColumnsTDDLadapter;

    private ColumnsDD columnsInTableModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_table_ddl);

        Intent intent = getIntent();
        tableName = intent.getStringExtra("tableName");


        TextView tableNameTVTDDL = findViewById(R.id.tableNameTVTDDL);
        rvColumnsTDDL = findViewById(R.id.rvColumnsTDDL);
        Button addClmnTDDL = findViewById(R.id.addClmnTDDL);
        Button doneBtnTDDL = findViewById(R.id.doneBtnTDDL);

        tableNameTVTDDL.setText("TableName : "+tableName);

        ArrayList<SingleColumnD> x = new ArrayList<SingleColumnD>();
        columnsInTableModel = new ColumnsDD(x);

        refreshRVColumns();

        addClmnTDDL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                currColumn=new SingleColumnD("","","");//backPress not accounted for. Will give errors. Add additional code
                showColumnEditDialog();
                //columnsInTableModel.addColumn(currColumn);
                //refreshRVColumns(); //add Column checking and raising exceptions in backend. Directly call add column route from here

            }
        });

        doneBtnTDDL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DataService service = ManDataServiceGenerator.createService(DataService.class);
                Call<TableResponseModel> call = service.postTable(tableName,columnsInTableModel);
                call.enqueue(new Callback<TableResponseModel>() {
                    @Override
                    public void onResponse(Call<TableResponseModel> call, Response<TableResponseModel> response) {
                        if (response.isSuccessful()){
                            TableResponseModel responseModel = response.body();
                            System.out.println(responseModel.getStatus());
                            finish();
                        }
                        else {
                            System.out.println("Unsuccessful");
                        }
                    }

                    @Override
                    public void onFailure(Call<TableResponseModel> call, Throwable t) {
                        System.out.println("Failure "+t.toString());
                    }
                });
            }
        });

    }

    @Override
    public void onDeleteClick(SingleColumnD column){
        Toast.makeText(this, column.getClmnName()+" Deleted", Toast.LENGTH_SHORT).show();
        columnsInTableModel.removeColumn(column);
        refreshRVColumns();
    }

    @Override
    public void onUpdateClick(SingleColumnD column){
        Toast.makeText(this, column.getClmnName()+"Updated", Toast.LENGTH_SHORT).show();
    }


    private void getInitialColumns(){
        ArrayList<SingleColumnD> cols = new ArrayList<>();
        //cols.add(new SingleColumnD("roll no","int","not null"));
        columnsInTableModel = new ColumnsDD(cols);
    }

    public void showColumnEditDialog(){

        final Dialog columnEditDialog = new Dialog(TableDDLActivity.this);
        columnEditDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        columnEditDialog.setCancelable(true);
        columnEditDialog.setContentView(R.layout.dialog_edit_column_tddl);
        EditText clmnNameETTDDLDialog = columnEditDialog.findViewById(R.id.clmnNameETTDDLDialog);
        Button doneBtnedtclmnTDDLDialog = columnEditDialog.findViewById(R.id.doneBtnedtclmnTDDLDialog);
        Spinner constraintsSpinnerTDDLDialog = columnEditDialog.findViewById(R.id.constraintsSpinnerTDDLDialog);
        Spinner dataTyeSpinnerTDDLDialog = columnEditDialog.findViewById(R.id.dataTyeSpinnerTDDLDialog);

        ArrayList<Boolean> selectStatus = new ArrayList<>();
        selectStatus.add(false);selectStatus.add(false);

        ArrayAdapter<CharSequence> dataTypeAdapter=ArrayAdapter.createFromResource(this, R.array.dataTypesMySQL, android.R.layout.simple_spinner_item);
        dataTypeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
        dataTyeSpinnerTDDLDialog.setAdapter(dataTypeAdapter);

        dataTyeSpinnerTDDLDialog.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectStatus.set(0,true);
                selectedDataType = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                ;
            }
        });

        ArrayAdapter<CharSequence> constraintsAdapter=ArrayAdapter.createFromResource(this, R.array.constraintsMySQL, android.R.layout.simple_spinner_item);
        constraintsAdapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
        constraintsSpinnerTDDLDialog.setAdapter(constraintsAdapter);

        constraintsSpinnerTDDLDialog.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectStatus.set(1,true);
                selectedConstraint = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                ;
            }
        });

        doneBtnedtclmnTDDLDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String columnName = clmnNameETTDDLDialog.getText().toString();
                if (!columnName.isEmpty() && selectStatus.get(0) && selectStatus.get(1)){
                    currColumn.setClmnName(columnName);
                    currColumn.setConstraint(selectedConstraint);
                    currColumn.setDataType(selectedDataType);
                    columnEditDialog.dismiss();
                    columnsInTableModel.addColumn(currColumn);
                    refreshRVColumns();
                }
                else{
                    Toast.makeText(TableDDLActivity.this, "Enter all the fields", Toast.LENGTH_SHORT).show();
                }
            }
        });
        columnEditDialog.show();

    }
    public void refreshRVColumns(){
        rvColumnsTDDLadapter = new AdapterRVColumnsTDDL(columnsInTableModel,this);
        rvColumnsTDDL.setLayoutManager(new LinearLayoutManager(this));
        rvColumnsTDDL.setAdapter(rvColumnsTDDLadapter);
    }
}