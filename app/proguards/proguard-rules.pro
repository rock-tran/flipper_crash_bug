# Add project specific ProGuard rules here.
# By default, the flags in this file are appended to flags specified
# in /mnt/DA1E23A91E237D9F/Ubuntu/Android/Sdk/tools/proguard/proguard-android.txt
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
# This is a configuration file for ProGuard.
# http://proguard.sourceforge.net/index.html#manual/usage.html

# Optimizations: If you don't want to optimize, use the
# proguard-android.txt configuration file instead of this one, which
# turns off the optimization flags.  Adding optimization introduces
# certain risks, since for example not all optimizations performed by
# ProGuard works on all versions of Dalvik.  The following flags turn
# off various optimizations known to have issues, but the list may not
# be complete or up to date. (The "arithmetic" optimization can be
# used if you are only targeting Android 2.0 or later.)  Make sure you
# test thoroughly if you go this route.

-optimizations !code/simplification/arithmetic,!code/simplification/cast,!field/*,!class/merging/*
-optimizationpasses 5
-allowaccessmodification
-dontpreverify

# The remainder of this file is identical to the non-optimized version
# of the Proguard configuration file (except that the other file has
# flags to turn off optimization).

-dontusemixedcaseclassnames
-dontskipnonpubliclibraryclasses
-verbose

-dontwarn javax.xml.**
-keepattributes *Annotation*
-keep public class com.google.vending.licensing.ILicensingService
-keep public class com.android.vending.licensing.ILicensingService

# For native methods, see http://proguard.sourceforge.net/manual/examples.html#native
-keepclasseswithmembernames class * {
    native <methods>;
}

# keep setters in Views so that animations can still work.
# see http://proguard.sourceforge.net/manual/examples.html#beans
-keepclassmembers public class * extends android.view.View {
   void set*(***);
   *** get*();
}

# We want to keep methods in Activity that could be used in the XML attribute onClick
-keepclassmembers class * extends android.app.Activity {
   public void *(android.view.View);
}

# For enumeration classes, see http://proguard.sourceforge.net/manual/examples.html#enumerations
-keepclassmembers enum * {
    public static **[] values();
    public static ** valueOf(java.lang.String);
}

-keep class * implements java.io.Serializable

-keep class * implements android.os.Parcelable {
  public static final android.os.Parcelable$Creator *;
}

-keepclassmembers class **.R$* {
    public static <fields>;
}

-keepattributes SourceFile,LineNumberTable

-dontusemixedcaseclassnames
-dontskipnonpubliclibraryclasses
-verbose

-keep class android.support.v4.app.** { *; }
-keep interface android.support.v4.app.** { *; }
-keep class android.support.v7.app.** { *; }
-keep interface android.support.v7.app.** { *; }

-keepattributes Signature

-keep class com.crashlytics.** { *; }
-dontwarn com.crashlytics.**

-dontwarn com.google.errorprone.annotations.**