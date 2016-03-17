package pl.greenpath.gradle.dockerlocalregistry

import org.gradle.testkit.runner.BuildResult
import org.gradle.testkit.runner.GradleRunner
import org.gradle.testkit.runner.TaskOutcome
import org.junit.Rule
import org.junit.rules.TemporaryFolder
import spock.lang.Specification

import static pl.greenpath.gradle.dockerlocalregistry.BuildscriptClasspathDefinitionGenerator.generateBuildscriptClasspathDefinition

class DockerLocalRegistryPluginFunctionalTest extends Specification {

  @Rule
  final TemporaryFolder testProjectDir = new TemporaryFolder()

  private File buildFile

  def setup() {
    File settingsFile = testProjectDir.newFile('settings.gradle')
    settingsFile << "rootProject.name = 'myProject'"
    testProjectDir.newFile('.bash_profile') << '''
export DOCKER_TLS_VERIFY="0"
export DOCKER_HOST="tcp://192.168.99.100:2376"
export DOCKER_CERT_PATH="/Users/michalborek/.docker/machine/machines/default"
export DOCKER_MACHINE_NAME="default"
# Run this command to configure your shell:
# eval "$(docker-machine env default)"
    '''
    buildFile = testProjectDir.newFile('build.gradle')
    buildFile << generateBuildscriptClasspathDefinition()
  }

  def 'should execute a task that setups local registry'() {
    given:
    buildFile << '''
      apply plugin: 'pl.greenpath.gradle.dockerlocalregistry'
      dockerLocalRegistry {

      }
      '''
    when:
    BuildResult setupResult = GradleRunner.create()
        .withProjectDir(testProjectDir.root)
        .withArguments(':setupDockerRegistry', '--stacktrace')
        .build()
    then:
    setupResult.tasks.first().outcome == TaskOutcome.SUCCESS
    println setupResult.output
    when:
    BuildResult removalResult = GradleRunner.create()
        .withProjectDir(testProjectDir.root)
        .withArguments(':removeDockerRegistry', '--stacktrace')
        .build()
    then:
    removalResult.tasks.first().outcome == TaskOutcome.SUCCESS
    println removalResult.output

  }
}
