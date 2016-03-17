package pl.greenpath.gradle.dockerlocalregistry.task

import org.gradle.api.tasks.Exec

abstract class DockerExec extends Exec {

  DockerExec() {
    project.afterEvaluate {
      executable 'docker'
//      executable 'whoami'
      args wrapCommand("${commandArguments}")
    }
  }

  protected abstract String getCommandArguments()

  public static String[] wrapCommand(final String command) {
    if (isWindows()) {
      return (['cmd', '/c'] + command.split(' ')).flatten()
    } else {
      return command.split(' ')
    }
  }

  public static boolean isWindows() {
    return System.getProperty('os.name').toLowerCase().contains('windows')
  }
}
