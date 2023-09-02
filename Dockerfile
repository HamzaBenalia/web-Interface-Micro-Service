FROM openjdk:17-alpine
ENV PORT=9092
COPY target/*.jar ./app-webinterface.jar
EXPOSE 9092
ENTRYPOINT ["java","-jar","/app-webinterface.jar"]