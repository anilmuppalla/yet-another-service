import io.ktor.application.Application
import io.ktor.application.call
import io.ktor.application.install
import io.ktor.features.ContentNegotiation
import io.ktor.gson.gson
import io.ktor.http.ContentType
import io.ktor.response.respond
import io.ktor.response.respondText
import io.ktor.routing.get
import io.ktor.routing.routing
import io.ktor.server.engine.embeddedServer
import io.ktor.server.netty.Netty
import java.text.DateFormat

data class Author(val name: String, val website: String)

val author = Author("anil", "example.com")

fun main(args: Array<String>) {
    embeddedServer(
            Netty,
            port = 8080,
            watchPaths = listOf("."),
            module = Application::ping
    ).apply { start(wait = true) }
}

fun Application.ping() {
    install(ContentNegotiation) {
        gson {
            setDateFormat(DateFormat.LONG)
            setPrettyPrinting()
        }
    }
    routing {
        get("/") {
            call.respondText("hello", ContentType.Text.Html)
        }
        get("/author") {
            call.respond(author)
        }
    }
}

