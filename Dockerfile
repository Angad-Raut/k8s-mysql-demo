FROM openjdk:17
EXPOSE 9092
ADD target/khatabook-app.jar khatabook-app.jar
ENTRYPOINT ["java","-jar","/khatabook-app.jar"]