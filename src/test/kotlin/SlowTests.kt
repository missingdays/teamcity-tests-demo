import org.junit.jupiter.api.Tag
import org.junit.jupiter.api.Test
import java.time.LocalDateTime
import java.util.*
import kotlin.random.Random
import kotlin.test.assertEquals

class SlowTests {
    @Test
    @Tag("slow")
    fun `this is a slow test`() {
        Thread.sleep(5000)
    }

    @Test
    @Tag("slow")
    fun `this is another slow test`() {
        Thread.sleep(5000)
    }

    @Test
    @Tag("slow")
    fun `this is a slow test that fails`() {
        Thread.sleep(5000)
    }

    @Test
    @Tag("slow")
    fun `and this is yet another slow test`() {
        Thread.sleep(5000)
    }
}