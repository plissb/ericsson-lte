job("build and publish"){
    container(displayName = "Run & Publish", image = "4.10.3-jre8-alpine") {
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