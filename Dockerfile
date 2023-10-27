FROM openjdk:17-slim
VOLUME /target
COPY api-demo.jar app.jar
EXPOSE 8888
ENTRYPOINT ["java","-jar","/app.jar"]