# config-server

This project is a configuration server. It stores the configurations so that they can be read 
through a restful API.

```shell script
curl http://localhost:8080/configurations/pool_size
{"id":"pool_size","value":"20"}
```

It uses MongoDB as database. So before run this application, don't forget to set MongoDB configurations 
in the `src/main/resources/application.properties` file. 

This project uses Quarkus, the Supersonic Subatomic Java Framework.

If you want to learn more about Quarkus, please visit its website: https://quarkus.io/ .

## Running the application in dev mode

You can run your application in dev mode that enables live coding using:
```shell script
./mvnw compile quarkus:dev
```

## Packaging and running the application

The application can be packaged using:
```shell script
./mvnw package
```
It produces the `config-server-1.0.0-runner.jar` file in the `/target` directory.
Be aware that it’s not an _über-jar_ as the dependencies are copied into the `target/lib` directory.
If you want to build an _über-jar_, execute the following command:
```shell script
./mvnw package build -Dquarkus.package.type=uber-jar
```

The application is now runnable using `java -jar target/config-server-1.0.0-runner.jar`.

## Creating a native executable

You can create a native executable using: 
```shell script
./mvnw package -Pnative
```

Or, if you don't have GraalVM installed, you can run the native executable build in a container using: 
```shell script
./mvnw package -Pnative -Dquarkus.native.container-build=true
```

You can then execute your native executable with: `./target/config-server-1.0.0-runner`

If you want to learn more about building native executables, please consult https://quarkus.io/guides/maven-tooling.html.

# RESTEasy JAX-RS

Guide: https://quarkus.io/guides/rest-json


