package org.ashcode.mandatahack23;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private String tableName;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button existTableBtnDash = findViewById(R.id.existTableBtnDash);
        Button createTableBtnDash = findViewById(R.id.createTableBtnDash);

        existTableBtnDash.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent existTablesFromMain = new Intent(getApplicationContext(),ExistingTablesActivity.class);
                startActivity(existTablesFromMain);
            }
        });

        createTableBtnDash.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                requestTablenameDialog();
            }
        });
    }
    private void requestTablenameDialog(){
        final Dialog reqTablenameDialog = new Dialog(MainActivity.this);
        reqTablenameDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        reqTablenameDialog.setCancelable(true);
        reqTablenameDialog.setContentView(R.layout.dialog_req_tablename_main);
        EditText tableNameETDialogDash = reqTablenameDialog.findViewById(R.id.tableNameETDialogDash);
        Button doneBtnReqDialogDash = reqTablenameDialog.findViewById(R.id.doneBtnReqDialogDash);

        doneBtnReqDialogDash.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tableName = tableNameETDialogDash.getText().toString();
                if (tableName.isEmpty()){
                    Toast.makeText(MainActivity.this, "Enter a Valid Tablename", Toast.LENGTH_SHORT).show();
                }
                else{
                    reqTablenameDialog.dismiss();
                    Intent TDDLFromMain = new Intent(getApplicationContext(),TableDDLActivity.class);
                    TDDLFromMain.putExtra("tableName",tableName);
                    startActivity(TDDLFromMain);
                }
            }
        });
        reqTablenameDialog.show();

    }
}