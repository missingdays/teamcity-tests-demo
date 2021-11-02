package teamcity.demo

import org.junit.jupiter.api.Tag
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class MoreSimpleTests {
    @Test
    @Tag("simple")
    fun `this is more failing test`() {
        assertEquals(1, 2)
    }

    @Test
    @Tag("simple")
    fun `this is another failing test`() {
        assertEquals(4, 3)
    }
}