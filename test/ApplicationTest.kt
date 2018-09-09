import com.google.gson.Gson
import com.mak.Author
import com.mak.main
import io.ktor.application.Application
import io.ktor.http.HttpMethod
import io.ktor.server.testing.handleRequest
import io.ktor.server.testing.withTestApplication
import org.junit.Test
import kotlin.test.assertEquals

class ApplicationTest {
    @Test
    fun getAuthorTest() = withTestApplication(Application::main) {
        val gson = Gson()
        val expected = Author("anil", "example.com")
        handleRequest(HttpMethod.Get, "/author") {

        }.response.let {
            val result = gson.fromJson<Author>(it.content, Author::class.java)
            assertEquals(expected, result)
        }
    }
}
