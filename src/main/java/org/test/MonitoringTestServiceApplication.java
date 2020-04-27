package org.test;

import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import org.eclipse.jetty.servlet.ServletHolder;
import io.prometheus.client.exporter.MetricsServlet;
import io.prometheus.client.hotspot.DefaultExports;
import org.test.resources.PrometheusResource;

public class MonitoringTestServiceApplication  extends Application<MonitoringTestServiceConfiguration> {
    public static void main(String[] args) throws Exception {
        new MonitoringTestServiceApplication().run(args);
    }

    @Override
    public String getName() {
        return "monitoring-test-service";
    }

    @Override
    public void initialize(Bootstrap<MonitoringTestServiceConfiguration> bootstrap) { }

    @Override
    public void run(MonitoringTestServiceConfiguration configuration,
                    Environment environment) {
        final PrometheusResource prometheusResource = new PrometheusResource();

        environment.jersey().register(prometheusResource);
        DefaultExports.initialize();
        environment.getApplicationContext().addServlet(new ServletHolder(new MetricsServlet()), "/metrics");
    }
}
