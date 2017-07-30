package govhack.thecodefather.patentpending.data.models;

import com.google.gson.annotations.SerializedName;
import lombok.Getter;

/**
 * Created by andrewkevin on 30/7/17.
 */
@Getter
public class SuccessDataModel {
  @SerializedName("message")
  private boolean success;
}
