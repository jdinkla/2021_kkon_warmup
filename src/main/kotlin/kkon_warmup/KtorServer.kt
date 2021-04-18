package kkon_warmup

import io.ktor.application.*
import io.ktor.html.*
import io.ktor.routing.*
import io.ktor.server.cio.*
import io.ktor.server.engine.*
import kotlinx.html.*

fun server(): CIOApplicationEngine = embeddedServer(CIO, port = 8080) {
    routing {
        get("/health") {
            log.info("/health")
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

