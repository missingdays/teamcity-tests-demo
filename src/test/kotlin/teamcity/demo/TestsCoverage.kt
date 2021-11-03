package teamcity.demo

import ClassToBeCoveredByTests
import org.junit.jupiter.api.Tag
import org.junit.jupiter.api.Test

class TestsCoverage {
    private val t = ClassToBeCoveredByTests()

    @Test
    @Tag("coverage")
    fun `test that partially covers the class`() {
        t.method(false)
    }
}