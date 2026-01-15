package di

object AppConfig {
    var useFakeData: Boolean = false
        private set

    fun configure(useFakeData: Boolean) {
        this.useFakeData = useFakeData
    }
}
