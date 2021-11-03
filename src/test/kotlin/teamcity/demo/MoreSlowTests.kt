package teamcity.demo

import org.junit.jupiter.api.Tag
import org.junit.jupiter.api.Test
import java.time.LocalDateTime
import java.util.*
import kotlin.random.Random
import kotlin.test.assertEquals

class MoreSlowTests {
    private val testDuration = 15000L

    @Test
    @Tag("slow")
    fun `this is a slow test`() {
        Thread.sleep(testDuration)
    }

    @Test
    @Tag("slow")
    fun `this is another slow test`() {
        Thread.sleep(testDuration)
    }

    @Test
    @Tag("slow")
    fun `this is a slow test that fails`() {
        Thread.sleep(testDuration)
        assertEquals(2, 1)
    }

    @Test
    @Tag("slow")
    fun `this is a slow test that executed random amount of time`() {
        Thread.sleep(testDuration * (Math.random() + 0.1).toLong())
    }

    @Test
    @Tag("slow")
    fun `and this is yet another slow test`() {
        Thread.sleep(testDuration)
    }
}