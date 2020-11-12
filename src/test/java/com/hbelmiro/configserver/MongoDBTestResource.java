package com.hbelmiro.configserver;

import io.quarkus.test.common.QuarkusTestResourceLifecycleManager;
import org.testcontainers.containers.MongoDBContainer;

import java.util.Map;

public class MongoDBTestResource implements QuarkusTestResourceLifecycleManager {

    private static final MongoDBContainer MONGO_DB_CONTAINER =
            new MongoDBContainer("mongo:4.2").withExposedPorts(27017);

    @Override
    public Map<String, String> start() {
        MONGO_DB_CONTAINER.start();

        return Map.of(
                "quarkus.mongodb.connection-string",
                "mongodb://" + MONGO_DB_CONTAINER.getContainerIpAddress() + ":"
                        + MONGO_DB_CONTAINER.getFirstMappedPort()
        );
    }

    @Override
    public void stop() {
        if (!MONGO_DB_CONTAINER.isShouldBeReused()) {
            MONGO_DB_CONTAINER.stop();
        }
    }

}
