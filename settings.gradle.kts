rootProject.name = "ishare"

// https://docs.gradle.org/7.4/userguide/declaring_dependencies.html#sec:type-safe-project-accessors
enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")

// Public modules
include(
    "ishare-core",
    "ishare-qq",
    "ishare-wechat",
    "ishare-ui",
    "ishare-bom",
)

// Private modules
include(
    "app",
)
