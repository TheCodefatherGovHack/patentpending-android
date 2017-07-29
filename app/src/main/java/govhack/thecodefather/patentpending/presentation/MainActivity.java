package govhack.thecodefather.patentpending.presentation;

import static android.support.design.widget.Snackbar.make;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.widget.SearchView;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import govhack.thecodefather.patentpending.R;
import govhack.thecodefather.patentpending.data.api.ApiClientFactory;
import govhack.thecodefather.patentpending.data.api.HttpCallback;
import govhack.thecodefather.patentpending.data.models.GodDataModel;
import lombok.val;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.Fullscreen;
import org.androidannotations.annotations.UiThread;
import org.androidannotations.annotations.ViewById;
import retrofit2.Call;
import retrofit2.Response;

@Fullscreen
@EActivity(R.layout.activity_main)
public class MainActivity extends ActivityBase {

  @ViewById(R.id.root_view)
  CoordinatorLayout rootView;
  @ViewById(R.id.progress_overlay)
  FrameLayout progressOverlay;

  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    val inflater = getMenuInflater();
    inflater.inflate(R.menu.main_search_view, menu);
    MenuItem searchViewItem = menu.findItem(R.id.action_search);

    val searchViewAndroidActionBar = (SearchView) MenuItemCompat.getActionView(searchViewItem);

    searchViewAndroidActionBar.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
      @Override
      public boolean onQueryTextSubmit(String query) {
        submitSearchRequest(query);
        searchViewAndroidActionBar.clearFocus();
        return true;
      }

      @Override
      public boolean onQueryTextChange(String newText) {
        return false;
      }
    });

    return super.onCreateOptionsMenu(menu);
  }

  private void submitSearchRequest(@Nullable String query) {
    if (!TextUtils.isEmpty(query)) {
      isProgressOverlayVisible(true);
      ApiClientFactory.getPatentPending().getGod(query).enqueue(new HttpCallback<GodDataModel>() {
        @Override
        public void onSuccess(Call<GodDataModel> call, Response<GodDataModel> response) {
          // TODO update recycler view
        }

        @Override
        public void onError(Call<GodDataModel> call, Response<GodDataModel> response) {
          showSnackbar("Uh-oh an error occurred, please retry");
        }

        @Override
        public void onFailure(Call<GodDataModel> call) {
          showSnackbar("Uh-oh an error occurred, please retry");
        }

        @Override
        protected void always() {
          isProgressOverlayVisible(false);
        }
      });
    }
  }

  @UiThread
  void isProgressOverlayVisible(boolean b) {
    progressOverlay.setVisibility(b ? View.VISIBLE : View.GONE);
  }

  private void showSnackbar(@NonNull String s) {
    showSnackbar(s, Snackbar.LENGTH_LONG);
  }

  private void showSnackbar(@NonNull String s, int duration) {
    Snackbar snackbar = make(rootView, s, duration);
    snackbar.show();
  }
}
