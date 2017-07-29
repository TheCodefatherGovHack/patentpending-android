package govhack.thecodefather.patentpending.utility;

import android.content.Context;
import android.content.ContextWrapper;
import govhack.thecodefather.patentpending.R;
import uk.co.chrisjenx.calligraphy.CalligraphyConfig;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

/**
 * Created by andrewkevin on 29/7/17.
 */

public class FontUtility {

  static final String TAG = FontUtility.class.getName();

  public static void setUpDefaultFont(String fontPath) {
    CalligraphyConfig.initDefault(new CalligraphyConfig.Builder()
        .setDefaultFontPath(fontPath)
        .setFontAttrId(R.attr.fontPath)
        .build()
    );
  }

  public static ContextWrapper wrapContext(Context context) {
    return CalligraphyContextWrapper.wrap(context);
  }
}
