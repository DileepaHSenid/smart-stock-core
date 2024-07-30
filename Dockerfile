FROM openjdk:22
EXPOSE 8080
ADD target/IMSjar.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]