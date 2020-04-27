FROM maven:openjdk-11-slim
RUN apt-get update && \
    apt-get upgrade -y && \
    apt-get install -y git
RUN git clone https://github.com/amitw-vmware/MonitoringTestService.git /opt/MonitoringTestService
WORKDIR /opt/MonitoringTestService
CMD ["mvn", "clean", "install"]
CMD ["java", "-jar", "/opt/MonitoringTestService/target/MonitoringTestService-1.0-SNAPSHOT.jar", "server", "/opt/MonitoringTestService/MonitoringTestService.yml"]