package pl.greenpath.gradle.dockerlocalregistry.task

import pl.greenpath.gradle.dockerlocalregistry.DockerLocalRegistryExtension

class SetupDockerRegistryTask extends DockerExec {

  @Override
  protected String getCommandArguments() {
    DockerLocalRegistryExtension extension = this.project.getExtensions().getByType(DockerLocalRegistryExtension)
    return "run -d -p ${extension.port}:5000 --restart=always --name ${extension.registryName} registry:2"
  }
}
