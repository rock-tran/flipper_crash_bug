# Parceler configuration
-keep interface org.parceler.Parcel
-keep @org.parceler.Parcel class * { *; }
-keep class **$$Parcelable { *; }
-keep class org.parceler.Parceler$$Parcels
-keep class * implements android.os.Parcelable {
  public static final android.os.Parcelable$Creator *;
}