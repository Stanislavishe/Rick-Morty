plugins {
    alias(libs.plugins.jetbrains.kotlin.jvm)
    id("com.google.devtools.ksp")
}

dependencies {
    implementation(project(":domain"))

    implementation(libs.retrofit)

    ksp(libs.moshi.kotlin.codegen)

    implementation(libs.javax.inject)
}
