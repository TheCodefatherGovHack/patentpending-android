package govhack.thecodefather.patentpending.utility;

import android.support.annotation.Nullable;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import java.lang.reflect.Type;
import org.json.JSONObject;

/**
 * Created by andrewkevin on 29/7/17.
 */

public class JsonUtility {
  /**
   * Converts the Model object into the Json string.
   */
  public static String convertTypeToJson(Object object, Type type) {
    Gson gson = new Gson();
    return gson.toJson(object, type);
  }

  /**
   * Converts the JsonObject to the Model class object whose Type is sent as API parameters
   */
  public static <T> T deserialize(JSONObject jsonObject, Class<T> classType)
      throws JsonSyntaxException {
    return deserialize(jsonObject, classType, new Gson());
  }

  /**
   * Deserialize JSON string to a class
   */
  public static <T> T deserialize(String jsonString, Class<T> typeClass)
      throws JsonSyntaxException {
    return deserialize(jsonString, typeClass, new Gson());
  }

  /**
   * Deserialises jsonObject
   *
   * @param gson gson object to use for deserialisation
   */
  public static <T> T deserialize(JSONObject jsonObject, Class<T> classType, @Nullable Gson gson)
      throws JsonSyntaxException {
    return deserialize(jsonObject.toString(), classType, gson);
  }

  public static <T> T deserialize(String jsonString, Class<T> typeClass, @Nullable Gson gson)
      throws JsonSyntaxException {
    if (gson == null) {
      gson = new Gson();
    }

    return gson.fromJson(jsonString, typeClass);
  }
}
