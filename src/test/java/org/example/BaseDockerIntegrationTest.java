package org.example;

import org.testcontainers.containers.DockerComposeContainer;

import java.io.File;

public abstract class BaseDockerIntegrationTest {

    public static final DockerComposeContainer<?> DOCKER_COMPOSE_CONTAINER;

    static {
        DOCKER_COMPOSE_CONTAINER = new DockerComposeContainer<>(new File("src/test/resources/docker-compose.yml"));
        DOCKER_COMPOSE_CONTAINER.start();
    }
}
