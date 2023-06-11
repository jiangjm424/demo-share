/*
 * Copyright 2023 The IShare Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
import buildhelper.setupAppModule

plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
}

setupAppModule(name = "com.grank.db.demo") {
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

    testImplementation(libs.bundles.test.jvm)
    androidTestImplementation(libs.bundles.test.android)
}
