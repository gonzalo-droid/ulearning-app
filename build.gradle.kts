// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    id("com.android.application") version "8.1.0" apply false
    id("org.jetbrains.kotlin.android") version "1.8.0" apply false

    id("com.google.dagger.hilt.android") version "2.48" apply false

    id("androidx.navigation.safeargs.kotlin") version "2.7.1" apply false
    id("org.jlleitschuh.gradle.ktlint") version "12.1.0"

    id("com.google.gms.google-services") version "4.3.15" apply false
    id("com.google.firebase.appdistribution") version "4.0.0" apply false

    id("com.google.protobuf") version "0.9.4" apply false

}
buildscript {
    repositories {
        google()  // Google's Maven repository
        mavenCentral()  // Maven Central repository
    }

    dependencies {
        classpath("com.google.firebase:firebase-crashlytics-gradle:2.9.9")
    }
}



/*
buildscript {
    repositories {
        google()
        mavenCentral()
    }

    val splashVersion        by extra("1.0.0")
    val recyclerviewVersion  by extra("1.2.1")
    val recyclerviewSelectionVersion  by extra("1.1.0")
    val androidCoreVersion  by extra("1.7.0")
    val appCompatVersion    by extra("1.4.1")
    val materialVersion     by extra("1.5.0")
    val constraintLyoutVersion      : by extra("2.1.3")
    val legacySupportVersion        : by extra("1.0.0")
    val livedataVersion     by extra("2.4.1")
    val viewmodelVersion    by extra("2.4.1")
    val navigationVersion   by extra("2.5.1")
    val runtimeVersion      by extra("2.4.1")
    val hiltVersion         by extra("2.41")
    val hiltAnotationProcesorVersion: by extra("1.0.0")
    val dataStoreVersion    by extra("1.0.0")
    val coroutinesVersion   by extra("1.6.0")
    val retrofitVersion     by extra("2.9.0")
    val retrofitgsonVersion by extra("2.9.0")
    val gsonVersion         by extra("2.8.8")
    val okhttpInterceptorVersion    : by extra("4.9.3")
    val mapsVersion         by extra("18.0.2")
    val glideVersion        by extra("4.13.0")
    val frescoVersion        by extra("2.6.0")
    val roomVersion         by extra("2.4.2")
    val pinviewVersion         by extra("1.4.4")
    val protoDataStoreVersion         by extra("1.0.0")
    val protobufVersion         by extra("3.18.0")


    ext {


        room = [
                room        : "androidx.room:room-ktx:$versions.room",
                roomCompiler: "androidx.room:room-compiler:$versions.room"
        ]

        maps = [
                maps: "com.google.android.gms:play-services-maps:$versions.maps",

        ]

        navigation = [
                //NAVIGATION
                navigationUi      : "androidx.navigation:navigation-ui-ktx:$versions.navigation",
                navigationFragment: "androidx.navigation:navigation-fragment-ktx:$versions.navigation",

                navigationSupport : "androidx.navigation:navigation-dynamic-features-fragment:$versions.navigation",

                navigationTest    : "androidx.navigation:navigation-testing::$versions.navigation",
        ]

        daggerHilt = [
                hilt                  : "com.google.dagger:hilt-android:$versions.hilt",
                compiler              : "com.google.dagger:hilt-android-compiler:$versions.hilt",
                annotationProcessor   : "androidx.hilt:hilt-compiler:$versions.hiltAnotationProcesor",
                hiltLifecycleViewmodel: "androidx.hilt:hilt-lifecycle-viewmodel:$versions.hiltLifecycleViewmodel",
        ]

        retrofit = [
                //RETROFIT
                retrofit         : "com.squareup.retrofit2:retrofit:$versions.retrofit",
                retrofitgson     : "com.squareup.retrofit2:converter-gson:$versions.retrofitgson",
                gson             : "com.google.code.gson:gson:$versions.gson",
                okhttpInterceptor: "com.squareup.okhttp3:logging-interceptor:$versions.okhttpInterceptor",
        ]

        others = [
                fresco: "com.facebook.fresco:fresco:$versions.fresco",

                glide : "com.github.bumptech.glide:glide:$versions.glide",
                pinview: "io.github.chaosleung:pinview:$versions.pinview"
        ]

    }

    dependencies {
        classpath("androidx.navigation:navigation-safe-args-gradle-plugin:$versions.navigation")
        classpath "com.google.dagger:hilt-android-gradle-plugin:$versions.hilt"
        classpath "org.jlleitschuh.gradle:ktlint-gradle:10.3.0"
        classpath 'com.android.tools.build:gradle:3.4.0'
        classpath 'com.google.gms:google-services:4.3.13'
        classpath 'com.google.firebase:firebase-crashlytics-gradle:2.9.1'
        classpath 'com.google.firebase:firebase-appdistribution-gradle:3.0.3'
    }

}

plugins {
    id("com.android.application") version '7.1.3' apply false
    id("com.android.library") version '7.1.3' apply false
    id("org.jetbrains.kotlin.android") version '1.6.21' apply false
    id("com.google.android.libraries.mapsplatform.secrets-gradle-plugin") version '2.0.0' apply false
    id("org.jetbrains.kotlin.jvm") version '1.6.20' apply false
}

task clean(type: Delete) {
    delete rootProject.buildDir
}

*/