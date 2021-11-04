package teamcity.demo

import org.junit.jupiter.api.Tag
import org.junit.jupiter.api.Test

class TestsCoverage {
    private val t = ClassToBeCovered()

    @Test
    @Tag("coverage")
    fun `test that partially covers the class`() {
        t.method(false)
    }

    /*
    @Test
    @Tag("coverage")
    fun `test that fully covers all the classes`() {
        t.method(true)
        t.notCoveredMethod()
        ClassThatIsNotCovered().methodThatIsNotCovered()
    }
     */
}