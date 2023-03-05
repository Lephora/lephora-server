FROM openjdk:17

COPY "./lephora-server-0.0.1.jar" "app.jar"

ARG GITHUB_TOKEN
ARG CONTRACT_ENABLE
ARG CONTRACT_REPO_OWNER
ARG CONTRACT_REPO_NAME
ARG CONTRACT_REPO_BRANCH
ARG CONTRACT_REPO_PATH

ENV GITHUB_TOKEN $GITHUB_TOKEN
ENV CONTRACT_ENABLE $CONTRACT_ENABLE
ENV CONTRACT_REPO_OWNER $CONTRACT_REPO_OWNER
ENV CONTRACT_REPO_NAME $CONTRACT_REPO_NAME
ENV CONTRACT_REPO_BRANCH $CONTRACT_REPO_BRANCH
ENV CONTRACT_REPO_PATH $CONTRACT_REPO_PATH

ENTRYPOINT ["java", "-jar","app.jar","--spring.server.port=8080","--spring.datasource.url=jdbc:postgresql://0.0.0.0:5432/lephora", "--spring.datasource.username=postgres","--spring.datasource.password=123456","--spring.flyway.enabled=false","--spring.data.redis.host=0.0.0.0","--spring.data.redis.port=6379","--spring.data.redis.connect-timeout=5000ms"]
