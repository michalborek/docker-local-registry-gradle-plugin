package pl.greenpath.gradle.dockerlocalregistry

import org.gradle.api.Plugin
import org.gradle.api.Project
import pl.greenpath.gradle.dockerlocalregistry.task.SetupDockerRegistryTask

class DockerLocalRegistryPlugin implements Plugin<Project> {

  @Override
  void apply(Project project) {
    project.extensions.create('dockerLocalRegistry', DockerLocalRegistryExtension, project)

    project.task('setupDockerRegistry', type: SetupDockerRegistryTask)
  }

}
