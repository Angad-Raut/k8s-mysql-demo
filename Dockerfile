FROM openjdk:17
EXPOSE 9091
ADD target/k8s-mysql-demo.jar k8s-mysql-demo.jar
ENTRYPOINT ["java","-jar","/k8s-mysql-demo.jar"]