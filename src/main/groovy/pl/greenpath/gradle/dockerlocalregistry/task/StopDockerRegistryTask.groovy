package pl.greenpath.gradle.dockerlocalregistry.task

import pl.greenpath.gradle.dockerlocalregistry.DockerLocalRegistryExtension

class StopDockerRegistryTask extends DockerExec {

  @Override
  protected String getCommandArguments() {
    DockerLocalRegistryExtension extension = this.project.getExtensions().getByType(DockerLocalRegistryExtension)
    return "stop ${extension.registryName}"
  }
}
