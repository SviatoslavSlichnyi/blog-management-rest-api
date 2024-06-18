# Blog Management Rest app

---

## Swagger UI url pattern

    http://localhost:<port>/swagger-ui/index.html 

---

## Deployment plan:

1.  Dockerize the Spring Boot application. [Dockerfile](Dockerfile)
2.	Push the Docker image to a container registry (AWS ECR or Azure ACR).
3.	Deploy the Docker image to a container orchestration service (AWS ECS/EKS or Azure AKS).
4.	Set up CI/CD pipelines to automate the build, test, and deployment process. (For example: GitHub Actions)

---

## Links
Eureka Server: https://github.com/SviatoslavSlichnyi/eureka-server
