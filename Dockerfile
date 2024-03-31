FROM openjdk:11
ADD target/tkht-cicd.jar tkht-cicd.jar
ENTRYPOINT ["java" ,"-jar" , "tkht-cicd.jar"]