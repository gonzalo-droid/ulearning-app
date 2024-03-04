import com.google.protobuf.gradle.id

plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")

    kotlin("kapt")
    id("com.google.dagger.hilt.android")

    id("androidx.navigation.safeargs")
    id("org.jlleitschuh.gradle.ktlint")
    id("com.google.protobuf") version "0.9.4"

    id("com.google.gms.google-services")
    id("com.google.firebase.crashlytics")
    id("com.google.firebase.appdistribution")
}

android {
    namespace = "com.ulearning.ulearning_app"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.ulearning.ulearning_app"
        minSdk = 21
        targetSdk = 34
        versionCode = 15
        versionName = "1.1.5"
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            isDebuggable = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro",
            )
        }
        getByName("debug") {
            isMinifyEnabled = false
            isDebuggable = true
        }
    }

    flavorDimensions("versionFlavor")

    productFlavors {
        create("dev") {
            dimension = "versionFlavor"
            buildConfigField("boolean", "IS_SHOW_LOG", "true")
            buildConfigField("boolean", "DEBUG", "true")
            buildConfigField(
                "String",
                "BASE_URL",
                "\"https://sandbox.api.ulearning.com.pe/api/\"",
            )
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
    buildFeatures {
        viewBinding = true
        buildConfig = true
        dataBinding = true
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }

    ktlint {
        debug.set(true)
        disabledRules.set(listOf("no-wildcard-imports"))
    }
    kapt {
        correctErrorTypes = true
    }
}

dependencies {

    implementation("androidx.activity:activity:1.8.0")
    val constraintLyout = "2.1.3"
    val hilt = "2.41"
    val hiltAnotationProcesor = "1.0.0"
    val coroutines = "1.6.0"

    implementation("androidx.core:core-splashscreen:1.0.1")
    implementation("androidx.core:core-ktx:1.12.0")
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.11.0")
    implementation("androidx.constraintlayout:constraintlayout:$constraintLyout")
    implementation("androidx.legacy:legacy-support-v4:1.0.0")

    implementation("androidx.lifecycle:lifecycle-livedata-ktx:2.4.1")
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.4.1")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.4.1")

    // Kotlin Coroutines
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:$coroutines")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:$coroutines")
    implementation("androidx.recyclerview:recyclerview:1.2.1")
    implementation("androidx.recyclerview:recyclerview-selection:1.1.0")
    // protobuf
    implementation("androidx.datastore:datastore-preferences:1.0.0")
    implementation("androidx.datastore:datastore-core:1.0.0")
    implementation("com.google.protobuf:protobuf-javalite:3.23.0")
    implementation("com.google.protobuf:protobuf-kotlin-lite:3.23.0")
    // navigation
    implementation("androidx.navigation:navigation-ui-ktx:2.5.1")
    implementation("androidx.navigation:navigation-fragment-ktx:2.5.1")
    implementation("com.google.android.gms:play-services-vision:20.1.3")
    implementation("androidx.paging:paging-runtime:3.1.1")
    // Retrofit
    implementation("com.squareup.retrofit2:retrofit:2.9.0")
    implementation("com.squareup.retrofit2:converter-gson:2.9.0")
    implementation("com.google.code.gson:gson:2.10")
    implementation("com.squareup.okhttp3:logging-interceptor:4.10.0")
    implementation("com.localebro:okhttpprofiler:1.0.8")
    // Glide
    implementation("com.github.bumptech.glide:glide:4.14.1")
    kapt("com.github.bumptech.glide:compiler:4.14.1")
    // Shimmer
    implementation("com.facebook.shimmer:shimmer:0.5.0")
    // Firebase
    implementation(platform("com.google.firebase:firebase-bom:32.3.1"))
    implementation("com.google.firebase:firebase-crashlytics-ktx")
    implementation("com.google.firebase:firebase-analytics-ktx")
    implementation("com.google.firebase:firebase-messaging-ktx")
    implementation("com.google.firebase:firebase-auth-ktx")
    implementation("com.google.android.gms:play-services-auth:21.0.0")

    // facebook
    implementation("com.facebook.android:facebook-login:latest.release")
    // HILT
    implementation("com.google.dagger:hilt-android:2.48")
    kapt("com.google.dagger:hilt-compiler:2.48")
    implementation("androidx.preference:preference-ktx:1.2.1")
    // material
    implementation("androidx.viewpager2:viewpager2:1.0.0")
    implementation("androidx.annotation:annotation:1.3.0")
    implementation("com.google.android.material:material:1.5.0")

    // testing
    testImplementation("org.junit.jupiter:junit-jupiter")
    // kotlin coroutines test
    /*
     * kotlinx-coroutines-test: Esta biblioteca se utiliza para probar las funciones asincrónicas
     * que se implementan con Kotlin Coroutines en Android. Por ejemplo, si tienes una función
     * que usa Coroutines para hacer una llamada a la red y deseas probarla,
     * puedes usar kotlinx-coroutines-test para escribir pruebas para esa función. Ejemplo:*/
    testImplementation("org.jetbrains.kotlinx:kotlinx-coroutines-test:1.6.4")

    /*
     * Hamcrest: Hamcrest es una biblioteca de aserciones para pruebas unitarias en Java y Kotlin.
     * Puedes usar Hamcrest para escribir aserciones más expresivas en tus pruebas unitarias*/
    testImplementation("org.hamcrest:hamcrest:2.2")

    /*
     * androidx.arch.core:core-testing: Esta biblioteca se utiliza para probar los componentes
     * de Arquitectura de Android, como LiveData y ViewModel. Con esta biblioteca, puedes simular
     * cambios de datos y observar cómo los componentes de Arquitectura reaccionan a esos cambios*/
    testImplementation("androidx.arch.core:core-testing:2.2.0")

    /*
     * com.squareup.okhttp3:mockwebserver: Esta biblioteca se utiliza para simular respuestas de
     * red en pruebas unitarias de Android. Por ejemplo, si tienes una función que hace una llamada
     * a una API, puedes usar mockwebserver para simular la respuesta de esa API en tus pruebas unitarias*/
    testImplementation("com.squareup.okhttp3:mockwebserver:4.9.3")

    /*
     * io.mockk:mockk: Esta biblioteca se utiliza para crear objetos simulados en pruebas unitarias
     * de Kotlin. Puedes usar mockk para simular objetos que dependen de otros objetos o
     * para simular interacciones de usuario en tus pruebas unitarias.*/
    testImplementation("io.mockk:mockk:1.12.5")

    /*
     * org.mockito:mockito-core: Esta biblioteca se utiliza para crear objetos simulados en
     * pruebas unitarias de Java y Kotlin. Puedes usar Mockito para simular objetos que dependen
     * de otros objetos o para simular interacciones de usuario en tus pruebas unitari*/
    testImplementation("org.mockito:mockito-core:4.2.0")

    /*
     * This is a library that provides Kotlin extensions and functions to use JUnit 4 or JUnit 5
     * in Android tests. The version being used in this project is 1.1.5.*/
    testImplementation("androidx.test.ext:junit-ktx:1.1.5")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")

    /*
     * This is a library that provides Kotlin extensions and functions for
     * testing Android components and interactions with the Android framework.
     * The version being used in this project is 1.5.0.
     */
    testImplementation("androidx.test:core-ktx:1.5.0")

    /**
     * This is a library that allows running Android unit tests on the JVM without the need
     * for an emulator or device. It provides a simulated Android environment for tests.
     */
    testImplementation("org.robolectric:robolectric:4.7.3")

    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
}

protobuf {
    protoc {
        artifact =
            if (osdetector.os == "osx") {
                // support both Apple Silicon and Intel chipsets
                val arch = System.getProperty("os.arch")
                val suffix = if (arch == "x86_64") "x86_64" else "aarch_64"
                "com.google.protobuf:protoc:3.21.7:osx-$suffix"
            } else {
                "com.google.protobuf:protoc:3.21.7"
            }
    }
    plugins {
        generateProtoTasks {
            all().forEach {
                it.builtins {
                    create("java") {
                        option("lite")
                    }
                }
            }
        }
    }
}

/*
protobuf {
    protoc {
        artifact = "com.google.protobuf:protoc:3.21.7"
    }

    generateProtoTasks {
        this.all().forEach { task ->
            task.builtins {
                getByName("java") {
                    option("lite")
                }
            }
        }
    }
}

---->
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
 */
