package govhack.thecodefather.patentpending.data.api;

import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.util.Log;
import java.io.IOException;
import java.net.ConnectException;
import java.net.UnknownHostException;
import okhttp3.RequestBody;
import okio.Buffer;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by andrewkevin on 29/7/17.
 */

public abstract class HttpCallback<T> implements Callback<T> {

  private final String TAG = this.getClass().getName();
  @NonNull
  private int responseCode;
  private T payload;
  private Throwable t;
  private String errorBody;

  /**
   * Invoked for HTTP success response; 200 - 300
   */
  public abstract void onSuccess(Call<T> call, Response<T> response);

  /**
   * Invoked for HTTP client error and server error; 400 - 600
   */
  public abstract void onError(Call<T> call, Response<T> response);

  public abstract void onFailure(Call<T> call);

  /**
   * Invoked ALL HTTP responses. This is not a connection failure.
   */
  @Override
  public void onResponse(Call<T> call, Response<T> response) {
    this.responseCode = response.code();

    // Handle specific codes first
    switch (responseCode) {
//      case HttpURLConnection.HTTP_UNAUTHORIZED:
//        break;
//      case HttpURLConnection.HTTP_GONE:
//        break;
      default:
        handleResponseCode(call, response, responseCode);
        break;
    }
  }

  /**
   * Override the Retrofit callback
   * Do some logging
   */
  @Override
  public final void onFailure(Call<T> call, Throwable t) {
    this.t = t;
    t.printStackTrace();
    onFailure(call);
  }

  /**
   * Map response code to resepective handler method
   */
  @SuppressWarnings("MagicNumber")
  private void handleResponseCode(Call<T> call, Response<T> response, int responseCode) {
    if (responseCode >= 200 && responseCode < 300) {
      handle2xx(call, response);
    } else if (responseCode >= 300 && responseCode < 400) {
      handle3xx(call, response);
    } else if (responseCode >= 400 && responseCode < 500) {
      handle4xx(call, response);
    } else if (responseCode >= 500 && responseCode < 600) {
      handle5xx(call, response);
    } else {
      throw new UnsupportedOperationException();
    }
    always();
  }

  void handle2xx(Call<T> call, Response<T> response) {
    Log.i(TAG, getTransactionInfoString(response));
    this.payload = response.body();
    onSuccess(call, response);
  }

  void handle3xx(Call<T> call, Response<T> response) {
    logError(response);
    onError(call, response);
  }

  void handle4xx(Call<T> call, Response<T> response) {
    logError(response);
    onError(call, response);
  }

  void handle5xx(Call<T> call, Response<T> response) {
    logError(response);
    onError(call, response);
  }

  protected void always() {};

  void logError(Response<T> response) {
    try {
      this.errorBody = response.errorBody().string();
    } catch (Exception e) {
      e.printStackTrace();
    }

    // Log error
    if (!TextUtils.isEmpty(getErrorBody())) {
      Log.e(TAG, getTransactionInfoString(response));
    }
  }

  protected final T getPayload() {
    return payload;
  }

  protected final int getResponseCode() {
    return responseCode;
  }

  protected final String getErrorBody() {
    return errorBody;
  }

  protected final boolean isThrowableInstanceOf(Class classType) {
    return classType.isInstance(t);
  }

  protected final boolean haveConnectionIssue() {
    return (isThrowableInstanceOf(UnknownHostException.class)
        || isThrowableInstanceOf(ConnectException.class));
  }

  final public String getTransactionInfoString(Response<T> response) {
    String nl = System.getProperty("line.separator");

    StringBuilder stringBuilder = new StringBuilder()
        .append("### HTTP Transaction ###")
        .append(nl + nl)
        .append(response)
        .append(nl)
        .append(response.raw().request())
        .append(nl + nl)
        .append("Headers")
        .append(nl)
        .append(response.raw().request().headers());

    if (response.raw().request().body() != null && !TextUtils
        .isEmpty(response.raw().request().body().toString())) {
      stringBuilder
          .append(nl)
          .append("Request Body")
          .append(nl)
          .append(bodyToString(response.raw().request().body()));
    }

    if (!TextUtils.isEmpty(getErrorBody())) {
      stringBuilder
          .append(nl + nl)
          .append("Error Body")
          .append(nl)
          .append(getErrorBody());
    }

    stringBuilder
        .append(nl + nl)
        .append("#### END ###");

    return stringBuilder.toString();
  }

  private String bodyToString(final RequestBody request) {
    try {
      final RequestBody copy = request;
      final Buffer buffer = new Buffer();
      if (copy != null) {
        copy.writeTo(buffer);
      } else {
        return "";
      }
      return buffer.readUtf8();
    } catch (final IOException e) {
      return "!!!DID NOT WORK!!!";
    }
  }
}

