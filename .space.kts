job("build and publish"){
    container(displayName = "Run & Publish", image = "gradle:4.10.3-jre-alpine") {
        kotlinScript { api ->
            api.gradlew("build")
            try {
                api.gradlew("publish")
            }catch (e: Exception){
                println("Publishing failed: $e")
            }
        }
    }
}