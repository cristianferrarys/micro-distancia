FROM openjdk:8
VOLUMEN /tmp
ADD ./target/micro-distancia-0.0.1-SNAPSHOT.jar micro-distancia.jar
ENTRYPOINT ["java","-jar","/micro-distancia.jar"]