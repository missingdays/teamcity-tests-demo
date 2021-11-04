package patches.buildTypes

import jetbrains.buildServer.configs.kotlin.v2019_2.*
import jetbrains.buildServer.configs.kotlin.v2019_2.failureConditions.BuildFailureOnMetric
import jetbrains.buildServer.configs.kotlin.v2019_2.failureConditions.failOnMetricChange
import jetbrains.buildServer.configs.kotlin.v2019_2.ui.*

/*
This patch script was generated by TeamCity on settings change in UI.
To apply the patch, change the buildType with id = 'id7Coverage'
accordingly, and delete the patch script.
*/
changeBuildType(RelativeId("id7Coverage")) {
    failureConditions {
        val feature1 = find<BuildFailureOnMetric> {
            failOnMetricChange {
                metric = BuildFailureOnMetric.MetricType.COVERAGE_LINE_PERCENTAGE
                threshold = 100
                units = BuildFailureOnMetric.MetricUnit.DEFAULT_UNIT
                comparison = BuildFailureOnMetric.MetricComparison.LESS
                compareTo = value()
            }
        }
        feature1.apply {
            threshold = 0
            compareTo = build {
                buildRule = lastSuccessful()
            }
        }
    }
}