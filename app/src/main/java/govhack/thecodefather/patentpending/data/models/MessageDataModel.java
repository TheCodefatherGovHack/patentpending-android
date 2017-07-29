package govhack.thecodefather.patentpending.data.models;

import com.google.gson.annotations.SerializedName;
import java.util.List;
import lombok.Getter;

/**
 * Created by andrewkevin on 29/7/17.
 */

@Getter
public class MessageDataModel {
  @SerializedName("applicationId")
  private Integer applicationId;
  @SerializedName("title")
  private String title;
  @SerializedName("type")
  private String type;
  @SerializedName("applicantName")
  private String applicantName;
  @SerializedName("stages")
  private List<StageDataModel> stages;
}
