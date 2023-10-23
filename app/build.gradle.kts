plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("kotlin-kapt")
    id("dagger.hilt.android.plugin")
    id("androidx.navigation.safeargs")
    id("org.jlleitschuh.gradle.ktlint")
    id("com.google.protobuf") version "0.8.18"
    id("com.google.gms.google-services")
    id("com.google.firebase.crashlytics")
    id("com.google.firebase.appdistribution")
}




android {
    compileSdkVersion = 33

    defaultConfig {
        applicationId = 'com.ulearning.ulearning_app'
        minSdkVersion = 21
        targetSdkVersion = 33
        versionCode = 15
        versionName = "1.1.5"
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            minifyEnabled = false
            debuggable = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
        debug {
            minifyEnabled = false
            debuggable = true
        }

    }


    flavorDimensions("versionFlavor")

    productFlavors {
        create("dev") {
            dimension = "versionFlavor"
            buildConfigField("boolean", "IS_SHOW_LOG", "true")
            buildConfigField("boolean", "DEBUG", "true")
            buildConfigField("String", "BASE_URL", "\"https://www.sandbox.api.ulearning.com.pe/api/\"")
            buildConfigField("String", "STUDENT_URL", "\"https://student.ulearning.com.pe\"")
        }

        create("prod") {
            dimension = "versionFlavor"
            buildConfigField("boolean", "IS_SHOW_LOG", "false")
            buildConfigField("boolean", "DEBUG", "false")
            buildConfigField("String", "BASE_URL", "\"https://api.ulearning.com.pe/api/\"")
            buildConfigField("String", "STUDENT_URL", "\"https://student.ulearning.com.pe\"")
        }
    }

    // email : "one_student@gmail.com"
    // password : "ulearning2022"


    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    viewBinding {
        enabled = true
    }
    dataBinding {
        enabled = true
    }
    kotlinOptions {
        jvmTarget = '1.8'
    }

    ktlint {
        debug.set(true)
        disabledRules.set(["no-wildcard-imports"])
    }
    buildFeatures {
        viewBinding = true
    }
}

dependencies {


    implementation("androidx.core:core-splashscreen:${extra["splashVersion"]}")
    implementation("androidx.core:core-ktx:${extra["androidCoreVersion"]}")
    implementation("androidx.appcompat:appcompat:${extra["appCompatVersion"]}")
    implementation("com.google.android.material:material:${extra["materialVersion"]}")
    implementation("androidx.constraintlayout:constraintlayout:${extra["constraintLyoutVersion"]}")
    implementation("androidx.legacy:legacy-support-v4:${extra["legacySupportVersion"]}")
    implementation("androidx.lifecycle:lifecycle-livedata-ktx:${extra["livedataVersion"]}")
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:${extra["viewmodelVersion"]}")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:${extra["runtimeVersion"]}")

