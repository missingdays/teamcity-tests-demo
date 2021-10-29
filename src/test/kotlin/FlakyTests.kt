import org.junit.jupiter.api.Tag
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class FlakyTests {
    private val random = Math.random() < 0.5
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