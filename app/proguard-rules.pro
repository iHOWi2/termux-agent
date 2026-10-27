# Keep kotlinx.serialization generated serializers
-keepattributes *Annotation*, InnerClasses
-dontnote kotlinx.serialization.**
-keepclassmembers class com.ihowi.termuxagent.** {
    *** Companion;
    kotlinx.serialization.KSerializer serializer(...);
}
