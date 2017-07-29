package govhack.thecodefather.patentpending.data.models;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import com.google.gson.annotations.SerializedName;
import govhack.thecodefather.patentpending.data.enums.StageName;
import govhack.thecodefather.patentpending.utility.ValidationUtililty;
import lombok.Getter;
import org.joda.time.DateTime;

/**
 * Created by andrewkevin on 29/7/17.
 */
class StageDataModel implements Comparable<StageDataModel> {

  @SerializedName("stageName")
  private StageName stageName;
  @Getter
  @SerializedName("finished")
  private Boolean finished;
  @SerializedName("dateFinished")
  private String dateFinished;
  @SerializedName("estimatedDateOfFinish")
  private String estimatedDateOfFinish;

  @NonNull
  public StageName getStageName() {
    return ValidationUtililty.withDefault(stageName, StageName.UNKNOWN_STAGE_NAME);
  }

  @Nullable
  public DateTime getDateFinished() {
    return TextUtils.isEmpty(dateFinished) ? null : DateTime.parse(dateFinished);
  }

  @Nullable
  public DateTime getEstimatedDateOfFinish() {
    return TextUtils.isEmpty(estimatedDateOfFinish) ? null : DateTime.parse(estimatedDateOfFinish);
  }

  @Override
  public int compareTo(@NonNull StageDataModel stageDataModel) {
    if (finished && stageDataModel.finished) {
      return dateFinished.compareTo(stageDataModel.dateFinished);
    } else if (!finished && !stageDataModel.finished) {
      return estimatedDateOfFinish.compareTo(estimatedDateOfFinish);
    } else if (finished){
      // finished and !stageDataModel.finished
      return -1;
    } else {
      // !finished and stageDataModel.finished
      return 1;
    }
  }
}