// Kotlin Coroutines
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:${extra["coroutinesVersion"]}")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:${extra["coroutinesVersion"]}")
    implementation("androidx.datastore:datastore-preferences:${extra["dataStoreVersion"]}")
    implementation("androidx.datastore:datastore-core:${extra["protoDataStoreVersion"]}")
    implementation("com.google.protobuf:protobuf-javalite:${extra["protobufVersion"]}")
    implementation("androidx.recyclerview:recyclerview:${extra["recyclerviewVersion"]}")
    implementation("androidx.recyclerview:recyclerview-selection:${extra["recyclerviewSelectionVersion"]}")




    implementation("io.github.chaosleung:pinview:$versions.pinview")

    //NAVIGATION COMPONENT
    implementation("androidx.navigation:navigation-ui-ktx:$versions.navigation")
    implementation("androidx.navigation:navigation-fragment-ktx:$versions.navigation")
    implementation("com.google.android.gms:play-services-vision:20.1.3")
    implementation("androidx.paging:paging-runtime:3.1.1")
    // Retrofit
    implementation("com.squareup.retrofit2:retrofit:$versions.retrofit"
    implementation("com.squareup.retrofit2:converter-gson:$versions.retrofitgson"
    implementation("com.google.code.gson:gson:$versions.gson"
    implementation("com.squareup.okhttp3:logging-interceptor:$versions.okhttpInterceptor"
    implementation("com.localebro:okhttpprofiler:1.0.8"

    // Glide
    implementation 'com.github.bumptech.glide:glide:4.14.1'
    kapt 'com.github.bumptech.glide:compiler:4.14.1'



    testImplementation 'junit:junit:4.12'
    testImplementation 'org.junit.jupiter:junit-jupiter'



    // https://medium.com/geekculture/shimmer-effect-in-android-2b6840cc0097
    implementation 'com.facebook.shimmer:shimmer:0.5.0'

    // login firebase
    // Import the BoM for the Firebase platform
    implementation platform('com.google.firebase:firebase-bom:31.1.0')

    // Add the dependency for the Firebase Authentication library
    // When using the BoM, you don't specify versions in Firebase library dependencies
    implementation 'com.google.firebase:firebase-auth-ktx'

    // Also add the dependency for the Google Play services library and specify its version
    implementation("com.google.android.gms:play-services-auth:20.4.0'

    // facebook
    //implementation("com.facebook.android:facebook-android-sdk:[4,5)'
    implementation("com.facebook.android:facebook-login:latest.release'


    //HILT
    implementation("com.google.dagger:hilt-android:$versions.hilt")
    implementation("androidx.preference:preference:1.1.1")
    implementation("com.google.firebase:firebase-crashlytics-ktx:18.3.2")
    implementation("com.google.firebase:firebase-analytics-ktx:21.2.0")
    implementation("com.google.firebase:firebase-messaging-ktx:23.1.0")
    kapt "com.google.dagger:hilt-android-compiler:$versions.hilt"
    annotationProcessor "androidx.hilt:hilt-compiler:$versions.hiltAnotationProcesor"

    //MAPS
    implementation("com.google.android.gms:play-services-maps:$versions.maps"

    implementation("androidx.viewpager2:viewpager2:1.0.0")
    implementation("com.github.PhilJay:MPAndroidChart:v3.1.0")
    implementation("androidx.annotation:annotation:1.3.0")


    implementation("com.google.android.material:material:1.5.0")


    //kotlin coroutines test
    /**
     * kotlinx-coroutines-test: Esta biblioteca se utiliza para probar las funciones asincrónicas
     * que se implementan con Kotlin Coroutines en Android. Por ejemplo, si tienes una función
     * que usa Coroutines para hacer una llamada a la red y deseas probarla,
     * puedes usar kotlinx-coroutines-test para escribir pruebas para esa función. Ejemplo:*/
    testImplementation 'org.jetbrains.kotlinx:kotlinx-coroutines-test:1.6.4'

    /**
     * Hamcrest: Hamcrest es una biblioteca de aserciones para pruebas unitarias en Java y Kotlin.
     * Puedes usar Hamcrest para escribir aserciones más expresivas en tus pruebas unitarias*/
    testImplementation 'org.hamcrest:hamcrest:2.2'

    /**
     * androidx.arch.core:core-testing: Esta biblioteca se utiliza para probar los componentes
     * de Arquitectura de Android, como LiveData y ViewModel. Con esta biblioteca, puedes simular
     * cambios de datos y observar cómo los componentes de Arquitectura reaccionan a esos cambios*/
    testImplementation 'androidx.arch.core:core-testing:2.2.0'

    /**
     * com.squareup.okhttp3:mockwebserver: Esta biblioteca se utiliza para simular respuestas de
     * red en pruebas unitarias de Android. Por ejemplo, si tienes una función que hace una llamada
     * a una API, puedes usar mockwebserver para simular la respuesta de esa API en tus pruebas unitarias*/
    testImplementation 'com.squareup.okhttp3:mockwebserver:4.9.3'

    /**
     * io.mockk:mockk: Esta biblioteca se utiliza para crear objetos simulados en pruebas unitarias
     * de Kotlin. Puedes usar mockk para simular objetos que dependen de otros objetos o
     * para simular interacciones de usuario en tus pruebas unitarias.*/
    testImplementation "io.mockk:mockk:1.12.5"

    /**
     * org.mockito:mockito-core: Esta biblioteca se utiliza para crear objetos simulados en
     * pruebas unitarias de Java y Kotlin. Puedes usar Mockito para simular objetos que dependen
     * de otros objetos o para simular interacciones de usuario en tus pruebas unitari*/
    testImplementation 'org.mockito:mockito-core:4.2.0'

    /**
     * This is a library that provides Kotlin extensions and functions to use JUnit 4 or JUnit 5
     * in Android tests. The version being used in this project is 1.1.5.*/
    testImplementation "androidx.test.ext:junit-ktx:1.1.5"
    testImplementation 'junit:junit:4.13.2'
    androidTestImplementation 'androidx.test.ext:junit:1.1.5'


    /**
     * This is a library that provides Kotlin extensions and functions for
     * testing Android components and interactions with the Android framework.
     * The version being used in this project is 1.5.0.
     */
    testImplementation "androidx.test:core-ktx:1.5.0"

    /**
     * This is a library that allows running Android unit tests on the JVM without the need
     * for an emulator or device. It provides a simulated Android environment for tests.
     */
    testImplementation "org.robolectric:robolectric:4.7.3"




    androidTestImplementation 'androidx.test.espresso:espresso-core:3.5.1'

}

protobuf {
    protoc {
        artifact = "com.google.protobuf:protoc:3.18.0"
    }

    generateProtoTasks {
        all().each { task ->
            task.builtins {
                java {
                    option 'lite'
                }
            }
        }
    }
}