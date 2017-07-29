package govhack.thecodefather.patentpending.data.models;

import android.support.annotation.NonNull;
import com.google.common.base.Predicate;
import com.google.common.collect.FluentIterable;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Ordering;
import com.google.gson.annotations.SerializedName;
import govhack.thecodefather.patentpending.data.enums.PatentType;
import govhack.thecodefather.patentpending.utility.ValidationUtililty;
import java.util.List;
import javax.annotation.Nullable;
import lombok.Getter;
import lombok.val;

/**
 * Created by andrewkevin on 29/7/17.
 */

public class PatentDataModel {

  @Getter
  @SerializedName("tradeMarkNumber")
  private Integer tradeMarkNumber;
  @Getter
  @SerializedName("title")
  private String title;
  @SerializedName("type")
  private PatentType patentType;
  @SerializedName("applicantName")
  @Getter
  private String applicantName;
  @SerializedName("stages")
  private List<StageDataModel> stages;

  @NonNull
  public PatentType getPatentType() {
    return ValidationUtililty.withDefault(patentType, PatentType.UNKNOWN_PATENT_TYPE);
  }

  @NonNull
  public ImmutableList<StageDataModel> getStages() {
    val nonNullStages = ValidationUtililty.withDefault(stages);
    return FluentIterable.from(nonNullStages).toSortedList(Ordering.<StageDataModel>natural());
  }

  @NonNull
  public StageDataModel getCurrentStage() {
    return FluentIterable.from(getStages())
        .firstMatch(new Predicate<StageDataModel>() {
          @Override
          public boolean apply(@Nullable StageDataModel input) {
            return input.getFinished() == null;
          }
        }).get();
  }
}
