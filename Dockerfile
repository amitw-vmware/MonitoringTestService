FROM alpine/git as clone 
WORKDIR /app
RUN git clone https://github.com/amitw-vmware/MonitoringTestService.git

FROM maven:3.5-jdk-8-alpine as build 
WORKDIR /app
COPY --from=clone /app/MonitoringTestService /app 
RUN mvn clean install

FROM java:8-jdk-alpine
WORKDIR /app
COPY --from=build /app/MonitoringTestService.yml /app
COPY --from=build /app/target/MonitoringTestService-1.0-SNAPSHOT.jar /app
EXPOSE 8087
ENTRYPOINT ["java", "-jar", "MonitoringTestService-1.0-SNAPSHOT.jar", "server", "MonitoringTestService.yml"]