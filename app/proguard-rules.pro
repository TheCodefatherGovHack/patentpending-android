# Add project specific ProGuard rules here.
# By default, the flags in this file are appended to flags specified
# in /Users/andrewkevin/Library/Android/sdk/tools/proguard/proguard-android.txt
# You can edit the include path and order by changing the proguardFiles
# directive in build.gradle.
#
# For more details, see
#   http://developer.android.com/guide/developing/tools/proguard.html

# Add any project specific keep options here:

# If your project uses WebView with JS, uncomment the following
# and specify the fully qualified class name to the JavaScript interface
# class:
#-keepclassmembers class fqcn.of.javascript.interface.for.webview {
#   public *;
#}

# Uncomment this to preserve the line number information for
# debugging stack traces.
#-keepattributes SourceFile,LineNumberTable

# If you keep the line number information, uncomment this to
# hide the original source file name.
#-renamesourcefileattribute SourceFile

# keep GsonSerializable interface, it would be thrown away by proguard since it is empty
-keep interface govhack.thecodefather.data.models.GsonSerialisable
# Ensure we keep member names of classes implementing GsonSerialisable
-keepclassmembers enum * implements govhack.thecodefather.data.models.GsonSerialisable {
    <fields>;
}

# We only want obfuscation
-keepattributes InnerClasses
-keepattributes Signature
-keepattributes Exceptions
-keepattributes *Annotation*
-keepattributes EnclosingMethod

# Retrofit 2.X
## https://square.github.io/retrofit/ ##
-dontwarn retrofit2.**
-keep class retrofit2.** { *; }
-keepattributes Signature
-keepattributes Exceptions

-keepclasseswithmembers class * {
    @retrofit2.http.* <methods>;
}

## joda-time-android 2.8.0
# This is only necessary if you are not including the optional joda-convert dependency
-dontwarn org.joda.convert.FromString
-dontwarn org.joda.convert.ToString