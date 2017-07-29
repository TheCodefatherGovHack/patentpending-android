package govhack.thecodefather.patentpending;

import android.app.Application;
import govhack.thecodefather.patentpending.utility.FontUtility;

/**
 * Created by andrewkevin on 29/7/17.
 */

public class PatentPendingApp extends Application {

  @Override
  public void onCreate() {
    super.onCreate();

    FontUtility.setUpDefaultFont(getString(R.string.font_path_open_sans_regular));
  }
}
