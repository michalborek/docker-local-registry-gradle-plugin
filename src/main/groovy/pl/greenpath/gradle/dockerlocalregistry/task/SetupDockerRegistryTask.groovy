package pl.greenpath.gradle.dockerlocalregistry.task

import org.gradle.api.tasks.Exec
import pl.greenpath.gradle.dockerlocalregistry.DockerLocalRegistryExtension

class SetupDockerRegistryTask extends Exec {

  SetupDockerRegistryTask() {
    project.afterEvaluate {
      DockerLocalRegistryExtension extension = this.project.getExtensions().getByType(DockerLocalRegistryExtension)
      // TODO
      commandLine wrapCommand("docker run -d -p ${extension.port}:5000 --restart=always " +
          "--name ${extension.registryName} registry:2")
    }
  }

  private static String[] wrapCommand(final String command) {
    if (isWindows()) {
      return (['cmd', '/c'] + command.split(' ')).flatten()
    } else {
      return command.split(' ')
    }
  }

  private static boolean isWindows() {
    return System.getProperty('os.name').toLowerCase().contains('windows')
  }
}
