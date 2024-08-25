package org.imperiocobra.companion

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform