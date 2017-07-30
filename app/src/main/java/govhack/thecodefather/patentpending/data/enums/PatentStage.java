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
    PatentStage result = null;
    switch (this) {
      case FILED:
        result = WAITING_FOR_CONFIRMATION;
        break;
      case WAITING_FOR_CONFIRMATION:
        result = BEING_EXAMINED;
        break;
      case ACCEPTANCE_AND_OPPOSITION_PHASE:
        result = REGISTERED;
        break;
    }
    return result;
  }

  public boolean hasNextStage() {
    return nextStage() != null;
  }

  public boolean isUnknown() {
    return this == UNKNOWN_STAGE_NAME;
  }

  @Override
  public String toString() {
    switch (this) {
      case FILED:
        return "Filed";
      case ACCEPTANCE_AND_OPPOSITION_PHASE:
        return "Acceptance and Opposition Phase";
      case BEING_EXAMINED:
        return "Being Examined";
      case EXPIRED:
        return "Expired";
      case REGISTERED:
        return "Registered";
      case WAITING_FOR_CONFIRMATION:
        return "Waiting For Confirmation";
      default:
      case UNKNOWN_STAGE_NAME:
        return "UNKNOWN";
    }
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
