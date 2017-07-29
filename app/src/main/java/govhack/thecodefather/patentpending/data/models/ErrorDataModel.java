package govhack.thecodefather.patentpending.data.models;

import android.text.TextUtils;
import com.google.gson.annotations.SerializedName;
import lombok.Getter;

/**
 * Created by andrewkevin on 30/7/17.
 */

@Getter
public class ErrorDataModel {
  @SerializedName("success")
  private boolean success;
  @SerializedName("Error")
  private String error;

  public boolean hasError() {
    return !TextUtils.isEmpty(error);
  }
}
