package govhack.thecodefather.patentpending.data.enums;

import com.google.gson.annotations.SerializedName;
import govhack.thecodefather.patentpending.data.GsonSerialisable;

/**
 * Created by andrewkevin on 29/7/17.
 */

public enum StageName implements GsonSerialisable {
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
}
