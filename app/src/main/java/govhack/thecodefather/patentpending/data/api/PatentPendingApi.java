package govhack.thecodefather.patentpending.data.api;

import govhack.thecodefather.patentpending.data.models.GodDataModel;
import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by andrewkevin on 29/7/17.
 */

public interface PatentPendingApi {
  @GET("/dev")
  Call<GodDataModel> getGod();
}
