# Akka-Http-performance-test

This is a very basic Hello World app adapted from the Dropwizard getting started app (minus the health check) 
for the purpose of comparing performance of different HTTP libraries.
The application is intentionally broken down in a similar fashion, although it could be made less verbose.

## Usage:
mvn clean install

java -jar target/AkkaHttpREST-0.0.1-SNAPSHOT.jar

curl http://localhost:3000/hello?name=Bob
