FROM openjdk:21
LABEL authors="sviatoslavslichnyi"

COPY ./build/libs/blog-0.0.1-SNAPSHOT.jar blog-management-rest-api.jar
ENTRYPOINT ["java","-jar","/blog-management-rest-api.jar"]
