includeBuild("buildPlugin")
include("app","utilities", "core", "data")


project(":core").projectDir = File("must_have/core")
project(":utilities").projectDir = File("must_have/utilities")