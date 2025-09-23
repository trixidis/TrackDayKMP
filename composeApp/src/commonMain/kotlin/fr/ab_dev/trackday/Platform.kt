package fr.ab_dev.trackday

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform