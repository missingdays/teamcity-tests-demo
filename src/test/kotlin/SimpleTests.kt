import org.junit.jupiter.api.Tag
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class SimpleTests {
    @Test
    @Tag("simple")
    fun `this is a simple test`() {
        assertEquals(2, 1 + 1)
    }

    @Test
    @Tag("simple")
    fun `this is a failing test`() {
        assertEquals(1, 2)
    }

    @Test
    @Tag("slow")
    fun `this is a slow test`() {
        Thread.sleep(3000)
    }
}