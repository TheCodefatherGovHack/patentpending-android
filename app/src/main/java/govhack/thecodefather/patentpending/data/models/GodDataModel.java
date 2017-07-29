package govhack.thecodefather.patentpending.data.models;

import com.google.common.collect.ImmutableList;
import com.google.gson.annotations.SerializedName;
import govhack.thecodefather.patentpending.utility.ValidationUtililty;
import java.util.List;
import lombok.Getter;

/**
 * Created by andrewkevin on 29/7/17.
 */

@Getter
public class GodDataModel {

  @SerializedName("message")
  private List<PatentDataModel> patents;

  public ImmutableList<PatentDataModel> getPatents () {
    return ValidationUtililty.asImmutable(patents);
  }
}
