package govhack.thecodefather.patentpending.data.api;

import android.support.annotation.Nullable;
import retrofit2.Call;
import retrofit2.Response;

/**
 * Created by andrewkevin on 30/7/17.
 */

public abstract class HttpCallback2<T, S> extends HttpCallback<T> {
  private final Class<S> mErrorTypeClass;
  private S errorPayload;

  public HttpCallback2(Class<S> mErrorTypeClass) {
    this.mErrorTypeClass = mErrorTypeClass;
  }

  protected abstract void onError(Call<T> call, ErrorResponse<S> errorResponse);

  @Override
  void handle3xx(Call<T> call, Response<T> response) {
    logError(response);
    onFailure(call);
  }

  @Override
  void handle4xx(Call<T> call, Response<T> response) {
    logError(response);
    onError(call, response);
  }

  @Override
  void handle5xx(Call<T> call, Response<T> response) {
    logError(response);
    onFailure(call);
  }

  @Override
  public void onError(Call<T> call, Response<T> response) {
    ErrorResponse<S> errorResponse = new ErrorResponse<>(response, getErrorBody(), mErrorTypeClass);
    this.errorPayload = errorResponse.getErrorPayload();
    onError(call, errorResponse);
  }

  @Nullable
  protected final S getErrorPayload() {
    return this.errorPayload;
  }
}
