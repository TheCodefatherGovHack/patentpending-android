package govhack.thecodefather.patentpending.presentation;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import govhack.thecodefather.patentpending.R;
import govhack.thecodefather.patentpending.data.api.ApiClientFactory;
import govhack.thecodefather.patentpending.data.api.HttpCallback;
import govhack.thecodefather.patentpending.data.models.GodDataModel;
import retrofit2.Call;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    ApiClientFactory.getPatentPending().getGod().enqueue(new HttpCallback<GodDataModel>() {
      @Override
      public void onSuccess(Call<GodDataModel> call, Response<GodDataModel> response) {

      }

      @Override
      public void onError(Call<GodDataModel> call, Response<GodDataModel> response) {

      }

      @Override
      public void onFailure(Call<GodDataModel> call) {

      }
    });
  }
}
