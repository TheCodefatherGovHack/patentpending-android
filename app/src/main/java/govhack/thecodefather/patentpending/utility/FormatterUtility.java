package govhack.thecodefather.patentpending.utility;

import android.support.annotation.NonNull;
import govhack.thecodefather.patentpending.data.enums.PatentStage;
import lombok.val;
import org.joda.time.DateTime;
import org.joda.time.Days;

/**
 * Created by andrewkevin on 29/7/17.
 */

public class FormatterUtility {

  @NonNull
  public static String formatNextStageApproval(@NonNull PatentStage stage,
      @NonNull DateTime estimatedDateOfFinish) {
    val daysRemaining = Days.daysBetween(DateTime.now(), estimatedDateOfFinish);
    return String.format("%1 days to %2", daysRemaining, stage.name());
  }
}
