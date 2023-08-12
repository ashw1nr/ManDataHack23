package org.ashcode.mandatahack23;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface DataService {
    @GET("/table/{tablename}")
    public Call<TableResponseModel> getTable(
            @Path("tablename") String tablename
    );

    @POST("/table/create/{tablename}")
    public Call<TableResponseModel> postTable(
            @Path("tablename") String tablename,
            @Body ColumnsDD fields
    );

    @GET("/tables/")
    public Call<ExistingTableNamesResponseModel> getAllTableNames(
    );
}
