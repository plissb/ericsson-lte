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
            content = """
                echo userName: ${"$"}USR_NAME
                echo password: ${"$"}USR_PWD
                ./gradlew build publish""".trimIndent()
        }
    }
}