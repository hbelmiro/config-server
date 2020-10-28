package com.hbelmiro.configserver;

import com.hbelmiro.configserver.model.Configuration;
import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.testcontainers.containers.MongoDBContainer;

import javax.json.bind.JsonbBuilder;
import javax.ws.rs.core.Response;

import java.util.List;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

@QuarkusTest
public class ConfigurationResourceTest {

    private static final MongoDBContainer MONGO_DB_CONTAINER =
            new MongoDBContainer("mongo:4.2").withExposedPorts(27017);

    static {
        MONGO_DB_CONTAINER.start();
        System.setProperty("quarkus.mongodb.connection-string",
                "mongodb://" + MONGO_DB_CONTAINER.getContainerIpAddress() + ":" + MONGO_DB_CONTAINER.getFirstMappedPort());
    }

    @AfterAll
    static void tearDownAll() {
        if (!MONGO_DB_CONTAINER.isShouldBeReused()) {
            MONGO_DB_CONTAINER.stop();
        }
    }

    @BeforeEach
    void setUpEach() {
        Configuration.deleteAll();
    }

    @Test
    void getAll() {
        Configuration poolSize = new Configuration("pool_size", "20");
        poolSize.persist();

        Configuration autoCommit = new Configuration("auto_commit", "true");
        autoCommit.persist();

        given().when().get("/configurations")
               .then()
               .statusCode(200)
               .body(is(JsonbBuilder.create().toJson(List.of(autoCommit, poolSize))));
    }

    @Test
    void get() {
        Configuration configuration = new Configuration("config1", "value1");
        configuration.persist();

        given().when().get("/configurations/config1")
               .then()
               .statusCode(200)
               .body(is(JsonbBuilder.create().toJson(configuration)));
    }

    @Test
    void getNotFound() {
        given().when().get("/configurations/config1")
               .then()
               .statusCode(Response.Status.NOT_FOUND.getStatusCode());
    }

    @Test
    void delete() {
        Configuration configuration = new Configuration("config1", "value1");
        configuration.persist();

        given().when().delete("/configurations/config1")
               .then()
               .statusCode(204);

        assertNull(Configuration.findById("config1"));
    }

    @Test
    void deleteNotFound() {
        given().when().delete("/configurations/config1")
               .then()
               .statusCode(Response.Status.NOT_FOUND.getStatusCode());
    }

    @Test
    void post() {
        Configuration configuration = new Configuration("config1", "value1");

        given().contentType(ContentType.JSON)
               .body(configuration)
               .when().post("/configurations")
               .then()
               .statusCode(204);

        assertEquals(configuration, Configuration.findById("config1"));
    }

    @Test
    void put() {
        Configuration configuration = new Configuration("config1", "value1");

        given().contentType(ContentType.JSON)
               .body(configuration)
               .when().put("/configurations")
               .then()
               .statusCode(204);

        assertEquals(configuration, Configuration.findById("config1"));
    }

}