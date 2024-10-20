plugins {
    alias(libs.plugins.jetbrains.kotlin.jvm)
    id("com.google.devtools.ksp")
}

dependencies {

    api(libs.moshi)
    api(libs.converter.moshi)
    api(libs.moshi.kotlin)
    ksp(libs.moshi.kotlin.codegen)

    implementation(libs.javax.inject)
}