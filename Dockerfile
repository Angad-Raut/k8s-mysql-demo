FROM openjdk:17
EXPOSE 9091
ADD target/k8s-mysql-app.jar k8s-mysql-app.jar
ENTRYPOINT ["java","-jar","/k8s-mysql-app.jar"]