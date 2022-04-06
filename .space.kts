job("build and publish"){
    startOn {
        gitPush {
            branchFilter {
                +"refs/heads/main"
            }
        }
    }
    container(displayName = "Run gradle build", image = "openjdk:8-alpine") {
        env["USR_NAME"] = Secrets("user_name")
        env["USR_PWD"] = Secrets("pwd")
        shellScript {
            content = """./gradlew build publish""".trimIndent()
        }
    }
}
