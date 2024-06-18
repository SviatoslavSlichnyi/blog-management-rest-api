# Blog Management Rest app

---

Deployment plan:

1.  Dockerize the Spring Boot application. [Dockerfile](Dockerfile)
2.	Push the Docker image to a container registry (AWS ECR or Azure ACR).
3.	Deploy the Docker image to a container orchestration service (AWS ECS/EKS or Azure AKS).
4.	Set up CI/CD pipelines to automate the build, test, and deployment process. (For example: GitHub Actions)
