import com.google.gson.Gson
import com.mak.Author
import com.mak.main
import io.ktor.application.Application
import io.ktor.http.HttpMethod
import io.ktor.server.testing.handleRequest
import io.ktor.server.testing.withTestApplication
import org.junit.Test
import kotlin.test.assertEquals
import kotlin.test.assertNotEquals

class ApplicationTest {
    @Test
    fun getAuthorTest() = withTestApplication(Application::main) {
        val gson = Gson()
        val expected = Author("anil", "example.com")
        val illegal = Author("kumar", "")
        handleRequest(HttpMethod.Get, "/author") {

        }.response.let {
            val result = gson.fromJson<Author>(it.content, Author::class.java)
            assertEquals(expected, result)
            assertNotEquals(illegal, result)
        }
    }
}
