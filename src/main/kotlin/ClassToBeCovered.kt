package teamcity.demo

class ClassToBeCovered {
    fun method(flag: Boolean): Int {
        return if (flag) {
            10
        } else {
            20
        }
    }

    fun notCoveredMethod() {
        println("hello")
    }
}

