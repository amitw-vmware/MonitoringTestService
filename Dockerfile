FROM openjdk:11-jre-slim
COPY target/MonitoringTestService-*.jar /MonitoringTestService.jar
COPY MonitoringTestService.yml /MonitoringTestService.yml
CMD ["java", "-jar", "/MonitoringTestService.jar", "server", "/MonitoringTestService.yml"]