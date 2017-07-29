package govhack.thecodefather.patentpending.data.enums;

import com.google.gson.annotations.SerializedName;
import govhack.thecodefather.patentpending.data.GsonSerialisable;

/**
 * Created by andrewkevin on 29/7/17.
 */

public enum PatentType implements GsonSerialisable {
  @SerializedName("trademark")
  TRADEMARK,
  @SerializedName("patent")
  PATENT,
  UNKNOWN_PATENT_TYPE
}
