import org.junit.jupiter.api.Tag
import org.junit.jupiter.api.Test
import java.time.LocalDateTime
import java.util.*
import kotlin.random.Random
import kotlin.test.assertEquals

class FlakyTests {
    private val random = Random(Date().time).nextBoolean()
    @Test
    @Tag("flaky")
    fun `this is a flaky test`() {
        if (random) {
            assertEquals(2, 1 + 1)
        } else {
            assertEquals(2, 1)
        }
    }

    @Test
    @Tag("flaky")
    fun `this is another flaky test`() {
        if (!random) {
            assertEquals(2, 1 + 1)
        } else {
            assertEquals(2, 1)
        }
    }
}