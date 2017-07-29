package govhack.thecodefather.patentpending.utility;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import java.lang.reflect.Type;
import okhttp3.MediaType;
import okhttp3.RequestBody;

/**
 * Created by andrewkevin on 29/7/17.
 */

public class NetworkUtility {
  public static RequestBody buildRequestBody(Object object, Type type) {
    String json = JsonUtility.convertTypeToJson(object, type);
    return RequestBody.create(MediaType.parse("application/json"), json);
  }

  public static RequestBody buildEmptyRequestBody() {
    return RequestBody.create(MediaType.parse("application/json"), "{}");
  }

  /**
   * Checks and returns whether there is an Internet connectivity or not. This
   * would be useful to check the network connectivity before making a network
   * call.
   *
   * @param context
   * @return "True" -> is Connected , "False" -> if not.
   */
  public synchronized static boolean isNetworkAvailable(Context context) {
    boolean isConnected = false;

    final ConnectivityManager connectivityService = (ConnectivityManager) context
        .getSystemService(Context.CONNECTIVITY_SERVICE);

    if (connectivityService != null) {
      final NetworkInfo networkInfo = connectivityService
          .getActiveNetworkInfo();
      if (networkInfo != null && networkInfo.isConnected()) {
        isConnected = true;
      }
    }

    return isConnected;
  }
}
