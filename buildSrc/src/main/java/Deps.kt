object Deps {
    // gardle section
    const val gradle = "com.android.tools.build:gradle:${Versions.gradle_version}"
    const val kotlin_gradle_plugin = "org.jetbrains.kotlin:kotlin-gradle-plugin:${Versions.kotlin_gradle_plugin_version}"
    // core section
    const val core_ktx = "androidx.core:core-ktx:${Versions.core_ktx_version}"
    const val appcompat = "androidx.appcompat:appcompat:${Versions.appcompat_version}"
    const val fragment_ktx = "androidx.fragment:fragment-ktx:${Versions.fragment_ktx_version}"
    // presentation section
    const val material = "com.google.android.material:material:${Versions.material_version}"
    const val constraintlayout = "androidx.constraintlayout:constraintlayout:${Versions.constraintlayout_version}"
    // lifecycle section
    const val lifecycle_livedata_ktx = "androidx.lifecycle:lifecycle-livedata-ktx:${Versions.lifecycle_livedata_ktx_version}"
    const val lifecycle_viewmodel_ktx = "androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.lifecycle_viewmodel_ktx_version}"
    // navigation component
    const val navigation_fragment_ktx = "androidx.navigation:navigation-fragment-ktx:${Versions.navigation_version}"
    const val navigation_ui_ktx = "androidx.navigation:navigation-ui-ktx:${Versions.navigation_version}"
    const val navigation_safe_args_gradle_plugin = "androidx.navigation:navigation-safe-args-gradle-plugin:${Versions.navigation_version}"
    // retrofit 2 section
    const val retrofit = "com.squareup.retrofit2:retrofit:${Versions.retrofit_version}"
    const val converter_gson = "com.squareup.retrofit2:converter-gson:${Versions.retrofit_version}"
    // androidx - paging
    const val paging_runtime_ktx = "androidx.paging:paging-runtime-ktx:${Versions.paging_version}"
    // image downloading and presenting section
    const val coil_kt = "io.coil-kt:coil:${Versions.coil_kt_version}"
    // dependency injection section
    const val hilt_android = "com.google.dagger:hilt-android:${Versions.hilt_version}"
    const val hilt_android_compiler = "com.google.dagger:hilt-android-compiler:${Versions.hilt_version}"
    const val hilt_android_gradle_plugin = "com.google.dagger:hilt-android-gradle-plugin:${Versions.hilt_version}"
    const val hilt_compiler = "androidx.hilt:hilt-compiler:${Versions.androidx_hilt_version}"
    const val hilt_lifecycle_viewmodel = "androidx.hilt:hilt-lifecycle-viewmodel:${Versions.androidx_hilt_version}"
    // unit test section
    const val junit = "junit:junit:${Versions.junit_version}"
    // mockk test section
    const val mockk = "io.mockk:mockk:${Versions.mockk_version}"
    // coroutine test section
    const val coroutine_test = "org.jetbrains.kotlinx:kotlinx-coroutines-test:${Versions.kotlinx_coroutines_test_version}"
    // instrumentation test section
    const val androidx_junit = "androidx.test.ext:junit:${Versions.androidx_junit_version}"
    const val espresso_core = "androidx.test.espresso:espresso-core:${Versions.androidx_espresso_core_version}"
}