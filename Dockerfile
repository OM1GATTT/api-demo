FROM openjdk:17-slim
VOLUME /tmp
COPY api-demo.jar app.jar
EXPOSE 8888
ENTRYPOINT ["java","-jar","/app.jar"]