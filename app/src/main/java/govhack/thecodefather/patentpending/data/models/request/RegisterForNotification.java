package govhack.thecodefather.patentpending.data.models.request;

import com.google.gson.annotations.SerializedName;
import govhack.thecodefather.patentpending.data.enums.PatentType;
import govhack.thecodefather.patentpending.data.models.StageDataModel;
import java.util.List;
import lombok.Getter;

/**
 * Created by andrewkevin on 29/7/17.
 */

public class RegisterForNotification {
  @Getter
  @SerializedName("tradeMarkNumber")
  private Integer tradeMarkNumber;
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
//
//  public static RegisterForNotification build(@NonNull PatentDataModel patentDataModel) {
//
//  }
}
