import buildhelper.setupLibraryModule

plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
}

setupLibraryModule(publish = true)

dependencies {
    implementation(libs.androidx.core)
    implementation(libs.androidx.appcompat)
    compileOnly(projects.ishareCore)
    compileOnly(projects.ishareQq)
    compileOnly(projects.ishareWechat)

    implementation(libs.material)
    implementation(libs.androidx.constraintlayout)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.test.ext)
    androidTestImplementation(libs.androidx.test.espresso)
}
