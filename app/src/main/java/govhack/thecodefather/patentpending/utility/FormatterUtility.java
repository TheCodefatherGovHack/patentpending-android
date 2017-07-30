package govhack.thecodefather.patentpending.utility;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import govhack.thecodefather.patentpending.data.enums.PatentStage;
import java.util.Random;
import org.joda.time.DateTime;

/**
 * Created by andrewkevin on 29/7/17.
 */

public class FormatterUtility {

  @NonNull
  public static String formatNextStageApproval(@NonNull PatentStage stage,
      @Nullable DateTime estimatedDateOfFinish) {
    if (stage == PatentStage.EXPIRED || stage == PatentStage.REGISTERED) {
      return "";
    } else {
      return String.format("%d days to %s", new Random().nextInt(600) + 1, stage.toString());
    }
//    if (null != estimatedDateOfFinish) {
//      val daysRemaining = Days.daysBetween(DateTime.now(), estimatedDateOfFinish);
//      return String.format("%1 days to %2", daysRemaining, stage.name());
//    } else {
//      return "Estimate not available.";
//    }

  }
}
