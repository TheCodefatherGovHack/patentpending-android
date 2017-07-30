package govhack.thecodefather.patentpending.utility;

import android.content.Context;
import android.os.Build;
import android.support.annotation.ColorInt;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;

/**
 * Created by andrewkevin on 30/7/17.
 */

public class ColorUtility {

  @ColorInt
  public static int getColor(@NonNull Context context, @NonNull int colorResId) {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
      return context.getColor(colorResId);
    } else {
      return ContextCompat.getColor(context, colorResId);
    }
  }
}
