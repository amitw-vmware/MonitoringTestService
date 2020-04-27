FROM openjdk:11-jre-slim
RUN git clone https://github.com/amitw-vmware/MonitoringTestService.git /opt/MonitoringTestService
WORKDIR /opt/MonitoringTestService
RUN mvn clean install
CMD ["java", "-jar", "/opt/MonitoringTestService/target/MonitoringTestService-1.0-SNAPSHOT.jar", "server", "/opt/MonitoringTestService/MonitoringTestService.yml"]