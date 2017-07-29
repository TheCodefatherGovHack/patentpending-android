package govhack.thecodefather.patentpending.data.models;

import com.google.gson.annotations.SerializedName;
import govhack.thecodefather.patentpending.data.enums.PatentType;
import java.util.List;
import lombok.Getter;

/**
 * Created by andrewkevin on 29/7/17.
 */

@Getter
public class PatentDataModel {
  @SerializedName("tradeMarkNumber")
  private Integer tradeMarkNumber;
  @SerializedName("title")
  private String title;
  @SerializedName("type")
  private PatentType patentType;
  @SerializedName("applicantName")
  private String applicantName;
  @SerializedName("stages")
  private List<StageDataModel> stages;
}
