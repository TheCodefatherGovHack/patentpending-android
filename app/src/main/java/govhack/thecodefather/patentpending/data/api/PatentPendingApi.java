package govhack.thecodefather.patentpending.data.api;

import govhack.thecodefather.patentpending.data.models.GodDataModel;
import govhack.thecodefather.patentpending.data.models.SuccessDataModel;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Created by andrewkevin on 29/7/17.
 */

public interface PatentPendingApi {

  @GET("/dev")
  Call<GodDataModel> getGod(@Query("query") String query);

  @POST("/dev/notify")
  Call<SuccessDataModel> registerForNotification(@Body RequestBody requestBody);
}
