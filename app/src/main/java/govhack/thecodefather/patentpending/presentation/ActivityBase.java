package govhack.thecodefather.patentpending.presentation;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import govhack.thecodefather.patentpending.utility.FontUtility;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.UiThread;

/**
 * Created by andrewkevin on 29/7/17.
 */
@EActivity
public class ActivityBase extends AppCompatActivity {

  @Override
  protected void attachBaseContext(Context newBase) {
    super.attachBaseContext(FontUtility.wrapContext(newBase));
  }

  @UiThread
  void showSnackbar(@NonNull View v, @NonNull String s) {
    showSnackbar(v, s, Snackbar.LENGTH_LONG);
  }

  @UiThread
  void showSnackbar(@NonNull View v, @NonNull String s, int duration) {
    Snackbar snackbar = Snackbar.make(v, s, duration);
    snackbar.show();
  }
}
