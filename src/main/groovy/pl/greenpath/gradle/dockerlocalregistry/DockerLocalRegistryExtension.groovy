package pl.greenpath.gradle.dockerlocalregistry

class DockerLocalRegistryExtension {

  private int publishPort = 5000
  private String registryName = 'registry'

  void name(String registryName) {
    this.registryName = registryName
  }

  void port(final int newPort) {
    this.publishPort = newPort
  }

  String getRegistryName() {
    return registryName
  }

  int getPort() {
    return publishPort
  }
}
