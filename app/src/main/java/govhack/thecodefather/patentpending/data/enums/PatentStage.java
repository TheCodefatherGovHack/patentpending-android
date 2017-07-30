package govhack.thecodefather.patentpending.data.enums;

import android.support.annotation.ColorRes;
import android.support.annotation.Nullable;
import com.google.gson.annotations.SerializedName;
import govhack.thecodefather.patentpending.R;
import govhack.thecodefather.patentpending.data.GsonSerialisable;

/**
 * Created by andrewkevin on 29/7/17.
 */

public enum PatentStage implements GsonSerialisable {
  @SerializedName("filed")
  FILED,
  @SerializedName("waitingForExamination")
  WAITING_FOR_CONFIRMATION,
  @SerializedName("beingExamined")
  BEING_EXAMINED,
  @SerializedName("acceptanceAndOppositionPhase")
  ACCEPTANCE_AND_OPPOSITION_PHASE,
  @SerializedName("registered")
  REGISTERED,
  @SerializedName("expired")
  EXPIRED,
  UNKNOWN_STAGE_NAME;

  @Nullable
  public PatentStage nextStage() {
    switch (this) {
      case FILED:
        return WAITING_FOR_CONFIRMATION;
      case WAITING_FOR_CONFIRMATION:
        return BEING_EXAMINED;
      case ACCEPTANCE_AND_OPPOSITION_PHASE:
        return REGISTERED;
      default:
        return null;
    }
  }

  public boolean hasNextStage() {
    return nextStage() != null;
  }

  public boolean isUnknown() {
    return this == UNKNOWN_STAGE_NAME;
  }

  @ColorRes
  public int getColorResId() {
    switch (this) {
      case FILED:
        return R.color.green5;
      case WAITING_FOR_CONFIRMATION:
        return R.color.green4;
      case BEING_EXAMINED:
        return R.color.green3;
      case ACCEPTANCE_AND_OPPOSITION_PHASE:
        return R.color.green2;
      case REGISTERED:
        return R.color.green;
      case EXPIRED:
        return R.color.greyLight;
      default:
        return R.color.white;
    }
  }
}
