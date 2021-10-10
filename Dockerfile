FROM adoptopenjdk/openjdk11:alpine-jre
ADD target/*.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]