package kkon_warmup

import io.ktor.server.application.*
import io.ktor.server.cio.*
import io.ktor.server.engine.*
import io.ktor.server.html.*
import io.ktor.server.routing.*
import kotlinx.html.*

fun server(): CIOApplicationEngine = embeddedServer(CIO, port = 8080) {
    routing {
        get("/health") {
            this@embeddedServer.log.info("/health")
            call.respondHtml {
                head {
                    title("I am alive")
                }
                body {
                    h1 { +"I am alive" }
                    p { +"Using thread ${Thread.currentThread().name}" }
                }
            }
        }
    }
}

fun main() {
    server().start(wait = true)
}

