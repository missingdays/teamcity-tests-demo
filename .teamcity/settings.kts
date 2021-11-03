import jetbrains.buildServer.configs.kotlin.v2019_2.*
import jetbrains.buildServer.configs.kotlin.v2019_2.buildFeatures.notifications
import jetbrains.buildServer.configs.kotlin.v2019_2.buildSteps.gradle
import jetbrains.buildServer.configs.kotlin.v2019_2.buildSteps.maven
import jetbrains.buildServer.configs.kotlin.v2019_2.buildSteps.script
import jetbrains.buildServer.configs.kotlin.v2019_2.projectFeatures.versionedSettings
import jetbrains.buildServer.configs.kotlin.v2019_2.triggers.vcs
import jetbrains.buildServer.configs.kotlin.v2019_2.vcs.GitVcsRoot

/*
The settings script is an entry point for defining a TeamCity
project hierarchy. The script should contain a single call to the
project() function with a Project instance or an init function as
an argument.

VcsRoots, BuildTypes, Templates, and subprojects can be
registered inside the project using the vcsRoot(), buildType(),
template(), and subProject() methods respectively.

To debug settings scripts in command-line, run the

    mvnDebug org.jetbrains.teamcity:teamcity-configs-maven-plugin:generate

command and attach your debugger to the port 8000.

To debug in IntelliJ Idea, open the 'Maven Projects' tool window (View
-> Tool Windows -> Maven Projects), find the generate task node
(Plugins -> teamcity-configs -> teamcity-configs:generate), the
'Debug' option is available in the context menu for the task.
*/

version = "2021.2"

private val configurations = mapOf(
    "1. Simple tests" to "simple",
    "2. Slow tests" to "slow",
    "3. Flaky tests" to "flaky",
    "4. Investigations" to "simple",
    "7. Coverage" to "coverage",
)

project {
    vcsRoot(TestsMetadataVcsRoot)

    for ((configurationName, tests) in configurations) {
        buildType(
            BuildType {
                id = RelativeId(configurationName.toId())
                name = configurationName

                vcs {
                    root(DslContext.settingsRoot)
                }

                steps {
                    maven {
                        goals = "clean test"
                        runnerArgs = "-Dtests=$tests"
                    }
                }
            }
        )
    }

    buildType(TestsMetadataConfiguration)
    buildType(ReportingYourOwnTests)
}

object ReportingYourOwnTests : BuildType({
    name = "6. Reporting your own tests"

    steps {
        script {
            scriptContent = """
                echo "##teamcity[testStarted name='myCustomTest']"
                echo "##teamcity[testFinished name='myCustomTest' duration='1337']"
                
                echo "##teamcity[testStarted name='myFailedTest']"
                echo "##teamcity[testFailed name='myFailedTest' message='The number must be 20000' details='junit.framework.AssertionFailedError: expected:<20000> but was:<10000>|n|r    at junit.framework.Assert.fail(Assert.java:47)|n|r    at junit.framework.Assert.failNotEquals(Assert.java:280)|n|r...']"
                echo "##teamcity[testFinished name='myFailedTest']"
            """.trimIndent()
        }
    }
})

object TestsMetadataVcsRoot : GitVcsRoot({
    id("TestsMetadata_GitGithubComJetBrainsTeamcityTestMetadataDemoGitRefsHeadsMaster")
    name = "git@github.com:JetBrains/teamcity-test-metadata-demo.git#refs/heads/master"
    url = "git@github.com:JetBrains/teamcity-test-metadata-demo.git"
    branch = "refs/heads/master"
    branchSpec = "refs/heads/*"
    authMethod = uploadedKey {
        userName = "git"
        uploadedKey = "id_rsa"
    }
})

object TestsMetadataConfiguration : BuildType({
    id("RunTests")
    name = "5. Tests metadata"

    artifactRules = "build/reports/tests/test => gradle_test_report.zip"

    params {
        param("teamcity.build.serviceMessages.logOriginal", "true")
    }

    vcs {
        root(TestsMetadataVcsRoot)
    }

    steps {
        gradle {
            tasks = "clean build"
            buildFile = ""
            gradleWrapperPath = ""
        }
    }

    triggers {
        vcs {
        }
    }
})
