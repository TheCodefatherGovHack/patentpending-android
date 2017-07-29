package govhack.thecodefather.patentpending.presentation;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import govhack.thecodefather.patentpending.utility.FontUtility;

/**
 * Created by andrewkevin on 29/7/17.
 */

public class ActivityBase extends AppCompatActivity {

  @Override
  protected void attachBaseContext(Context newBase) {
    super.attachBaseContext(FontUtility.wrapContext(newBase));
  }
}
