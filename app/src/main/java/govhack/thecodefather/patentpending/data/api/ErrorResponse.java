package govhack.thecodefather.patentpending.data.api;

import android.support.annotation.Nullable;
import govhack.thecodefather.patentpending.utility.JsonUtility;
import okhttp3.Headers;
import okhttp3.ResponseBody;
import retrofit2.Response;

/**
 * Created by andrewkevin on 30/7/17.
 */

public class ErrorResponse<T> {

  private final static String TAG = ErrorResponse.class.getName();

  private final okhttp3.Response mRawResponse;
  private final ResponseBody mResponseBody;
  private final T mErrorPayload;

  public ErrorResponse(Response response, String errorBody, Class<T> errorTypeClass) {
    this.mRawResponse = response.raw();
    this.mResponseBody = response.errorBody();
    this.mErrorPayload = deserialiseErrorBody(errorBody, errorTypeClass);
  }

  @Nullable
  private T deserialiseErrorBody(String errorBody, Class<T> errorTypeClass) {
    // Check if errorTypeClass is string
    if (errorTypeClass == String.class) {
      return (T) errorBody;
    } else {
      // Otherwise assume JSON and attempt to deserialise
      try {
        // Return parsed error body
        return JsonUtility.deserialize(errorBody, errorTypeClass);
      } catch (Exception e) {
        e.printStackTrace();
        return null;
      }
    }
  }

  /**
   * The raw response from the HTTP client.
   */
  public okhttp3.Response raw() {
    return mRawResponse;
  }

  /**
   * HTTP status code.
   */
  public int code() {
    return mRawResponse.code();
  }

  /**
   * HTTP status message or null if unknown.
   */
  public String message() {
    return mRawResponse.message();
  }

  /**
   * HTTP headers.
   */
  public Headers headers() {
    return mRawResponse.headers();
  }

  /**
   * The raw response body of an unsuccessful response.
   */
  public ResponseBody errorBody() {
    return mResponseBody;
  }

  /**
   * Return deserialised error
   */
  @Nullable
  public T getErrorPayload() {
    return mErrorPayload;
  }
}
