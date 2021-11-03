package teamcity.demo

class ClassToBeCoveredByTests {
    fun method(flag: Boolean): Int {
        return if (flag) {
            10
        } else {
            20
        }
    }

    fun notCoveredMethod() {}
}

class ClassThatIsNotCoveredByTests {
    fun methodThatIsNotCovered() {}
}