package govhack.thecodefather.patentpending.utility;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import org.joda.time.DateTime;
import org.joda.time.Days;

import govhack.thecodefather.patentpending.data.enums.PatentStage;
import lombok.val;

/**
 * Created by andrewkevin on 29/7/17.
 */

public class FormatterUtility {

    @NonNull
    public static String formatNextStageApproval(@NonNull PatentStage stage,
                                                 @Nullable DateTime estimatedDateOfFinish) {
        if (null != estimatedDateOfFinish) {
            val daysRemaining = Days.daysBetween(DateTime.now(), estimatedDateOfFinish);
            return String.format("%1 days to %2", daysRemaining, stage.name());
        } else {
            return "Estimate not available.";
        }

    }
}
