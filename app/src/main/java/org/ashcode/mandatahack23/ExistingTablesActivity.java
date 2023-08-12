package org.ashcode.mandatahack23;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ExistingTablesActivity extends AppCompatActivity implements AdapterRVExistTables.OnItemClickListener {
    private RecyclerView rvExistingTables;
    private AdapterRVExistTables adapter;
    private ExistingTableNamesResponseModel responseModel ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_existing_tables);






        getTNames();

        adapter = new AdapterRVExistTables(responseModel, this);
        rvExistingTables = findViewById(R.id.rvExistingTables);
        rvExistingTables.setLayoutManager(new LinearLayoutManager(this));
        rvExistingTables.setAdapter(adapter);
    }

    @Override
    public void onItemClick(String tName) {
        // Implement your action here when an item is clicked
        Toast.makeText(this, "Clicked: " + tName, Toast.LENGTH_SHORT).show();
    }

    // Your data source method (replace this with your actual data)
    private void getTNames() {
        DataService service = ManDataServiceGenerator.createService(DataService.class);
        Call<ExistingTableNamesResponseModel> call = service.getAllTableNames();
        call.enqueue(new Callback<ExistingTableNamesResponseModel>() {
            @Override
            public void onResponse(Call<ExistingTableNamesResponseModel> call, Response<ExistingTableNamesResponseModel> response) {
                if (response.isSuccessful()){
                    responseModel.setTable_names(response.body().getTable_names());
                    System.out.println(responseModel.getTable_names());
                }
                else {
                    System.out.println("Unsuccessful");
                }
            }

            @Override
            public void onFailure(Call<ExistingTableNamesResponseModel> call, Throwable t) {
                System.out.println("Failure "+t.toString());
            }
        });

    }

}