import buildhelper.setupAppModule

plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("kotlin-kapt")
}

setupAppModule {
    defaultConfig {
        applicationId = "com.share.sample"
        manifestPlaceholders["WECHAT_APPID"] = "xxxxx"
        manifestPlaceholders["QQ_APPID"] = "xxxxx"
    }

    buildTypes {
        getByName("debug") {
            isMinifyEnabled = false
            isShrinkResources = false
            signingConfig = signingConfigs["debug"]
        }
        release {
            isMinifyEnabled = true
            isShrinkResources = true
            proguardFiles("shrinker-rules.pro", "shrinker-rules-android.pro")
            signingConfig = signingConfigs["debug"]
        }
    }
}

dependencies {
    implementation(projects.ishareCore)
    implementation(projects.ishareWechat)
    implementation(projects.ishareQq)
    implementation(projects.ishareUi)
    implementation(libs.androidx.core)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.constraintlayout)
    implementation(libs.androidx.navigation.fragment)
    implementation(libs.androidx.navigation.ui)


    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.test.ext)
    androidTestImplementation(libs.androidx.test.espresso)
}
