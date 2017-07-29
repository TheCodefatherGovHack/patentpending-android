package govhack.thecodefather.patentpending.data.api;

import android.support.annotation.NonNull;
import govhack.thecodefather.patentpending.Config;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by andrewkevin on 29/7/17.
 */
public class ApiClientFactory {
  private static final int THREAD_POOL_SIZE = 1;
  private static final Executor THREAD_POOL_EXECUTOR = Executors.newFixedThreadPool(THREAD_POOL_SIZE);

  @NonNull
  public static PatentPendingApi getPatentPending() {
    return new Retrofit.Builder()
        .baseUrl(Config.patentPendingBaseUrl)
        .addConverterFactory(GsonConverterFactory.create())
        .callbackExecutor(THREAD_POOL_EXECUTOR)
        .build()
        .create(PatentPendingApi.class);
  }
}