import buildhelper.setupLibraryModule

plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
}

setupLibraryModule(publish = true)

dependencies {
    implementation(libs.androidx.core)
    implementation(libs.androidx.appcompat)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.test.ext)
    androidTestImplementation(libs.androidx.test.espresso)
}
