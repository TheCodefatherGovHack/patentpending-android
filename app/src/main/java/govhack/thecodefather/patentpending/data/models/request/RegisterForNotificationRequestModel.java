package govhack.thecodefather.patentpending.data.models.request;

import android.support.annotation.NonNull;
import com.google.gson.annotations.SerializedName;
import govhack.thecodefather.patentpending.data.models.PatentDataModel;
import govhack.thecodefather.patentpending.data.models.StageDataModel;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Created by andrewkevin on 29/7/17.
 */

@Getter
@AllArgsConstructor
public class RegisterForNotificationRequestModel {

  @SerializedName("tradeMarkNumber")
  private Integer tradeMarkNumber;
  @SerializedName("australianApplicationNumber")
  private String australianApplicationNumber;
  @SerializedName("emailAddress")
  private String emailAddress;
//  @SerializedName("deviceUUID")
//  private String deviceUUID;
  @SerializedName("lastNotifiedState")
  private List<StageDataModel> stages;

  public static RegisterForNotificationRequestModel build(
      @NonNull String emailAddress,
      @NonNull PatentDataModel patentDataModel) {
    return new RegisterForNotificationRequestModel(
        patentDataModel.getTradeMarkNumber(),
        patentDataModel.getAustralianApplicationNumber(),
        emailAddress,
        patentDataModel.getStages()
    );
  }
}
