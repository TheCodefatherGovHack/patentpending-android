package govhack.thecodefather.patentpending.data.models;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.google.common.base.Predicate;
import com.google.common.collect.FluentIterable;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Ordering;
import com.google.gson.annotations.SerializedName;

import java.util.List;

import govhack.thecodefather.patentpending.data.enums.PatentStage;
import govhack.thecodefather.patentpending.data.enums.PatentType;
import govhack.thecodefather.patentpending.utility.FormatterUtility;
import govhack.thecodefather.patentpending.utility.ValidationUtililty;
import lombok.Getter;
import lombok.val;

/**
 * Created by andrewkevin on 29/7/17.
 */
public class PatentDataModel {
    @Getter
    @SerializedName("tradeMarkNumber")

    private int tradeMarkNumber;
    @Getter
    @SerializedName("australianApplicationNumber")
    private String australianApplicationNumber;

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
    public StageDataModel getCurrentStageModel() {
        return FluentIterable.from(getStages())

                .firstMatch(new Predicate<StageDataModel>() {
                    @Override
                    public boolean apply(@javax.annotation.Nullable StageDataModel input) {
                        return input != null && input.getDateFinished() == null;
                    }
                }).get();
    }

    @NonNull
    public PatentStage getCurrentStage() {
        return getCurrentStageModel().getStage();
    }

    public boolean hasNextStage() {
        return getCurrentStage().hasNextStage();
    }

    @Nullable
    public StageDataModel getNextStageModel() {
        return FluentIterable.from(stages)
                .firstMatch(new Predicate<StageDataModel>() {
                    @Override
                    public boolean apply(@javax.annotation.Nullable StageDataModel input) {
                        return input != null
                                && hasNextStage()
                                && input.getStage() == getCurrentStage().nextStage();
                    }
                }).orNull();
    }

    @NonNull
    public String nextStageDisplayString() {
        if (hasNextStage()) {
            val nextStageModel = getNextStageModel();
            assert nextStageModel != null;
            assert nextStageModel.getEstimatedDateOfFinish() != null;
            return FormatterUtility.formatNextStageApproval(nextStageModel.getStage(),
                    nextStageModel.getEstimatedDateOfFinish());
        } else {
            return "";
        }
    }
}
