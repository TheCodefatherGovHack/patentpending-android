package govhack.thecodefather.patentpending.utility;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableListMultimap;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by andrewkevin on 29/7/17.
 */

public class ValidationUtililty {

  @NonNull
  public static <T> T withDefault(@Nullable T tVal, @NonNull T defaultVal) {
    if (tVal == null) {
      return defaultVal;
    } else {
      return tVal;
    }
  }

  @NonNull
  public static <T> List<T> withDefault(@Nullable List<T> tList) {
    return withDefault(tList, new ArrayList<T>(0));
  }

  @NonNull
  public static <T> ImmutableList<T> withDefault(@Nullable ImmutableList<T> tList) {
    //noinspection ConstantConditions
    return withDefault(tList, ImmutableList.<T>of());
  }

  @NonNull
  public static <T> ImmutableSet<T> withDefault(@Nullable ImmutableSet<T> tImmutableSet) {
    //noinspection ConstantConditions
    return withDefault(tImmutableSet, ImmutableSet.<T>of());
  }

  public static <T, V> ImmutableListMultimap<T, V> withDefault(@Nullable
      ImmutableListMultimap<T, V> tvImmutableListMultimap) {
    //noinspection ConstantConditions
    return withDefault(tvImmutableListMultimap, ImmutableListMultimap.<T, V>of());
  }

  @NonNull
  public static String withDefault(@Nullable String s) {
    return withDefault(s, "");
  }

  @NonNull
  public static <K, V> ImmutableMap<K, V> withDefault(@Nullable ImmutableMap<K, V> kvImmutableMap) {
    return withDefault(kvImmutableMap, ImmutableMap.<K, V>of());
  }

  public static <T> ImmutableList<T> asImmutable(List<T> messages) {
    return ImmutableList.copyOf(withDefault(messages));
  }
}
