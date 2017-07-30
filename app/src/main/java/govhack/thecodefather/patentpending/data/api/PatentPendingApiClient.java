package govhack.thecodefather.patentpending.data.api;

import android.support.annotation.NonNull;
import govhack.thecodefather.patentpending.Config;
import govhack.thecodefather.patentpending.data.models.ErrorDataModel;
import govhack.thecodefather.patentpending.data.models.GodDataModel;
import govhack.thecodefather.patentpending.data.models.PatentDataModel;
import govhack.thecodefather.patentpending.data.models.SuccessDataModel;
import govhack.thecodefather.patentpending.data.models.request.RegisterForNotificationRequestModel;
import govhack.thecodefather.patentpending.utility.NetworkUtility;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import lombok.val;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by andrewkevin on 29/7/17.
 */
public class PatentPendingApiClient {

  private static final int THREAD_POOL_SIZE = 1;
  private static final Executor THREAD_POOL_EXECUTOR = Executors
      .newFixedThreadPool(THREAD_POOL_SIZE);

  @NonNull
  private static PatentPendingApi getClient() {
    return new Retrofit.Builder()
        .baseUrl(Config.patentPendingBaseUrl)
        .addConverterFactory(GsonConverterFactory.create())
        .callbackExecutor(THREAD_POOL_EXECUTOR)
        .build()
        .create(PatentPendingApi.class);
  }

  public static void searchPatents(@NonNull String query,
      @NonNull HttpCallback<GodDataModel> httpCallback) {
    getClient().getGod(query).enqueue(httpCallback);
  }

  public static void registerEmailForNotification(@NonNull String emailAddress,
      @NonNull PatentDataModel patentDataModel,
      HttpCallback2<SuccessDataModel, ErrorDataModel> httpCallback) {
    val requestModel = RegisterForNotificationRequestModel.build(emailAddress, patentDataModel);
    val body = NetworkUtility
        .buildRequestBody(requestModel, RegisterForNotificationRequestModel.class);
    getClient().registerForNotification(body).enqueue(httpCallback);
  }
}